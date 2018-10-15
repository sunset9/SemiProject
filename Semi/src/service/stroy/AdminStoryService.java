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

	
	// 현재 페이지 얻어오기
	public int getCurPage(HttpServletRequest req);
	
	// 게시물 전체 수 얻어오기
	public int getTotalCount();
	
	//댓글 삭제
	public void deleteComment(Comment cmt);

	// 댓글 내용 검색 내용 페이징 리스트
	public List<Comment> getPagingList(Paging paging);
	
	
}
