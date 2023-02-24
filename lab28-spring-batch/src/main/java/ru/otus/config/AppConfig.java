package ru.otus.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;

@Configuration
public class AppConfig {

    @Autowired
    private ServletContext servletContext;


}
