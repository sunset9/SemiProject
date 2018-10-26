package service.contents;

import java.util.List;

import dao.contents.ContentsDao;
import dao.contents.ContentsDaoImpl;
import dto.plan.Plan;

public class ContentsServiceImpl implements ContentsService {

	ContentsDao conDao = new ContentsDaoImpl();
	
	//검색한 리스트 가져오기
	@Override
	public List<Plan> getList(String category, String searchValue) {
		
		conDao.selectList(category, searchValue);
		
		
		
		return null;
	}

}
