<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 헤더  -->
<c:import url="../layout/headerWithMenu.jsp" />
<!-- 미니뷰 modal -->
<jsp:include page="/plan/timetable/miniView.jsp"/>

<!-- fullcalendar -->
<link rel='stylesheet' href='/resources/timetable/fullcalendar/fullcalendar.css' />
<link href='/resources/timetable/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />

<script type="text/javascript" src="/resources/timetable/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/scheduler.min.js"></script>
<script src='/resources/timetable/fullcalendar/locale-all.js'></script>

<!-- timetable utils -->
<script type="text/javascript" src="/utils/timetableUtils.js"></script>
<script type="text/javascript" src="/utils/mapUtils.js"></script>

<!-- Maps JavaScript API 로드 -->
<script async defer
 src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&libraries=places&language=ko&callback=initMap">
</script>

<!-- 원형 그래프 -->
<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
		<script> zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
		ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9","ee6b7db5b51705a13dc2339db3edaf6d"];</script>

<!-- 공개유무 슬라이드 버튼 -->
<style type="text/css">
	/* 가계부 그래프 팝업 */
	.pop-layer .pop-container {
	  padding: 20px 25px;
	  margin:0px;
	  display:inline-block;
	  font-size:15px;
	  width: 700px;
	  height: 700px;
	}
	
	.pop-layer p.ctxt {
	  color: #666;
	  line-height: 25px;
	}
	
	.pop-layer .btn-r {
	  width: 100%;
	  margin: 10px 0 20px;
	  padding-top: 10px;
	  border-top: 1px solid #DDD;
	  text-align: right;
	}
	
	.pop-layer {
	  display: none;
	  position: absolute;
	  top: 50%;
	  left: 50%;
	  width: 800px;
	  height: 800px;
	  background-color: #fff;
	  border: 5px solid #3571B5;
	  z-index: 10;
	}
	
	.dim-layer {
	  display: none;
	  position: fixed;
	  _position: absolute;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  z-index: 100;
	}
	
	.dim-layer .dimBg {
	  position: absolute;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  background: #000;
	  opacity: .5;
	  filter: alpha(opacity=50);
	}
	
	.dim-layer .pop-layer {
	  display: block;
	}
	
	a.btn-layerClose {
	  display: inline-block;
	  height: 25px;
	  padding: 0 14px 0;
	  border: 1px solid #304a8a;
	  background-color: #3f5a9d;
	  font-size: 13px;
	  color: #fff;
	  line-height: 25px;
	}
	
	a.btn-layerClose:hover {
	  border: 1px solid #091940;
	  background-color: #1f326a;
	  color: #fff;
	}
/* 	----------------------------------------------------- */
/* 	구글 맵 크기 설정 */
	#map {
		height: 100%;
	}
/* 	----------------------------------------------------- */  
	#calendar {
	    float: right;
	    width: 900px;
	    height: 833px;
	    margin-bottom: 20px;
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
		cursor: pointer;
		font-weight: bold;
		font-size: 1.5em;
		padding-top: 5px;
	}
	
	.fc-bg:not(:first-child){
		margin-left: 10px;  /* fc-bg(이벤트 덮고 있는 투명도 있는 판?)css 수정 , 왼쪽에 색 진하게 하는 효과줌*/
	}
	
</style>

<script>

// 서버에서 넘어온 일정의 시작, 끝 날짜 정보
var planStartDate = '${planView.start_date}';
var planEndDate = '${planView.end_date}';
// 서버에서 넘어온 timetable, location 정보들
var ttbList = ${ttbList };
var locList = ${locList };

var plan_idx = ${planView.plan_idx};

var is_diplayStory = false;

var cost = [
		"${airfare}",
		"${traffic}",
		"${stay}",
		"${admission}",
		"${food}",
		"${play}",
		"${shop}",
		"${etc}"
	];
	var airfare = "${airfare}";


</script>

<script type="text/javascript">
// 읽기모드일때, 검색창 on/off
var isModify = 0;
	console.log("view.jsp isModify : " + isModify);
	
$(document).ready(function() {
	
	// 브라우저에 timetable 그려주기
	initFullCalendar(planStartDate, planEndDate, true);
	
// 	function diplayStory() {
// 		if( ${is_displayStory ne null} && ${is_displayStory eq ""}){
// 			is_diplayStory = ${is_displayStory};
// 		}else{
// 			is_displayStory = false;
// 		}
		
// 		if(is_display == true){
// 			document.getElementById("calendar").style.display= "none";
// 			document.getElementById("viewStory").style.display= "block";
			
// 			$.ajax({ 	
// 				type: "get"
// 				, url: "/story/view"
// 				, data: {"plan_idx" : plan_idx }
// 				, dataType: "html"
// 				, success: function( d ) {
// 					$("#viewStory").html(d);
// 				}
// 				, error: function() {
// 					console.log("실패");
// 				}
// 			});
			
// 		}else{
// 			document.getElementById("calendar").style.display= "block";
// 			document.getElementById("viewStory").style.display= "none";
// 		}
// 	}
	
// 	displayStory();
	
// 	스토리탭
	$("#btnStory").click(function() {
		document.getElementById("calendar").style.display= "none";
		document.getElementById("viewStory").style.display= "block";
		document.getElementById("googleMap").style.display= "none";
		document.getElementById("googleSearch").style.display= "none";

		
		//AJAX 처리하기
		$.ajax({ 	
			type: "get"
			, url: "/story/view"
			, data: {"plan_idx" : plan_idx }
			, dataType: "html"
			, success: function( d ) {
				$("#viewStory").html(d);
			}
			, error: function() {
				console.log("실패");
			}
		});
				
	});
// 	타임테이블탭
	$("#btnPlan").click(function() {
		document.getElementById("viewStory").style.display= "none";
		document.getElementById("calendar").style.display= "block";
		document.getElementById("googleMap").style.display= "block";
	});
	
// 	수정버튼
	$("#btnModify").click(function() {
		isModify = 1;
		$("#Modify").submit();
		console.log("view.jsp isModify : " + isModify);
	});

	// 	수정모드일 때, 공개유무버튼
	$("#isChecked").click(function(){
			
// 		  $("p").toggle();
		  var check = $("input[type='checkbox']").is(':checked');
		  
		  if(check) {
			document.getElementById("isClose").style.display= "none";
			document.getElementById("isOpen").style.display= "block";
		  } else {
			document.getElementById("isClose").style.display= "block";
			document.getElementById("isOpen").style.display= "none";
		  }
			console.log(check);
		});
	
// 		// 타임테이블 읽기 모드로 변경
// 		$('#calendar').fullCalendar('option', 'editable', false); // 수정 불가능하게
// 		$('#calendar').fullCalendar('option', 'droppable', false); // 드롭 불가능하게

// 	});
	
// 	북마크 버튼
	$("#btnBookMark").click(function() {
		document.getElementById("viewStory").style.display= "none";
		document.getElementById("calendar").style.display= "block";
		document.getElementById("googleMap").style.display= "block";
		document.getElementById("googleSearch").style.display= "block";
	});
	
// 	가계부 그래프 팝업 동작
	$("#btnAccGraph").click(function(){
	    var $el = $($(this).attr('href'));        //레이어의 id를 $el 변수에 저장
	    var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

	    isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

	    var $elWidth = ~~($el.outerWidth()),
	        $elHeight = ~~($el.outerHeight()),
	        docWidth = $(document).width(),
	        docHeight = $(document).height();

	    // 화면의 중앙에 레이어를 띄운다.
	    if ($elHeight < docHeight || $elWidth < docWidth) {
	        $el.css({
	            marginTop: -$elHeight /2,
	            marginLeft: -$elWidth/2
	        })
	    } else {
	        $el.css({top: 0, left: 0});
	    }

	    $el.find('a.btn-layerClose').click(function(){
	        isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
	        return false;
	    });

	    $('.layer .dimBg').click(function(){
	        $('.dim-layer').fadeOut();
	        return false;
	    });
	});
	
	$("#totalGraph").click(function() {
		document.getElementById("idTotal").style.display= "block";
		document.getElementById("idDaily").style.display= "none";
		
		var myConfig = {
				  "type":"pie",
				  "title":{
				    "text":"전체"
				  },
				  "legend":{
				    "x":"75%",
				    "y":"25%",
				    "border-width":1,
				    "border-color":"gray",
				    "border-radius":"5px",
				    "header":{
				      "text":"전체",
				      "font-family":"Georgia",
				      "font-size":12,
				      "font-color":"#3333cc",
				      "font-weight":"normal"
				    },
				    "marker":{
				      "type":"circle"
				    },
				    "toggle-action":"remove",
				    "minimize":true,
				    "icon":{
				      "line-color":"#9999ff"
				    },
				    "max-items":8,
				    "overflow":"scroll"
				  },
				  "plotarea":{
				    "margin-right":"30%",
				    "margin-top":"15%"
				  },
				  "plot":{
				    "value-box":{
				      "text":"%v",
				      "font-size":12,
				      "font-family":"Georgia",
				      "font-weight":"normal",
				          "placement":"out",
				          "font-color":"gray",
				    },
				    "tooltip":{
				      "text":"%t: %v (%npv%)",
				      "font-color":"black",
				      "font-family":"Georgia",
				      "text-alpha":1,
				      "background-color":"white",
				      "alpha":0.7,
				      "border-width":1,
				      "border-color":"#cccccc",
				      "line-style":"dotted",
				      "border-radius":"10px",
				      "padding":"10%",
				      "placement":"node:center"
				    },
				    "border-width":1,
				    "border-color":"#cccccc",
				    "line-style":"dotted"
				  },
				  "series":[
						{ "values":[cost[0]], "background-color":"#cc0000","text":"항공료"},
					    { "values":[cost[1]], "background-color":"#ff6600", "text":"교통"},
					    { "values":[cost[2]], "background-color":"#ffcc00", "text":"숙박"},
					    { "values":[cost[3]], "background-color":"#88cc00", "text":"입장료"},
					    { "values":[cost[4]], "background-color":"#66ccff", "text":"음식" },
					    { "values":[cost[5]], "background-color":"#0066ff", "text":"오락" },
					    { "values":[cost[6]], "background-color":"#6600ff", "text":"쇼핑" },
					    { "values":[cost[7]], "background-color":"#9999ff", "text":"기타" }
					  ]
				};
				 
				zingchart.render({ 
					id : 'total', 
					data : myConfig, 
					height: 600, 
					width: "100%" 
				});
	});
	
	$("#dailyGraph").click(function() {
		document.getElementById("idTotal").style.display= "none";
		document.getElementById("idDaily").style.display= "block";
		
		var myConfig = {
			  "type":"pie",
			  "title":{
			    "text":"일일"
			  },
			  "legend":{
			    "x":"75%",
			    "y":"25%",
			    "border-width":1,
			    "border-color":"gray",
			    "border-radius":"5px",
			    "header":{
			      "text":"일일",
			      "font-family":"Georgia",
			      "font-size":12,
			      "font-color":"#3333cc",
			      "font-weight":"normal"
			    },
			    "marker":{
			      "type":"circle"
			    },
			    "toggle-action":"remove",
			    "minimize":true,
			    "icon":{
			      "line-color":"#9999ff"
			    },
			    "max-items":8,
			    "overflow":"scroll"
			  },
			  "plotarea":{
			    "margin-right":"30%",
			    "margin-top":"15%"
			  },
			  "plot":{
			    "value-box":{
			      "text":"%v",
			      "font-size":12,
			      "font-family":"Georgia",
			      "font-weight":"normal",
			          "placement":"out",
			          "font-color":"gray",
			    },
			    "tooltip":{
			      "text":"%t: %v (%npv%)",
			      "font-color":"black",
			      "font-family":"Georgia",
			      "text-alpha":1,
			      "background-color":"white",
			      "alpha":0.7,
			      "border-width":1,
			      "border-color":"#cccccc",
			      "line-style":"dotted",
			      "border-radius":"10px",
			      "padding":"10%",
			      "placement":"node:center"
			    },
			    "border-width":1,
			    "border-color":"#cccccc",
			    "line-style":"dotted"
			  },
			  "series":[
				{ "values":[cost[0]], "background-color":"#cc0000","text":"항공료"},
			    { "values":[cost[1]], "background-color":"#ff6600", "text":"교통"},
			    { "values":[cost[2]], "background-color":"#ffcc00", "text":"숙박"},
			    { "values":[cost[3]], "background-color":"#88cc00", "text":"입장료"},
			    { "values":[cost[4]], "background-color":"#66ccff", "text":"음식" },
			    { "values":[cost[5]], "background-color":"#0066ff", "text":"오락" },
			    { "values":[cost[6]], "background-color":"#6600ff", "text":"쇼핑" },
			    { "values":[cost[7]], "background-color":"#9999ff", "text":"기타" }
			  ]
			};
			 
			zingchart.render({ 
				id : 'daily', 
				data : myConfig, 
				height: 600, 
				width: "100%" 
			});
	});
	
	// 일정 일자 변경할때의 처리
	var beforeStartDate = planStartDate;
	var beforeEndDate = planEndDate;
	$(".planDate").on("change", function(){
		// 바뀐 날짜 값 받아오기
		var changedStartDate = $(".planDate[name='editStartDate']").val();
		var changedEndDate = $(".planDate[name='editEndDate']").val();
		
		// 예외처리
		if(changedStartDate > changedEndDate){
			alert("일정의 마지막일이 시작일보다 작을 수 없습니다.");
			$(".planDate[name='editStartDate']").val(beforeStartDate);
			$(".planDate[name='editEndDate']").val(beforeEndDate);
			return;
		}
		
		// 바뀐 시작일이 기존 시작일보다 큰 경우(미래인 경우) 
		var alertStartDate = moment(changedStartDate) > moment(planStartDate);
		// 바뀐 종료일이 기족 종료일보다 작은 경우(과거인 경우)
		var alertEndDate = moment(changedEndDate) <  moment(planEndDate);
		if( alertStartDate || alertEndDate ){
			// 경고창 띄워주기
			$.ajax({
				url: '/plan/timetable/alert.jsp'
				, method: "GET"
				, dataType: "html"
				, success: function(d){
					$('body').append(d);
					$("#alertOnDateChange").modal('show');
					// 모달창이 켜지면
					$("#alertOnDateChange").on('shown.bs.modal',function(e){
						// 클릭 이벤트 걸어줌
						$("#btnOkOnDateChange").on("click", function(){
							// 바뀐 날짜 정보 저장해놓기 (추후에 캔슬했을 때 이 값으로 다시 돌려놓음)
							beforeStartDate = changedStartDate;
							beforeEndDate = changedEndDate;
							
							// 기간 외의 타임테이블 삭제하고
							deleteTimetableByDate(changedStartDate, changedEndDate);
							// 캘린더 다시 그려주기
							initFullCalendar(changedStartDate, changedEndDate, false);
						});
						// 취소버튼 누르면
						$("#btnCancelOnDateChange").on("click", function(){
							// 바꾸기 전  날짜로 다시 변경
							if(alertStartDate){
								$(".planDate[name='editStartDate']").val(beforeStartDate);
							}else if(alertEndDate){
								$(".planDate[name='editEndDate']").val(beforeEndDate);
							}
						});
					})
				}
			});
		} else{
			// 캘린더 다시 그려주기
			initFullCalendar(changedStartDate, changedEndDate, false);
		} 
		
		// 새로 캘린더 그려서 읽기모드로 세팅 -> 수정  모드로 변경
		$('#calendar').fullCalendar('option', 'editable', true); // 수정 가능하게
		$('#calendar').fullCalendar('option', 'droppable', true); // 드롭할 수 있게
	});
	
}); // $(document).ready() End
	
	
</script>

</head>
<body>
<!-- 플래너 대문 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;background-color:#EEEEEE;">

	<!-- 플래너 대문 정보(공개유무, 수정버튼, 일정제목 등 UI) -->
	<div style="background-color:#EEEEEE;">
		
		<p id="isClose" style="display:none;">비공개</p>
		<p id="isOpen" style="display:none;">공개 </p>
		
		<label id="btnSelectShare" class="switch" style="display:none;" >
			<input id="isChecked" type="checkbox" name="checkbox" value="check">
			<span class="slider round"></span>
		</label>
		
<!-- 		게시자와 열람자가 같은 유저면 수정버튼을 -->
		<c:if test="${writtenUserView.user_idx eq loginedUserView.user_idx}">
		    <!-- <input id="btnModify" type="button" value="수정" style="float:right;"
		    onClick="location.href='/plan/write'"> -->
		    <form action="/plan/write" method="get" id="Modify">
		    	<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
				<input type="hidden" name="user_idx" value="${planView.user_idx}" />
				<input type="hidden" name="editTitleView" value="${planView.title}" />
				<input type="hidden" name="editStartDate" value="${planView.start_date}" />
				<input type="hidden" name="editEndDate" value="${planView.end_date}" />
				<input type="hidden" name="editTraveled" value="${planView.traveled}" />
				
		    	<input id="btnModify" type="button" value="수정" style="float:right;">
		    </form>
		</c:if>
<!-- 		다르면 북마크 버튼을 보여준다 -->
		<c:if test="${writtenUserView.user_idx ne loginedUserView.user_idx}">
			
			<c:if test="${bookmark.plan_idx ne planView.plan_idx}">
				<form action="/bookmark/insert" method="post">
					<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
					<button id="btnBookMark" type="submit" style="float:right;">북마크 추가</button>
				</form>
			</c:if>
			
			<c:if test="${bookmark.plan_idx eq planView.plan_idx}">
				<form action="/bookmark/delete" method="post">
					<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
					<button id="btnBookMark" type="submit" style="float:right;">북마크 삭제</button>
				</form>
			</c:if>
			
		</c:if> 
			
	</div><br>
		
	<div id="header" style="background-color:#EEEEEE;text-align:center;">
		<div id="viewTitle">
			<h1 id="titleView" style="margin-bottom:0;">${planView.title }</h1>
			<h4 id="planRouteView"> 여행 경로 2개</h4> 
			<h4 id="dateView">${planView.start_date } ~ ${planView.end_date }</h4>
			<h4 id="traveledView">
				<c:if test="${planView.traveled eq 1 }">여행 전</c:if>
				<c:if test="${planView.traveled eq 0 }">여행 후</c:if>
			</h4>
		</div>
			<br>
	</div>
</div><br>
<!-- 플래너 입력 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;">
	<!-- 좌측 정보목록 (게시자 정보, 가계부, 검색 등 )-->
	<div id="container" style="width:230px; border-radius:10px;float:left;">
	
		<!-- 게시자 정보 DIV -->
		<div id="menu" style="background-color:#EEEEEE;height:100%;float:bottom;width:100%;border-radius:10px;">
			
			<div class="profileImage">
				<img src="${writtenUserView.profile }" style="border-radius:70px; width:100px;"/>
			</div>
			
			<br>
			<b>${writtenUserView.nickname }</b>님 <br>
			포스팅 : <b>${writtenUserView.totalPlanCnt }</b>개 <br>
			등급 : <b>${writtenUserView.grade }</b><br>
			<b>${planView.tot_dist }</b> km<br>
		</div><br>
		
	 	<!-- 가계부 DIV -->
		<div id="menu" style="background-color:#CCCCCC;height:100%;float:bottom;width:100%;border-radius:10px;">
			
<!-- 			가계부 그래프 -->
			<a href="#layer2" id="btnAccGraph" >가계부 그래프</a><br><br>
			<div class="dim-layer">
			    <div class="dimBg"></div>
			    <div id="layer2" class="pop-layer">
			        <div class="pop-container">
			            <div class="pop-conts">
			                <div>
			                	<button id="totalGraph">전체</button> <button id="dailyGraph">일일</button>
			                </div>
			                <div id="idTotal">
		                		<div id="total"></div>
		                	</div>
		                	<div id="idDaily">
		                		<div id='daily'></div>
		                	</div>
			                <div class="btn-r">
			                    <a href="#" class="btn-layerClose">X</a>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>

			항공료 : ${airfare }<br> 
			교통 : ${traffic }<br>
			숙박 : ${stay }<br>
			입장료 : ${admission }<br>
			음식 : ${food }<br>
			오락 : ${play }<br>
			쇼핑 : ${shop }<br>
			기타 : ${etc }<br><br>
			<b>총합 : ${acc_total }</b><br>
			<b>환율 : ${accView.caled_cost }</b><br>
		</div><br>
		
		<!-- 일정 저장 -->

		<input id="planCommit" type="button" value="저장" onclick="store();" style="display:none;width:100%;">
		
		<!-- 검색 INPUT DIV -->
		<div id="googleSearch" style="float:bottom;width:100%;border-radius:10px;display:none;">
		검색 : <input id="pac-input" class="controls" type="text" placeholder="Search Box">
		    <div id="right-panel"
		    style="border-top:3px solid; border-bottom:3px solid; border-left:3px dashed; border-right:3px groove; padding:3px;">
		    <ul>
		     <li id="results" ></li>
		     </ul>
		    </div>
		</div><br>
		
	</div>
	
	<!-- 우측 일정 & 타임테이블정보 (지도, 일정탭 & 타임테이블탭 등 )-->
	<div id="container" style="width:900px; border-radius:10px;float:left;">
		<!-- 일정 / 스토리 탭 DIV -->
		<div id="content" style="float:bottom;width:100%;">
			<button id="btnPlan" style="width:447px;background-color:#ff5555;border-radius:10px;">일정</button>
			<button id="btnStory" style="width:447px;background-color:#5555ff;border-radius:10px;">스토리</button>
		</div>
		
		<!-- 구글맵 DIV -->
		<div id="googleMap" style="background-color:#DDDDDD;height:500px;float:bottom;width:100%;border-radius:10px;">
			<div id="map"></div>
	 	</div>
	 	
	 	<!-- 타임테이블 & 스토리테이블 -->
		<div id="calendar"></div>
		<div id="viewStory"></div>
		
	</div>
</div>
</body>
</html>
