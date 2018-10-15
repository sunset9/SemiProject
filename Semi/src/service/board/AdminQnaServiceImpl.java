package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.QnaDao;
import dao.board.QnaDaoImpl;
import dao.board.QnaFileDao;
import dao.board.QnaFileDaoImpl;
import dto.board.Qna;
import dto.board.QnaFile;
import utils.Paging;

public class AdminQnaServiceImpl implements  AdminQnaService{

	private QnaDao qnaDao = new QnaDaoImpl();
	private QnaFileDao qnaFileDao  = new QnaFileDaoImpl();
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
	public List<Qna> getPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Qna view(Qna qna) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Qna getParam(HttpServletRequest res, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void write(Qna qna) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Qna qna) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Qna qna) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteQnaFile(QnaFile file) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public QnaFile viewFile(Qna qna) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insertFile(QnaFile file) {
		// TODO Auto-generated method stub
		
	}
	


}
