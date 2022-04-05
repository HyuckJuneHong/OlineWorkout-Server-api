package project.olineworkout.domain.service.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.olineworkout.domain.dto.MemberDto;
import project.olineworkout.domain.entity.member.Member;
import project.olineworkout.domain.shared.ResponseFormat;
import project.olineworkout.infrastructure.exception.BadRequestException;
import project.olineworkout.infrastructure.exception.NotFoundException;
import project.olineworkout.infrastructure.security.jwt.JwtTokenProvider;
import project.olineworkout.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

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

        if(!member.getPassword().equals(login.getPassword())){
                throw new BadRequestException("비밀번호 일치하지 않음");
        }

        String[] tokens = generateToken(member);

        member.updateFcmToken(login.getFcmToken());
        member.updateRefreshToken(tokens[1]);

        return new MemberDto.TOKEN(tokens[0], tokens[1]);
    }

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
     *     ->To Do... 시큐리티 적용하면 아래 처럼 수정
     *         UserEntity userEntity = UserThreadLocal.get();
     *         userEntity.update(update);
     *         userRepository.save(userEntity);
     * @param update
     * @return
     */
    @Override
    public ResponseFormat updateMember(MemberDto.UPDATE update){

        Member member = memberRepository.findByIdentity(update.getIdentity())
                .orElseThrow(() -> new NotFoundException("memberEntity"));
        if(member == null){
            return ResponseFormat.fail("해당 아이디 존재하지 않음");
        }

        member.updateMember(update);
        memberRepository.save(member);

        return ResponseFormat.ok();
    }

    /**
     * To do ....
     * @param update_password
     * @return
     */
    @Override
    public ResponseFormat updatePassword(MemberDto.UPDATE_PASSWORD update_password) {
        return null;
    }

    private String[] generateToken(Member member) {
        String accessToken = jwtTokenProvider.createAccessToken(member.getIdentity(), member.getMemberRole(), member.getName());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getIdentity(), member.getMemberRole(), member.getName());

        return new String[]{accessToken, refreshToken};
    }

}
