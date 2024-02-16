package com.movie.app.dto;

import com.movie.app.model.Movie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class UserDto {

    public Long id;

    @NotBlank(message = "Lege berichten zijn niet toegestaan")
    @Size(min=3, max=128, message = "Minimaal 3 en maximaal 128 karakters toegestaan")
    public String username;


    public String password;
    @NotBlank(message = "Lege berichten zijn niet toegestaan")

    public String email;
    @NotBlank(message = "Lege berichten zijn niet toegestaan")

    public String address;

    public String profileUrl;

    public Set<Movie> favoriteMovie;
    public Set<Movie> moviesSeen;
    public Set<Movie> moviesRated;



}
