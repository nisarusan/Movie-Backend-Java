package com.movie.app.service;

import com.movie.app.dto.MovieDto;
import com.movie.app.model.Movie;
import com.movie.app.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            // For now, let's return null, but you might want to throw an exception or handle it differently based on your use case.
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
