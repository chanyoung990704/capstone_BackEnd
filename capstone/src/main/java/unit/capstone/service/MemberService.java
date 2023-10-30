package unit.capstone.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.LikeMovies;
import unit.capstone.domain.Member;
import unit.capstone.domain.Movie;
import unit.capstone.exception.member.NotFoundMemberException;
import unit.capstone.exception.movie.DuplicateLikedMovieException;
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

        //중복 이메일 검증 로직
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



    private boolean isEmailTaken(String email) {
        return memberRepository.existsByEmail(email);
    }



}
