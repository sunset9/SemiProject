package service.main;

import java.util.List;

public interface MainService {

	//추천콘텐츠 리스트 가져오기
	public List getRecommendedContents();
	
	//최신콘텐츠 리스트 가져오기
	public List getNewestContents();
}
