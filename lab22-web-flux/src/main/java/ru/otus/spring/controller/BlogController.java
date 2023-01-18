package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Blog;
import ru.otus.spring.service.BlogService;
import java.net.URI;

@RestController
@RequestMapping(value = "/blogs", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BlogController {

	private final BlogService blogService;

	@GetMapping
	Publisher<Blog> getAll() {
		return blogService.all();
	}
	
	@GetMapping("/page")
	Publisher<Page<Blog>> getAllPageble(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
		return this.blogService.findPage(PageRequest.of(page, size));
	}

	@GetMapping("/{id}")
	Publisher<Blog> getById(@PathVariable("id") String id) {
		return this.blogService.get(id);
	}

	@PostMapping
	Publisher<ResponseEntity<Blog>> create(@RequestBody Blog blog) {
		return this.blogService.create(blog.getEmail()).map(b -> ResponseEntity
				.created(URI.create("/blogs/" + b.getId())).contentType(MediaType.APPLICATION_JSON).build());
	}

	@DeleteMapping("/{id}")
	Publisher<Blog> deleteById(@PathVariable String id) {
		return this.blogService.delete(id);
	}

	@PutMapping("/{id}")
	Publisher<ResponseEntity<Blog>> updateById(@PathVariable("id") String id, @RequestBody Blog blog) {
		return Mono.just(blog).flatMap(b -> this.blogService.update(id, b.getEmail()))
				.map(b -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build());
	}
}
