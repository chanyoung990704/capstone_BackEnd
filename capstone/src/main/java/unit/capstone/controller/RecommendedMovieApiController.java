package unit.capstone.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import unit.capstone.controller.dto.MovieRecommendRequestDTO;
import unit.capstone.controller.dto.MovieRecommendedFlaskDTO;
import unit.capstone.domain.Member;
import unit.capstone.service.MemberService;
import unit.capstone.service.RecommendedMovieService;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class RecommendedMovieApiController {
    private final RecommendedMovieService recommendedMovieService;
    private final MemberService memberService;

    // 추천페이지에서 양식 받아옴
    @PostMapping("/api/movies/recommendations")
    public ResponseEntity<?> recommendMovies(Authentication authentication, @RequestBody MovieRecommendRequestDTO request) {
        try {
            // 사용자 정보 가져오기
            String email = authentication.getName();
            Member member = memberService.findMemberByEmail(email).get();

            // Flask 서버에 요청을 전송
            MovieRecommendedFlaskDTO requestFlask = new MovieRecommendedFlaskDTO(member.getId(), request);
            List<Long> recommendedMovies = sendToFlask(requestFlask);

            // 사용자의 추천리스트 초기화 및 업데이트
            recommendedMovieService.clearRecommendedMovieList(member);
            recommendedMovieService.updateRecommendedMovies(member, recommendedMovies);

            return ResponseEntity.ok("추천 영화 처리 완료");
        } catch (Exception e) {
            // 에러 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
        }
    }

    private List<Long> sendToFlask(MovieRecommendedFlaskDTO dto) throws JsonProcessingException {
        String flaskUrl = "http://172.31.47.11:5000/recommend"; // Flask 서버 URL

        // JSON으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);

        // 헤더 설정 및 REST API 호출
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, request, String.class);

        // 응답 본문(JSON) 파싱
        String responseBody = response.getBody();
        Map<String, List<Long>> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, List<Long>>>() {});
        return responseMap.getOrDefault("recommendedMovies", Collections.emptyList());
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(30000); // 연결 시간 제한 설정 (예: 5초)
        factory.setReadTimeout(30000); // 읽기 시간 제한 설정 (예: 5초)
        return factory;
    }
}
