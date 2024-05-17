package org.example.movie_api.genre.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.movie_api.genre.entity.Genre;

import java.util.List;


@Getter
@Setter
public class GenreListResponse {

    private List<Genre> genres;

}
