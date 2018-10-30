package service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.user.User;
import utils.Paging;

public class AdminUserServiceImpl implements AdminUserService {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public int userCnt(String grade) {
		return userDao.selectUserCnt(grade);
	}
	
	
	@Override
	public User getParam(HttpServletRequest req, HttpServletResponse resp) {

		User user = new User();
		
		String user_idx = req.getParameter("user_idx");
		
		// inq_idx 가 null이나 빈값이 아닐때 DTO에 저장하기
		if(user_idx != null && !"".equals(user_idx)) {
			user.setUser_idx(Integer.parseInt(user_idx));
		}
		return user;
	}

	@Override
	public User getUserByEmail(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(User user) {
		boolean rs = false;
		
		if(userDao.delete(user)==1) {
			rs = true;
		}
		
		return rs;
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

	
	
//-------------------------------------------------------------
	
	
	@Override
	public int getCurPage(HttpServletRequest req) {
		// 요청 파라미터 받아오기
		String curPage = req.getParameter("curPage");
		
		// null이나 공백이 아닐 때 변수값 반환하기
		if( curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}
			
		// null이나 공백일 때 0 반환
		return 0;
	}


	@Override
	public String getSearch(HttpServletRequest req) {
		
		String search = req.getParameter("search");
		return search;
	}

  
	@Override
	public int getTotalCount(String search, int searchType) {
		return userDao.selectUserCnt(search, searchType);
	}

	@Override
	public List<User> getPagingList(Paging paging) {
		return userDao.selectList(paging);
	}


	@Override
	public void userListDelete(String names) {
		userDao.deleteUserList(names);
	}


	@Override
	public boolean upgrade(User user) {
		boolean rs = false;
		
		if(userDao.updateGrade(user)==1) {
			rs = true;
		}
		
		return rs ;
		
	}


	@Override
	public boolean loginCheck(HttpServletRequest req) {
		boolean check =false;
		
		if(req.getSession().getAttribute("login")!=null){
			check = (boolean)req.getSession().getAttribute("login");
		}
		
//		System.out.println("check : " +check);
		return check;
	}



}
