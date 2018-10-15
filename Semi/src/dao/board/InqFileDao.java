package dao.board;

import java.util.List;

import dto.board.InqFile;
import dto.board.Inquiry;



public interface InqFileDao {


	// 파일 업로드 정보 입력 
	public void insert(InqFile file);
	
	// 파일 리스트 
	public List<InqFile> list();
	
	// 파일번호로 정보 얻기
	public InqFile selectByFileno(InqFile file);
	
	// 보드 번호로 파일 가져오기
	public InqFile selectByInqIdx(Inquiry inq);

	// 파일 지우기 
	public void delete(InqFile file);
}
