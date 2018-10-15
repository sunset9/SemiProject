package dao.board;

import java.util.List;

import dto.board.NoticeFile;


public interface NoticeFileDao {


	// 파일 업로드 정보 입력 
	public void insert(NoticeFile file);
	
	// 파일 리스트 
	public List<NoticeFile> list();
	
	// 파일번호로 정보 얻기
	public NoticeFile selectByFileno(int fileno);

	// 파일 지우기 
	public void delete(NoticeFile file);
}
