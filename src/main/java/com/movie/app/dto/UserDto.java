package com.movie.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.app.model.Auth;
import com.movie.app.model.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;
//@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonIgnore
    public Set<Auth> authorities;

    @JsonIgnore
    public Set<Movie> favoriteMovie;

    @JsonIgnore
    public Set<Movie> moviesSeen;

    @JsonIgnore
    public Set<Movie> moviesRated;



}
