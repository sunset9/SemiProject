package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.NoticeDao;
import dao.board.NoticeDaoImpl;
import dto.board.Notice;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;
import utils.Paging;




@WebServlet("admin/notice/list")
public class AdminNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoticeService noticeService = new NoticeServiceImpl();
	private NoticeDao noticeDao = new NoticeDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//----- ����¡ �۾� -----
		// ���� ������ ��ȣ ���
		int curPage = noticeService.getCurPage(req);
		
		// ����¡ ��ü
		int totalCount = noticeService.getTotalCount();
		
		Paging paging = new Paging(totalCount, curPage,10); 
		
		// List�� ��ȸ ��� ���
		List<Notice> list = noticeService.getPagingList(paging);
		
		// ��û�� ��� ��Ƽ� ������
		req.setAttribute("noticelist", list);
		
		// ����¡ ��ü �𵨷� �߰� �ϱ�
		req.setAttribute("paging", paging);
		
		// ������ ȭ�� ����
		req.getRequestDispatcher("").forward(req, resp);
		
		
	}
}
