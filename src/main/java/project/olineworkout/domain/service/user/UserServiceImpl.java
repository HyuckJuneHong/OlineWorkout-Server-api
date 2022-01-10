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

    @Override
    public boolean checkIdentity(String identity){
        return userRepository.existsByIdentity(identity);
    }

}
