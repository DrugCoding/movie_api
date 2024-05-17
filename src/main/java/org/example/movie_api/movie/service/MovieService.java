package org.example.movie_api.movie.service;

import lombok.AllArgsConstructor;
import org.example.movie_api.movie.dto.MovieListResponse;
import org.example.movie_api.movie.dto.MovieSearchDto;
import org.example.movie_api.movie.entity.Movie;
import org.example.movie_api.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class MovieService {

    private final WebClient webClient;
    private final MovieRepository movieRepository;


//    // movie table에 id가 장르명으로 들어가게
//    private void loadGenreMap() {
//        genreMap = genreRepository
//                .findAll()
//                .stream()
//                .collect(Collectors.toMap(Genre::getId, Genre::getName));
//    }

    public String savePopularMovies() {
        // movieRepository에서 모든 영화 목록 조회, 결과에서 각 영화의 ID추출 set<Long>형태로 변환후 inMovieData로 선언
        Set<Long> inMovieData = new HashSet<>(movieRepository.findAll()
                .stream()
                .map(Movie::getId)
                .collect(Collectors.toList()));
        // 1개가 넘어가는 여러개의 리스트
        // 여러 데이터를 비동기적으로 저장하고 처리할 때 사용
        Flux
                // param(page)에 이용 될 정보 1~100까지 숫자 생성
                .range(1, 100)
                // 각 페이지번호에 비동기적으로 GET요청 (mapper = page)
                .flatMap(page -> webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder.path("/movie/popular")
                                .queryParam("language", "ko")
                                .queryParam("page", page)
                                .build())
                        // http에 요청하는 메소드 (mono 또는 flux 반환)
                        .retrieve()
                        // 여러데이터를 하나의 객체로 묶고 있어서 Mono
                        .bodyToMono(MovieListResponse.class)
                        .map(MovieListResponse::getResults))
                // Flux로 이미 여러개이기 때문에 (Flux=flatMap, Mono=flatMapMany
                .flatMap(Flux::fromIterable)
                // 가져온 데이터에 조회하고있는(movieDto.getId) 것이 없다면, 진행중인 movieDto 다음단계로 진행
                .filter(movieDto -> !inMovieData.contains(movieDto.getId()))
                // movieDto에서 데이터를 추출하여 {}안의 과정을 반복 (map 입력받아 다른형태로 변환하는 함수, 원본데이터 변경하지 않고 새로운데이터 생성, 여러번사용하여 순차적으로적용 가능)
                .map(movieDto -> {
                    Movie movie = new Movie();
                    String genre_ids = movieDto.getGenre_ids().stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(","));
                    movie.setGenre_ids(genre_ids);
                    movie.setId(movieDto.getId());
                    movie.setTitle(movieDto.getTitle());
                    movie.setOriginal_title(movieDto.getOriginal_title());
                    movie.setRelease_date(movieDto.getRelease_date());
                    movie.setPoster_path(movieDto.getPoster_path());

                    String overview = movieDto.getOverview() != null ? movieDto.getOverview() : "데이터가 없습니다";
                    if (overview.length() > 255) {
                        overview = overview.substring(0, 249) + "...";
                    }
                    movie.setOverview(overview);

                    return movie;

                })
                .collectList()
                .doOnNext(movieRepository::saveAll)
                .block();

        return "popular movies 저장완료";

    }

    public String saveNowPlayingMovies() {
        // movieRepository에서 모든 영화 목록 조회, 결과에서 각 영화의 ID추출 set<Long>형태로 변환후 inMovieData로 선언
        Set<Long> inMovieData = new HashSet<>(movieRepository.findAll()
                .stream()
                .map(Movie::getId)
                .collect(Collectors.toList()));
        // 1개가 넘어가는 여러개의 리스트
        Flux
                // param(page)에 이용 될 정보 1~100까지 숫자 생성
                .range(1, 100)
                // 각 페이지번호에 비동기적으로 GET요청 (mapper = page)
                .flatMap(page -> webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder.path("/movie/now_playing")
                                .queryParam("language", "ko")
                                .queryParam("page", page)
                                .build())
                        // retrieve 메소드 서버로부터 응답을 가져옴
                        .retrieve()
                        .bodyToMono(MovieListResponse.class)
                        .map(MovieListResponse::getResults))
                .flatMap(Flux::fromIterable)
                // 가져온 데이터에 조회하고있는(movieDto.getId) 것이 없다면, 진행중인 movieDto 다음단계로 진행
                .filter(movieDto -> !inMovieData.contains(movieDto.getId()))
                .map(movieDto -> {
                    Movie movie = new Movie();
                    String genre_ids = movieDto.getGenre_ids().stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(","));
                    movie.setGenre_ids(genre_ids);
                    movie.setId(movieDto.getId());
                    movie.setTitle(movieDto.getTitle());
                    movie.setOriginal_title(movieDto.getOriginal_title());
                    movie.setRelease_date(movieDto.getRelease_date());
                    movie.setPoster_path(movieDto.getPoster_path());

                    String overview = movieDto.getOverview() != null ? movieDto.getOverview() : "데이터가 없습니다";
                    if (overview.length() > 255) {
                        overview = overview.substring(0, 249) + "...";
                    }
                    movie.setOverview(overview);

                    return movie;

                })
                .collectList()
                .doOnNext(movieRepository::saveAll)
                .block();

        return "now playing movies 저장완료";

    }

    // 검색(제목:title) 영화 리스트 출력
    public List<MovieSearchDto> listMovies(String title) {
        // title을 포함하는 모든 영화를 조회
        return movieRepository.findByTitleContaining(title)
                // 조회결과를 스트림
                .stream()
                // movie를 MovieSearchDto 객체로 변환
                .map(movie -> new MovieSearchDto(movie.getId(), movie.getTitle(), movie.getRelease_date()))
                // 변환한 MovieSearchDto를 list로 수집
                .collect(Collectors.toList());

    }

    // 검색(아이디:id) 영화 출력
    public Movie oneMovie(Long id) {

        return movieRepository.findById(id).orElse(null);

    }
}

//// 바꾸기전 코드 : 코드의 재사용성과 유지 보수성은 이게 더 좋다. 바꾼코드는 직관성이 좋다. 이해하기 좋음.
//    private Movie convertToEntity(MovieDto movieDto) {
//        // 새로운 Movie는 Movie를 반환하고 movie라고 선언한다.
//        Movie movie = new Movie();
//        // genre_ids 저장
//        String genre_ids = movieDto.getGenre_ids().stream()
//                .map(Object::toString)
//                .collect(Collectors.joining(","));
//        movie.setGenre_ids(genre_ids);
//
//        movie.setId(movieDto.getId());
//        // movie에 movieDto의 title을 호출하여 담아서 저장한다.
//        movie.setTitle(movieDto.getTitle());
//        movie.setOriginal_title(movieDto.getOriginal_title());
//        movie.setRelease_date(movieDto.getRelease_date());
//        movie.setPoster_path(movieDto.getPoster_path());
//
//        // overview 저장 3항 연산자 사용
//        // movieDto의 Overview를 가져온 값이 null이 아니라면 getOverview를 호출한 값을 overview에 넣고 아니면 "데이터가 없습니다"를 넣는다.
//        String overview = movieDto.getOverview() != null ? movieDto.getOverview() : "데이터가 없습니다";
//        // String 타입 255제한
//        // overview의 길이가 255보다 길다면
//        if (overview.length() > 255) {
//            // substring(문자열 자르는 메소드) 0부터 249번째까지 잘라 ... 을 더해서 overview에 저장
//            overview = overview.substring(0, 249) + "...";
//        }
//        // overview를 담아 movie의 Overview에 저장
//        movie.setOverview(overview);
//
//        return movie;
//    }
//
//    // 인기영화 api 저장
//    // popular movie 데이터 호출
//    private Mono<List<MovieDto>> popularMoviePages(int page) {
//        return webClient
//                .get()
//                .uri(uriBuilder -> uriBuilder.path("/movie/popular")
//                        .queryParam("language", "ko")
//                        .queryParam("page", page)
//                        .build())
//                .retrieve()
//                .bodyToMono(MovieDto.MovieListResponse.class)
//                .map(MovieDto.MovieListResponse::getResults);
//    }
//
//    // popula rmovie 데이터 저장
//    public void savePopularMovies() {
//        // 1~100 페이지
//        Flux
//                .range(1, 100)
//                .flatMap(this::popularMoviePages)
//                .flatMap(Flux::fromIterable)
//                .map(this::convertToEntity)
//                .collectList()
//                .doOnNext(movieRepository::saveAll)
//                .block();
//    }
//
//    // 상영중인영화 페이지 저장
//    // now playing movie 데이터 호출
//    private Mono<List<MovieDto>> nowPlayingMovie(int page) {
//        return webClient
//                .get()
//                .uri(uriBuilder -> uriBuilder.path("/movie/now_playing")
//                        .queryParam("language", "ko")
//                        .queryParam("page", page)
//                        .build())
//                .retrieve()
//                .bodyToMono(MovieDto.MovieListResponse.class)
//                .map(MovieDto.MovieListResponse::getResults);
//    }
//
//    // now playing movie 데이터 저장
//    public void saveNowPlayingMovies() {
//        Set<Long> inMovieData = new HashSet<>(movieRepository.findAll()
//                .stream()
//                .map(Movie::getId)
//                .collect(Collectors.toList()));
//
//        Flux
//                .range(1, 100)
//                .flatMap(this::nowPlayingMovie)
//                .flatMap(Flux::fromIterable)
//                .filter(movieDto -> !inMovieData.contains(movieDto.getId()))
//                .map(this::convertToEntity)
//                .collectList()
//                .doOnNext(movieRepository::saveAll)
//                .block();
//    }