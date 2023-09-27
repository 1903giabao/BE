package com.example.demoapi.Endpoint.User;

import com.example.demoapi.Entity.User.User;
import com.example.demoapi.Service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        User user = userService.findUserById(id);
        if(user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot find user");
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user){
        if(userService.isUserNameDupplicated(user.getUserName()) || userService.isEmailDupplicated(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username or Email is dupplicated.");
        } else {
            if (userService.save(user)) {
                return ResponseEntity.status(HttpStatus.OK).body(true);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Create User Failed !");
    }

}
