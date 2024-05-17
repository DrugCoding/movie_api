package org.example.movie_api.movie.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 생성자를 자동으로 생성
@AllArgsConstructor
public class MovieSearchDto {

    private Long id;
    private String title;
    private String release_date;

}
