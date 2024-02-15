package com.movie.springboot3.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //test for security jwot token



    @GetMapping("/hello")
    public String helloYou(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            return "Hello " + userDetails.getUsername();
        } else {
            return "Hello stranger!";
        }
    }

    @GetMapping("/secret")
    public String tellSecret() {
        return "Hello";
    }
}
