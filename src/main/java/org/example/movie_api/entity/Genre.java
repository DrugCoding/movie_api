package org.example.movie_api.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name = "genre")
public class Genre {

    @Id
    private int id;
    private String name;

//    public Genre(int id, String name) {
//        this.id = id;   // 이 줄이 인스턴스 변수 'id'에 값을 할당합니다.
//        this.name = name; // 이 줄이 인스턴스 변수 'name'에 값을 할당합니다.
//    }

}
