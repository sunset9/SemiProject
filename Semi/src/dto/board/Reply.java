package dto.board;

import java.util.Date;

public class Reply {

	private int rep_idx;
	private int inq_idx;
	private int user_idx;
	private String content;
	private Date create_date;
	
	
	@Override
	public String toString() {
		return "Reply [rep_idx=" + rep_idx + ", inq_idx=" + inq_idx + ", user_idx=" + user_idx + ", content=" + content
				+ ", create_date=" + create_date + "]";
	}
	
	public int getRep_idx() {
		return rep_idx;
	}
	public void setRep_idx(int rep_idx) {
		this.rep_idx = rep_idx;
	}
	public int getInq_idx() {
		return inq_idx;
	}
	public void setInq_idx(int inq_idx) {
		this.inq_idx = inq_idx;
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
