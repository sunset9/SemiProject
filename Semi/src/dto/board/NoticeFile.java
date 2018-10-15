package dto.board;

import java.util.Date;

public class NoticeFile {

	private int nFile_idx;
	private String stored_name;
	private String origin_name;
	private int notice_idx;
	private Date create_date;
	public int getnFile_idx() {
		return nFile_idx;
	}
	public void setnFile_idx(int nFile_idx) {
		this.nFile_idx = nFile_idx;
	}
	public String getStored_name() {
		return stored_name;
	}
	public void setStored_name(String stored_name) {
		this.stored_name = stored_name;
	}
	public String getOrigin_name() {
		return origin_name;
	}
	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}
	public int getNotice_idx() {
		return notice_idx;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "NoticeFile [nFile_idx=" + nFile_idx + ", stored_name=" + stored_name + ", origin_name=" + origin_name
				+ ", notice_idx=" + notice_idx + ", create_date=" + create_date + "]";
	}

}
