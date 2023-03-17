package com.example.main.model;

import lombok.*;

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
