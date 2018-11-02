package dto.user;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	
	private int user_idx;
	private String id;
	private String password;
	private String nickname;
	private String profile;
	private String grade;
	private int sns_idx;
	private String snsType;
	private Date create_date;
	private int status; //탈퇴회원 여부 -> 1:존재하는 회원, 0:탈퇴회원
	private int totalPlanCnt; //총 게시글 개수 조회
	private double totalDist; // 총 여행 거리
	
	@Override
	public String toString() {
		return "User [user_idx=" + user_idx + ", id=" + id + ", password=" + password + ", nickname=" + nickname
				+ ", profile=" + profile + ", grade=" + grade + ", sns_idx=" + sns_idx + ", snsType=" + snsType
				+ ", create_date=" + create_date + ", status=" + status + ", totalPlanCnt=" + totalPlanCnt
				+ ", totalDist=" + totalDist + "]";
	}

	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSnsType() {
		return snsType;
	}

	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTotalPlanCnt() {
		return totalPlanCnt;
	}

	public void setTotalPlanCnt(int totalPlanCnt) {
		this.totalPlanCnt = totalPlanCnt;
	}

	public double getTotalDist() {
		return totalDist;
	}

	public void setTotalDist(double totalDist) {
		this.totalDist = totalDist;
	}

	
	
}
