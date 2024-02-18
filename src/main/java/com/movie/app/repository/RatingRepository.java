package com.movie.app.repository;


import com.movie.app.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMovie_Id(Long movieId);
}
