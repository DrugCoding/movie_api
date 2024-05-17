package org.example.movie_api.genre.service;


import lombok.AllArgsConstructor;
import org.example.movie_api.genre.dto.GenreListResponse;
import org.example.movie_api.genre.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class GenreService {

    private final WebClient webClient;
    private final GenreRepository genreRepository;

    public String saveGenre() {
        // Mono 코드
        webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/genre/movie/list")
                        .queryParam("language", "ko")
                        .build())
                // http에 요청하는 메소드 (mono 또는 flux 반환)
                .retrieve()
                // 여러데이터를 하나의 객체로 묶고 있어서 Mono
                .bodyToMono(GenreListResponse.class)
                .map(GenreListResponse::getGenres)
                // 장르가 여러개 이기 때문에 Flux로 변환(Mono 라서)
                .flatMapMany(Flux::fromIterable)
                // 리스트로 수집
                .collectList()
                // 데이터베이스에 저장
                .doOnNext(genreRepository::saveAll)
                // 비동기 실행 대기
                .block();

        return "장르 데이터 저장 완료";

    }
}
//        // Flux.range() 로 시작. 한 페이지에 다 있기에 1,1 -> Mono로 바꾸면 좋아보임
//        Flux
//                .range(1, 1)
//                .flatMap(page -> webClient
//                        .get()
//                        .uri(uriBuilder -> uriBuilder.path("/genre/movie/list")
//                                .queryParam("language", "ko")
//                                .queryParam("page", page)
//                                .build())
//                        .retrieve()
//                        // json 형식으로 변환
//                        .bodyToMono(GenreListResponse.class)
//                        //
//                        .map(GenreListResponse::getGenres))
//                .flatMap(Flux::fromIterable)
//                .collectList()
//                .doOnNext(genreRepository::saveAll)
//                .block();


// 변경 전 코드
//        List<GenreDto> genres = webClient.get()
//                .uri(uriBuilder -> uriBuilder.path("/genre/movie/list")
//                        .queryParam("language", "ko")
//                        .build())
//                .retrieve()
//                .bodyToMono(GenreListResponse.class)
//                .block()
//                .getGenres();
//
//        for (GenreDto genre : genres) {
//            Genre genre1 = new Genre();
//            genre1.setId(genre.getId());
//            genre1.setName(genre.getName());
//            genreRepository.save(genre1);
//        }
//
//        return "genre 데이터 저장완료";
//
//    }
//}