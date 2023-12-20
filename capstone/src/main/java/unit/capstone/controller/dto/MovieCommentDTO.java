package unit.capstone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class MovieCommentDTO {
    private List<String> comments;
    private int totalPages;
}
