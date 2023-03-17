package com.example.main.service.interfaces;

import com.example.main.exeption.UserNotFoundException;
import com.example.main.model.Users;

import java.util.List;

public interface UserService {
    Users createUser(Users user);

    List<Users> getAllUsers();

    boolean deleteUser(Long id) throws UserNotFoundException;
    Users getUserById(Long id) throws UserNotFoundException;

    Users updateUser(Long id, Users users) throws UserNotFoundException;
    Users updateFollowerUser(Long id, Users users) throws  UserNotFoundException;
}
