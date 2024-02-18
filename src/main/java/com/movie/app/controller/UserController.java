package com.movie.app.controller;

import com.movie.app.dto.MovieDto;
import com.movie.app.dto.UserDto;
import com.movie.app.model.Movie;
import com.movie.app.model.User;
import com.movie.app.service.MovieService;
import com.movie.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {


    //Constructor injector to service
    private final UserService service;
    private final MovieService movieService;

    public UserController(UserService service, MovieService movieService) {
        this.service = service;
        this.movieService = movieService;
    }


    //Create User when signup
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        service.createUser(userDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + userDto.id).toUriString());
        return ResponseEntity.created(uri).body(userDto);
    }


    //get user ID profile
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        // Call the service method to retrieve the user by ID
        UserDto userDto = service.getUserById(id);

        // Check if the user exists
        if (userDto != null) {
            return ResponseEntity.ok(userDto);
        } else {
            // Return 404 Not Found if the user is not found
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/user/{userId}/favorite-movies")
    public ResponseEntity<Void> setUserFavoriteMovies(
            @PathVariable Long userId,
            @RequestBody Set<Long> favoriteMovie) {
        service.setFavoriteMovies(userId, favoriteMovie);
        return ResponseEntity.ok().build();
    }


}
