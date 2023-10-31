package unit.capstone.domain;

import jakarta.persistence.*;

@Entity
public class RecommendedMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommended_movie_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // 생성자 통한 Setter 기능
    // 기본 생성자 protected 생성 방지
    protected RecommendedMovie() {

    }

    // Member클래스에서 Cascade 상속
    public RecommendedMovie(Member member, Movie movie) {
        setMember(member);
        this.movie = movie;
    }

    //연관관계 세팅
    public void setMember(Member member) {
        this.member = member;
        member.getRecommendedMovies().add(this);
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
