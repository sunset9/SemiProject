package dao.board;

import java.util.List;

import dto.board.Notice;
import utils.Paging;

public interface NoticeDao {

	// 페이징 된 Notice 리스트 조회
	public List<Notice> selectPagingList(Paging paging);
	
	// 전체 게시물 수 조회
	public int selectCntAll(String search) ;
		
	// notice-idx로 게시물 조회하기 
	public Notice selectNoticeByNoticeIdx (Notice notice);
	
	// 조회수 올리기
	public void updateHit(Notice notice);
	
	// 게시물 추가하기
	public void insert(Notice notice);
	
	// 게시물 삭제하기
	public void delete(Notice notice);
	
	// 게시물 수정하기 
	public void update(Notice notice);
	
	// notice-idx로 작성자 id 조회하기 
	public String selectIdByNoticeIdx (Notice notice);
}
