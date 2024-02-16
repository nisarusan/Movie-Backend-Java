package com.movie.app.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
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


    @Column(name = "favorite_movie")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // adjust this based on your database schema
    private Set<Movie> favoriteMovie;

    @Column(name = "movie_seen")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<Movie> moviesSeen;
    @Column(name = "movies_rated")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<Movie> moviesRated;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Rating> ratings;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
