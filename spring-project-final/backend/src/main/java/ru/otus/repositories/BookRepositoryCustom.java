package ru.otus.repositories;

import reactor.core.publisher.Flux;
import ru.otus.domain.Book;

public interface BookRepositoryCustom {
	
    Flux<Book> findByTitle(String title);

}
