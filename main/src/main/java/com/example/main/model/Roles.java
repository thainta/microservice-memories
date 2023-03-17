package com.example.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    @author Anh Dung
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    private Long role_id;
    private String roleName;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
