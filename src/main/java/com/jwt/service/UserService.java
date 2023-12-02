package com.jwt.service;

import com.jwt.repositories.UserRepo;
import com.jwt.schema.UserSchema;
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


    public List<UserSchema> getUser(){
        return this.userRepo.findAll();
    }

    public UserSchema createUser(UserSchema user){
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);

    }
}
