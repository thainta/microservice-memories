package com.example.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Long id;
    private String content;
    private String sender;
    private LocalDateTime createAt;
    private MessageType type;

    public enum MessageType{
        CHAT,
        JOIN,
        LEAVE
    }
}
