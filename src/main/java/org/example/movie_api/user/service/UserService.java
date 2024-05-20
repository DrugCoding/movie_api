package org.example.movie_api.user.service;


import jakarta.servlet.http.HttpSession;
import org.example.movie_api.user.dto.UserDto;

public interface UserService {

    String saveSignup(UserDto userDto, HttpSession httpSession);
    // 회원가입 (비밀번호 암호화)

    String login(String username, String password, HttpSession httpSession);
    // 로그인

    String logoutUser(HttpSession httpSession);
    // 로그아웃

    public String saveDelete(String username, HttpSession httpSession);
    // 회원탈퇴

}
