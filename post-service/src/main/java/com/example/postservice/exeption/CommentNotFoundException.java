package com.example.postservice.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Comment is not found")
public class CommentNotFoundException extends Exception{
    public CommentNotFoundException(String message){
        super(message);
    }
}
