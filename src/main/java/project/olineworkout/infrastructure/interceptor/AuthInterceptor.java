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
