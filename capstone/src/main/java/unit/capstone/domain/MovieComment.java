package unit.capstone.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MovieComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_comment_id")
    private Long id;
    private String comment;
    private LocalDateTime createDate;

    // 반대쪽 위치에서 @OnetoMany를 사용할 것에 대한 당위성 체크
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;


    protected MovieComment() {

    }


    public MovieComment(String comment, LocalDateTime createDate, Member member, Movie movie) {
        this.comment = comment;
        this.createDate = createDate;
        this.member = member;
        this.movie = movie;
    }

    //Getter
    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Movie getMovie() {
        return movie;
    }
}
