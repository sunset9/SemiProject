package dto.user;

import java.util.Date;

public class User {
	private int user_idx;
	private String email;
	private String password;
	private String nickname;
	private String profile;
	private String grade;
	private int sns_idx;
	private Date create_date;
	private int totalPlanCnt; //총 게시글 개수 조회
	
	@Override
	public String toString() {
		return "User [user_idx=" + user_idx + ", email=" + email + ", password=" + password + ", nickname=" + nickname
				+ ", profile=" + profile + ", grade=" + grade + ", sns_idx=" + sns_idx + ", create_date=" + create_date
				+ ", totalPlanCnt=" + totalPlanCnt + "]";
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

	public int getSns_idx() {
		return sns_idx;
	}

	public void setSns_idx(int sns_idx) {
		this.sns_idx = sns_idx;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public int getTotalPlanCnt() {
		return totalPlanCnt;
	}

	public void setTotalPlanCnt(int totalPlanCnt) {
		this.totalPlanCnt = totalPlanCnt;
	}
	
	
}
