package utils;

import java.util.Date;

import dto.plan.Plan;

public class CalcDate {
	
	//여행 기간 계산
	public int CalcPriod(Plan plan){     
		
		// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
        long diff = plan.getEnd_date().getTime() - plan.getStart_date().getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000)) + 1;
	
		return diffDays;
	}

}

