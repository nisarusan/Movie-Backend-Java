package com.movie.app.controller;


import com.movie.app.dto.MovieDto;
import com.movie.app.model.Movie;
import com.movie.app.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MoviesController {

    private final MovieService service;

    public MoviesController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movieDto = service.allMovies();
        return ResponseEntity.ok().body(movieDto);
    }

    //get all movies
    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long id) {
        MovieDto movieDto = service.getMovie(id);
        if (movieDto != null) {
            return ResponseEntity.ok().body(movieDto);
        } else {
            //  error page
            return ResponseEntity.notFound().build();
        }
    }

    //getMovie by Title
    @GetMapping("/movie")
    public ResponseEntity<List<MovieDto>> getMovieByTitle(@RequestParam String title) {
        List<MovieDto> movieDto = service.searchMovies(title);
        if (movieDto != null) {
            return ResponseEntity.ok().body(movieDto);
        } else {
            // Movie not found, return a ResponseEntity with NOT_FOUND status
            return ResponseEntity.notFound().build();
        }
    }

    //add new Movies
    @PostMapping("/movie")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        movieDto = service.addMovie(movieDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + movieDto.id).toUriString());
        return ResponseEntity.created(uri).body(movieDto);
    }
}
