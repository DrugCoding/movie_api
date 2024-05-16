사용 기술스택
* 언어 : Java
* 프레임워크 : Springboot
* 데이터베이스 : MySQL

API 명세서
* 영화정보 저장
  - http://localhost:8080/genres/save_genre (GET)
  - http://localhost:8080/movies/save_popular (GET)
  - http://localhost:8080/movies/save_popular (GET)

* 영화리뷰 작성
  - http://localhost:8080/comment/create (POST : @RequestBody) = 작성
  - http://localhost:8080/comment/detail/{id} (GET : @PathVariable) = 조회
  - http://localhost:8080/comment/update/{id} (POST : @PathVarialbe, @RequestBody) = 수정
  - http://localhost:8080/comment/create (DELETE : @PathVarialbe) = 삭제

* 회원가입 (세션을 통한 권한에 따른 기능제한)
  - http://localhost:8080/user/signup (POST : @RequestBody) = 회원가입
  - http://localhost:8080/user/login (GET : @RequestBody) = 로그인
  - http://localhost:8080/user/out (GET) = 로그아웃
  - http://localhost:8080/user/delete (DELETE : @PathVariable) = 회원탈퇴
 

*** 미숙사항
  - 기능 마다 commit하며 메시지 남기기
  - branch 사용
  - git 사용
  - 기획?의 과정이 없음 (ERD 등)
  - 모든 코드를 정확히 알고 사용하지 못함
