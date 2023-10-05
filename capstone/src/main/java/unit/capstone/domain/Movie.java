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


    //getter

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
