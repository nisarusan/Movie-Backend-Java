package com.movie.app.dto;

import com.movie.app.model.Movie;
import com.movie.app.model.User;

import java.util.Set;

public class RecommendDto {
    public Long id;
    public User user;
    public Set<Movie> recommendedMovies;

}
