package controller.timetable;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class apiTestController
 */
@WebServlet("/api/test/aa")
public class apiTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4"
//				+ "&fields=name,rating,formatted_phone_number&key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q").forward(req, resp);
		resp.sendRedirect("https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJN1t_tDeuEmsRUsoyG83frY4"
				+ "&fields=name,rating,formatted_phone_number&key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("hh");
	}
}
