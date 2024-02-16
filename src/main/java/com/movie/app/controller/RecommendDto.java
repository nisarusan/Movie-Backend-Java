package com.movie.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RecommendDto {
    @GetMapping("/hello")
    public String seeHello() {
        return "Hello";
    }
}
