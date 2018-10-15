package service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.user.User;

public interface AdminUserService {

	// 요청 파라미터 처리
	public User getParam(HttpServletRequest req, HttpServletResponse resp);
	
	// 유저 정보 가져오기
	public User getUserByEmail(User user);
	
	// 회원 삭제
	public void deleteUser(User user);

	// 회원 전제 조회 
	public List<User> selectUserAll();
	
	// 회원 조건 조회 
	public User selectUserBy(User user);
	
	
}
