package com.example.jmcbackend.configuration;


import com.example.jmcbackend.member.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserServiceImpl userServiceImpl;
    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/api/v1/users/join", "/api/v1/users/login","/api/v1/users/logout"
                        ,"/store/**","/category/**").permitAll()

                .antMatchers(HttpMethod.POST, "/store/register","/create/review"
                        ,"/category/add", "/store/delete","/api/v1/reviews/myReviewList").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우 씀
                .and()
                .addFilterBefore(new JwtFilter(userServiceImpl, secretKey), UsernamePasswordAuthenticationFilter.class)
                //여기
                .logout()
                .logoutUrl("/api/v1/users/logout") // 로그아웃 API 경로
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/users/logout", "POST")) // 로그아웃 API 경로

                .logoutSuccessHandler((req, res, auth) -> {
                    // JWT 토큰을 무효화
                    String token = req.getHeader("Authorization");
                    if (token != null && token.startsWith("Bearer ")) {
                        token = token.substring(7);
                        // 토큰을 무효화할 작업 수행
                    }
                })
                .deleteCookies("jwt") // 쿠키 삭제
                .permitAll()
                .and()
                //여기
                .build();
    }
}
