package com.example.main.model;

import com.example.main.entity.UsersEntity;
import lombok.*;

import javax.persistence.*;

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
