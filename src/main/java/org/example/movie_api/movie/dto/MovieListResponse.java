package org.example.movie_api.movie.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieListResponse {

    @JsonProperty(value = "results")
    private List<MovieDto> movies;

}
