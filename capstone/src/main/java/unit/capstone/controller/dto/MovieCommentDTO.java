package unit.capstone.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieCommentDTO {

    private List<String> comments;
    private int totalPages;

    public MovieCommentDTO(List<String> comments, int totalPages) {
        this.comments = comments;
        this.totalPages = totalPages;
    }
}
