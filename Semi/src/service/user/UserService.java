package service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.user.User;

public interface UserService {
	//요청 파라미터 처리
	public User getParam(HttpServletRequest req, HttpServletResponse resp);
	
	//로그인 처리
	public boolean login(User user);
	
	//유저 정보 가져오기
	public User getUserByEmail(User user);
	
	//회원가입
	public void join(User user);
	
	//회원탈퇴 
	public void deleteUserByEmail(User user);
	
	//비밀번호 조회
	public int checkPw(User user);
	
	//이메일 중복확인
	public boolean checkEmail(User user);
	
	//유저정보수정 
	public void updateUserInfo(User user);
	
	//세션 설정하기
}
