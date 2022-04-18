package project.olineworkout.domain.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.olineworkout.domain.dto.MemberDto;
import project.olineworkout.domain.entity.member.Member;
import project.olineworkout.domain.shared.ResponseFormat;
import project.olineworkout.infrastructure.exception.BadRequestException;
import project.olineworkout.infrastructure.exception.NotFoundException;
import project.olineworkout.infrastructure.interceptor.MemberThreadLocal;
import project.olineworkout.infrastructure.security.jwt.JwtTokenProvider;
import project.olineworkout.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인 서비스
     * @param login
     * @return
     */
    @Override
    @Transactional
    public MemberDto.TOKEN login(MemberDto.LOGIN login){

        Member member = memberRepository.findByIdentity(login.getIdentity())
                .orElseThrow(() -> new NotFoundException("MemberEntity"));

        //boolean matches(rP, eP) : 저장소에서 얻은 인코딩된 암호도 인코딩된 원시 암호화 일치하는지 확인하는 메소드 (절대 디코딩되지 않음)
        if(!passwordEncoder.matches(login.getPassword(), member.getPassword())){
                throw new BadRequestException("비밀번호 일치하지 않음");
        }

        String[] tokens = generateToken(member);

        member.updateFcmToken(login.getFcmToken()); //To do ... => FCM Token
        member.updateRefreshToken(tokens[1]);

        return new MemberDto.TOKEN(tokens[0], tokens[1]);
    }

    //To do...
//    boolean reCheckPassword(String password){
//        return passwordEncoder.matches(password, UserThreadLocal.get().getPassword();
//    }
//    boolean resetPasswordCheck(UserDto.RESET_CHECK reset)

    /**
     * 회원 가입 서비스
     * @param create
     * @return
     */
    @Override
    public void signUp(MemberDto.CREATE create){

        if(checkIdentity(create.getIdentity())){
            throw new BadRequestException("중복");
        }

        memberRepository.save(Member.builder()
                .identity(create.getIdentity())
                .password(create.getPassword())
                .name(create.getName())
                .gender(create.getGender())
                .birthDay(create.getBirthDay())
                .phone(create.getPhone())
                .memberRole(create.getMemberRole())
                .build());
    }

    /**
     * 아이디 중복 체크
     * @param identity
     * @return
     */
    @Override
    public boolean checkIdentity(String identity){
        return memberRepository.existsByIdentity(identity);
    }

    /**
     * 고객 정보 수정 서비스
     * @param update
     * @return
     */
    @Override
    public void updateMember(MemberDto.UPDATE update){

        Member member = MemberThreadLocal.get();
        member.updateMember(update);
        memberRepository.save(member);
    }

    /**
     * 비밀번호 변경 서비스
     * @param update_password
     * @return
     */
    @Override
    public void updatePassword(MemberDto.UPDATE_PASSWORD update_password) {

        Member member = MemberThreadLocal.get();
        if(!passwordEncoder.matches(update_password.getPassword(), member.getPassword())){
            throw new BadRequestException("비밀번호가 올바르지 않습니다.");
        }

        if(!update_password.getNewPassword().equals(update_password.getReNewPassword())){
            throw new BadRequestException("새 비밀번호가 일치하지 않습니다.");
        }

        member.updatePassword(passwordEncoder.encode(update_password.getNewPassword()));
        memberRepository.save(member);
    }

    /**
     * 토큰 발급 서비스
     * @param member
     * @return
     */
    private String[] generateToken(Member member) {
        String accessToken = jwtTokenProvider.createAccessToken(member.getIdentity(), member.getMemberRole(), member.getName());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getIdentity(), member.getMemberRole(), member.getName());

        return new String[]{accessToken, refreshToken};
    }

}
