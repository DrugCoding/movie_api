package org.example.movie_api.movie.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Movie {

    @Id
    private Long id;
    private String genre_ids;
    private String title;
    private String original_title;
    private String release_date;
    private String poster_path;
    private String overview;

}
