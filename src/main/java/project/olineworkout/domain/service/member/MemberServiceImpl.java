package project.olineworkout.domain.service.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.olineworkout.domain.dto.MemberDto;
import project.olineworkout.domain.entity.member.Member;
import project.olineworkout.domain.shared.ResponseFormat;
import project.olineworkout.infrastructure.exception.BadRequestException;
import project.olineworkout.infrastructure.exception.NotFoundException;
import project.olineworkout.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * 로그인 서비스
     *        -> To Do... 시큐리티 토큰 적용 예정
     * @param login
     * @return
     */
    @Override
    public String login(MemberDto.LOGIN login){


        Member member = memberRepository.findByIdentity(login.getIdentity())
                .orElseThrow(() -> new NotFoundException("MemberEntity"));

        if(!member.getPassword().equals(login.getPassword())){
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
    public ResponseFormat updateUser(MemberDto.UPDATE update){

        Member member = memberRepository.findByIdentity(update.getIdentity())
                .orElseThrow(() -> new NotFoundException("UserEntity"));
        if(member == null){
            return ResponseFormat.fail("해당 아이디 존재하지 않음");
        }

        member.updateUser(update);
        memberRepository.save(member);

        return ResponseFormat.ok();
    }


}
