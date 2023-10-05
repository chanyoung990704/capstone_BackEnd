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


    @GetMapping("/api/movie/{id}")
    public String findMovieById(@PathVariable Long id) {
        Movie movieById = movieService.findMovieById(id);
        return movieById.getTitle();
    }


    @GetMapping("/autocomplete/search")
    public List<Movie> autoCompleteSearch(@RequestParam String prefix) {
        return movieService.autoComplete(prefix);
    }


}
