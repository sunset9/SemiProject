package service.contents;

import java.util.List;

import dto.plan.Plan;

public interface ContentsService {

	//검색한 리스트 가져오기
	public List<Plan> getList(String category, String searchValue);
	
}
