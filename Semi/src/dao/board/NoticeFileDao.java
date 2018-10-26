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

	// 게시물에 관련된 파일 지우기
	public void delete(Notice notice);
}
