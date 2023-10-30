package unit.capstone.repository.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import unit.capstone.domain.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, PagingAndSortingRepository<Movie, Long> {

    List<Movie> findByTitleStartsWith(String prefix);

    // extends 부분 PagingAndSortingRepository은 페이징 테스트 해보기 위해 추가함.
    // 삭제해도 무관. 쿼리가 검색 커리 + count쿼리 총 2개가 발생해 사용하지 않음.
    Page<Movie> findFirst10ByTitleStartsWith(String prefix, Pageable pageable);
}
