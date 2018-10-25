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

public class QnaServiceImpl implements QnaService{

	private QnaDao qnaDao = new QnaDaoImpl();
	private QnaFileDao qnaFileDao  = new QnaFileDaoImpl();
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
	public int getTotalCount(String search) {
		return qnaDao.selectCntAll(search);
	}
	
	@Override
	public List<Qna> getPagingList(Paging paging) {
		return qnaDao.selectPagingList(paging);
	}
	
	@Override
	public Qna view(Qna qna) {
		Qna q = new Qna();
		qnaDao.updateHit(qna);
		q = qnaDao.selectQnaByQnaIdx(qna);
		
		return q;
	}
	@Override
	public Qna getParam(HttpServletRequest req, HttpServletResponse resp) {
		Qna qna = new Qna();
		
		String qna_idx = req.getParameter("qna_idx");
		
		if(qna_idx !=null && !"".equals(qna_idx)) {
			qna.setQna_idx(Integer.parseInt(qna_idx));
		}
		return qna;
	}
	
	@Override
	public QnaFile viewFile(Qna qna) {
		return qnaFileDao.selectFilebyQna(qna);
	}
	@Override
	public String getId(Qna qna) {
		return qnaDao.selectIdByQnaIdx(qna);
	}

	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");
		
		return search;
	}

}
