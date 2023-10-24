package unit.capstone.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tmdb")
public class Movie {

    @Id
    @Column(columnDefinition = "integer", name = "movie_id")
    private Long id;

    @Column(columnDefinition = "text")
    private String title;

    // 생성자 통한 Setter 기능
    // 기본 생성자 protected 생성 방지
    protected Movie() {

    }

    //Getter
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
