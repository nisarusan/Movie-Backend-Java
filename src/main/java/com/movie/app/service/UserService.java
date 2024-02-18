package com.movie.app.service;


import com.movie.app.dto.MovieDto;
import com.movie.app.dto.UserDto;
import com.movie.app.model.Authority;
import com.movie.app.model.Movie;
import com.movie.app.model.User;
import com.movie.app.repository.AuthRepository;
import com.movie.app.repository.MovieRepository;
import com.movie.app.repository.UserRepository;
import com.movie.app.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository repos;

    //Need second injector for roles
    private final AuthRepository rolerepos;
    private final MovieService movieService;

    public UserService(UserRepository repos, AuthRepository rolerepos, MovieService movieService) {
        this.repos = repos;
        this.rolerepos = rolerepos;
        this.movieService = movieService;

    }


    //create User
    public UserDto createUser(UserDto userDto) {
        Authority userRole = new Authority();
        userRole.setName("ROLE_USER"); // Set the identifier value
        rolerepos.save(userRole);


        User user = dtoToUser(userDto);
        user.setUsername(userDto.username);
        user.setPassword(userDto.password);
        user.setEmail(userDto.email);
        user.setProfileUrl(userDto.profileUrl);
        user.setAddress(userDto.address);
        user.setFavoriteMovie(userDto.favoriteMovie);
        user.setMoviesRated(userDto.moviesRated);
        user.setMoviesSeen(userDto.moviesSeen);

        // Set the roles directly from the userRole
        user.setAuthorities(Collections.singleton(userRole));

        repos.save(user);
        userDto.id = user.getId();
        return userDto;
    }

//getUser By Id
    public UserDto getUserById(Long userId) {
        // Retrieve the user entity by ID from the repository
        Optional<User> optionalUser = repos.findById(userId);

        // Map the user entity to a UserDto if it exists
        return optionalUser.map(this::userDto).orElse(null);
    }

    // Set favorite movies for a user
    public UserDto setFavoriteMovies(Long userId, Set<Long> favoriteMovies) {
        User existingUser = repos.findById(userId).orElse(null);

        if (existingUser != null) {
            // Convert movie IDs to Movie entities
            Set<Movie> favoriteMovieEntities = favoriteMovies.stream()
                    .map(movieService::getMovieById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

            existingUser.setFavoriteMovie(favoriteMovieEntities);
            repos.save(existingUser);

            return userDto(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
    //User Profile by ID

    //user DTO MAPPER
    public UserDto userDto(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.address = user.getAddress();
        userDto.email = user.getEmail();
        userDto.profileUrl = user.getProfileUrl();
        userDto.moviesSeen = user.getMoviesSeen();
        userDto.moviesRated = user.getMoviesRated();
        userDto.favoriteMovie = user.getFavoriteMovie();
        userDto.authorities = user.getAuthorities();
        return userDto;
    }

    // DTO TO USER OBJECT MAPPING
    public User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username);
        user.setPassword(userDto.password);
        user.setAddress(userDto.address);
        user.setEmail(userDto.email);
        user.setProfileUrl(userDto.profileUrl);
        user.setMoviesRated(userDto.moviesRated);
        user.setFavoriteMovie(userDto.favoriteMovie);
        user.setMoviesSeen(userDto.moviesSeen);
        return user;
    }
}
