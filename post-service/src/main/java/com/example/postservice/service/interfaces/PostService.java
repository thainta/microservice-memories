package com.example.postservice.service.interfaces;

import com.example.postservice.builder.PostResponse;
import com.example.postservice.exeption.PostNotFoundException;
import com.example.postservice.model.Posts;

import java.util.List;

public interface PostService {
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir, String keyword);
    List<Posts> getPostByUserId(long user_id);
    Posts createPost(long userId, Posts post) throws Exception;
    Posts updatePost(long id, Posts post) throws PostNotFoundException;
    Posts updateAudiencePost(long id, Posts post) throws PostNotFoundException;
    Posts getPostById(long id) throws PostNotFoundException;
    Boolean deletePostById(long id) throws PostNotFoundException;
    Long countPost();
    Long countPostByMonth(String startDate, String endDate);
}
