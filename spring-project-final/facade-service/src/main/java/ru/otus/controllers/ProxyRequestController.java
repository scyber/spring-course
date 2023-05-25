package ru.otus.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class ProxyRequestController {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello(){
        return "Hello";
    }
}
