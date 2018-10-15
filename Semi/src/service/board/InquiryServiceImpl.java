package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.InqFileDao;
import dao.board.InqFileDaoImpl;
import dao.board.InquiryDao;
import dao.board.InquiryDaoImpl;
import dto.board.InqFile;
import dto.board.Inquiry;
import utils.Paging;

public class InquiryServiceImpl implements InquiryService {
	private InquiryDao dao= new InquiryDaoImpl(); 
	private InqFileDao fileDao = new InqFileDaoImpl();
	
	@Override
	public int getCurPage(HttpServletRequest req) {
		
		return 0;
	}

	@Override
	public int getTotalCount() {
		return 0;
	}

	@Override
	public List<Inquiry> getPagingList(Paging paging) {
		
		return dao.selectPagingList(paging);
	}

	@Override
	public Inquiry view(Inquiry inq) {
		dao.selectInqByInqIdx(inq);
		dao.updateHit(inq);
		
		return null;
	}

	@Override
	public Inquiry getParam(HttpServletRequest req, HttpServletResponse resp) {
		return null;
	}

	@Override
	public void write(Inquiry inq) {
		dao.insert(inq);
		
	}

	@Override
	public void update(Inquiry inq) {
		dao.update(inq);
	}

	@Override
	public void delete(Inquiry inq) {
		dao.delete(inq);
	}

	@Override
	public void deleteInqFile(InqFile file) {
		
		fileDao.delete(file);
	}

	@Override
	public InqFile viewFile(Inquiry inq) {
		
		return fileDao.selectByInqIdx(inq);
	}

	@Override
	public void insertFile(InqFile file) {
		fileDao.insert(file);
	}



}
