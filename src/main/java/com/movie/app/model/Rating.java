package com.movie.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")

public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


    @Column
    private int rating;


    public Rating() {
    }

    public Rating(User user, Movie movie, int rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getRatingScore() {
        return rating;
    }

    public void setRatingScore(int ratingScore) {
        this.rating = ratingScore;
    }
}
