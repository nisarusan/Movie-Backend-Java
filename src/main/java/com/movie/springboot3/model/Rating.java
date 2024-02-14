package com.movie.springboot3.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Rating {

    @Id
    @GeneratedValue
    Long id;
    double ratingScore;



}
