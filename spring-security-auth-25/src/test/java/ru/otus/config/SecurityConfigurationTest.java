package ru.otus.config;



import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

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
    @DisplayName("Тестирование закрытого авторизацией взызов удаления книги")
    public void testAuthenticatedOnUserDeleteBook() throws Exception {
        mockMvc.perform(post("/deleteBook").param("id", "1")).andExpect(status().is(302));
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

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызов просмотра комментариев к книге")
    public void testAuthenticatedOnUserEditComments() throws Exception {
        mockMvc.perform(get("/editComments").param("id","1")).andExpect(status().isOk());
    }

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызов добавления комментариев к книге")
    public void testAuthenticatedOnUserAddComments() throws Exception {
        mockMvc.perform(post("/addComment")
                            .param("bookId","1")
                            .param("title","test Title"))
                            .andExpect(status().is(302));
    }

    @WithMockUser(username = "user", authorities = {"ROLE_USER"}, setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    @DisplayName("Тестирование закрытого авторизацией взызов удаления комментариев к книге")
    public void testAuthenticatedOnUserPostDeleteComment() throws Exception {
        mockMvc.perform(post("/deleteComment")
                            .param("bookId", "1")
                            .param("commentId", "1"))
                            .andExpect(status().is(302));
    }


}