package com.example.demo.helloworld;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    public String getHello() {
        return "Hello World";
    }
}
