package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.InqFileDao;
import dao.board.InqFileDaoImpl;
import dao.board.InquiryDao;
import dao.board.InquiryDaoImpl;
import dao.board.ReplyDao;
import dao.board.ReplyDaoImpl;
import dto.board.InqFile;
import dto.board.Inquiry;
import dto.board.Reply;
import utils.Paging;

public class AdminInquiryServiceImpl implements AdminInquiryService {
	private InquiryDao inquiryDao= new InquiryDaoImpl(); 
	private InqFileDao fileDao = new InqFileDaoImpl();
	private ReplyDao replyDao = new ReplyDaoImpl();
	
	@Override
	public int getCurPage(HttpServletRequest req) {
		// 요청 파라미터 받아오기
		String curPage = req.getParameter("curPage");
		
		// null이나 공백이 아닐 때 변수값 반환하기
		if( curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}
			
		// null이나 공백일 때 0 반환
		return 0;
	}

	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");

		return search;
	}
	@Override
	public int getTotalCount(String search) {
		return inquiryDao.selectCntAll(search);
	}
	
	@Override
	public List<Inquiry> getPagingList(Paging paging) {
		return inquiryDao.selectPagingList(paging);
	}
	
	@Override
	public List<Inquiry> getMainPagingList(Paging paging) {
		
		return inquiryDao.selectInqByAnswer(paging);
	}
	
	@Override
	public Inquiry view(Inquiry inq) {
		Inquiry inquiry = new Inquiry();
		inquiryDao.updateHit(inq);
		inquiry = inquiryDao.selectInqByInqIdx(inq);
		
		return inquiry;
	}
	
	@Override
	public Inquiry getParam(HttpServletRequest req, HttpServletResponse resp) {

		// 파라미터 담을 객체 만들기
		Inquiry inquiry = new Inquiry();
		
		// 요청에 있는 inq넘버 받아오기
		String inq_idx = req.getParameter("inq_idx");
		
		// inq_idx 가 null이나 빈값이 아닐때 DTO에 저장하기
		if(inq_idx != null && !"".equals(inq_idx)) {
			inquiry.setInq_idx(Integer.parseInt(inq_idx));
		}
//		System.out.println("inqservice inq: "+ inquiry);
		// DTO 객체 반환하기
		return inquiry;
	}
	
	@Override
	public void delete(Inquiry inq) {
		inquiryDao.delete(inq);
		fileDao.delete(inq);
		
	}

	@Override
	public InqFile viewFile(Inquiry inq) {
		return fileDao.selectByInqIdx(inq);
	}
	
	@Override
	public void insertFile(InqFile file) {
		fileDao.insert(file);		
	}
	@Override
	public String getId(Inquiry inq) {
		return inquiryDao.selectIdByInq_idx(inq);
	}
	@Override
	public String getNick(Inquiry inq) {
		return inquiryDao.selectNickByInq_idx(inq);
	}
	@Override
	public boolean checkWriter(HttpServletRequest req, Inquiry inq) {

		int loginId = (int) req.getSession().getAttribute("user_idx");
		Inquiry inquiry= inquiryDao.selectInqByInqIdx(inq);
		
		if(loginId != inquiry.getUser_idx()) {
			return false;
		}
		return true;
	}
	
	@Override
	public void insertRepley(Reply reply) {
		replyDao.insertReply(reply);		
	}
	@Override
	public void answerOk(Reply reply) {
		replyDao.answerUpdate(reply);		
	}
	@Override
	public boolean deleteReply(Reply reply) {
replyDao.delete(reply);
		
		if(replyDao.countReply(reply) > 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void inqListDelete(String names) {
		fileDao.deleteInqListFile(names);
		inquiryDao.deleteInqList(names);
		
		
	}

	@Override
	public boolean loginCheck(HttpServletRequest req) {
		boolean check =false;
		
		if(req.getSession().getAttribute("login")!=null){
			check = (boolean)req.getSession().getAttribute("login");
		}
		
//		System.out.println("check : " +check);
		return check;
	}
	
	

}
