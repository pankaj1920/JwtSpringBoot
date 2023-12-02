package com.jwt.controller;

import com.jwt.jwt.JwtHelper;
import com.jwt.model.JwtRequest;
import com.jwt.model.JWTResponse;
import com.jwt.repositories.UserRepo;
import com.jwt.schema.UserSchema;
import com.jwt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    JwtHelper helper;

    @Autowired
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        JWTResponse jwtResponse = JWTResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername()).build();
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        try {

            manager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username password");
        }
    }



    @ExceptionHandler(BadCredentialsException.class)
    private String exceptionHandler(){
        return "Credential Invalid";
    }

    @PostMapping("/create_user")
    public UserSchema createUser(@RequestBody UserSchema userRequest){

        return userService.createUser(userRequest);
    }
}
