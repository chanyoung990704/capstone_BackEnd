package unit.capstone.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.Movie;
import unit.capstone.repository.movie.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {


    private final MovieRepository movieRepository;


    public Movie findMovieById(Long movieId) {
        Optional<Movie> byId = movieRepository.findById(movieId);
        Movie movie = byId.orElseThrow(() -> new RuntimeException("영화 ID 존재하지 않아요"));
        return movie;
    }


    public List<Movie> autoComplete(String prefix) {
        List<Movie> byKeywords = movieRepository.findByTitleStartsWith(prefix);
        return byKeywords;
    }



}
