package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.otus.spring.domain.Blog;

//@RepositoryRestResource
public interface BlogRepository extends ReactiveMongoRepository<Blog, String> {

}
