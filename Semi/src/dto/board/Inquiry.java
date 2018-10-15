package dto.board;

import java.util.Date;

public class Inquiry {

	private int inq_idx;
	private int user_idx;
	private String title;
	private String content;
	private int answer;
	private int hit;
	private Date create_date;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "Inquiry [inq_idx=" + inq_idx + ", user_idx=" + user_idx + ", title=" + title + ", content=" + content
				+ ", answer=" + answer + ", hit=" + hit + ", create_date=" + create_date + "]";
	}
	
	
}
