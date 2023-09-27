package com.example.demoapi.Repository.User;

import com.example.demoapi.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<User, String>{
    User findUserById(String id);
    User findUserByUserName(String username);
    User findUserByEmail(String email);

}
