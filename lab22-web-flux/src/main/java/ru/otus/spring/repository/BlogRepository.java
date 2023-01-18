package ru.otus.spring.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import ru.otus.spring.domain.Blog;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface BlogRepository extends ReactiveMongoRepository<Blog, String> {

	Flux<Blog> findAllBy(Pageable pageable);
	
}
