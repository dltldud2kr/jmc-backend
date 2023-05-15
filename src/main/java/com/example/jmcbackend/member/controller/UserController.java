package com.example.jmcbackend.member.controller;

import com.example.jmcbackend.member.dto.UserDto;
import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.example.jmcbackend.member.dto.UserListResponse;
import com.example.jmcbackend.member.dto.UserLoginResponse;
import com.example.jmcbackend.member.service.UserService;
import com.example.jmcbackend.member.service.UserServiceImpl;
import com.example.jmcbackend.storeLike.dto.StoreLikesDto;
import com.example.jmcbackend.storeLike.service.StoreLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final StoreLikesService storeLikesService;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserJoinRequest dto) {


        return ResponseEntity.ok(userService.join(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(Principal principal ) {
        String userId = principal.getName();

        userService.delete(userId);
        return ResponseEntity.ok().build();

    }
    @PostMapping("/userEdit")
    public ResponseEntity userInfoEdit( Principal principal, @RequestBody UserDto dto) {
        String userId = principal.getName();

        ResponseEntity result = userService.modify(userId,dto);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/userInfo")
    public ResponseEntity userInfo(Principal principal) {
        String userId = principal.getName();
        UserDto userDto = userService.myInfo(userId);

        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginResponse dto) {

        String token = userService.login(dto);
        return ResponseEntity.ok().body(token); // hashmap으로하세요.
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // JWT 토큰을 무효화
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            // 토큰을 무효화할 작업 수행
            // 예를 들어, 토큰 블랙리스트에 추가하거나, 세션을 만료시키는 등의 작업을 수행할 수 있습니다.
        }

        // 로그아웃 후 동작 또는 응답 처리
        return ResponseEntity.ok("Logged out successfully");
    }


//    @PostMapping("/logout")
//    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            new SecurityContextLogoutHandler().logout(request, response, authentication);
//        }
//        return ResponseEntity.ok().body("로그아웃 되었습니다.");
//    }




    @GetMapping("list")
    public ResponseEntity list() {

        List<UserListResponse> list = userService.list();

        return ResponseEntity.ok(list);
    }
}
