package unit.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.MovieComment;
import unit.capstone.repository.comment.MovieCommentRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieCommentService {

    private final MovieCommentRepository movieCommentRepository;

    public void saveComment(MovieComment movieComment) {
        movieCommentRepository.save(movieComment);
    }


}
