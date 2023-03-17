package com.example.main.model;

import com.example.main.entity.CommentsEntity;
import com.example.main.entity.PostsEntity;
import com.example.main.entity.UsersEntity;
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
