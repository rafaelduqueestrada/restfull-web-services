package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldRestController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "HelloWorld!";
    }

    @GetMapping("hello-world-bean")
    public HelloWorldBean helloWorldWithBean() {
        Locale locale = LocaleContextHolder.getLocale();
        return new HelloWorldBean(messageSource.getMessage("good.morning.message", null, locale));
    }
}
