package project.olineworkout.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.olineworkout.repository.member.MemberRepository;


//토큰에 저장된 유저 정보를 활용해야 하기 때문에 CustomUserDetatilService 라는 이름의 클래스를 만들고
//UserDetailsService를 상속받아 재정의 하는 과정을 진행
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    //인증의 주체에 대한 정보를 가져오는 메소드
    @Override
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {

        return null;
    }
}
