package org.example.movie_api.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MovieDto {

    private Long id;
    private List<Integer> genre_ids;
    private String title;
    private String original_title;
    private String release_date;
    private String poster_path;
    private String overview;

    public static class MovieListResponse {

        private List<MovieDto> results;

        public List<MovieDto> getResults() {
            return results;
        }
    }
}