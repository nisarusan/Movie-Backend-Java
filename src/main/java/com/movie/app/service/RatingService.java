package com.movie.app.service;

import com.movie.app.dto.MovieDto;
import com.movie.app.dto.RatingDto;
import com.movie.app.dto.UserDto;
import com.movie.app.model.Movie;
import com.movie.app.model.Rating;
import com.movie.app.model.User;
import com.movie.app.repository.RatingRepository;
import com.movie.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RatingService {


    private final UserService userService;
    private final MovieService movieService;
    private final UserRepository userRepos;
    private RatingRepository repos;

    public RatingService(UserService userService, MovieService movieService, RatingRepository repos, UserRepository userRepos) {
        this.userService = userService;
        this.movieService = movieService;
        this.repos = repos;
        this.userRepos = userRepos;
    }


    //Get the user's rated movies
    public Set<MovieDto> getUserRatedMovies(Long userId) {
        User existUser = userRepos.findById(userId).orElse(null);

        if(existUser != null) {
            //Retrieve the user's rated movie and map them to MovieDto
            Set<MovieDto> ratedMovies = existUser.getMoviesRated()
                    .stream()
                    .map(movieService:: movieDto)
                    .collect(Collectors.toSet());
            return ratedMovies;
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }



    // Rating DTO Mapper
    public RatingDto ratingDto(Rating rating) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.id = rating.getId();

        // Check if user is not null before accessing its properties
        if (rating.getUser() != null) {
            ratingDto.userDto = userService.userDto(rating.getUser());
        }

        ratingDto.movieId = rating.getMovie().getId();
        ratingDto.rating = rating.getRating();
        return ratingDto;
    }

    // DTO To Rating mapper
    public Rating dtoToRating(RatingDto ratingDto) {
        Rating rating = new Rating();
        // Ensure userDto is not null before accessing its properties
        if (ratingDto.user != null) {
            rating.setUser(userService.dtoToUser(ratingDto.userDto));
        }
        rating.setMovie(movieService.getMovieById(ratingDto.movieId));
        rating.setRating(ratingDto.rating);
        return rating;

    }
}

