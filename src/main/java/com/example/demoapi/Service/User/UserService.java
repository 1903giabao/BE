package com.example.demoapi.Service.User;

import com.example.demoapi.Entity.User.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findUserById(String id);
    List<User> findAll();
    boolean save(User user);
    boolean saveUser_Role(User user);
    boolean isUserIdDupplicated(String id);
    boolean isUserNameDupplicated(String username);
    boolean isEmailDupplicated(String email);
}
