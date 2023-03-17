package com.example.postservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {
    @GetMapping("/get")
    public String get(){
        return "Thu";
    }
}
