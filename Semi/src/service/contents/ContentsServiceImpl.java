package service.contents;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.contents.ContentsDao;
import dao.contents.ContentsDaoImpl;
import dto.board.Inquiry;
import dto.plan.Plan;
import utils.Paging;

public class ContentsServiceImpl implements ContentsService {

	ContentsDao conDao = new ContentsDaoImpl();
	
	//검색한 리스트 가져오기
	@Override
	public List<Plan> getList(String category, String searchValue) {
		return conDao.selectList(category, searchValue);
	}

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
		return conDao.selectCntAll(searchType, search);
	}

	//페이징 된 리스트 불러오기
	@Override
	public List<Plan> getPagingList(Paging paging) {
		return conDao.selectPagingList(paging);
	}


	
}
