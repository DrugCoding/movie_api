package org.example.movie_api.controller;


import jakarta.servlet.http.HttpSession;
import org.example.movie_api.dto.UserDto;
import org.example.movie_api.entity.User;
import org.example.movie_api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public String signupUser(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {

        return userService.saveSignup(username, password, httpSession);

    }

    // 로그인
    @PostMapping("/login")
    public String logincheck(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {

        return userService.login(username, password, httpSession);

    }

    // 로그아웃
    @GetMapping("/out")
    public String logout(HttpSession httpSession) {

        return userService.logoutUser(httpSession);

    }

    // 회원탈퇴
    @DeleteMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username, HttpSession httpSession) {

        return userService.saveDelete(username, httpSession);

    }
}
