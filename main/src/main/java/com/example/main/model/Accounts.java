package com.example.main.model;


import com.example.main.entity.RolesEntity;
import com.example.main.entity.UsersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    @author Anh Dung
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    private Long acc_id;
    private String userName;
    private String hashPassword;
    private Long phone_number;
    private String email;
    private int isArchieved;
    private RolesEntity roles;
    private UsersEntity users;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
