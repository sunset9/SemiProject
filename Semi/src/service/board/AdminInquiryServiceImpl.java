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
import dto.board.Reply;
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
	public String getSearch(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getTotalCount(String search) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Inquiry> getPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Inquiry> getMainPagingList(Paging paging) {
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
	public void insertFile(InqFile file) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getId(Inquiry inq) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getNick(Inquiry inq) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean checkWriter(HttpServletRequest req, Inquiry inq) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void insertRepley(Reply reply) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void answerOk(Reply reply) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
