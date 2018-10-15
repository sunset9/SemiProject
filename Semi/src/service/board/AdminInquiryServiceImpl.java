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

public class AdminInquiryServiceImpl implements AdminInquiryService {
	private InquiryDao dao= new InquiryDaoImpl(); 
	private InqFileDao fileDao = new InqFileDaoImpl();
	@Override
	public int getCurPage(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Inquiry> getPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Inquiry view(Inquiry inq) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Inquiry getParam(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Inquiry inq) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteInqFile(InqFile file) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public InqFile viewFile(Inquiry inq) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Inquiry> notAnswerList() {
		// TODO Auto-generated method stub
		return null;
	}

}
