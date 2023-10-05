package unit.capstone.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.LikeMovies;
import unit.capstone.domain.Member;
import unit.capstone.domain.Movie;
import unit.capstone.exception.member.NotFoundMemberException;
import unit.capstone.repository.member.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long registerMember(Member member) {

        //검증 로직
        if (isEmailTaken(member.getEmail())) {
            throw new UsernameNotFoundException("This Email is Using....");
        }

        memberRepository.save(member);
        return member.getId();

    }


    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        Optional<Member> isMember = memberRepository.findById(id);
        Member member = isMember.orElseThrow(() -> new NotFoundMemberException("No MemberID!!!"));
        return member;
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email);
    }


    @Transactional(readOnly = true)
    public Member findMemberByUsername(String username) {
        Optional<Member> isMember = memberRepository.findMemberByUsername(username);
        Member member = isMember.orElseThrow(() -> new NotFoundMemberException("No Member Using this Username!!"));
        return member;
    }

    // 영화 좋아요 기능 컨트롤러에서 Member 불러옴 -> Cascade 사용
    public void movieLike(Member member, Movie movie) {


        // 이미 좋아요로 저장된 영화라면 오류 호출
        if(memberRepository.isSavedLikeMovie(member.getId(), movie.getId()))
            throw new RuntimeException("이미 좋아요로 등록한 영화");
        else
            new LikeMovies(member, movie);

    }


    private boolean isEmailTaken(String email) {
        return memberRepository.existsByEmail(email);
    }



}
