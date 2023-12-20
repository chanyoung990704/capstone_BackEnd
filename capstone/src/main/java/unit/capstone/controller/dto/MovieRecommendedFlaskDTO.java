package unit.capstone.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class MovieRecommendedFlaskDTO {
    private Long memberId; // memberId 필드 추가
    private String selectionType;
    private List<String> genres;
    private String director;
    private List<Integer> userLikes; // userLikes는 영화 ID의 리스트라고 가정

    public MovieRecommendedFlaskDTO(Long memberId, MovieRecommendRequestDTO request) {
        this.memberId = memberId;
        this.selectionType = request.getSelectionType();
        this.genres = request.getGenres();
        this.director = request.getDirector();
        this.userLikes = request.getUserLikes();
    }
}
