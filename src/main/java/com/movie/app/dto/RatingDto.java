package com.movie.app.dto;

import com.movie.app.model.Movie;
import com.movie.app.model.User;
import jakarta.persistence.*;

public class RatingDto {

    private Long id;

    private User user;
    private Movie movie;

    private int rating;

}
