package org.example.movie_api.user.service.Impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movie_api.user.dto.UserDto;
import org.example.movie_api.user.entity.User;
import org.example.movie_api.user.repository.UserRepository;
import org.example.movie_api.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입 (비밀번호 암호화)
    public String saveSignup(UserDto userDto, HttpSession httpSession) {

        // httpSession 값 존재여부 확인(로그인 상태에서 회원가입 막기)
        // httpsession의 속성 값이 null이 아니라면,
        if (httpSession.getAttribute("useme") != null) {
            // 이미 로그인 되어 있습니다를 리턴한다.
            return "이미 로그인 되어 있습니다";

        }

        // Optional 값이 있을수도 없을수도 있는 객체 포장
        // userDto의 username을 담아 userrepository의 findById를 호출하여 Optional타입이라고 선업하고 userdata라고 선언한다.
        Optional<User> userdata = userRepository.findById(userDto.getUsername());

        // userdata 객체가 존재 할 경우 아래 글 반환
        if (userdata.isPresent()) {

            return "아이디 중복입니다 다시 해주세요";

        }

        // 새로운 User는 User타입이고 user라고 선언한다.
        User user = new User();

        // 아니라면 아래 실행
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

        return "회원가입 성공";

    }


    // 로그인
    public String login(String username, String password, HttpSession httpSession) {

        // 기존 userRepository에서 username을 가져온다
        User user = userRepository.findById(username).orElse(null);

        // 아이디 존재 체크
        // user 가 null fail 리턴
        if (user == null) {
            return "fail";
        }

        // 아이디 있고, 비밀번호 일치 체크
        // 받아온 userDto의 username과 userrepository에 있는 username이 매치하지 않으면(!) fail 리턴
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "fail";
        }

        // 통과하면 받아온 username을 result로 선언
        String result = username;

        // 만약 result가 "fail" 또는 null 이라면 httpSession에 fail를 속성에 추가 useme로 사용
        if (Objects.equals(result, "fail")) {
            httpSession.setAttribute("useme", "fail");
        }
        // 아니라면(위로직을 통과하면) httpSession에 result값을 속성에 추가 useme로 사용
        else {
            httpSession.setAttribute("useme", result);
        }
        System.out.println("result = " + result);

        return "로그인 로직 작동";

    }


    // 로그아웃
    public String logoutUser(HttpSession httpSession) {

        if (httpSession.getAttribute("useme") == null) {

            return "로그인이 되어있지 않습니다";

        }

        httpSession.removeAttribute("useme");

        return "로그아웃 완료";

    }


    // 회원탈퇴
    public String saveDelete(String username, HttpSession httpSession) {

        if (httpSession.getAttribute("useme") == null || userRepository.findById(username) != httpSession.getAttribute("useme")) {

            return "잘못 된 접근입니다";

        }

        userRepository.deleteById(username);

        return "회원탈퇴 성공";

    }
}
