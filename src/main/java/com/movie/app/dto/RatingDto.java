package com.movie.app.dto;

import com.movie.app.model.Movie;
import com.movie.app.model.User;
import jakarta.persistence.*;

public class RatingDto {

    public Long id;
    public Long userId;
    public Long movieId;
    public double rating;
    public User user;
    public UserDto userDto;
}
