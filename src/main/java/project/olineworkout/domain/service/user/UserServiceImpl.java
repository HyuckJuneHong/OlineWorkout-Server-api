package project.olineworkout.domain.service.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.olineworkout.domain.dto.UserDto;
import project.olineworkout.domain.entity.user.User;
import project.olineworkout.domain.shared.ResponseFormat;
import project.olineworkout.infrastructure.exception.BadRequestException;
import project.olineworkout.infrastructure.exception.NotFoundException;
import project.olineworkout.repository.user.UserRepository;

import java.util.Optional;

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
    public String login(UserDto.LOGIN login){


        User user = userRepository.findByIdentity(login.getIdentity())
                .orElseThrow(() -> new NotFoundException("UserEntity"));

        if(!user.getPassword().equals(login.getPassword())){
                throw new BadRequestException("비밀번호 일치하지 않음");
        }

        return login.getIdentity();
    }

    /**
     * 회원 가입 서비스
     * @param create
     * @return
     */
    @Override
    public void signUp(UserDto.CREATE create){

        if(checkIdentity(create.getIdentity())){
            throw new BadRequestException("중복");
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

        User user = userRepository.findByIdentity(update.getIdentity())
                .orElseThrow(() -> new NotFoundException("UserEntity"));
        if(user == null){
            return ResponseFormat.fail("해당 아이디 존재하지 않음");
        }

        user.updateUser(update);
        userRepository.save(user);

        return ResponseFormat.ok();
    }


}
