package org.example.movie_api.service;

import jakarta.transaction.Transactional;
import org.example.movie_api.dto.MovieDto;
import org.example.movie_api.entity.Movie;
import org.example.movie_api.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final RestTemplate restTemplate;

//    private final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OTAyMDA3MzBkNDE0Mjg3ZTJiZjlmOWYxNDdiYWNhNyIsInN1YiI6IjYyZGEyODI1YjM5ZTM1MDA2NzY3NTA3NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.E9Xk9fdU9pIRV2hkhURuDxEzZok2vEcjtVY5FWScrIQ";
//    private final String URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=en-US&page=1";

    public MovieService(MovieRepository movieRepository, RestTemplate restTemplate) {
        this.movieRepository = movieRepository;
        this.restTemplate = restTemplate;
    }


//    public void fetchAndStoreMovies() {
//        MovieDto[] movies = restTemplate.getForObject(URL, MovieDto[].class);
//        if (movies != null) {
//            for (MovieDto movieDto : movies) {
//                Movie movie = convertToEntity(movieDto);
//                movieRepository.save(movie);
//            }
//        }
//    }

    private Movie convertToEntity(MovieDto dto) {
        Movie movie = new Movie();
        movie.setGenre_id(dto.getGenre_id());
        movie.setTitle(dto.getTitle());
        movie.setOriginal_title(dto.getOriginal_title());
        movie.setRelease_date(dto.getRelease_date());
        movie.setPoster_path(dto.getPoster_path());
        movie.setOverview(dto.getOverview());
        return movie;
    }

    //    @Transactional
//    public void fetchAndStoreMovies() {
//        try {
//            MovieDto[] response = restTemplate.getForObject(URL, MovieDto[].class);
//            Optional.ofNullable(response)
//                    .ifPresent(movies -> Arrays.stream(movies).forEach(movieDto -> {
//                        Movie movie = convertToEntity(movieDto);
//                        movieRepository.save(movie);
//                    }));
//        } catch (RestClientException e) {
//            // 외부 API 호출 실패
//            System.err.println("Failed to fetch data from TMDB: " + e.getMessage());
//            throw new RuntimeException("Failed to fetch data from TMDB", e);
//        } catch (Exception e) {
//            // 데이터베이스 오류 또는 기타 오류
//            System.err.println("An error occurred: " + e.getMessage());
//            throw new RuntimeException("Error during database operation", e);
//        }
//    }
}
