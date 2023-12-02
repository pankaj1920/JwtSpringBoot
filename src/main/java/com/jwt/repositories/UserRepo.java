package com.jwt.repositories;

import com.jwt.schema.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<UserSchema,Integer> {

    public  Optional<UserSchema> findByEmail(String email);
}
