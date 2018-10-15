package dao.main;

import java.util.List;

public interface MainDao {

	//추천콘텐츠 검색
	List getRecommendedContents();
	
	//최신콘텐츠 검색
	List getNewestContents();
}
