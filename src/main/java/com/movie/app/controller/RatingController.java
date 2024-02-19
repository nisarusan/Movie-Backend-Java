package com.movie.app.controller;

import com.movie.app.dto.MovieDto;
import com.movie.app.dto.RatingDto;
import com.movie.app.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/ratings")
    public class RatingController {
        private final RatingService ratingService;

        public RatingController(RatingService ratingService) {
            this.ratingService = ratingService;
        }

    //get all a movies rated by the user
    @GetMapping("/{userId}/rated-movies")
    public ResponseEntity<Set<MovieDto>> getUserRatedMovies(@PathVariable Long userId) {
        // Call the service method to retrieve the user's rated movies
        Set<MovieDto> ratedMovies = ratingService.getUserRatedMovies(userId);

        // Check if the user exists
        if (ratedMovies != null) {
            return ResponseEntity.ok(ratedMovies);
        } else {
            // Return 404 Not Found if the user is not found
            return ResponseEntity.notFound().build();
        }
    }



}
