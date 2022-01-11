package project.olineworkout.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.olineworkout.domain.entity.user.Gender;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.entity.user.UserRole;
import project.olineworkout.repository.user.UserRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    /**
    * address, weight, height는 아직 안했음. User Entity Builder에 추가 필요
    * */
    @Test
    public void 유저_데이터_생성_테스트(){

        IntStream.rangeClosed(1,10).forEach(k -> {
            User user = User.builder()
                    .identity("id_"+k)
                    .password("password_"+k)
                    .name("user_"+k)
                    .userRole(UserRole.ROLE_USER)
                    .gender(Gender.MALE)
                    .birthDay("000000")
                    .phone("010-0000-0000")
                    .build();

            System.out.println(userRepository.save(user));
        });

    }

    /**
     * 수정날짜 업데이트 되는지 확인 + 데이터 수정 되는지 확인
     * */
    @Test
    public void 유저_데이터_수정_테스트(){

        Optional<User> result = userRepository.findById(10L);

        if(result.isPresent()){
            User user = result.get();

            user.updatePassword("updatePassword_10");

            userRepository.save(user);
        }

    }
}
