package unit.capstone.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import unit.capstone.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    boolean existsByEmail(String email);
    Optional<Member> findMemberByUsername(String username);
    Optional<Member> findMemberByEmail(String email);
}
