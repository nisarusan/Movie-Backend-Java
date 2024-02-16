package com.movie.app.service;


import com.movie.app.dto.UserDto;
import com.movie.app.model.Role;
import com.movie.app.model.User;
import com.movie.app.repository.MovieRepository;
import com.movie.app.repository.RoleRepository;
import com.movie.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repos;

    //Need second injector for roles
    private final RoleRepository rolerepos;
    public UserService(UserRepository repos, RoleRepository rolerepos) {
        this.repos = repos;
        this.rolerepos = rolerepos;
    }



    //create User
    public UserDto createUser(UserDto userDto) {
        Role userRole = new Role("USER");
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
        user.setRoles(Collections.singleton(userRole));

        repos.save(user);
        userDto.id = user.getId();
        return userDto;
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
