package com.example.demoapi.Entity.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "tblUser")
@Data
public class User {
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private String id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "statususer")
    private boolean status;

    @Column(name = "image")
    private String img;

    @JsonIgnore
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<User_Role> user_roles;
}
