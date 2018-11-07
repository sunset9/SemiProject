package controller.plan;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import dao.plan.PlanDao;
import dao.plan.PlanDaoImpl;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import dto.user.UploadFile;
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
	PlanDao pDao = new PlanDaoImpl();
	
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

			Plan planParam = pService.getParamEdit(req);
			
			Plan planView = pService.getPlanInfo(planParam);
				// 플랜 정보 파라미터 받기 
				// 요청파라미터 -> 타임테이블, 위치정보 Map 타입
				Map<Timetable, Location> ttbLocParam = ttbService.getParam(req);
				System.out.println(ttbLocParam);
				
				// 업데이트 하려는 타임테이블 리스트로 받기 
				List<Timetable> updateTtbList = ttbService.getCompletedTimetable(ttbLocParam); 
				// 삭제된 타임테이블에 해당하는 가계부 정보 삭제
				aService.deleteList(planParam, updateTtbList);
				// 삭제된 타임테이블에 해당하는 스토리 삭제
				sService.deleteList(planParam, updateTtbList);
				// 타임테이블, 위치정보 정보 업데이트
				ttbService.update(planParam, ttbLocParam);
				
				// 일정 정보 업데이트
				pService.update(planParam);
				//----------------------------------------------------------------------------------------
				
				//배너 업데이트
				if(req.getParameter("bannerURL") != null && !"".equals((String)req.getParameter("bannerURL"))) {
					String path = (String) req.getParameter("bannerURL");
					planView.setBannerURL(path);
					// DB에서 유저의 banner 수정 
					pDao.bannerUpdate(planView);
				}
				System.out.println(planView);
				req.getSession().setAttribute("planView", planView);
				req.getSession().setAttribute("plan_idx", planView.getPlan_idx());
    }
}
