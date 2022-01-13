package project.olineworkout.domain.service.user;


import project.olineworkout.domain.dto.UserDto;
import project.olineworkout.domain.shared.ResponseFormat;

public interface UserService {

    ResponseFormat login(UserDto.LOGIN login);  //로그인 서비스

    ResponseFormat signUp(UserDto.CREATE create);    //회원 가입 서비스
    boolean checkIdentity(String identity); //Id 중복 체크

    ResponseFormat updateUser(UserDto.UPDATE update); //회원 정보 수정 서비스
}
