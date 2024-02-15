package com.movie.springboot3.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class VotesDto {

    public Long id;
    @NotBlank
   public List<String> partijStemmen;


}
