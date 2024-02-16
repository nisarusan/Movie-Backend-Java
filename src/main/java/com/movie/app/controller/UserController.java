package com.movie.app.controller;

import com.movie.app.dto.UserDto;
import com.movie.app.model.User;
import com.movie.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {


    //Constructor injector to service
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        service.createUser(userDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + userDto.id).toUriString());
        return ResponseEntity.created(uri).body(userDto);
    }

//    @GetMapping("/user/{id}")
//    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
//
//    }
}
