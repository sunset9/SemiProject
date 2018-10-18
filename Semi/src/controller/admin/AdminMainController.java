package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Inquiry;
import service.board.AdminInquiryService;
import service.board.AdminInquiryServiceImpl;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/admin/main")
public class AdminMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminInquiryService adminInquiryService = new AdminInquiryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Inquiry> inqList = adminInquiryService.notAnswerList();

	}
}
