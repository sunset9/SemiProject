package dto.board;

import java.util.Date;

public class InqFile {

	private int iFile_idx;
	private String stored_name;
	private String origin_name;
	private int inq_idx;
	private Date create_date;
	public int getiFile_idx() {
		return iFile_idx;
	}
	public void setiFile_idx(int iFile_idx) {
		this.iFile_idx = iFile_idx;
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
	public int getInq_idx() {
		return inq_idx;
	}
	public void setInq_idx(int inq_idx) {
		this.inq_idx = inq_idx;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "InqFile [iFile_idx=" + iFile_idx + ", stored_name=" + stored_name + ", origin_name=" + origin_name
				+ ", inq_idx=" + inq_idx + ", create_date=" + create_date + "]";
	}
	
}
