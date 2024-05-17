package org.example.movie_api.movie.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieListResponse {

    private List<MovieDto> results;

}
