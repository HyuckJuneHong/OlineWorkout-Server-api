package project.olineworkout.repository.member;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.olineworkout.domain.entity.member.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByIdentity(String identity);
    boolean existsByIdentityAndNameAndBirthDay(String identity, String name, String birthDay);

    Optional<Member> findByIdentity(String identity);
}
