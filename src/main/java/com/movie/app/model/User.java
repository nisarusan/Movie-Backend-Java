package com.movie.app.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String address;

    @Column(name = "profile_url")
    private String profileUrl;

//v1
    @Column(name = "favorite_movie")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorite_movie_id") // adjust this based on your database schema
    private Set<Movie> favoriteMovie = new HashSet<>();
    @Column(name = "movie_seen")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movies_seen_id")
    private Set<Movie> moviesSeen = new HashSet<>();
    @Column(name = "movies_rated")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movies_rated_id")
    private Set<Movie> moviesRated = new HashSet<>();

//    //v2
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL})
//    @JoinTable(
//            name = "favorite_movie",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "favorite_movie_id")
//    )
//    private Set<Movie> favoriteMovie = new HashSet<>();
//
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL})
//    @JoinTable(
//            name = "movie_seen",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "movies_seen_id")
//    )
//    private Set<Movie> moviesSeen = new HashSet<>();
//
//
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL})
//    @JoinTable(
//            name = "movies_rated",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "movies_rated_id")
//    )
//    private Set<Movie> moviesRated = new HashSet<>();



    @ManyToMany
    @JoinTable(
            name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_name"))
    private Set<Auth> authorities;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<>();

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

    public Set<Auth> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Auth> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
