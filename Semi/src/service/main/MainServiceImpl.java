package service.main;

import java.util.List;

import dao.main.MainDao;
import dao.main.MainDaoImpl;
import dto.plan.Plan;

public class MainServiceImpl implements MainService {

	MainDao mainDao = new MainDaoImpl();
	
	//추천 콘텐츠 리스트 가져오기 
	@Override
	public List getRecommendedContents() {
		
		return mainDao.getRecommendedContents();
	}

	//최신콘텐츠 리스트 가져오기 
	@Override
	public List getNewestContents() {
		
		return mainDao.getNewestContents();
	}

	//새 일정 정보 파라미터로 받아오기
	@Override
	public Plan getNewPlanParam() {
		
		return null;
	}
	
	

}
