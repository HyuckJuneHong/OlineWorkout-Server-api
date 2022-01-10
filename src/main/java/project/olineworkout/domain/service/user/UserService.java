package project.olineworkout.domain.service.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.olineworkout.domain.dto.UserDto;
import project.olineworkout.domain.shared.ResponseFormat;

public interface UserService {

    ResponseFormat signUp(UserDto.CREATE create);
    boolean checkIdentity(String identity);
}
