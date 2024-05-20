package org.example.movie_api.user.controller;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.movie_api.user.dto.UserDto;
import org.example.movie_api.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    // 예외처리 예상 : 회원가입시 한쪽데이터 미입력시, 중복일시, 로그인 시 데이터 없을 때, 로그아웃 안됐는데 시도할 때(?), 탈퇴하려는 데이터가 없을 때(?), 로그인 하지않은 다른사람을 지우려할 때(?)


    // 회원가입
    @PostMapping("/signup")
    public String signupUser(@ModelAttribute UserDto userDto, HttpSession httpSession) {
//        System.out.println("username = " + userDto.getUsername());
//        System.out.println("password = " + userDto.getPassword());
        return userService.saveSignup(userDto, httpSession);

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
