package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;



@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    private static final String AUTHOR_NAME = "Тестовый Автор";
    private static final String AUTРOR_FOR_DEL = "Автор на удаление";

    @Autowired
    AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    TestEntityManager em;

    @Test
    @DisplayName("Тестирование записи Автора в репозиторий")
    void testAuthorSave() {
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        System.out.println(author.getId());
        Author authorFromRepo = authorRepositoryJpa.save(author);
        Assertions.assertEquals(author, authorFromRepo);
        var id = authorFromRepo.getId();
        var authorRepositoryJpaById = authorRepositoryJpa.findById(id).get();
        Assertions.assertEquals(id, authorRepositoryJpaById.getId());
    }
    @Test
    @DisplayName("Тестирование получения всех авторов")
    void testGetAllAuthors(){
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        authorRepositoryJpa.save(author);
        var authors = authorRepositoryJpa.findAll();
        Assertions.assertTrue(authors.contains(author));
    }
    @Test
    @DisplayName("Тестирование удаления автора")
    @Transactional
    @Rollback
    void testDeleteAuthor(){
        var author = new Author();
        author.setName(AUTРOR_FOR_DEL);
        var authorFromRepo =  authorRepositoryJpa.save(author);
        authorRepositoryJpa.delete(authorFromRepo.getId());
        var authors = authorRepositoryJpa.findAll();
        Assertions.assertTrue(!authors.contains(authorFromRepo));
    }
    @Test
    @DisplayName("Тестирование поиска автора по имени")
    @Transactional
    @Rollback
    void testFindAuthorByName(){
        var author = new Author();
        author.setName(AUTHOR_NAME);
        authorRepositoryJpa.save(author);
        var authors = authorRepositoryJpa.findByName(AUTHOR_NAME);
        Assertions.assertTrue(authors.size() > 0);
    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("Тест обновления имени Автора по id")
    void updateAuthorNameById(){
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var authorSaved = authorRepositoryJpa.save(author);
        var id = authorSaved.getId();
        authorRepositoryJpa.updateAuthorNameById(id,AUTРOR_FOR_DEL);
        var updatedAuthor = authorRepositoryJpa.findById(id).get();
        Assertions.assertEquals(authorSaved,updatedAuthor);
    }
}