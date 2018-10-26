package dao.contents;

import java.util.List;

import dto.plan.Plan;

public interface ContentsDao {

	//리스트 검색하기
	public List<Plan> selectList(String category, String searchValue);
}
