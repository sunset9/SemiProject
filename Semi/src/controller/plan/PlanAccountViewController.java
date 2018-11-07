package controller.plan;

import java.io.IOException;
import java.util.EnumMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.Account.AccType;
import dto.Account.Account;
import dto.plan.Plan;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;

/**
 * Servlet implementation class PlanAccountViewController
 */
@WebServlet("/plan/accView")
public class PlanAccountViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Plan planParam = pService.getParam(req);
		
		// 가계부 정보 가져오기
		EnumMap<AccType, Long> accEnumMap = new EnumMap<AccType, Long>(AccType.class);
		
		accEnumMap.put(AccType.airfare, pService.getAccountAccTpeCost(planParam, AccType.airfare));
		accEnumMap.put(AccType.traffic, pService.getAccountAccTpeCost(planParam, AccType.traffic));
		accEnumMap.put(AccType.stay, pService.getAccountAccTpeCost(planParam, AccType.stay));
		accEnumMap.put(AccType.admission, pService.getAccountAccTpeCost(planParam, AccType.admission));
		accEnumMap.put(AccType.food, pService.getAccountAccTpeCost(planParam, AccType.food));
		accEnumMap.put(AccType.play, pService.getAccountAccTpeCost(planParam, AccType.play));
		accEnumMap.put(AccType.shop, pService.getAccountAccTpeCost(planParam, AccType.shop));
		accEnumMap.put(AccType.etc, pService.getAccountAccTpeCost(planParam, AccType.etc));
		
		long acc_total = pService.getAccountTotal(accEnumMap);
		
		long accCalcedTotal = acc_total;
		
		JsonObject obj = new JsonObject();
		obj.addProperty("accTypeCost", new Gson().toJson(accEnumMap));
		obj.addProperty("acc_total", acc_total);
		obj.addProperty("accCalcedTotal", accCalcedTotal);
		String objStr = new Gson().toJson(obj); 
		
		resp.getWriter().println(objStr);
	}
	

}
