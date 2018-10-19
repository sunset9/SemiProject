package dto.timetable;

import java.util.Date;

public class Timetable {
	private int ttb_idx;
	private int plan_idx;
	private int loc_idx;
	private Date start_time;
	private Date end_time;
	private Date create_date;
	
	@Override
	public String toString() {
		return "Timetable [ttb_idx=" + ttb_idx + ", plan_idx=" + plan_idx + ", loc_idx=" + loc_idx + ", start_time="
				+ start_time + ", end_time=" + end_time + ", create_date=" + create_date + "]";
	}
	public int getTtb_idx() {
		return ttb_idx;
	}
	public void setTtb_idx(int ttb_idx) {
		this.ttb_idx = ttb_idx;
	}
	public int getPlan_idx() {
		return plan_idx;
	}
	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}
	public int getLoc_idx() {
		return loc_idx;
	}
	public void setLoc_idx(int loc_idx) {
		this.loc_idx = loc_idx;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
}
