package org.example.movie_api.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Movie {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genre_ids;
    private String title;
    private String original_title;
    private String release_date;
    private String poster_path;
    private String overview;

}
