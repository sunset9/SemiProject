package service.stroy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.story.Comment;
import dto.Account.Account;
import dto.plan.Plan;
import dto.story.Story;
import dto.timetable.Timetable;

public interface StoryService {
	//plan 번호로 스토리 리스트 불러오기 
	public List<Story> getStoryList(Plan plan); 

	//파라미터 불러오기
	public Story getParam(HttpServletRequest req);

	//스토리 미니뷰 불러오기
	public Story getStory(Story story);

	//스토리 삭제하기
	public void delete(Story story);
	
	//플랜 삭제시 스토리 전부 삭제 
	public void deleteStory(Plan plan);
	
	// 타임테이블 삭제시 스토리도 삭제
	public void deleteList(Plan plan, List<Timetable> ttbList);
	
	//스토리 업데이트
	public void update(Story story);

	// 미니뷰 업데이트
	public Story writeMini(int ttb_idx, Story storyParam, boolean isStory);
	
	//스토리 삽입
	public int write(Story story);
	
	//가계부 계산(회의때 추가함!) 

	//---------------커멘드 -------------

	//플랜의 모든 댓글 리스트 불러오기
	public List<Comment> getCommentList(Plan plan);
	
	//코멘트 가져오기
	public Comment getCommet(Comment comment);
	
	//스토리삭제시 등록된 모든 코멘트 삭제
	public void deleteCommentListByStoryIdx(Story story);
	
	//해당플랜의 모든 커멘드 삭제
	public void deleteCommentListByPlanIdx(Plan plan);
	
	//해당 타임테이블과 연동된 스토리의 모든 커멘드 삭제
	public void deleteCommentListByTtbIdx(Timetable tb);
	
	//댓글 수정
	public void updateComment(Comment cmt);
	
	//댓글 삭제
	public void deleteComment(Comment cmt);
	
	//댓글 삽입
	public void writeComment(Comment cmt);

	
	
}
