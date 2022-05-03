package project.olineworkout.infrastructure.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import project.olineworkout.domain.entity.member.Member;
import project.olineworkout.infrastructure.security.jwt.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    //컨트롤러 호출 전 실행되는 메소드로 true 반환 시 메소드 살행 후 핸들러에 접근을 하고 false 반환 작업을 중단해 컨트롤러 및 핸들러 실행이 중단된다.
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object object){

        String token = request.getHeader("Authorization");

        if(token == null){
            return true;
        }

        Member member = jwtTokenProvider.findMemberByToken(token);
        MemberThreadLocal.set(member);

        return true;
    }
}
