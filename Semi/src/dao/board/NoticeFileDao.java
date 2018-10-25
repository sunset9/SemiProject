package dao.board;

import java.util.List;

import dto.board.Notice;
import dto.board.NoticeFile;


public interface NoticeFileDao {


	// 첨부파일 추가 
	public void insert(NoticeFile file);
	
	// 파일하나 불러오기  
	public NoticeFile selectFileByNotice_idx (Notice notice);
	
	// ���Ϲ�ȣ�� ���� ���
	public NoticeFile selectByFileno(int fileno);

	// ���� ����� 
	public void delete(NoticeFile file);
}
