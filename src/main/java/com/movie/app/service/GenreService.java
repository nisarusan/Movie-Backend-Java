package com.movie.app.service;


import com.movie.app.dto.GenreDto;
import com.movie.app.dto.MovieDto;
import com.movie.app.model.Genre;
import com.movie.app.model.Movie;
import com.movie.app.repository.GenreRepository;
import com.movie.app.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository repos;
    private final MovieRepository moviesRepository;

    public GenreService(GenreRepository repos, MovieRepository movieRepository) {
        this.repos = repos;
        this.moviesRepository = movieRepository;
    }

    // get all genres exist
    public Set<Genre> getAllGenres() {
        return new HashSet<>(repos.findAll());
    }

    // Get genre by name
    public Genre getGenreByName(String genreName) {
        return repos.findByName(genreName);
    }



    // Genre DTO Mapper
    public GenreDto genreDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.id = genre.getId();
        genreDto.name = genre.getName();
        return genreDto;
    }

    // DTO To Genre mapper
    public Genre dtoToGenre(GenreDto genreDto) {
        Genre genre = new Genre();
        genre.setName(genreDto.name);
        return genre;
    }

    // Convert Set<Genre> to Set<GenreDto>
    public Set<GenreDto> convertToGenreDtos(Set<Genre> genres) {
        return genres.stream().map(this::genreDto).collect(Collectors.toSet());
    }

}
