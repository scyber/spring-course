package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Book;
import ru.otus.repository.BookRepository;
import ru.otus.services.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LibraryResourceImpl implements BookLibraryResource {
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Override
    public Page<Book> findAll(Integer page, Integer size) {
        return bookRepository.findAll(
                PageRequest.of(page - 1, size));
    }

    @Override
    public ResponseEntity<Book> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Book> save(Object o) {
        return null;
    }

    @Override
    public ResponseEntity<Book> update(Object o) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return null;
    }
}
