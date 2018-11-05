package controller.plan;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

@WebServlet("/plan/update")
public class PlanUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PlanService pService = new PlanServiceImpl();
	TimetableService ttbService = new TimetableServiceImpl();
	StoryService sService = new StoryServiceImpl();
	AccountService aService = new AccountServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// 업데이트 뷰 폼 띄워주기 
			req.getRequestDispatcher("/plan/view.jsp").forward(req, resp);
		}
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println();
			System.out.println("----- PlanUpdateController -----");
			req.setCharacterEncoding("utf-8");
			
			// 플랜 정보 파라미터 받기 
			Plan planParam = pService.getParamEdit(req);
			
			// 요청파라미터 -> 타임테이블, 위치정보 Map 타입
			Map<Timetable, Location> ttbLocParam = ttbService.getParam(req);
			System.out.println(ttbLocParam);
			
			// 타임테이블, 위치정보 정보 업데이트
			List<Timetable> updatedTtbList = null; 
			updatedTtbList = ttbService.update(planParam, ttbLocParam);
			System.out.println(updatedTtbList);
			// 삭제된 타임테이블에 해당하는 스토리 삭제
			sService.deleteList(planParam, updatedTtbList);
			
			// 삭제된 타임테이블에 해당하는 가계부 정보 삭제
			aService.deleteList(planParam, updatedTtbList);
			
			// 일정 정보 업데이트
			pService.update(planParam);
//			System.out.println(req.getSession().getAttribute("plan_idx"));
      
			// 저장시 넘어온 파라미터 값으로
			// 저장 후에 view / write.jsp 보내주는 곳 결정
//			boolean isWriteMode = Boolean.valueOf(req.getParameter("isSendWriteMode"));
//			if(isWriteMode) {
////				req.getRequestDispatcher("/plan/write").forward(req, resp);
//				String url = "/plan/write";
//				String param = "";
//				if(planParam.getPlan_idx() > 0) {
//					param = "?plan_idx=" + planParam.getPlan_idx();}
//				resp.sendRedirect(url + param);	
//				
//			}else{
//				resp.sendRedirect("/plan?plan_idx="+planParam.getPlan_idx());	
//			}

		}
}
