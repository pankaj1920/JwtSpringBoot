package com.example.jwtauth.controller;

import com.example.jwtauth.model.User;
import com.example.jwtauth.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserService userService;



    @GetMapping("/getLoginUser")
    public String getLoginUser(Principal principal){
        return principal.getName();
    }
}
