package ru.otus.config;


import javax.sql.DataSource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.controllers.AuthorsController;
import ru.otus.controllers.BooksController;
import ru.otus.controllers.CommentsController;
import ru.otus.controllers.MainViewController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MainViewController.class)
@ExtendWith(SpringExtension.class)
@Disabled
class SecurityConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(
        username = "user",
        authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("Testing secure links")
    public void testAuthenticatedOnAdmin() throws Exception {
        mockMvc.perform(get("/list")).andExpect(status().isOk());
    }
}