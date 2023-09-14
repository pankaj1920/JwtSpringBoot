package com.example.jwtauth.service;

import com.example.jwtauth.model.User;
import com.example.jwtauth.model.entities.UserSchema;
import com.example.jwtauth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<UserSchema> getAllUser() {
        return userRepo.findAll();
    }

    public UserSchema createUser(UserSchema userSchema){
        userSchema.setUserId(UUID.randomUUID().toString());
        userSchema.setPassword(passwordEncoder.encode(userSchema.getPassword()));
        return this.userRepo.save(userSchema);
    }
}
