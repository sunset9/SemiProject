<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- fullcalendar -->
<link rel='stylesheet' href='/resources/timetable/fullcalendar/fullcalendar.css' />
<link href='/resources/timetable/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src='/resources/timetable/jquery-ui.min.js'></script>
<script type="text/javascript" src='/resources/timetable/moment.min.js'></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/scheduler.min.js"></script>
<script src='/resources/timetable/fullcalendar/locale-all.js'></script>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 개인 utils -->
<script type="text/javascript" src="/utils/timetableUtils.js"></script>

<style>

  body {
    margin-top: 240px;
    text-align: center;
    font-size: 14px;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
  }

  #wrap {
    width: 1100px;
    margin: 0 auto;
  }

  #external-events{
    float: left;
    width: 150px;
    padding: 0 10px;
    border: 1px solid #ccc;
    background: #eee;
    text-align: left;
  }

  #external-events h4 {
    font-size: 16px;
    margin-top: 0;
    padding-top: 1em;
  }

  #external-events .fc-event  {
    margin: 10px 0;
    cursor: pointer;
  }

  #external-events p {
    margin: 1.5em 0;
    font-size: 11px;
    color: #666;
  }

  #external-events p input {
    margin: 0;
    vertical-align: middle;
  }

  #calendar {
    float: right;
    width: 900px;
    height: 790px;
    margin-bottom: 20px;
  }
  
  #btnStore {
  	float: left;
  	width: 170px;
  	padding: 0px 10px;
  	margin-top: 10px;
  }
  
  .fc-ltr .fc-time-grid .fc-event-container { /* 일정 이벤트 박스 관련 */ 
  	margin: 0 3px 0 3px; 
  	font-size: 1.1em;
  }
  
  .fc-axis { /* 왼쪽 시간  넓이 수정 */ 
  	width: 60px !important;
  }
  
  #prevBtn { /* 이전날짜 선택 버튼*/
  	cursor: pointer;
  	float: left;
  	margin-left: 5px;
  }
  
  #nextBtn { /* 다음 날짜 선택 버튼*/
  	cursor: pointer;
  	float: right;
  	margin-right: 5px;
  }
  
  .fc-row.fc-widget-header tr{ /* 헤더의 날짜 정보*/
  	height: 50px;
  	font-size: 1.3em;
  }
  
  .fc-title{ /* 이벤트 안의 제목 부분 */
  	font-weight: bold;
  	font-size: 1.5em;
  	padding-top: 5px;
  }
  
  .fc-bg:not(:first-child){
  	margin-left: 10px;  /* fc-bg(이벤트 덮고 있는 투명도 있는 판?)css 수정 , 왼쪽에 색 진하게 하는 효과줌*/
  }
</style>

<script type="text/javascript">
var planStartDate = '2018-04-07';
var planEndDate = '2018-04-11';

$(document).ready(function(){
	var timetables = getTimetables();
	
	initFullCalendar(planStartDate, planEndDate, timetables);
});

function getTimetables(){
	var timetables=[];
	
	var ttbList = ${ttbList };
	var locList = ${locList };
	
	for(var i = 0; i<ttbList.length; i++){
		var timetable = {
			place_name: locList[i].place_name
			, start_date: ttbList[i].start_date
			, end_date: ttbList[i].end_date
			, lat: locList[i].lat
			, lon: locList[i].lon
			, address: locList[i].address
		}
		
		timetables.push(timetable);
	}
	
	console.log(timetables);
}
</script>
</head>
<body>
ss
</body>
</html>