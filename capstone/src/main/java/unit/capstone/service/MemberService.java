package unit.capstone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unit.capstone.domain.Member;
import unit.capstone.exception.member.NotFoundMemberException;
import unit.capstone.repository.member.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long registerMember(Member member) {
        validateDuplicateEmail(member.getEmail());
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException("Member not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email);
    }

    @Transactional(readOnly = true)
    public Member findMemberByUsername(String username) {
        return memberRepository.findMemberByUsername(username)
                .orElseThrow(() -> new NotFoundMemberException("Member not found with username: " + username));
    }

    public void clearRecommendedMovies(String email) {
        Member member = findMemberByEmail(email)
                .orElseThrow(() -> new NotFoundMemberException("Member not found with email: " + email));
        member.getRecommendedMovies().clear();
        memberRepository.save(member);
    }

    private void validateDuplicateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            // 중복 이메일에 대한 예외 만들어아 햠
            throw new NotFoundMemberException("Email already in use: " + email);
        }
    }
}
