package dao.story;

import java.util.List;

import dto.story.Comment;
import dto.plan.Plan;
import dto.story.Story;
import dto.timetable.Timetable;
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

	// 삭제된 타임테이블에 걸려있는 스토리 삭제
	public void delete(Plan plan, List<Timetable> ttbList);
	
	//플랜 삭제시 스토리 전부 삭제
	public void deleteList(Plan plan);
	
	//타임테이블 삭제시 해당 스토리 삭제
	public void deleteListByTtbIdx(Timetable tb);

	
	public int SelectCntAll();
	
	//현재 스토리의 인덱스값 불러오기
	public int SelectStoryIdx();
	
	//planidx로 커멘드 불러오기
	public List<Comment> selectCommentByPlanIdx(Plan plan);
	
	//커멘드 idx로 커멘드 검색
	public Comment selectCommentByCommentIdx(Comment cmt);
	
	//스토리삭제시 등록된 모든 코멘트 삭제
	public void deleteCommentListByStoryIdx(Story story);
	
	//해당플랜의 모든 커멘드 삭제
	public void deleteCommentListByPlanIdx(Plan plan);
	
	//해당 타임테이블과 연동된 스토리의 모든 커멘드 삭제
	public void deleteCommentListByTtbIdx(Timetable tb);
	
	//커멘트 추가
	public void insertComment(Comment comment);
	
	//커멘트 삭제
	public void deleteComment(Comment comment);
	
	//커멘트 업데이트
	public void updateComment(Comment comment);
	
	// 댓글 내용 검색 
	public Comment selectCommentByContent(Comment comment);
	
	//현재 커멘드 인덱스 불러오기
	public int selectCommentIdx();
	
	//댓글 갯수
	public int selectCntComm(Story story);


}
