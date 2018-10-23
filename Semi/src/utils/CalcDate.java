package utils;

import java.util.Date;

import dto.plan.Plan;

public class CalcDate {
	
	//1번째 인자 Date와 2번째 인자 date의 일수차계산
	public int CalcPriod(Date start, Date end){     
		
		// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
        long diff = end.getTime() -start.getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000)) + 1;
	
		return diffDays;
	}

}

