package dao.user;

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
	public User selectUserAll();
	
	//회원 아이디 중복 조회
	public int checkid(User user);
	
	//닉네임 수정
	public void changeNick(User user);
	
	//비밀번호 수정
	public void changePw(User user);
}
