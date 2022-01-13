package project.olineworkout.domain.service.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.olineworkout.domain.dto.UserDto;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.shared.ResponseFormat;
import project.olineworkout.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 로그인 서비스
     *        -> To Do... 시큐리티 토큰 적용 예정
     * @param login
     * @return
     */
    @Override
    public ResponseFormat login(UserDto.LOGIN login){

        User user = userRepository.findByIdentity(login.getIdentity());
        if(user == null){
            return ResponseFormat.fail("해당 아이디는 존재하지 않음");
        }
        if(!user.getPassword().equals(login.getPassword())){
            return ResponseFormat.fail("비밀번호가 일치하지 않음.");
        }

        return ResponseFormat.ok();
    }

    /**
     * 회원 가입 서비스
     * @param create
     * @return
     */
    @Override
    public ResponseFormat signUp(UserDto.CREATE create){

        if(checkIdentity(create.getIdentity())){
            return ResponseFormat.fail("아이디가 중복되었습니다.");
        }

        userRepository.save(User.builder()
                .identity(create.getIdentity())
                .password(create.getPassword())
                .name(create.getName())
                .gender(create.getGender())
                .birthDay(create.getBirthDay())
                .phone(create.getPhone())
                .userRole(create.getUserRole())
                .build());

        return ResponseFormat.ok();
    }

    /**
     * 아이디 중복 체크
     * @param identity
     * @return
     */
    @Override
    public boolean checkIdentity(String identity){
        return userRepository.existsByIdentity(identity);
    }


    /**
     * 고객 정보 수정 서비스
     *     ->To Do... 시큐리티 적용하면 아래 처럼 수정
     *         UserEntity userEntity = UserThreadLocal.get();
     *         userEntity.update(update);
     *         userRepository.save(userEntity);
     * @param update
     * @return
     */
    @Override
    public ResponseFormat updateUser(UserDto.UPDATE update){

        User user = userRepository.findByIdentity(update.getIdentity());
        if(user == null){
            return ResponseFormat.fail("해당 아이디 존재하지 않음");
        }

        user.updateUser(update);
        userRepository.save(user);

        return ResponseFormat.ok();
    }


}
