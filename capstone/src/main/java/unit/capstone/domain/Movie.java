package unit.capstone.domain;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "integer", name = "movie_id")
    private Long id;

    @Column(columnDefinition = "integer", name = "tmdb_id")
    private Long tmdbId;

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String genres;

    // 생성자 통한 Setter 기능
    // 기본 생성자 protected 생성 방지
    protected Movie() {

    }

    //Getter
    public Long getId() {
        return id;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenres() {
        return genres;
    }
}
