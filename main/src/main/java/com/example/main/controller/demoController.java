package com.example.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class demoController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/getDemo")
    public String get(){
        String res = restTemplate.getForObject("http://POST-SERVICE/get", String.class);
        return res;
    }
}
