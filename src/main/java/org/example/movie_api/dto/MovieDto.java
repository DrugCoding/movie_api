package org.example.movie_api.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    private String genre_id;
    private String title;
    private String original_title;
    private String release_date;
    private String poster_path;
    private String overview;

}
