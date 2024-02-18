package com.movie.app.repository;

import com.movie.app.model.Movie;
import com.movie.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("UPDATE User u SET u.favoriteMovie = :favoriteMovies WHERE u.id = :userId")
    void setFavoriteMovie(@Param("userId") Long userId, @Param("favoriteMovies") Set<Movie> favoriteMovies);
}
