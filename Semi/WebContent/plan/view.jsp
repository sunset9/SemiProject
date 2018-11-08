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

<!-- 원형 그래프 -->
<script src= "https://cdn.zingchart.com/zingchart.min.js"></script>
		<script> zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
		ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9","ee6b7db5b51705a13dc2339db3edaf6d"];</script>

<link rel="stylesheet" href="/resources/planCommStyle.css">

<style type="text/css">

	
	#titleView {
	  margin-top: 73px;
	  font-size: 70px;
	}
	#planRouteView {
		margin-top: 62px;
	}
	
	/* 북마크 추가 아이콘 */
	#plusBookmark {
		float:right;
		font-size:45px;
		cursor:pointer;
		position: absolute;
	    right: 14px;
	    color: #FFF;
	}
	
	#plusBookmark:hover {
		color: #4FB99F;
	}
	
	/* 북마크 제거 아이콘 */
	#minusBookmark {
		float:right;
		color: #4FB99F;
		font-size:45px;
		cursor:pointer;
		position: absolute;
	    right: 14px;
	}
	
	#minusBookmark:hover {
		color: #fff;
	}
	
	/* 가계부 그래프 아이콘 */
	#AccGraph {
		font-size:125px;
		cursor:pointer;
		margin-bottom: 10px;
		right: 25px;
	}
	
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
	
	/* 가계부 그래프 아이콘 */
	#btnAccGraph {
		text-decoration:none;
		color: #cdcdcd;
	}
	
	#btnAccGraph .glyphicon-zoom-in {
		font-size: 32px;
	    left: 22px;
	    top: -58px;
	    
	}
	
	#btnAccGraph:hover{
		color:#4FB99F;
	}
	
	/* 배너쪽 수정 아이콘 */
	#btnModify {
		text-decoration:none;
		color: #ffffff;
	}
	
	#btnModify:hover{
		color:#4FB99F;
	}
	
	/* 가계부 가격 정보 */
	#accTypeCost {
		text-align: left;
	    width: 75%;
	    margin: 8px auto;
        overflow: hidden;
	}
	/* 가계부 카테고리*/
	#accTypeCost p{
	    margin: 0;
	    width: 100%;
	    float: left;
	}
	/* 가계부 가격*/
	#accTypeCost p b{
/* 		color:#4FB99F; */
		font-size:16px;
		margin-right: 3px;
	}

	#accTypeCost span.accType{
	    display: block;
    	overflow: hidden;
    	float: left;
   	    width: 43px;
	}
	
	#accTypeCost span:not(.accType){
	    display: block;
    	overflow: hidden;
    	float: right;
   	 	text-align: right;
	}
	
	/* 환율 변환기*/
	.curr-converter {
		border-radius: 10px;
	    margin-bottom: 3px !important;
	    transform: scale(0.94);
    }
    /* 환율 변환기 제목*/
    .gcw_headerF89vAYf4k{
    	border-radius: 7px 7px 0px 0px;
    }
	
	/* 일정 수정하기 버튼*/
	#planWriteModeBtn{
		margin-bottom: 5px;
		background: #eee;
		border: none;
		border-radius: 6px;
		height: 38px;
		width: 100%;
		font-size: 17px;
	}
	#planWriteModeBtn:hover {
		background: #dadada;
	}
	#planWriteModeBtn span {
		right: 4px;
	    top: 3px;
	    color: #444;
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

var isModify = 0;

var is_diplayStory = false;

var accTypeCost = [];

</script>
<script>
//숫자만 입력
function Numberchk() { 
	if (event.keyCode < 46 || event.keyCode > 57) event.returnValue = false; 
} 

//콤마찍기
function vComma(obj) { 
	var str    = "" + obj.value.replace(/,/gi,''); // 콤마 제거 
	var regx    = new RegExp(/(-?\d+)(\d{3})/); 
	var bExists = str.indexOf(".",0); 
	var strArr  = str.split('.'); 
	
	while(regx.test(strArr[0])){ 
		strArr[0] = strArr[0].replace(regx,"$1,$2"); 
	} 
	if (bExists > -1) 
		obj.value = strArr[0] + "." + strArr[1]; 
	else 
		obj.value = strArr[0]; 
} 
</script>
<script type="text/javascript">

// 읽기모드일때, 검색창 on/off
var check=0;
	console.log("view.jsp isModify : " + isModify);
	
$(document).ready(function() {
	console.log($(".userInfoText:first-child"));
	// isCookieTabClear 플래그가 true 이고
	// 새로고침 된게 아닌 경우 (performance.navigation.type == 1 : 새로고침)
	if(getCookie("isCookieTabClear") == 'true' && performance.navigation.type != 1){
		deleteCookie('tab');
	}
	setCookie("isCookieTabClear", "true");
	
	
	// 브라우저에 timetable 그려주기
	initFullCalendar(planStartDate, planEndDate, true);
	$('#calendar').fullCalendar('option', 'editable', false); // 수정 불가능하게
	$('#calendar').fullCalendar('option', 'droppable', false); // 드롭할 수 없게
	
// 	수정버튼 submit
	$("#btnModify").click(function() {
		// 탭 유지하면서 화면 전환
		setCookie("isCookieTabClear", "false");
		
		$("#Modify").submit();
	});
	
// 	북마크 add submit
	$("#plusBookmark").click(function() {
		// 탭 유지하면서 화면 전환
		setCookie("isCookieTabClear", "false");
		$("#addBookmark").submit();
	});
	
// 	북마크 delete submit
	$("#minusBookmark").click(function() {
		// 탭 유지하면서 화면 전환
		setCookie("isCookieTabClear", "false");
		$("#deleteBookmark").submit();
	});
	
	// 가계부 그려주기
	displayAcc();
	
// 	가계부 그래프 팝업 동작
	$("#btnAccGraph").click(function(){
// 		그래프 스팬 누르면 바로 전체그래프 보여줌
		$("#hrefTotal").click();
		
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
	
	$("#hrefTotal").click(function() {

// 		document.getElementById("idTotal").style.display= "block";
		
		var myConfig = {
				  "type":"pie",
				  "title":{
				    "text":"가계부"
				  },
				  "legend":{
				    "x":"75%",
				    "y":"25%",
				    "border-width":1,
				    "border-color":"gray",
				    "border-radius":"5px",
				    "header":{
				      "text":"가계부",
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
						{ "values":[accTypeCost.airfare], "background-color":"#cc0000","text":"항공료"},
					    { "values":[accTypeCost.traffic], "background-color":"#ff6600", "text":"교통"},
					    { "values":[accTypeCost.stay], "background-color":"#ffcc00", "text":"숙박"},
					    { "values":[accTypeCost.admission], "background-color":"#88cc00", "text":"입장료"},
					    { "values":[accTypeCost.food], "background-color":"#66ccff", "text":"음식" },
					    { "values":[accTypeCost.play], "background-color":"#0066ff", "text":"오락" },
					    { "values":[accTypeCost.shop], "background-color":"#6600ff", "text":"쇼핑" },
					    { "values":[accTypeCost.etc], "background-color":"#9999ff", "text":"기타" }
					  ]
				};
				 
				zingchart.render({ 
					id : 'total', 
					data : myConfig, 
					height: 600, 
					width: "100%" 
				});
	});
	
	// 처음 탭 선택하여 띄워주기
 	// 쿠키값이 없거나 tab-ttb 인 경우
	if(getCookie('tab')==null || getCookie('tab')=='tab-ttb'){
		$("#tab-main li").removeClass("active");
	    $("#tab-main li[rel='tab-ttb']").addClass("active");
		$("#tab-story").css('display', 'none');
	    $("#tab-ttb").show();
		
	// 쿠키값이 tab-story인 경우    
	}else if(getCookie('tab')=='tab-story'){
		$("#tab-main li").removeClass("active");
		$("#tab-main li[rel='tab-story']").addClass("active");
		$("#tab-ttb").css('display', 'none');
		$("#tab-story").show();
		
		// ajax 통신으로 내용 불러오기
		displayStoryView();
	}

	// 탭 선택 시 속성값, 탭 쿠키값 변경
	$('#tab-main li').click(function(){
		// active클래스 속성 변경
		$("#tab-main li").removeClass("active");
        $(this).addClass("active");
        
        // 선택한탭의 내용 띄워지게
        $(".tab-content").hide();
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).show();
        
        if(activeTab == 'tab-ttb'){ // 타임테이블 탭 선택한 경우
			setCookie('tab','tab-ttb');
        }else if(activeTab == 'tab-story'){ // 스토리 탭 선택한 경우
			setCookie('tab','tab-story');
        	// ajax 통신으로 내용 불러오기
			displayStoryView();
        }
	}); // tab on click 이벤트 설정
	
}); // $(document).ready() End

//스토리 뷰 ajax통신으로 띄워주기
function displayStoryView(){
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
}	

// 가계부 가격들 보여주기
function displayAcc(accCostJson){
	var accTotal = 0;
	
	// 서버에서 받아온 경우 . 일반적인 경우
	if(accCostJson == null || accCostJson ==""){
		accTypeCost = JSON.parse('${accTypeCost}');
		accTotal = ${acc_total };
		
	}else{ // ajax 통신을 통해 그려주는 경우
		accTypeCost = JSON.parse(accCostJson.accTypeCost);
		accTotal = accCostJson.acc_total;
	}
	
	// display
	$("#accTypeCost").html('');

	$("#accTypeCost").append("<p><span class='accType'>항공료</span>  <span><b>" + numberWithCommas(accTypeCost.airfare) + "</b>원</span></p>");
	$("#accTypeCost").append("<p><span class='accType'>교통</span>  <span><b>" + numberWithCommas(accTypeCost.traffic) + "</b>원</span></p>");
	$("#accTypeCost").append("<p><span class='accType'>숙박</span>  <span><b>" +  numberWithCommas(accTypeCost.stay) + "</b>원</span></p>");
	$("#accTypeCost").append("<p><span class='accType'>입장료</span>  <span><b>" + numberWithCommas(accTypeCost.admission) + "</b>원</span></p>");
	$("#accTypeCost").append("<p><span class='accType'>음식</span>  <span><b>" + numberWithCommas(accTypeCost.food) + "</b>원</span></p>");
	$("#accTypeCost").append("<p><span class='accType'>오락</span>  <span><b>" + numberWithCommas(accTypeCost.play) + "</b>원</span></p>");
	$("#accTypeCost").append("<p><span class='accType'>쇼핑</span>  <span><b>" + numberWithCommas(accTypeCost.shop) + "</b>원</span></p>");
	$("#accTypeCost").append("<p><span class='accType'>기타</span>  <span><b>" + numberWithCommas(accTypeCost.etc) + "</b>원</span></p>");
	$("#accTypeCost").append("<p><span class='accType'>총합</span>  <span><b>" + numberWithCommas(accTotal) + "</b>원</p>");
}

var eventMini; // 수정모드를 위한 event 값 전송에 사용하는 전역변수
function viewMini(event){
	eventMini = event;
	// ajax로 story 정보 가져옴 (content 정보)
	$.ajax({
		url: "/story/mini/view"
		, type: "GET"
		, data: {
			JSON: JSON.stringify({
					plan_idx: plan_idx
					, ttb_idx: event.id
				})
		}
		, dataType: "json"
		, success: function(obj){
			var story = JSON.parse(obj.story);
			var accountList = JSON.parse(obj.accountList);
			
			// miniView modal에 값 채워줌
			$("#miniViewTitle").text(event.title); // 타이틀 = 장소이름
			$("#miniViewPlace").text(event.title); // 장소 이름
			$("#miniViewAddress").text(event.address);  // 주소
			$("#miniViewImg").attr("src", event.photo_url); // 이미지
			
			$("#miniViewContent").html(story.content); // 스토리 내용
			var obj = document.getElementById("miniModalAccount");
			
			var accTypeName = {'airfare':'항공료', 'traffic':'교통', 'stay':'숙박',
					'admission':'입장료', 'food':'음식', 'play':'오락', 'shop':'쇼핑', 'etc':'기타'};
			for (var i=0; i<accountList.length;i++){
				
				/*비용콤마찍기*/
				var cost = accountList[i].origin_cost;
				if(accountList[i].curr_idx_name == 'USD'){
					var originCost = numberWithCommas(cost);
				}else{
					var originCost = numberWithCommas(parseInt(cost));
				}
				
				var append = $( 
						'<tr name="account"> <td style="padding-right: 15px" colspan="2"> <font size="2">'
						+ accTypeName[accountList[i].accType]
						+' | '
						+ accountList[i].curr_idx_name
						+' '
						+ originCost
						+'</font></td></tr>' 
						);
				$(obj).append(append);
			}
			if(accountList.length == 0){
				var append = $('<tr name ="account"> <td style="padding-right: 15px" colspan="2"> <font size="2"> 총액 | KRW 0원 </font></td></tr>');
				
				$(obj).append(append);
			}
	
			// 모달 창 닫힌 경우
			$("#miniViewModal").on('hidden.bs.modal', function () {
				// 기존 스토리내용 삭제
				$(this).find($("#miniViewContent")).html('');
			});
		}
		, error: function(){
			console.log("Mini-view Ajax 통신 실패");
		}
	}); // ajax end
}

function changeViewMode(){
	// 탭 유지하면서 화면 전환
	setCookie("isCookieTabClear", "false");
	
	$("#Modify").submit();
}

// 마우스오버시 색바꾸기
function mover(obj) {
	   obj.css( "color", "#4FB99F" );
	}

//마우스 클릭시 색바꾸기
function mdown(obj){
	   obj.css( "color", "#777777" );
}

//마우스 떠날때 색바꾸기
	function mleave(obj) {
		obj.css("color", "#ffffff");
	}
	
//마우스떠날때 색 gray로바꾸기
	function mleave_gray(obj) {
		obj.css("color", "#777777");
}

/*콤마찍기*/
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

</script>

    
<!-- 일정 기본 정보 -->
<header>
<!-- 플래너 배너 -->
<div id="planInfoHeader">
	<div id="planInfoBanner">
		<img src='${planView.bannerURL }'>
	</div>
	
	<div id="viewTitle" ><br>
	<!-- 게시자와 열람자가 같은 유저면 수정버튼을 -->
	<c:if test="${writtenUserView.user_idx eq loginedUserView.user_idx}">
	    <form action="/plan/write" method="post" id="Modify">
	    	<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
			<input type="hidden" name="user_idx" value="${planView.user_idx}" />
			<input type="hidden" name="editTitleView" value="${planView.title}" />
			<input type="hidden" name="editStartDate" value="${planView.start_date}" />
			<input type="hidden" name="editEndDate" value="${planView.end_date}" />
			<input type="hidden" name="editTraveled" value="${planView.traveled}" />
	    	<span id="btnModify" class = "glyphicon glyphicon-pencil" >
			</span>
	    </form>
	</c:if>
	<!-- 다르면 북마크 버튼을 보여준다 -->
	<c:if test="${loginedUserView.user_idx ne 0}">
		<c:if test="${writtenUserView.user_idx ne loginedUserView.user_idx}">
	<%-- 		${writtenUserView }<br> --%>
	<%-- 		${loginedUserView } --%>
			<c:if test="${bookmark.user_idx ne loginedUserView.user_idx}">
				<form action="/bookmark/insert" method="post" id="addBookmark">
					<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
					<span id="plusBookmark" class = "glyphicon glyphicon-bookmark">
					</span>
				</form>
			</c:if>
			
			<c:if test="${bookmark.user_idx eq loginedUserView.user_idx}">
	<%-- 		${bookmark }<br> --%>
	<%-- 		${loginedUserView } --%>
				<form action="/bookmark/delete" method="post" id="deleteBookmark">
					<input type="hidden" name="book_idx" value="${bookmark.book_idx}" />
					<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
					<span id="minusBookmark" class = "glyphicon glyphicon-bookmark" ></span>
				</form>
			</c:if>
		</c:if>
	</c:if>
<!-- 	</div> -->
<!-- 	<div id="viewTitle" > -->
		<h1 id="titleView">${planView.title }</h1>
		<c:if test = "${cName1 eq cName2 }">
		<h4 id="planRouteView" style="font-size:20px;"> ${cName1 }</h4>
		</c:if>
		<c:if test = "${cName1 ne cName2 }">
		<h4 id="planRouteView" style="font-size:20px;"> ${cName1 } ${cName2 }</h4>
		 </c:if>
		<h4 id="dateView" style="font-size:20px;">${planView.start_date } ~ ${planView.end_date }</h4>
		<h4 id="traveledView" style="font-size:15px;">
			<c:if test="${planView.traveled eq 1 }">여행 전</c:if>
			<c:if test="${planView.traveled eq 0 }">여행 후</c:if>
		</h4>
	</div>
	<br>
</div><br>
</header>

<!-- 좌측 정보목록 (게시자 정보, 가계부, 검색 등 )-->
<nav>
<div id="planInfoNav">
	<!-- 게시자 정보 DIV -->
	<div id="userInfoView">
		<div class="profileImage">
			<img id="userInfoProfileImg" src="${writtenUserView.profile }"/>
		</div>
		
		<p class="userInfoText"><b id="userInfoNick">${writtenUserView.nickname }</b>님 </p>
		<p class="userInfoText">포스팅 : <b>${writtenUserView.totalPlanCnt }</b>개 </p>
		<p class="userInfoText">등급 : <b>${writtenUserView.grade }</b></p>
		<p class="userInfoText"><b><fmt:formatNumber value='${writtenUserView.totalDist }' pattern="0.##"/></b> km</p>
	</div><br>
	
 	<!-- 가계부 DIV -->
	<div id="accountView">
			<!-- 가계부 그래프 -->
			<a href="#layer2" id="btnAccGraph">
			<span class='glyphicon glyphicon-zoom-in'></span>
			<span id="AccGraph" class = "glyphicon glyphicon-signal"></span>
			</a>
		<br>
		
		<div class="dim-layer">
		    <div class="dimBg"></div>
		    <div id="layer2" class="pop-layer">
		        <div class="pop-container">
		            <div class="pop-conts">
		                <div>
		                	<a href="#totalGraph" id="hrefTotal" ></a>
		                </div>
		                <div id="idTotal">
	                		<div id="total"></div>
	                	</div>
		                <div class="btn-r">
		                    <a href="#" class="btn-layerClose">X</a>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		<div id="accTypeCost">
		</div>
		
		<div id='gcw_mainF89vAYf4k' class='gcw_mainF89vAYf4k curr-converter' style="border-radius: 10px;"></div>
			<script>
				function reloadF89vAYf4k(){
					var sc = document.getElementById('scF89vAYf4k');
					
					if (sc) {
						sc.parentNode.removeChild(sc);
					}
					
					sc = document.createElement('script');
					sc.type = 'text/javascript';
					sc.charset = 'UTF-8';
					sc.async = true;
					sc.id='scF89vAYf4k';
					sc.src = 'https://freecurrencyrates.com/en/widget-vertical?iso=USDEURGBPJPYCNYXUL&df=2&p=F89vAYf4k&v=fits&source=fcr&width=245&width_title=0&firstrowvalue=1&thm=4FB99F,f0f0f0,B5E1D6,B5E1D6,FFFFFF,C5DBEC,FFFFFF,4FB99F,000000&title=Currency%20Converter&tzo=-540';
					// 1: 테두리 , 2: 배경 , 3: 제목 테두리, 4:제목 배경
					var div = document.getElementById('gcw_mainF89vAYf4k');
					div.parentNode.insertBefore(sc, div);
					}
					reloadF89vAYf4k();
		</script>
	</div><br> <!-- 가게부 end -->
	
	<!-- 일정 수정 모드-->
	<c:if test="${writtenUserView.user_idx eq loginedUserView.user_idx}">
		<button id="planWriteModeBtn" onclick="changeViewMode()">
			<span class='glyphicon glyphicon-pencil'></span>일정 수정하기</button>
	</c:if>
</div>
</nav>

<!-- 우측 일정 & 타임테이블정보 (지도, 일정탭 & 타임테이블탭 등 )-->
<section>
<div id="planMainSection" >
	<!-- 일정 / 스토리 탭  -->
	<ul id="tab-main" >
		<li rel="tab-ttb"><img id="ttb-icon" src="/resources/img/timetable-tab.png">일정</li>
		<li rel="tab-story"><img id="story-icon" src="/resources/img/story-tab.png">스토리</li>
	</ul>
	
	<div class="tab-container">
		<div id="tab-ttb" class="tab-content tab-ttb">
			<!-- 구글맵 DIV -->
			<div id="map"></div>
		 	<!-- 타임테이블 -->
			<div id="calendar"></div>
	 	</div>
	 	
	 	<div id="tab-story" class="tab-content tab-story">
		 	<!-- 스토리테이블 -->
			<div id="viewStory"></div>
	 	</div>
 	</div>
</div>	
</section>

<jsp:include page="../layout/footer.jsp" />

<!-- Maps JavaScript API 로드 -->
<script async defer
 src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&libraries=places&language=ko&callback=initMap">
</script>

