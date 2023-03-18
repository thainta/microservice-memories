package com.example.searchservice.model;

import com.example.searchservice.entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRecents {
    private Long searchId;
    private String keyword;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private UsersEntity user;
}
