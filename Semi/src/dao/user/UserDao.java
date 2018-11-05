package dao.user;

import java.util.List;

import dto.user.UploadFile;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.user.Bookmark;
import dto.user.User;
import utils.Paging;

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
	public int delete(User user);
	
	//비밀번호 조회 
	public int checkPw(User user);
	
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
	
	public List<Location> getTotDist(Plan plan);
	
	public User selectUserByUserIdx(User user);
	
	// ---------------------------------------------------
	// 관리자 페이지에서만 사용하는 메소드 - 나영
	
	// 유저 수 조회
	public int selectUserCnt(String grade); 

	// option paging total count 조회 
	public int selectUserCnt(String search, int searchThype);
	
	// 조건 추가된 user list 조회
	public List<User> selectList(Paging paging);
	
	// 선택된 유저 삭제
	public void deleteUserList(String names);
	
	// 회원 등급 조회 
	public String selectGrade(User user);
	
	// 회원 등급 올리기
	public int updateGrade(User user);
	
	// 현재 유저의 글을 제외한 모든 글 가져오기 
	public List<Plan> getAllPlanList(User cUser);
	
	//파일 업로드 정보 입력
	public void insert(UploadFile file);
	
	// 유저의 profile 변경
	public void profileUpdate(User cUser);
	
	// 임시 비번으로 비번 변경 
	public void changeTempPw(String email, String tempPw);
	

}
