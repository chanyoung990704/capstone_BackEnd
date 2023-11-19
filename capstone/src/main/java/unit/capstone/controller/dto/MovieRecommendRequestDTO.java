package unit.capstone.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieRecommendRequestDTO {

    private String selectionType;
    private List<String> genres;
    private String director;
    private List<Integer> userLikes; // userLikes는 영화 ID의 리스트라고 가정
}
