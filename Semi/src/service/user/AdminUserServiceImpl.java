package service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.user.User;

public class AdminUserServiceImpl implements AdminUserService {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public int userCnt() {
		return userDao.selectUserCnt();
	}
	
	@Override
	public int touristCnt() {
		return userDao.selectTouristCnt();
	}
	
	@Override
	public int authorCnt() {
		return userDao.selectAuthorCnt();
	}
	
	@Override
	public int managerCnt() {
		return userDao.selectManagerCnt();
	}
	@Override
	public User getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByEmail(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<User> selectUser(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectUserBy(User user) {
		// TODO Auto-generated method stub
		return null;
	}



}
