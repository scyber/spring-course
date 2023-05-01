package ru.otus.config;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;
import ru.otus.services.BookService;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityConfigurationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызова просмотра ")
    public void testAuthenticatedOnUserLists() throws Exception {
        mockMvc.perform(get("/list")).andExpect(status().isOk());
    }


    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызов редактирования списка книг")
    public void testAuthenticatedOnUserEditBooks() throws Exception {
        mockMvc.perform(get("/editBooks")).andExpect(status().isOk());
    }

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование взыза добавления комментариев к книге")
    public void testAuthenticatedOnUserAddComments() throws Exception {
        mockMvc.perform(post("/addComment")
                            .param("bookId","1")
                            .param("text","test comment"));

    }

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызов удаления комментариев к книге")
    public void testAuthenticatedOnUserPostDeleteComment() throws Exception {
        mockMvc.perform(post("/deleteComment")
                            .param("bookId", "1")
                            .param("commentId", "1"))
            .andExpect(status().is(403));
    }

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызов удаления книги")
    public void testAuthenticatedOnUserDeleteBook() throws Exception {
        mockMvc.perform(post("/deleteBook").param("id", "1")).andExpect(status().is(403));
    }

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызов добавления книги")
    public void testAuthenticatedOnUserAddBook() throws Exception {
        mockMvc.perform(get("/addBook")).andExpect(status().isOk());
    }

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызов получения комментариев к книге")
    public void testAuthenticatedOnUserGetComments() throws Exception {
        mockMvc.perform(get("/viewComments").param("id","1")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Проверка недоступности без нужных атрибутов ресурса")
    public void testNotAvailableWithoutAuth() throws Exception {
        mockMvc.perform(get("/list")).andExpect(status().is(302));
    }


}