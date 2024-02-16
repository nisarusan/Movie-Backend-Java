package com.movie.app.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class RoleDto {


    private Long id;


   @NotBlank
    private String name;

}
