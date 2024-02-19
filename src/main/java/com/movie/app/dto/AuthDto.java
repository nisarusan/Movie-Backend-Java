package com.movie.app.dto;

import com.movie.app.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Set;

public class AuthDto {
        public  String name;
        public Set<User> users;

}
