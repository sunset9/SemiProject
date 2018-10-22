package service.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.user.User;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	
	//요청파라미터 처리(id)
	@Override
	public User getParam(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User();
		
		user.setId(req.getParameter("userid"));
		user.setPassword(req.getParameter("userpw"));
		user.setNickname(req.getParameter("usernickname"));
		user.setSns_idx(Integer.parseInt(req.getParameter("snsIdx")));

		return user;
	}

	//요청 파라미터 처리(소셜)
	@Override
	public User getParamSocial(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User();
		
		user.setId(req.getParameter("id"));
		user.setNickname(req.getParameter("nickname"));
		user.setProfile(req.getParameter("profileImage"));
		user.setSns_idx(Integer.parseInt(req.getParameter("snsIdx")));
		
		return user;
	}

	//회원정보수정 파라미터 처리
	@Override
	public List<String> getParamUpdate(HttpServletRequest req, HttpServletResponse resp) {
		List<String> list = new ArrayList<>();
		
		String nickname = req.getParameter("nickname");
		String currPw = req.getParameter("currPw");
		String newPw = req.getParameter("newPw");
		String newPwCheck = req.getParameter("newPwCheck");
		
		System.out.println(currPw);
		System.out.println(newPw);
		System.out.println(newPwCheck);
		
		return list;
	}
	
	// id 로그인처리
	@Override
	public boolean login(User user) {
		if( userDao.selecCntUserByUseridAndUserpw(user) == 1 ) {
			return true;
		} else {
			return false;
		}
	}
	
	// social 로그인처리
	@Override
	public boolean socialLogin(User user) {
		//로그인 처리 전에 아이디 조회후
		//db에 아이디 검색후 있는 아이디면 바로 로그인처리, 없는 아이디면 db에 저장
		boolean checkid = checkid(user);
		if(checkid) {
			//true : 존재하지 않는 아이디 -> db에 저장
			userDao.insert(user);
			return true; 
		} else {
			//false : 존재하는 아이디 -> 바로 로그인 처리
			return true;
		}
	}

	//이메일로 유저조회
	@Override
	public User getUserByid(User user) {
		return userDao.selectUserByid(user);
	}

	//회원가입 처리
	@Override
	public void join(User user) {
		userDao.insert(user);
		System.out.println("INSERT success");
	}

	//아이디로 회원 삭제
	@Override
	public void deleteUserByid(User user) {
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

	//아이디 중복 조회
	@Override
	public boolean checkid(User user) {
		if( userDao.checkid(user) == 0 ) {
			System.out.println("아이디 중복되지 않음, 존재하지 않는 아이디");
			return true;
		} else {
			System.out.println("아이디 중복, 존재하는 아이디");
			return false;
		}
	}




}
