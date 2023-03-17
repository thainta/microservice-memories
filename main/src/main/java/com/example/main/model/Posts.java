package com.example.main.model;

import com.example.main.entity.PhotoInPostEntity;
import com.example.main.entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts {
    private Long postId;

    private String content;

    private String permission;

    private UsersEntity user;
    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private int isArchieved;

    private PhotoInPostEntity photoInPost;
}
