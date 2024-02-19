package com.movie.app.dto;

import com.movie.app.model.Genre;
import com.movie.app.model.Rating;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class MovieDto {
    public Long id;
    public String title;
    public String imageUrl;
    public String director;
    public LocalDate releaseDate;
    public int duration;
    public Set<Genre> genre;
    public String description;
    public double averageRating;
    public Set<Rating> ratings;


}
