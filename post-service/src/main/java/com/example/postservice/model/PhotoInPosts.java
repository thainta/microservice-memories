package com.example.postservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoInPosts {
    private Long photoId;
    private Integer isHighLight;
    private String photoUrl;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
