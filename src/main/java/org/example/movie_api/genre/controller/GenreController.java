package org.example.movie_api.genre.controller;


import lombok.AllArgsConstructor;
import org.example.movie_api.genre.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;


    // 예외처리 예상 :API에 장르 데이터가 없을 때


    @GetMapping("/save_genre")
    public String updateGenres() {

        return genreService.saveGenre();

    }
}
