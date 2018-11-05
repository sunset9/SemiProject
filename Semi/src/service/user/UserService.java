package service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.plan.Plan;
import dto.user.Bookmark;
import dto.user.User;

public interface UserService {
	//요청 파라미터 처리(id)
	public User getParam(HttpServletRequest req, HttpServletResponse resp);
	
	//요청 파라미터 처리(소셜)
	public User getParamSocial(HttpServletRequest req, HttpServletResponse resp);
	
	//회원정보수정 파라미터 처리
	public Map<String, String> getParamUpdate(HttpServletRequest req, HttpServletResponse resp);
	
	//id 로그인 처리
	public boolean login(User user);
	
	//social 로그인 처리 
	public boolean socialLogin(User user);
	
	//유저 정보 가져오기
	public User getUserByid(User user);
	
	//회원가입
	public void join(User user);
	
	//회원탈퇴 
	public void deleteUserByid(User user);
	
	//비밀번호 조회
	public boolean checkPw(User user);
	
	//이메일 중복확인
	public boolean checkid(User user);
	
	//유저정보수정 
	public void updateUserInfo(Map<String, String> param);
	
	//닉네임 변경
	public void changeNick(Map<String, String> param);
	
	//비밀번호 변경 
	public void changePw(Map<String, String> param);
	
	//내 일정 가져오기
	public List<Plan> getPlanner(User user);
	
	//내 북마크 가져오기
	public List<Bookmark> getBookmarkList(User user);
	
	//내 일정 포스팅 개수 가져오기
	public int getCntPlan(User user);
	
	public double getTotDist(List<Plan> plannerList);	
	
	public List<User> getSelectAll();
	
	//현재 유저의 글을 제외한 모든 글 가져오기
	public List<Plan> getAllPlanList(User cUser);
	
	//임시비번 발급 
	public String createTempPw(int length);
	
	//임시비번으로 비번 변경 
	public void changeTempPw(String email, String tempPw);
	
	//gmailSend()
	public void gmailSend(String userEmail, String tempPw);

	// 총 게시물 수, 총 여행거리 정보 추가된 user 객체 반환
	public User getUseraddedInfo(User user);

}
