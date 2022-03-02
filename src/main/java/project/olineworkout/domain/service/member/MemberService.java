package project.olineworkout.domain.service.member;


import project.olineworkout.domain.dto.MemberDto;
import project.olineworkout.domain.shared.ResponseFormat;

public interface MemberService {

    String login(MemberDto.LOGIN login);  //로그인 서비스
    void signUp(MemberDto.CREATE create);    //회원 가입 서비스
    boolean checkIdentity(String identity); //Id 중복 체크

    ResponseFormat updateUser(MemberDto.UPDATE update); //회원 정보 수정 서비스
}
