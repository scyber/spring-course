package ru.otus.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.services.LibraryUserDetailsService;

@Configuration
public class AppConfig {

    @Bean
    UserDetailsService userDetailsService(){
        return new LibraryUserDetailsService();
    }


}
