package project.olineworkout.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import project.olineworkout.infrastructure.security.jwt.JwtAuthenticationFilter;
import project.olineworkout.infrastructure.security.jwt.JwtTokenProvider;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final HandlerExceptionResolver handlerExceptionResolver;

    private static final String[] AUTH_ARR = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "favicon.ico"
    };

    /**
     * 보안 예외 처리
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(AUTH_ARR);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() //rest api만을 고려해 기본 설정은 해제
                .csrf().disable()      //csrf 보안 토큰 disable 처리
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //JWT 토큰 기반 인증이므로 세션 사용 안함

                .and()
                .authorizeRequests()
                .antMatchers("onlineworkout/**").permitAll()
                .antMatchers("/*/login", "/*/signUp").permitAll()

                .and() //지정된 필터 앞에 커스텀 필터를 추가
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, handlerExceptionResolver),
                        UsernamePasswordAuthenticationFilter.class);  //jwt Token 필터를 id/pw 인증 필터 전에 넣음

    }

    // 암호화에 필요한 PasswordEncoder Bean 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
