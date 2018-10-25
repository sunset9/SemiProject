package dao.board;

import java.util.List;

import dto.board.Qna;
import dto.board.QnaFile;


public interface QnaFileDao {


	// ���� ���ε� ���� �Է� 
	public void insert(QnaFile file);
	
	// ���� ����Ʈ 
	public List<QnaFile> list();
	
	// ���Ϲ�ȣ�� ���� ���
	public QnaFile selectByFileno(int fileno);
	
	// 게시글에 관련된 첨부파일 불러오기
	public QnaFile selectFilebyQna(Qna qna);

	// ���� ����� 
	public void delete(QnaFile file);
}
