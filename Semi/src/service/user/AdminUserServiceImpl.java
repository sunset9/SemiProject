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
	public List<User> selectUserAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectUserBy(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
