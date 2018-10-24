package controller.Reply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import org.apache.tomcat.jni.User;

import dto.board.Reply;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/reply/insert")
public class ReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private InquiryService inquiryService = new InquiryServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
	
		req.setCharacterEncoding("UTF-8");
		
		Reply reply = new Reply();
		
		String inq_idx = req.getParameter("inq_idx");
		String content = req.getParameter("content");
		
//		System.out.println(inq_idx+"," +content);
		
		reply.setInq_idx(Integer.parseInt(inq_idx));
		reply.setUser_idx((int)req.getSession().getAttribute("user_idx"));
		reply.setUserid((String) req.getSession().getAttribute("userid"));
		reply.setContent(content);
		
//		System.out.println("reply insert"+reply);
		
		inquiryService.insertRepley(reply);
		inquiryService.answerOk(reply);
		
		resp.sendRedirect("/inquiry/view?inq_idx="+reply.getInq_idx());
	}

}
