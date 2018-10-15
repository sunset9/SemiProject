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
	private int travled;
//	공개 여부 -> 1 or 0 값만
	private int opened;
//	여행 거리 -> 게시글 마다의 여행 거리
	private int distance;
//	작성일
	private Date create_date;
	//총 여행 거리
	private int tot_dist;
	
	@Override
	public String toString() {
		return plan_idx + ", " + user_idx + ", " +  start_date + ", " +  end_date
				+ title + ", " +  travled + ", " +  opened + ", " +  distance + ", " +  create_date;
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

	public int getTravled() {
		return travled;
	}

	public void setTravled(int travled) {
		this.travled = travled;
	}

	public int getOpened() {
		return opened;
	}

	public void setOpened(int opened) {
		this.opened = opened;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
}
