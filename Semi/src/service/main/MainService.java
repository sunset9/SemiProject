package service.main;

import java.util.List;

import dto.plan.Plan;

public interface MainService {

	//추천콘텐츠 리스트 가져오기
	public List getRecommendedContents();
	
	//최신콘텐츠 리스트 가져오기
	public List getNewestContents();
	
	//새 일정 정보(요청 파라미터) 가져오기
	public Plan getNewPlanParam();
}
