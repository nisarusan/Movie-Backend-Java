package com.movie.springboot3.model;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String username;

    @Column
    String email;

    @Column
    String address;

    @Column(name = "profile_url")
    String profileUrl;



    @Column(name = "favorite_movie")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // adjust this based on your database schema
    Set<Movie> favoriteMovie;

    @Column(name = "movie_seen")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Set<Movie> moviesSeen;
    @Column(name = "movies_rated")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Set<Movie> moviesRated;

    public Long getId() {
        return id;
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
