package service.contents;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.board.Inquiry;
import dto.plan.Plan;
import utils.Paging;

public interface ContentsService {

	// 현재 페이지 얻어오기 
	public int getCurPage(HttpServletRequest req);

	// 검색타입 얻어오기
	public int getSearchType(HttpServletRequest req);
	
	// 검색어 얻어 오기 
	public String getSearch(HttpServletRequest req);

	// 전체 게시물 수 얻어오기
	public int getTotalCount(int searchType, String search);

	// 페이징 된 리스트 불러오기
	public List<Plan> getPagingList(Paging paging);
	
	// 추천 게시물 페이징 해서 불러오기
	public List<Plan> getRecomPagingList(Paging paging);
	
	// 최신 게시물 페이징 해서 불러오기 
	public List<Plan> getNewPagingList(Paging paging);

}
