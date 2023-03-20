package com.example.main.controller;


import com.example.main.exeption.CommentNotFoundException;
import com.example.main.exeption.FriendRequestsNotFoundException;
import com.example.main.model.Comments;
import com.example.main.model.FriendRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value ="/api")
public class FriendServiceController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/friendrequests")
    public ResponseEntity<List<FriendRequests>> getAllFriendRequest(){
        ParameterizedTypeReference<List<FriendRequests>> responseType = new ParameterizedTypeReference<>() {};
        String url = "http://FRIEND-SERVICE/api/friendrequests";
        System.out.println(url);
        ResponseEntity<List<FriendRequests>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<FriendRequests> friendRequest = resp.getBody();
        return ResponseEntity.ok().body(friendRequest);
    }

    @GetMapping("/user/{userid}/friendrequests/sendUserId")
    public ResponseEntity<List<FriendRequests>> getSendUserFriendRequest(@PathVariable long userid){
        ParameterizedTypeReference<List<FriendRequests>> responseType = new ParameterizedTypeReference<>() {};
        String url =    String.format("http://FRIEND-SERVICE/api/user/%s/friendrequests/sendUserId", userid);
        System.out.println(url);
        ResponseEntity<List<FriendRequests>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<FriendRequests> friendRequest = resp.getBody();
        return ResponseEntity.ok().body(friendRequest);
    }
    @GetMapping("/user/{userid}/friendrequests/receiveUserId")
    public ResponseEntity<List<FriendRequests>> getReceiveUserFriendRequest(@PathVariable long userid){
        ParameterizedTypeReference<List<FriendRequests>> responseType = new ParameterizedTypeReference<>() {};
        String url =    String.format("http://FRIEND-SERVICE/api/user/%s/friendrequests/receiveUserId", userid);
        System.out.println(url);
        ResponseEntity<List<FriendRequests>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<FriendRequests> friendRequest = resp.getBody();
        return ResponseEntity.ok().body(friendRequest);
    }
    @PostMapping("user/{userId}/friendrequests")
    public ResponseEntity<FriendRequests> createFriendRequest(@PathVariable long userId,@RequestBody FriendRequests friendRequests) throws Exception {
        String url = String.format("http://FRIEND-SERVICE/api/user/%s/friendrequests", userId);

        ResponseEntity<FriendRequests> response = restTemplate.postForEntity(url, friendRequests ,FriendRequests.class);

        FriendRequests friendRequest = response.getBody();
        return ResponseEntity.ok().body(friendRequest);    }


    @DeleteMapping("/friendrequest/{id}")
    public ResponseEntity<Boolean> deleteFriendRequest(@PathVariable long id) throws FriendRequestsNotFoundException {
        String url = String.format("http://FRIEND-SERVICE/api/friendrequest/%s", id);
        try {
            restTemplate.delete(url);
            return ResponseEntity.ok().body(true);
        }
        catch (Exception e) {
            throw new FriendRequestsNotFoundException("Error deleting post with error: " + e.getMessage());
        }
    }

    @PutMapping("/friendrequest/{id}/accept")
    public ResponseEntity<Boolean> setAccept(@PathVariable long id) throws FriendRequestsNotFoundException {
        String url = String.format("http://FRIEND-SERVICE/api/friendrequest/%s/accept", id);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Boolean> entity = new HttpEntity<Boolean>(true, headers);
            ResponseEntity<Boolean> resp = restTemplate.exchange(url, HttpMethod.PUT, entity, Boolean.class);
            Boolean bool = resp.getBody();
            return ResponseEntity.ok().body(bool);
        }
        catch (Exception e) {
            throw new FriendRequestsNotFoundException("Error deleting post with error: " + e.getMessage());
        }
    }

}
