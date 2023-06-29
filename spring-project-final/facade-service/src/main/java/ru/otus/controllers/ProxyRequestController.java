package ru.otus.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController("/")
public class ProxyRequestController {



    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello(){
        var formatter = DateTimeFormatter.ofPattern("dd-MM-YY HH:mm:SS");
        var time = LocalDateTime.now().format(formatter);
        return "Hello " + time;
    }
}
