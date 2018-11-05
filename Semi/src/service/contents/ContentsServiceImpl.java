package service.contents;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.plan.PlanDao;
import dao.plan.PlanDaoImpl;
import dto.board.Inquiry;
import dto.plan.Plan;
import utils.Paging;

public class ContentsServiceImpl implements ContentsService {

	PlanDao planDao = new PlanDaoImpl();


	//현재 페이지 얻어오기
	@Override
	public int getCurPage(HttpServletRequest req) {
		
		// 요청 파라미터 받아오기
		String curPage = req.getParameter("curPage");
		
		// null이나 공백이 아닐 때 변수값 반환하기
		if( curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}
		
		// null이나 공백일 때 0 반환
		return 0;
	}

	//검색타입 얻어오기
	@Override
	public int getSearchType(HttpServletRequest req) {
		int searchType = Integer.parseInt(req.getParameter("searchType"));
		
		return searchType;
	}
	
	//검색어 얻어오기
	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");

		return search;
	}

	//전체 게시물 수 얻어오기
	@Override
	public int getTotalCount(int searchType, String search) {
		return planDao.selectCntAll(searchType, search);
	}

	//페이징 된 리스트 불러오기
	@Override
	public List<Plan> getPagingList(Paging paging) {
		return planDao.selectPagingList(paging);
	}

	// 추천 게시물 페이징 해서 불러오기
	@Override
	public List<Plan> getRecomPagingList(Paging paging) {
		return planDao.selectRecomPagingList(paging);
	}

	// 최신 게시물 페이징 해서 불러오기 
	@Override
	public List<Plan> getNewPagingList(Paging paging) {
		return  planDao.selectNewPagingList(paging);
	}

	// 추천 게시물 불러오기
	@Override
	public List<Plan> getRecomList() {
		return planDao.selectRecomList();
	}

	// 최신 게시물 불러오기
	@Override
	public List<Plan> getNewList() {
		return planDao.selectNewList();
	}


	
}
