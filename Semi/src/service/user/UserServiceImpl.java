package service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.user.User;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	
	//요청파라미터 처리
	@Override
	public User getParam(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User();
		
		return user;
	}

	//로그인처리
	@Override
	public boolean login(User user) {
		if( userDao.selecCntUserByUsereamilAndUserpw(user) == 1 ) {
			return true;
		} else {
			return false;
		}
	}

	//이메일로 유저조회
	@Override
	public User getUserByEmail(User user) {
		return userDao.selectUserByEmail(user);
	}

	//회원가입 처리
	@Override
	public void join(User user) {
		userDao.insert(user);
		
	}

	//이메일로 회원 삭제
	@Override
	public void deleteUserByEmail(User user) {
		userDao.delete(user);
		
	}

	//현재 로그인한 유저의 비밀번호 확인
	@Override
	public int checkPw(User user) {
		
		return userDao.chechPw(user);
	}

	//회원정보수정
	@Override
	public void updateUserInfo(User user) {
		userDao.update(user);
	}

	@Override
	public boolean checkEmail(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
