package com.movie.app.controller;


import com.movie.app.model.Genre;
import com.movie.app.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class GenreController {

    GenreService service;
    public GenreController(GenreService service) {
        this.service = service;
    }
    @GetMapping("/genre")
    public ResponseEntity<Set<Genre>> getAllGenres() {
    Set<Genre> genre = service.getAllGenres();
    return ResponseEntity.ok(genre);
    }
}
