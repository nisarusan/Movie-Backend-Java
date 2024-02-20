package com.movie.app.service;

import com.movie.app.dto.MovieDto;
import com.movie.app.model.Genre;
import com.movie.app.model.Movie;
import com.movie.app.model.Rating;
import com.movie.app.repository.MovieRepository;
import com.movie.app.repository.RatingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repos;
    private final RatingRepository ratingRepos;
    private final GenreService genreService;

    public MovieService(MovieRepository repos, RatingRepository ratingRepos, GenreService genreService) {

        this.repos = repos;
        this.ratingRepos = ratingRepos;
        this.genreService = genreService;

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

    //get the movie object only 1 movie by id
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

    // Get Movie by ID
    public Movie getMovieById(Long id) {
        Optional<Movie> optionalMovie = repos.findById(id);
        return optionalMovie.orElse(null);
    }

    // Get the average rating for a movie from the Rating table
    public Double getAverageRatingForMovie(Long movieId) {
        List<Rating> ratings = ratingRepos.findByMovie_Id(movieId);

        if (!ratings.isEmpty()) {
            // Calculate the average rating
            double sum = ratings.stream().mapToDouble(Rating::getRating).sum();
            return sum / ratings.size();
        } else {
            // Return null if no ratings are found for the movie
            return null;
        }
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