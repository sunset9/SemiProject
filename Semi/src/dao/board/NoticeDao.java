package dao.board;

import java.util.List;

import dto.board.Notice;
import utils.Paging;

public interface NoticeDao {

	// Notice 전체 페이징하여 리스트로 조회 
	public List<Notice> selectPagingList(Paging paging);
	
	// 전체 게시글 수 조회
	public int selectCntAll() ;
		
	// notice_idx로 하나의 Notice 조회하기
	public Notice selectInqByInqIdx (Notice notice);
	
	// 조회후 조회수 올리기
	public void updateHit(Notice notice);
	
	// 게시물 등록 하기
	public void insert(Notice notice);
	
	// 게시물 삭제 하기
	public void delete(Notice notice);
	
	// 게시물 수정 하기
	public void update(Notice notice);
}
