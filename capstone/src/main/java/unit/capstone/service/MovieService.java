package unit.capstone.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.Movie;
import unit.capstone.exception.movie.NotFoundMovieException;
import unit.capstone.repository.movie.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {


    private final MovieRepository movieRepository;

    //movieID(PK)를 이용한 엔티티 찾기
    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).get();
    }


    //tmdb 영화 ID를 이용해 DB에 영화가 들어있는지 확인
    public Movie findByTmdbId(Long tmdbId) {
        Optional<Movie> byId = movieRepository.findByTmdbId(tmdbId);
        Movie movie = byId.orElseThrow(() -> new NotFoundMovieException("영화 ID 존재하지 않아요"));
        return movie;
    }


    // 검색어 자동 완성
    @Cacheable(value = "autoCompleteCache", key = "#prefix")
    public List<Movie> autoComplete(String prefix) {
        List<Movie> byTitleStartsWith = movieRepository.findByTitleStartsWith(prefix);

        List<Movie> first10Movies = byTitleStartsWith.stream()
                .limit(10)
                .collect(Collectors.toList());

        return first10Movies;
    }



}
