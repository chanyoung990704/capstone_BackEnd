package unit.capstone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unit.capstone.domain.Movie;
import unit.capstone.service.MovieService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieApiController {


    private final MovieService movieService;

    // 임시 테스트용 나중에 필요하다면 ResponseEntity 타입으로 수정
    @GetMapping("/api/movie/{id}")
    public String findMovieById(@PathVariable Long id) {
        Movie movieById = movieService.findMovieById(id);
        return movieById.getTitle();
    }


    // 영화 제목 검색 자동완성을 위한 컨트롤러
    @GetMapping("/autocomplete/search")
    public List<Movie> autoCompleteSearch(@RequestParam String prefix) {
        return movieService.autoComplete(prefix);
    }


}
