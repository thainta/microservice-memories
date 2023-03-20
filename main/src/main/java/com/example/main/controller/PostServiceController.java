package com.example.main.controller;

import com.example.main.constant.SpringBootApplicationConstant;
import com.example.main.exeption.CommentNotFoundException;
import com.example.main.exeption.NotificationNotFoundException;
import com.example.main.exeption.PostNotFoundException;
import com.example.main.exeption.ReactionsNotFoundException;
import com.example.main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value ="/api")
public class PostServiceController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/posts")
    public ResponseEntity<Object> getAllPosts(
            @RequestParam(value = "keyword",defaultValue = SpringBootApplicationConstant.DEFAULT_PAGE_KEYWORD,required = false) String keyword,
            @RequestParam(value = "pageNo", defaultValue = SpringBootApplicationConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = SpringBootApplicationConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = SpringBootApplicationConstant.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = SpringBootApplicationConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        ParameterizedTypeReference<Object> responseType = new ParameterizedTypeReference<>() {};
        String url = String.format("http://POST-SERVICE/api/posts?pageNo=%s", pageNo);
        System.out.println(url);
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        Object listPosts = resp.getBody();
        return ResponseEntity.ok().body(listPosts);
    }

    @GetMapping("/{userId}/posts")
    public List<Posts> getPostsByUserId(@PathVariable Long userId){
        ParameterizedTypeReference<List<Posts>> responseType = new ParameterizedTypeReference<>() {};
        String url = String.format("http://POST-SERVICE/api/%s/posts", userId);
        System.out.println(url);
        ResponseEntity<List<Posts>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<Posts> posts = resp.getBody();
        return posts;
    }

    @GetMapping("/posts/{id}")
    public Posts getPostsById(@PathVariable Long id){
        String url = String.format("http://POST-SERVICE/api/posts/%s", id);
        ResponseEntity<Posts> res = restTemplate.getForEntity(url, Posts.class);
        Posts post = res.getBody();
        return post;
    }

    @PostMapping("/{userId}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Long userId, @Valid @RequestBody Posts post, BindingResult result) throws Exception{
        if  (result.hasErrors()){
            return ResponseEntity.badRequest().body("Validation error: "+result.getAllErrors());
        }
        String url = String.format("http://POST-SERVICE/api/%s/posts", userId);

        ResponseEntity<Posts> response = restTemplate.postForEntity(url, post ,Posts.class);

        Posts resPost = response.getBody();
        return ResponseEntity.ok().body(resPost);
    }
    @PutMapping("/posts/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @Valid @RequestBody Posts post, BindingResult result) throws PostNotFoundException {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("Validation error: " + result.getAllErrors());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Posts> entity = new HttpEntity<Posts>(post, headers);
        String url = String.format("http://POST-SERVICE/api/posts/%s", id);
        System.out.println(url);
        ResponseEntity<Posts> resp = restTemplate.exchange(url, HttpMethod.PUT, entity, Posts.class);
        Posts posts = resp.getBody();
        return ResponseEntity.ok().body(posts);
    }
    @PutMapping("/posts/{id}/audience")
    public ResponseEntity<Object> updateAudiencePost(@PathVariable Long id, @Valid @RequestBody Posts post, BindingResult result) throws PostNotFoundException{
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("Validtion error: "+result.getAllErrors());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Posts> entity = new HttpEntity<Posts>(post, headers);
        String url = String.format("http://POST-SERVICE/api/posts/%s/audience", id);
        System.out.println(url);
        ResponseEntity<Posts> resp = restTemplate.exchange(url, HttpMethod.PUT, entity, Posts.class);
        Posts posts = resp.getBody();
        return ResponseEntity.ok().body(posts);
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable @Min(value = 1, message = "Id must be greater than or equal to 1") Long id) throws Exception {
        String url = String.format("http://POST-SERVICE/api/posts/%s", id);
        try {
            restTemplate.delete(url);
            return ResponseEntity.ok().body(true);
        }
        catch (Exception e) {
            throw new Exception("Error deleting post with id " + id + ": " + e.getMessage());
        }
    }

    // Reactions----------------------------------------------------------------------------------------
    @GetMapping("/reactions")
    public ResponseEntity<List<Reactions>> getAllReactions(){
        ParameterizedTypeReference<List<Reactions>> responseType = new ParameterizedTypeReference<>() {};
        String url = "http://POST-SERVICE/api/reactions";
        System.out.println(url);
        ResponseEntity<List<Reactions>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<Reactions> reaction = resp.getBody();
        return ResponseEntity.ok().body(reaction);
    }
    @GetMapping("/reactions/{id}")
    public ResponseEntity<Reactions> getReactionById(@PathVariable @Min(value = 1, message = "Id must be greater than or equal to 1")Long id) throws ReactionsNotFoundException {
        String url = String.format("http://POST-SERVICE/api/reactions/%s", id);
        ResponseEntity<Reactions> res = restTemplate.getForEntity(url, Reactions.class);
        Reactions reaction = res.getBody();
        return ResponseEntity.ok().body(reaction);
    }
    @GetMapping("/post/{postId}/reactions")
    public ResponseEntity<List<Reactions>> getReactionByPostId(@PathVariable Long postId ){
        ParameterizedTypeReference<List<Reactions>> responseType = new ParameterizedTypeReference<>() {};
        String url = String.format("http://POST-SERVICE/api/post/%s/reactions", postId);
        System.out.println(url);
        ResponseEntity<List<Reactions>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<Reactions> reaction = resp.getBody();
        return ResponseEntity.ok().body(reaction);
    }
    @GetMapping("/comment/{commentId}/reactions")
    public ResponseEntity<List<Reactions>> getReactionByCommentId(@PathVariable Long commentId){
        ParameterizedTypeReference<List<Reactions>> responseType = new ParameterizedTypeReference<>() {};
        String url = String.format("http://POST-SERVICE/api/comment/%s/reactions", commentId);
        System.out.println(url);
        ResponseEntity<List<Reactions>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<Reactions> reaction = resp.getBody();
        return ResponseEntity.ok().body(reaction);
    }
    @PostMapping("/user/{userId}/reactions")
    public ResponseEntity<Object> createReaction(@PathVariable Long userId, @Valid @RequestBody Reactions reactions, BindingResult result) throws Exception{
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("Validaion error: " + result.getAllErrors());
        }
        String url = String.format("http://POST-SERVICE/api/user/%s/reactions", userId);

        ResponseEntity<Reactions> response = restTemplate.postForEntity(url, reactions ,Reactions.class);

        Reactions reaction = response.getBody();
        return ResponseEntity.ok().body(reaction);
    }

    @PutMapping("/reactions/{id}")
    public ResponseEntity<Object> updateReaction(@PathVariable Long id,@Valid @RequestBody Reactions reactions, BindingResult result) throws ReactionsNotFoundException {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("Validaion error: " + result.getAllErrors());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Reactions> entity = new HttpEntity<Reactions>(reactions, headers);
        String url = String.format("http://POST-SERVICE/api/reactions/%s", id);
        System.out.println(url);
        ResponseEntity<Reactions> resp = restTemplate.exchange(url, HttpMethod.PUT, entity, Reactions.class);
        Reactions reaction = resp.getBody();
        return ResponseEntity.ok().body(reaction);
    }
    @DeleteMapping("/reactions/post/{postId}/user/{userId}")
    public ResponseEntity<Boolean> deleteReaction(@PathVariable @Min(value = 1, message = "Id must be greater than or equal to 1") Long postId,
                                                  @PathVariable @Min(value = 1, message = "Id must be greater than or equal to 1") Long userId) throws ReactionsNotFoundException {
        String url = String.format("http://POST-SERVICE/api/reactions/post/%s/user/%s", postId, userId);
        try {
            restTemplate.delete(url);
            return ResponseEntity.ok().body(true);
        }
        catch (Exception e) {
            throw new ReactionsNotFoundException("Error deleting post with error: " + e.getMessage());
        }
    }

    //Comment--------------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<Comments>> getAllCommentsPost(@PathVariable Long postId){
        ParameterizedTypeReference<List<Comments>> responseType = new ParameterizedTypeReference<>() {};
        String url =    String.format("http://POST-SERVICE/api/post/%s/comments", postId);
        System.out.println(url);
        ResponseEntity<List<Comments>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<Comments> comment = resp.getBody();
        return ResponseEntity.ok().body(comment);    }
    @PostMapping("/user/{userId}/comments/{postId}")
    public ResponseEntity<Comments> createComment(@PathVariable Long postId,@PathVariable Long userId, @RequestBody Comments comments) throws Exception {
        String url = String.format("http://POST-SERVICE/api/user/%s/comments/%s", userId,postId);

        ResponseEntity<Comments> response = restTemplate.postForEntity(url, comments ,Comments.class);

        Comments comment = response.getBody();
        return ResponseEntity.ok().body(comment);
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) throws CommentNotFoundException {
        String url = String.format("http://POST-SERVICE/api/comments/%s", id);
        try {
            restTemplate.delete(url);
            return ResponseEntity.ok().body(true);
        }
        catch (Exception e) {
            throw new CommentNotFoundException("Error deleting post with error: " + e.getMessage());
        }
    }
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comments> updateComment(@PathVariable Long id,@RequestBody Comments comments) throws CommentNotFoundException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Comments> entity = new HttpEntity<Comments>(comments, headers);
        String url = String.format("http://POST-SERVICE/api/comments/%s", id);
        System.out.println(url);
        ResponseEntity<Comments> resp = restTemplate.exchange(url, HttpMethod.PUT, entity, Comments.class);
        Comments comment = resp.getBody();
        return ResponseEntity.ok().body(comment);
    }
//Notifications ------------------------------------------------------------------------------------------------
    @GetMapping("/notifications")
    public ResponseEntity<List<Notifications>>getAllNotification(){
        ParameterizedTypeReference<List<Notifications>> responseType = new ParameterizedTypeReference<>() {};
        String url = "http://POST-SERVICE/api/notifications";
        System.out.println(url);
        ResponseEntity<List<Notifications>> resp = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        List<Notifications> notification = resp.getBody();
        return ResponseEntity.ok().body(notification);
    }
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<Object> deleteNotification(@PathVariable Long id) throws NotificationNotFoundException {
        String url = String.format("http://POST-SERVICE/api/notifications/%s", id);
        try {
            restTemplate.delete(url);
            return ResponseEntity.ok().body(true);
        }
        catch (Exception e) {
            throw new NotificationNotFoundException("Error deleting post with error: " + e.getMessage());
        }
    }
}
