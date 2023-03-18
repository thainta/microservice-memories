package com.example.main.controller;

import com.example.main.model.FriendRequests;
import com.example.main.model.SearchRecents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value ="/api")
public class SearchServiceController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/user/{userId}/searchrecents")
    public ResponseEntity<List<SearchRecents>> getAllSearchRecentsByUserId(@PathVariable Long userId){
        ParameterizedTypeReference<List<SearchRecents>> responseType = new ParameterizedTypeReference<>() {};
        String url =    String.format("http://SEARCH-SERVICE/api/user/%s/searchrecents", userId);
        System.out.println(url);
        ResponseEntity<List<SearchRecents>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<SearchRecents> search = resp.getBody();
        return ResponseEntity.ok().body(search);    }
    @PostMapping("/user/{userId}/searchrecents")
    public ResponseEntity<Object> createSearch(@PathVariable Long userId, @Valid @RequestBody SearchRecents searchRecents, BindingResult result) throws Exception
    {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        String url = String.format("http://SEARCH-SERVICE/api/user/%s/searchrecents", userId);

        ResponseEntity<SearchRecents> response = restTemplate.postForEntity(url, searchRecents ,SearchRecents.class);

        SearchRecents search = response.getBody();
        return ResponseEntity.ok().body(search);    }
}
