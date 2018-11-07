package dto.plan;

import java.util.Date;

public class Plan {
//	여행 pk키
	private int plan_idx;
//	유저 pk키
	private int user_idx;
//	출발일
	private Date start_date;
//	도착일
	private Date end_date;
//	여행 제목
	private String title;
//	여행 (전, 후) -> 1 or 0 값만
	private int traveled;
//	공개 여부 -> 1 or 0 값만
	private int opened;
//	작성일
	private Date create_date;
//	배너 url
	private String bannerURL;
// 유저 nickname 
	private String nick;
// 임시 토큰
	private int planToken;

	@Override
	public String toString() {
		return "Plan [plan_idx=" + plan_idx + ", user_idx=" + user_idx + ", start_date=" + start_date + ", end_date="
				+ end_date + ", title=" + title + ", traveled=" + traveled + ", opened=" + opened + ", create_date="
				+ create_date + ", bannerURL=" + bannerURL + ", nick=" + nick + ", planToken=" + planToken + "]";
	}

	public int getPlan_idx() {
		return plan_idx;
	}

	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}

	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTraveled() {
		return traveled;
	}

	public void setTraveled(int traveled) {
		this.traveled = traveled;
	}

	public int getOpened() {
		return opened;
	}

	public void setOpened(int opened) {
		this.opened = opened;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getBannerURL() {
		return bannerURL;
	}

	public void setBannerURL(String bannerURL) {
		this.bannerURL = bannerURL;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getPlanToken() {
		return planToken;
	}

	public void setPlanToken(int planToken) {
		this.planToken = planToken;
	}
	
	
	
}
