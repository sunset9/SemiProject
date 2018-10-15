package service.main;

import java.util.List;

import dao.main.MainDao;
import dao.main.MainDaoImpl;

public class MainServiceImpl implements MainService {

	MainDao mainDao = new MainDaoImpl();
	
	@Override
	public List getRecommendedContents() {
		
		return mainDao.getRecommendedContents();
	}

	@Override
	public List getNewestContents() {
		
		return mainDao.getNewestContents();
	}

}
