package org.example.movie_api.movie.service;

import org.example.movie_api.movie.dto.MovieSearchDto;
import org.example.movie_api.movie.entity.Movie;

import java.util.List;


public interface MovieService {

    String savePopularMovies();
    // Popular Movies 저장

    String saveNowPlayingMovies();
    // NowPlaying 저장

    List<MovieSearchDto> listMovies(String title);
    // 영화검색 (title 로)

    Movie oneMovie(Long id);
    // 영화디테일 (id 로)

}