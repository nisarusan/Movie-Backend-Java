package com.movie.app.repository;

import com.movie.app.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String title);
}
