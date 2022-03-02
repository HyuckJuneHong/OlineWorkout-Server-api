package project.olineworkout.infrastructure.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import project.olineworkout.infrastructure.exception.JwtTokenExpiredException;
import project.olineworkout.infrastructure.exception.JwtTokenInvalidException;
import project.olineworkout.infrastructure.exception.UserDefineException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    //유저 정보를 UserDetails 타입으로 Spring Security 한테 제공하는 역할
    //UserDetail : 애플리케이션이 가지고 있는 유저 정보와 spring Security가 사용하는 Authenticaiton 객체 사이의 어댑터
    private UserDetailsService userDetailsService;

    /** init(), @PostConstruct
     * init() : 객체 초기화, secretKey를 Base64로 인코딩.
     * @PostConstruct : 의존성 주입이 이루어진 후 초기화를 수행하는 메서드.
     *      -> 즉, 클래스가 service(로직을 탈 때? 로 생각 됨)를 수행하기 전에 발생.
     *      -> 때문에, 이 메서드는 다른 리소스에서 호출되지 않는다해도 수행
     */
    @PostConstruct
    protected void init() {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    /**
     * 헤더에 있는 토큰을 추출하는 메서드
     * 평소에는 AccessToken을 담아서 주고 받다가 만료가 되었다는 예외가 발생하면 그때 Refresh만
     * @param request 사용자의 요청
     * @return AccessToken 과 RefreshToken 을 담은 객체를 Optional로 감싼 데이터
     */
    public Optional<String> resolveToken(HttpServletRequest request) {

        //ofNullable(): null이어도 예외 던지지 않고 비어있는 Optional 객체 생성
        return Optional.ofNullable(request.getHeader("X-AUTH-TOKEN"));
    }

    /**
     * 토큰의 유효성을 판단하는 메서드
     * @param token 토큰
     * @return 토큰이 만료되었는지에 대한 불리언 값
     * @exception ExpiredJwtException 토큰이 만료되었을 경우에 발생하는 예외
     */
    public boolean isUsable(String token) {

        try{
            Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException e) {
            log.error("Invalid JWT signature");
            throw new JwtTokenInvalidException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
            throw new JwtTokenInvalidException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
            throw new JwtTokenExpiredException();
        } catch (IllegalArgumentException e) {
            log.error("Empty JWT claims");
            throw new JwtTokenInvalidException("Empty JWT claims");
        }
    }

    /**
     * 키 변환을 위한 key를 만드는 메서드
     * @return secret key
     */
    private byte[] generateKey() {
        try{
            return SECRET_KEY.getBytes("UTF-8");
        }catch (UnsupportedEncodingException e){
            throw new UserDefineException("키 변환에 실패하였습니다. ", e.getMessage());
        }
    }

    /**
     * 토큰을 통해서 Authentication 객체를 만들어내는 메서드
     * @param token 토큰
     * @return 사용자 정보를 담은 UsernamePasswordAuthenticationToken 객체
     */
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // JWT 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token).getBody().getSubject();
    }
}
