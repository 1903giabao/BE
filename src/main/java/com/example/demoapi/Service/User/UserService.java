package com.example.demoapi.Service.User;

import com.example.demoapi.Entity.User.User;

import java.util.List;

public interface UserService {
    User findUserById(String id);
    List<User> findAll();
    boolean save(User user);
    boolean isUserIdDupplicated(String id);
    boolean isUserNameDupplicated(String username);
    boolean isEmailDupplicated(String email);
}
