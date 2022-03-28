package project.olineworkout.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.olineworkout.domain.entity.member.Gender;
import project.olineworkout.domain.entity.member.Member;
import project.olineworkout.domain.entity.member.MemberRole;
import project.olineworkout.repository.member.MemberRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    /**
    * address, weight, height는 아직 안했음. User Entity Builder에 추가 필요
    * */
    @Test
    public void 유저_데이터_생성_테스트(){

        IntStream.rangeClosed(1,10).forEach(k -> {
            Member member = Member.builder()
                    .identity("id_"+k)
                    .password("password_"+k)
                    .name("user_"+k)
                    .memberRole(MemberRole.ROLE_MEMBER)
                    .gender(Gender.MALE)
                    .birthDay("000000")
                    .phone("010-0000-0000")
                    .build();

            System.out.println(memberRepository.save(member));
        });

    }

    /**
     * 수정날짜 업데이트 되는지 확인 + 데이터 수정 되는지 확인
     * */
    @Test
    public void 유저_데이터_수정_테스트(){

        Optional<Member> result = memberRepository.findById(10L);

        if(result.isPresent()){
            Member member = result.get();

            member.updatePassword("updatePassword_10");

            memberRepository.save(member);
        }

    }
}
