package dto.board;

import java.util.Date;

public class Qna {

	private int rnum;
	private int qna_idx;
	private int user_idx;
	private String title;
	private String content;
	private String writer;
	private int hit;
	private int qFile_idx;
	
	public int getqFile_idx() {
		return qFile_idx;
	}
	public void setqFile_idx(int qFile_idx) {
		this.qFile_idx = qFile_idx;
	}
	private Date create_date;
	
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	@Override
	public String toString() {
		return "Qna [rnum=" + rnum + ", qna_idx=" + qna_idx + ", user_idx=" + user_idx + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", hit=" + hit + ", qFile_idx=" + qFile_idx
				+ ", create_date=" + create_date + "]";
	}
	public int getQna_idx() {
		return qna_idx;
	}
	public void setQna_idx(int qna_idx) {
		this.qna_idx = qna_idx;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	
}
