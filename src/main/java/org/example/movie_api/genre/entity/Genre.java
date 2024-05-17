package org.example.movie_api.genre.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name = "genre")
public class Genre {

    @Id
    private int id;
    private String name;

}
