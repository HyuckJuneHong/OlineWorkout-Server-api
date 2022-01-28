package project.olineworkout.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.olineworkout.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    //인증의 주체에 대한 정보를 가져오는 메소드
    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        return null;
    }
}
