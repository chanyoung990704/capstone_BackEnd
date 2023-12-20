package unit.capstone.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class MovieComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_comment_id")
    private Long id;
    private String comment;
    private LocalDateTime createDate;
    //rating 점수 1점에서 5점 정수형으로
    //ratiing을 입력하지 않았으면 기본값 -1
    @Column(columnDefinition = "int default -1")
    private int rating;

    // 반대쪽 위치에서 @OnetoMany를 사용할 것에 대한 당위성 체크
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // 생성자 통한 Setter 기능
    // 기본 생성자 protected 생성 방지
    protected MovieComment() {

    }

    //rating이 입력되지 않은 경우의 생성자
    public MovieComment(String comment, LocalDateTime createDate, Member member, Movie movie) {
        this.comment = comment;
        this.createDate = createDate;
        this.member = member;
        this.movie = movie;
    }

    //rating이 입력된 경우의 생성자
    public MovieComment(String comment, LocalDateTime createDate, int rating, Member member, Movie movie) {
        this.comment = comment;
        this.createDate = createDate;
        this.rating = rating;
        this.member = member;
        this.movie = movie;
    }
}
