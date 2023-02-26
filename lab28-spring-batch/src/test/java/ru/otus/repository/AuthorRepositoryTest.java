package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;



@DataJpaTest
@ExtendWith(SpringExtension.class)
@Disabled
class AuthorRepositoryTest {

    private static final String AUTHOR_NAME = "Тестовый Автор";
    private static final String AUTРOR_FOR_DEL = "Автор на удаление";

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Тестирование записи Автора в репозиторий")
    void testAuthorSave() {
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Author authorFromRepo = authorRepository.save(author);
        Assertions.assertEquals(author, authorFromRepo);
        var id = authorFromRepo.getId();
        var authorFoundById = authorRepository.findById(id).get();
        Assertions.assertEquals(id, authorFoundById.getId());
    }
    @Test
    @DisplayName("Тестирование получения всех авторов")
    void testGetAllAuthors(){
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        authorRepository.save(author);
        var authors = authorRepository.findAll();
        Assertions.assertTrue(authors.contains(author));
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
        var authors = authorRepository.findByName(AUTHOR_NAME);
        Assertions.assertTrue(authors.size() > 0);
    }
    @Test
    @DisplayName("Тест обновления имени Автора по id")
    void updateAuthorNameById(){
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var authorSaved = authorRepository.save(author);
        var id = authorSaved.getId();
        authorRepository.updateNameById(id,AUTРOR_FOR_DEL);
        var updatedAuthor = authorRepository.findById(id).get();
        Assertions.assertEquals(authorSaved,updatedAuthor);
    }
}