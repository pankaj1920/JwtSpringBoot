package com.example.jwtauth.repo;

import com.example.jwtauth.model.entities.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserSchema,String> {

    public Optional<UserSchema> findByEmail(String email);
}
