package dao.board;

import java.util.List;

import dto.board.Qna;
import dto.board.QnaFile;


public interface QnaFileDao {


	// 첨부파일 추가 
	public void insert(QnaFile file);
	
	// ���Ϲ�ȣ�� ���� ���
	public QnaFile selectByFileno(int fileno);
	
	// 게시글에 관련된 첨부파일 불러오기
	public QnaFile selectFilebyQna(Qna qna);

	// 게시글 삭제 
	public void delete(Qna qan);
	
	//리스트 선택해서 삭제하기
	public void deleteQnaListFile(String names);
}
