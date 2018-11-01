package controller.plan;

import java.io.IOException;
import java.io.PrintWriter;

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
import dto.plan.Plan;
import dto.user.UploadFile;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;

/**
 * Servlet implementation class PlanBannerUpdateController
 */
@WebServlet("/plan/banner")
public class PlanBannerUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("/user/update.jsp").forward(req, resp);
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- PlanViewController -----");
		// 인코딩 설정 
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 응답객체 출력 스트림
		PrintWriter out = resp.getWriter();
		
		// MultipartRequest 생성자 매개변수 준비 시작
		// 1. 요청 객체는 이미 있음.
		
		// 2. 파일 저장 위치 - String으로 서버의 실제 경로 지정
		ServletContext context = getServletContext();
		String saveDirectory = context.getRealPath("upload/banner");
		System.out.println("saveDirectory" + saveDirectory);
		
		// 3. 업로드 제한 사이즈 
		int maxPostSize = 10 * 1024 * 1024; //10MB
		
		// 4. 인코딩 - 업로드 정보 인코딩 방식 
		String encoding = "UTF-8";
		
		// 5. 중복 파일 이름 정책 
		//		DefaultFileRenamePolicy는 중복파일이 있으면
		//		파일이름 뒤에 숫자를 추가하고 1부터 증가시킨다.
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		// 매개변수 준비 끝
		
		
		// MultipartRequest 객체 생성 시작 
		// 파일 업로드 처리 시작
		MultipartRequest mul = new MultipartRequest(
				req,
				saveDirectory,
				maxPostSize,
				encoding,
				policy
				);

		UploadFile uploadFile = new UploadFile();
		
		// 저장된 파일 이름 
		uploadFile.setStored_name(mul.getFilesystemName("uploadFile"));
		
		// 원본 파일 이름 
		uploadFile.setOrigin_name(mul.getOriginalFileName("uploadFile"));
		
		PlanDao pDao = new PlanDaoImpl();
//		userDao.insert(uploadFile);

		String fullpath = saveDirectory+"/"+uploadFile.getStored_name();
		System.out.println("fullpath : "+fullpath);
		
		String path = "/upload/banner/"+uploadFile.getStored_name();
		System.out.println("path : "+path);
		
		PlanService pService = new PlanServiceImpl();		
		
		Plan planParam = new Plan();
		planParam.setPlan_idx(Integer.parseInt(mul.getParameter("plan_idx")));
		Plan planView = pService.getPlanInfo(planParam);
		
		planView.setBannerURL(path);
		
		// DB에서 유저의 profile 수정 
		pDao.bannerUpdate(planView);
		
		System.out.println(planView);
		
		req.getSession().setAttribute("planView", planView);
	
		resp.sendRedirect("/plan/write?plan_idx="+planParam.getPlan_idx());
	}
}
