package com.movie.app.service;

import com.movie.app.dto.MovieDto;

import java.util.List;

public interface MovieGenreService {
//general
    List<MovieDto> getMoviesByGenre(String genreName);
}
