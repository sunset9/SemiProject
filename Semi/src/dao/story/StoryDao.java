package dao.story;

import java.util.List;

import dto.story.Comment;
import dto.plan.Plan;
import dto.story.Story;
import utils.Paging;

public interface StoryDao {
	
	public List<Story> selectAllByPlanNo(Plan Plan);
	
	public Story selectStoryByStoryIdx(Story story);
	
	// 미니뷰를 위한 스토리 조회 
	public Story selectStoryByTtbIdx(Story story);
	
	public void updateHit(Story story);
	
	public void insert(Story story);
	
	public void update(Story story);
	
	public void delete(Story story);
	
	//플랜 삭제시 스토리 전부 삭제
	public void deleteList(Plan plan);
	
	public int SelectCntAll();
	
	
	//현재 스토리의 인덱스값 불러오기
	public int SelectStoryIdx();
	
	public List<Comment> selectCommentByStoryNo(Story story);
	
	public Comment selectCommentByCommentIdx(Comment cmt);
	
	public void deleteCommentList(Story story);
	
	public void insertComment();
	
	public void deleteComment();
	
	public void updateComment();
	
	// 댓글 내용 검색 
	public Comment selectCommentByContent(Comment comment);

}
