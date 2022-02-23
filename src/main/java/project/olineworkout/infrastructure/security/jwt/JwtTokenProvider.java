package project.olineworkout.infrastructure.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
     * 키 변환을 위한 key 를 만드는 메서드
     * @return secret key
     */
    private byte[] generateKey() {
        try{
            return SECRET_KEY.getBytes("UTF-8");
        }catch (UnsupportedEncodingException e){
            throw new UserDefineException("키 변환에 실패하였습니다. ", e.getMessage());
        }
    }
}
