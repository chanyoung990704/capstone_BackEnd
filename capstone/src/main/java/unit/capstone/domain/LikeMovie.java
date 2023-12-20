package unit.capstone.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class LikeMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_movie_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // 생성자 통한 Setter 기능
    // 기본 생성자 protected 생성 방지
    protected LikeMovie(){

    }

    public LikeMovie(Member member, Movie movie) {
        setMember(member);
        this.movie = movie;
    }

    // 엔티티 연관관계 설정
    public void setMember(Member member) {
        this.member = member;
        if(!member.getLikeMovies().contains(this)) {
            member.getLikeMovies().add(this);
        }
    }

    public void removeMember() {
        if(this.member != null) {
            this.member.getLikeMovies().remove(this);
        }
        this.member = null;
    }
}
