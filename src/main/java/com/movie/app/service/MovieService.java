package com.movie.app.service;

import com.movie.app.dto.MovieDto;
import com.movie.app.model.Movie;
import com.movie.app.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repos;
    public MovieService(MovieRepository repos) {
        this.repos = repos;
    }


    //add new movies
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


    //get all Movies
    public List<MovieDto> allMovies() {
        List<Movie> moviesList = repos.findAll();
        List<MovieDto> movieDtoList = new ArrayList<>();
        for(Movie movie : moviesList) {
            MovieDto movieDto = movieDto(movie);
            movieDtoList.add(movieDto);
        }
        return movieDtoList;
    }

    //get Movie by Id
    public MovieDto getMovie(Long id) {
        Optional<Movie> optionalMovie = repos.findById(id);

        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            return movieDto(movie);
        } else {
            // Handle the case where the movie with the given ID is not found
            return null;
        }
    }

    //get Movie by String
    public List<MovieDto> searchMovies(String title) {
        List<Movie> movies = repos.findByTitle(title);
        List<MovieDto> movieDtos = new ArrayList<>();

        for (Movie movie : movies) {
            movieDtos.add(movieDto(movie));
        }

        return movieDtos;
    }

    //get Movie by ID

    // Get Movie by ID
    public Movie getMovieById(Long id) {
        Optional<Movie> optionalMovie = repos.findById(id);
        return optionalMovie.orElse(null);
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

    public Set<Movie> convertToMovieEntities(Set<MovieDto> favoriteMovies) {
        Set<Movie> movieEntities = new HashSet<>();

        for (MovieDto movieDto : favoriteMovies) {
            Movie movie = new Movie();
            movie.setId(movieDto.id);
            // Set other properties as needed
            movieEntities.add(movie);
        }

        return movieEntities;
    }
}
