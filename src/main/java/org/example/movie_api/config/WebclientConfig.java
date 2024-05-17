package org.example.movie_api.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


// 이렇게 하면 다른 부분에서도 재사용 할 수 있고, 코드의 관리가 용이해 짐
@Configuration
public class WebclientConfig {

    // 값(불러온다 properties에서)
    @Value("${moviedb.api.key}")
    private String apiKey;

    // Bean 스프링컨테이너에 의해 관리될 객체 생성, WebClient타입의 빈 생성, 스프링 컨텍스트에 등록.
    @Bean
    // WebClient.Builder를 사용해여 WebClient 인스턴스를 구성.
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("Authorization", "Bearer" + apiKey)
                .build();
    }

}
