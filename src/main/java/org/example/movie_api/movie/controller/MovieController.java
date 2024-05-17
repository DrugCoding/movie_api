package org.example.movie_api.movie.controller;


import lombok.AllArgsConstructor;
import org.example.movie_api.movie.dto.MovieSearchDto;
import org.example.movie_api.movie.entity.Movie;
import org.example.movie_api.movie.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;


    // api의 데이터가 없을 때, 검색하였는데 반환하는 값이 없을 때


    // popular movie 불러와서 db저장
    @GetMapping("/save_popular")
    public String popularMovies() {

        return movieService.savePopularMovies();

    }

    // now_playing movie 불러와서 db저장
    @GetMapping("/save_playing")
    public String nowPlayingMovie() {

        return movieService.saveNowPlayingMovies();

    }

    // 영화제목 검색하면 리스트로 반환
    @GetMapping("/search_list")
    public List<MovieSearchDto> searchMoviesList(@RequestParam String title) {

        return movieService.listMovies(title);

    }

    // 영화아이디로 검색시 한개의 영화 반환
    @GetMapping("/search_one/{id}")
    public Movie searchMovieOne(@PathVariable Long id) {

        return movieService.oneMovie(id);

    }
}
