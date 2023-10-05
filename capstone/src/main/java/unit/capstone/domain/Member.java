package unit.capstone.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberAuthority authority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<LikeMovies> likeMovies = new ArrayList<>();


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


    //Getter


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public MemberAuthority getAuthority() {
        return authority;
    }

    public List<LikeMovies> getLikeMovies() {
        return likeMovies;
    }
}
