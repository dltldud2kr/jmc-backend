package com.example.jmcbackend.configuration;
import com.example.jmcbackend.member.service.UserServiceImpl;
import com.example.jmcbackend.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserServiceImpl userServiceImpl;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization:{}", authorization);

        //토큰 안 보내면 block
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authorization이 없습니다.");
            filterChain.doFilter(request, response);
            return ;
        }

        // Token 꺼내기
        String token = authorization.split(" ")[1];
        try {
            // Token Expired 되었는지 여부
            if (JwtUtil.isExpired(token, secretKey)) {
                log.error("Token이 만료 되었습니다.");
                filterChain.doFilter(request, response);
                return;
            }


            //UserName Token에서 꺼내기
            String userId = JwtUtil.getUserId(token,secretKey);
            log.info("userId:{}" , userId);

            // 권한 부여
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("USER")));
            // Detail 넣기
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            filterChain.doFilter(request,response);


            // 로그아웃 요청 처리
            boolean isLogoutRequest = request.getRequestURI().equals("/api/v1/users/logout");
            if (isLogoutRequest) {
                JwtBlacklist.addToBlacklist(token);
                filterChain.doFilter(request, response);
                return ;
            }


            boolean isBlacklisted = JwtBlacklist.isBlacklisted(token);
            if (isBlacklisted) {
                JwtBlacklist.invalidateToken(token); // 토큰을 무효화하는 작업
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("토큰이 블랙리스트에 포함되어 있습니다.");
                return;
            }

            filterChain.doFilter(request, response);


            //try catch 추가 ( 이부분 )
        } catch (JwtException e) {
            log.error("잘못된 JWT 서명입니다: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "잘못된 JWT 서명입니다.");
        }
    }
}
