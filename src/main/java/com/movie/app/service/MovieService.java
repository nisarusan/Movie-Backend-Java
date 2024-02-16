package com.movie.app.service;

import com.movie.app.dto.MovieDto;
import com.movie.app.model.Movie;
import com.movie.app.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repos;
    public MovieService(MovieRepository repos) {
        this.repos = repos;
    }

    public MovieDto addMovie(MovieDto movieDto) {
        Movie movie = dtoToMovie(movieDto);
        movie.setTitle(movieDto.title);
//        movie.setRatings(movieDto.ratings);
        movie.setAverageRating(movieDto.averageRating);
        movie.setGenre(movieDto.genre);
        movie.setDescription(movieDto.description);
        movie.setReleaseDate(movieDto.releaseDate);
        movie.setDuration(movieDto.duration);
        movie.setImageUrl(movieDto.imageUrl);
        movie.setDirector(movieDto.director);
        repos.save(movie);
        movieDto.id = movie.getId();
        return movieDto;
    }

    public List<MovieDto> allMovies() {
        List<Movie> moviesList = repos.findAll();
        List<MovieDto> movieDtoList = new ArrayList<>();
        for(Movie movie : moviesList) {
            MovieDto movieDto = movieDto(movie);
            movieDtoList.add(movieDto);
        }
        return movieDtoList;
    }


    //Movie DTO Mapper
    public MovieDto movieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.id = movie.getId();
        movieDto.averageRating = movie.getAverageRating();
        movieDto.description = movie.getDescription();
        movieDto.genre = movie.getGenre();
//        movieDto.ratings = movie.getRatings();
        movieDto.director = movie.getDirector();
        movieDto.imageUrl = movie.getImageUrl();
        movieDto.releaseDate = movie.getReleaseDate();
        movieDto.title = movie.getTitle();
        movieDto.duration = movie.getDuration();
        return movieDto;
    }


    //Dto To User mapper
    public Movie dtoToMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.title);
//        movie.setRatings(movieDto.ratings);
        movie.setDescription(movieDto.description);
        movie.setAverageRating(movieDto.averageRating);
        movie.setDirector(movieDto.director);
        movie.setDuration(movieDto.duration);
        movie.setGenre(movieDto.genre);
        movie.setImageUrl(movieDto.imageUrl);
        movie.setReleaseDate(movieDto.releaseDate);
        return movie;
    }


}