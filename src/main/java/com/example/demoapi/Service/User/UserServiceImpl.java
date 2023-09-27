package com.example.demoapi.Service.User;

import com.example.demoapi.Entity.User.User;
import com.example.demoapi.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(String id) {
        try {
            return userRepository.findUserById(id);
        } catch (DataIntegrityViolationException e) {
            // Handle specific database constraint violation (e.g., duplicate entry)
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(User user) {
        try {
            String userid = UUID.randomUUID().toString();
            user.setId(userid);
            while (isUserIdDupplicated(userid)) {
                userid = UUID.randomUUID().toString();
                user.setId(userid);
            }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            // Handle specific database constraint violation (e.g., duplicate entry)
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isUserIdDupplicated(String id) {
        return (userRepository.findUserById(id) != null);
    }

    @Override
    public boolean isUserNameDupplicated(String username) {
        return (userRepository.findUserByUserName(username) != null);
    }

    @Override
    public boolean isEmailDupplicated(String email) {
        return (userRepository.findUserByEmail(email) != null);
    }

}
