package com.example.postservice.model;

import com.example.postservice.entity.CommentsEntity;
import com.example.postservice.entity.PostsEntity;
import com.example.postservice.entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reactions {
    private Long reactId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private PostsEntity post;
    private UsersEntity userId;
    private CommentsEntity cmtId;
}
