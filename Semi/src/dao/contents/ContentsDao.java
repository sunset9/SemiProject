package dao.contents;

import java.util.List;

import dto.board.Inquiry;
import dto.plan.Plan;
import utils.Paging;

public interface ContentsDao {

	//리스트 검색하기
	public List<Plan> selectList(String category, String searchValue);
	
	// 전체 게시물 수 조회
	public int selectCntAll( int searchType, String search) ;

	// 페이징 된 plan 리스트 조회
	public List<Plan> selectPagingList(Paging paging);

	
}
