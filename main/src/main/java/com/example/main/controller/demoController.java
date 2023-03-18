package com.example.main.controller;

import com.example.main.entity.PostsEntity;
import com.example.main.model.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class demoController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/getDemo")
    public Posts get(){
        ResponseEntity<Posts> res = restTemplate.getForEntity("http://POST-SERVICE/api/posts/1", Posts.class);
        Posts post = res.getBody();
        return post;
    }

    @GetMapping("/getDemo1")
    public List<Posts> get1(){
        ParameterizedTypeReference<List<Posts>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<Posts>> resp = restTemplate.exchange("http://POST-SERVICE/api/1/posts", HttpMethod.GET, null, responseType);
        List<Posts> posts = resp.getBody();
        return posts;
    }
}
