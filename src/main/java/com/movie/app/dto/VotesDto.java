package com.movie.app.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class VotesDto {

    public Long id;
    @NotBlank
   public List<String> partijStemmen;


}
