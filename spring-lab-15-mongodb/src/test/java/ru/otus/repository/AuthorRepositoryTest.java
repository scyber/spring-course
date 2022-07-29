package ru.otus.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.domain.Author;
import java.util.stream.Collectors;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class AuthorRepositoryTest {
    private static final String AUTHOR_NAME = "Тестовый Автор";
    private static final String AUTРOR_FOR_DEL = "Автор на удаление";
    private static final String AUTHOR_FOR_RENAME = "Автор переименован";

    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeAll
    static void setUp(){
        mongoDBContainer.start();
    }

    @AfterAll
    static void clean() {
        mongoDBContainer.stop();
    }


    @Test
    @DisplayName("Тестирование записи Автора в репозиторий")
    void testAuthorSave() {
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Author authorFromRepo = authorRepository.save(author);
        var authors = authorRepository.findAll();
        authors.contains(authorFromRepo);
    }
    @Test
    @DisplayName("Тестирование получения всех авторов")
    void testGetAllAuthors(){
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        authorRepository.save(author);
        var authors = authorRepository.findAll().stream().map(a -> a.getName()).collect(Collectors.toList());
        Assertions.assertTrue(authors.contains(AUTHOR_NAME));
    }
    @Test
    @DisplayName("Тестирование удаления автора")
    void testDeleteAuthor(){
        var author = new Author();
        author.setName(AUTРOR_FOR_DEL);
        var authorFromRepo =  authorRepository.save(author);
        authorRepository.deleteById(authorFromRepo.getId());
        var authors = authorRepository.findAll();
        Assertions.assertTrue(!authors.contains(authorFromRepo));
    }
    @Test
    @DisplayName("Тестирование поиска автора по имени")
    void testFindAuthorByName(){
        var author = new Author();
        author.setName(AUTHOR_NAME);
        authorRepository.save(author);
        var authors = authorRepository.findByNameLike(AUTHOR_NAME);
        Assertions.assertTrue(authors.size() > 0);
    }
    @Test
    @DisplayName("Тест обновления имени Автора по id")
    void updateAuthorNameById(){
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var authorSaved = authorRepository.save(author);
        String id = authorSaved.getId();
        authorRepository.updateNameById(id,AUTHOR_FOR_RENAME);
        var updatedAuthor = authorRepository.findById(id).get();
        Assertions.assertEquals(authorSaved.getName(),updatedAuthor.getName());
    }
}