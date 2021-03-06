package dto.board;

import java.util.Date;

public class Notice {

	private int rnum;
	private int notice_idx;
	private int user_idx;
	private String title;
	private String content;
	private String writer;
	private int hit;
	private int nFile_idx;
	private Date create_date;
	
	
	public int getRnum() {
		return rnum;
	}
	public int getnFile_idx() {
		return nFile_idx;
	}
	public void setnFile_idx(int nFile_idx) {
		this.nFile_idx = nFile_idx;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	@Override
	public String toString() {
		return "Notice [rnum=" + rnum + ", notice_idx=" + notice_idx + ", user_idx=" + user_idx + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", hit=" + hit + ", nFile_idx=" + nFile_idx
				+ ", create_date=" + create_date + "]";
	}
	public int getNotice_idx() {
		return notice_idx;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
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
