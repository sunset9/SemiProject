package dto.user;

import java.util.Date;

public class User {
	private int user_idx;
	private String email;
	private String password;
	private String nickname;
	private String profile;
	private String grade;
	private String sns_type;
	private Date create_date;
	private int totalPlanCnt; //총 게시글 개수 조회
	
	@Override
	public String toString() {
		return "User [user_idx=" + user_idx + ", email=" + email + ", password=" + password + ", nickname=" + nickname
				+ ", profile=" + profile + ", grade=" + grade + ", sns_type=" + sns_type + ", create_date="
				+ create_date + "]";
	}

	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSns_type() {
		return sns_type;
	}

	public void setSns_type(String sns_type) {
		this.sns_type = sns_type;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
}
