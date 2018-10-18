package dao.board;

import java.util.List;

import dto.board.InqFile;
import dto.board.Inquiry;



public interface InqFileDao {


	// 문의사항 첨부파일 넣기
	public void insert(InqFile file);
	
	// 문의 사항에 관련된 첨부파일 불러오기 
	public List<InqFile> list();
	
	// 첨부파일 번호로 첨부파일 조회하기 
	public InqFile selectByFileno(InqFile file);
	
	// 문의사항에 관련된 첨부파일 조회하기
	public InqFile selectByInqIdx(Inquiry inq);

	// 게시물에 관련된 첨부파일 지우기
	public void delete(InqFile file);
}
