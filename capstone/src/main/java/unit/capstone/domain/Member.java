package unit.capstone.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    @SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", allocationSize = 1)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberAuthority authority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeMovie> likeMovies = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecommendedMovie> recommendedMovies = new ArrayList<>();

    // 생성자 통한 Setter 기능
    // 기본 생성자 protected 생성 방지
    protected Member() {

    }

    public Member(String username, String password, String email, MemberAuthority authority) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }
}
