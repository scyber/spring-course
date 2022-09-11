package ru.otus.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/index", "/login")
            .permitAll()
            .and()
            .authorizeRequests()
            .antMatchers("/list", "/edit", "/editBooks", "/deleteBook", "/addBook", "/editComments", "/deleteComment", "/addComment",
                         "/addGenreForBook", "/deleteComment", "/addComment", "/editComments", "/addAuthorForBook", "/deleteAuthorFromBook",
                         "/authors", "/success")
            .authenticated()
            .and()
            // Включает Form-based аутентификацию
            .formLogin();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService())
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public JdbcDaoImpl userDetailService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
        jdbcDao.setAuthoritiesByUsernameQuery("select username, authority from authorities where username = ?");
        return jdbcDao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }



}
