package dto.story;

import java.sql.Date;

public class Comment {
	
	private int comm_idx;
	private int story_idx;
	private int plan_idx;
	private int ttb_idx;
	private int user_idx;
	private String content;
	private Date create_date;
	private String profile;
	private String nickname;
	

	@Override
	public String toString() {
		return "Comment [comm_idx=" + comm_idx + ", story_idx=" + story_idx + ", plan_idx=" + plan_idx + ", ttb_idx="
				+ ttb_idx + ", user_idx=" + user_idx + ", content=" + content + ", create_date=" + create_date
				+ ", profile=" + profile + ", nickname=" + nickname + "]";
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getPlan_idx() {
		return plan_idx;
	}
	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}
	public int getTtb_idx() {
		return ttb_idx;
	}
	public void setTtb_idx(int ttb_idx) {
		this.ttb_idx = ttb_idx;
	}
	public int getComm_idx() {
		return comm_idx;
	}

	public void setComm_idx(int comm_idx) {
		this.comm_idx = comm_idx;
	}
	public int getStory_idx() {
		return story_idx;
	}
	public void setStory_idx(int story_idx) {
		this.story_idx = story_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	
}
