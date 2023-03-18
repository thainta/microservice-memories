package com.example.friendservice.model;

import com.example.friendservice.entity.UsersEntity;
import com.example.friendservice.entity.UsersEntity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequests {
    private Long reqId;
    private UsersEntity sendUser;
    private UsersEntity receiveUser;
    private int isAccepted;
    private int isArchived;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
