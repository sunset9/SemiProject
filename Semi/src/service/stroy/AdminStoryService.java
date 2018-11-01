package service.stroy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.story.Comment;
import dto.Account.Account;
import dto.board.Inquiry;
import dto.plan.Plan;
import dto.story.Story;
import utils.Paging;

public interface AdminStoryService {
	
	// 파라미터 얻어오기 
	public Comment getParam(HttpServletRequest req);
	
	// 로그인 체크
	public boolean loginCheck(HttpServletRequest req);
	
	// 현재 페이지 얻어오기
	public int getCurPage(HttpServletRequest req);
	
	// 검색어 얻어오기 
	public String getSearch(HttpServletRequest req);
	
	// 게시물 전체 수 얻어오기
	public int getTotalCount(String search);
	
	//댓글 삭제
	public boolean deleteComment(Comment cmt);

	//댓글 선택해서 삭제 
	public void commListDelete(String names);

	// 댓글 내용 검색 내용 페이징 리스트
	public List<Comment> getPagingList(Paging paging);
	
	
}
