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
	private InquiryDao inquiryDao= new InquiryDaoImpl(); 
	private InqFileDao fileDao = new InqFileDaoImpl();
	
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
		
		// DTO 객체 반환하기
		return inquiry;
	}

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
	public int getTotalCount() {
		return inquiryDao.selectCntAll();
	}

	@Override
	public List<Inquiry> getPagingList(Paging paging) {
		
		return inquiryDao.selectPagingList(paging);
	}

	@Override
	public Inquiry view(Inquiry inq) {
		inquiryDao.selectInqByInqIdx(inq);
		inquiryDao.updateHit(inq);
		
		return null;
	}


	@Override
	public void write(Inquiry inq) {
		inquiryDao.insert(inq);
		
	}

	@Override
	public void update(Inquiry inq) {
		inquiryDao.update(inq);
	}

	@Override
	public void delete(Inquiry inq) {
		inquiryDao.delete(inq);
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
