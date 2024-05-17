package org.example.movie_api.movie.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MovieDto {

    private Long id;
    private List<Long> genre_ids;
    private String title;
    private String original_title;
    private String release_date;
    private String poster_path;
    private String overview;

}