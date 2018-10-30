package service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.user.User;
import utils.Paging;

public interface AdminUserService {

	// 요청 파라미터 처리
	public User getParam(HttpServletRequest req, HttpServletResponse resp);
	
	// 유저 정보 가져오기
	public User getUserByEmail(User user);
	
	// 회원 삭제
	public boolean deleteUser(User user);
	
	// 회원 등급별 수 조회
	public int userCnt(String grade); 

	// 회원 전제 조회 
	public List<User> selectUser(String search);
	
	// 회원 조건 조회 
	public User selectUserBy(User user);
	
	// 현재 페이지 얻어오기
	public int getCurPage(HttpServletRequest req);
	
	// 검색어 얻어오기
	public String getSearch(HttpServletRequest req);
	
	// 전체 페이지 얻어오기
	public int getTotalCount(String search, int searchType);
	
	// 회원 조건 검색 리스트 조회
	public List<User> getPagingList(Paging paging);
	
	// 회원 선택 삭제
	public void userListDelete(String names);
	
	// 회원 등급 올리기
	public boolean upgrade(User user);
	
	// 로그인 체크하기
	public boolean loginCheck(HttpServletRequest req);

	
	
}
