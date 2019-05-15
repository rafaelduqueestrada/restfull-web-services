package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "HelloWorld!";
    }

    @GetMapping("hello-world-bean")
    public HelloWorldBean helloWorldWithBean() {
        return new HelloWorldBean("Hello World noob!");
    }
}
