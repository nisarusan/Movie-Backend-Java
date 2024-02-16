package com.movie.app.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "recommended_movies",
            joinColumns = @JoinColumn(name = "recommendation_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> recommendedMovies;

    // Constructors, getters, and setters...

    public Recommendation() {
    }

    public Recommendation(User user, Set<Movie> recommendedMovies) {
        this.user = user;
        this.recommendedMovies = recommendedMovies;
    }

    // Getters and Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Movie> getRecommendedMovies() {
        return recommendedMovies;
    }

    public void setRecommendedMovies(Set<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }
}
