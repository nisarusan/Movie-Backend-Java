package com.movie.app.dto;

import com.movie.app.model.Movie;
import jakarta.persistence.*;

import java.util.Set;

public class UserDto {
    public Long id;
    public String username;
    public String email;
    public String address;
    public String profileUrl;
    public Set<Movie> favoriteMovie;
    public Set<Movie> moviesSeen;
    public Set<Movie> moviesRated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Set<Movie> getFavoriteMovie() {
        return favoriteMovie;
    }

    public void setFavoriteMovie(Set<Movie> favoriteMovie) {
        this.favoriteMovie = favoriteMovie;
    }

    public Set<Movie> getMoviesSeen() {
        return moviesSeen;
    }

    public void setMoviesSeen(Set<Movie> moviesSeen) {
        this.moviesSeen = moviesSeen;
    }

    public Set<Movie> getMoviesRated() {
        return moviesRated;
    }

    public void setMoviesRated(Set<Movie> moviesRated) {
        this.moviesRated = moviesRated;
    }
}
