package dao.user;

import dto.user.User;

public interface UserDao {

	//useremail & userpw로 존재하는 유저인지 조회하기
	//존재하는 유저라면 1을 반환해준다.
	public int selecCntUserByUsereamilAndUserpw(User user);
	
	//email로 유저 조회하기 
	public User selectUserByEmail(User user);
	
	//회원가입
	public void insert(User user);
	
	//회원탈퇴 
	public void delete(User user);
	
	//비밀번호 조회 
	public int chechPw(User user);
	
	//회원정보수정 
	public void update(User user);
	
	// 회원 전체 조회
	public User selectUserAll();
	
	// 회원 닉네임으로 조회 
	public User selectUserByNick(User user);
}
