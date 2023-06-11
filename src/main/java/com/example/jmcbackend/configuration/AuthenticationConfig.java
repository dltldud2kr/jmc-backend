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
                .antMatchers("/api/v1/users/join", "/api/v1/users/login").permitAll()
                .antMatchers("/category/list").permitAll()  // 카테고리 목록
                .antMatchers(HttpMethod.GET,"/store/list").permitAll()  // 가게 목록
                .antMatchers(HttpMethod.GET,"/region**").permitAll()  // 리전별 카테고리 검색
                .antMatchers("/store").permitAll()  // 메인 프론트
                .antMatchers("/store/search**").permitAll() // 가게 검색 기능
                .antMatchers("/store/region**").permitAll()
                .antMatchers("/store/region").permitAll()
                .antMatchers("/dailyStore").permitAll()

                //s3 upload test
                .antMatchers("/s3/upload").permitAll()

                .anyRequest().authenticated()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우 씀
                .and()
                .addFilterBefore(new JwtFilter(userServiceImpl, secretKey), UsernamePasswordAuthenticationFilter.class)

                // 로그인 되지 않은 사용자가 페이지 접근 시 넘어가는 페이지.
//                .formLogin()
//                .loginPage("/api/v1/users/login")
//                .permitAll()
//                .and()

                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/users/logout"))
                .logoutSuccessUrl("/store")
                .invalidateHttpSession(true)    // 세션 무효화
                .and()
                .build();
    }
}
