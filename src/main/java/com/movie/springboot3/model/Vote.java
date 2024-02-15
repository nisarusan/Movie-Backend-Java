package com.movie.springboot3.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "partij_stemmen")
    List<String> partijStemmen;

    public Long getId() {
        return id;
    }

    public List<String> getPartijStemmen() {
        return partijStemmen;
    }

    public void setPartijStemmen(List<String> partijStemmen) {
        this.partijStemmen = partijStemmen;
    }


}
