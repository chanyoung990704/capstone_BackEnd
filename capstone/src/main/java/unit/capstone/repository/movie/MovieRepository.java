package unit.capstone.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import unit.capstone.domain.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleStartsWith(String prefix);

}
