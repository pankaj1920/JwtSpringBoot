package com.example.jwtauth.service;

import com.example.jwtauth.model.entities.UserSchema;
import com.example.jwtauth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // load user form data base
        UserSchema userSchema = this.userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        return userSchema;
    }
}
