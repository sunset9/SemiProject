package dao.user;

import java.util.List;

import dto.plan.Plan;
import dto.user.Bookmark;
import dto.user.User;

public interface UserDao {

	//userid & userpw로 존재하는 유저인지 조회하기
	//존재하는 유저라면 1을 반환해준다.
	public int selecCntUserByUseridAndUserpw(User user);
	
	//id로 유저 조회해서 정보 가져오기
	public User selectUserByid(User user);
	
	//회원가입
	public void insert(User user);
	
	//회원가입(카카오톡)
	
	//회원탈퇴 
	public void delete(User user);
	
	//비밀번호 조회 
	public int chechPw(User user);
	
	//회원정보수정 
	public void update(User user);
	
	//회원 전체 조회
	public List<User> selectUserAll();
	
	//회원 아이디 중복 조회
	public int checkid(User user);
	
	//닉네임 수정
	public void changeNick(User user);
	
	//비밀번호 수정
	public void changePw(User user);
	
	//내 일정 가져오기
	public List<Plan> getPlanner(User user);
	
	//내 북마크 가져오기 
	public List<Bookmark> getBookmarkList(User user);
	
	//내 일정 포스팅 개수 가져오기
	public int getCntPlan(User user);
	
	//내 일정의 여행거리들 리스트에 담아오기
	public int getTotDist(User user);
	
	public User selectUserByUserIdx(User user);
	
}
