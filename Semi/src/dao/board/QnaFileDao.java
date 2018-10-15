package dao.board;

import java.util.List;

import dto.board.QnaFile;


public interface QnaFileDao {


	// 파일 업로드 정보 입력 
	public void insert(QnaFile file);
	
	// 파일 리스트 
	public List<QnaFile> list();
	
	// 파일번호로 정보 얻기
	public QnaFile selectByFileno(int fileno);

	// 파일 지우기 
	public void delete(QnaFile file);
}
