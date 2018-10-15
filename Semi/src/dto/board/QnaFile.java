package dto.board;

import java.util.Date;

public class QnaFile {

	private int qFile_idx;
	private String stored_name;
	private String origin_name;
	private int qna_idx;
	private Date create_date;
	public int getqFile_idx() {
		return qFile_idx;
	}
	public void setqFile_idx(int qFile_idx) {
		this.qFile_idx = qFile_idx;
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
	public int getQna_idx() {
		return qna_idx;
	}
	public void setQna_idx(int qna_idx) {
		this.qna_idx = qna_idx;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "QnaFile [qFile_idx=" + qFile_idx + ", stored_name=" + stored_name + ", origin_name=" + origin_name
				+ ", qna_idx=" + qna_idx + ", create_date=" + create_date + "]";
	}

}
