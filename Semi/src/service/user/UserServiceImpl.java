package service.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, String> getParamUpdate(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String> map = new HashMap<>();
		String userid = req.getParameter("userid");
		String nickname = req.getParameter("nickname");
		String currPw = req.getParameter("currPw");
		String newPw = req.getParameter("newPw");
		String newPwCheck = req.getParameter("newPwCheck");
		
		map.put("userid", userid);
		map.put("nickname", nickname);
		map.put("currPw", currPw);
		map.put("newPw", newPw);
		map.put("newPwCheck", newPwCheck);
		
		return map;
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
	public void updateUserInfo(Map<String, String> param) {
		User user = new User();
		
		user.setId(param.get("userid"));
		user.setPassword(param.get("currPw"));

		System.out.println("제대로나오나:"+user.getId());
		System.out.println("제대로나오나:"+user.getPassword());
		
//		if (userDao.chechPw(user) == 1)
		
		
//		userDao.update(param);
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

	//닉네임 변경
	@Override
	public void changeNick(Map<String, String> param) {
		User user = new User();
		
		user.setId(param.get("userid"));
		user.setNickname(param.get("nickname"));
		
		userDao.changeNick(user);
		
	}

	//비밀번호 변경
	@Override
	public void changePw(Map<String, String> param) {
		User user = new User();
		
		user.setId(param.get("userid"));
		user.setPassword(param.get("newPw"));

		//param의 newPw와 newPwCheck 값 비교
		//같으면 비번 변경
		if( param.get("newPw").equals(param.get("newPwCheck"))) {
			userDao.changePw(user);			
		}
		
		
	}




}
