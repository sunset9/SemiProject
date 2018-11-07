<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 헤더  -->
<c:import url="../layout/headerWithMenu.jsp" />
<!-- 미니뷰 modal -->
<c:import url="/plan/timetable/miniViewWrite.jsp"/>

<!-- fullcalendar -->
<link rel='stylesheet' href='/resources/timetable/fullcalendar/fullcalendar.css' />
<link href='/resources/timetable/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />

<script type="text/javascript" src="/resources/timetable/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/scheduler.min.js"></script>
<script src='/resources/timetable/fullcalendar/locale-all.js'></script>

<!-- timetable utils -->
<script type="text/javascript" src="/utils/timetableUtils.js"></script>
<script type="text/javascript" src="/utils/mapUtils.js"></script>

<link rel="stylesheet" href="/resources/planCommStyle.css">

<!-- jQuery Form Plugin -->
<script src="http://malsup.github.com/min/jquery.form.min.js"></script> 

<!-- 공개유무 슬라이드 버튼 -->
<style type="text/css">
	.switch {
	  position: relative;
	  display: inline-block;
	  width: 60px;
	  height: 34px;
	  vertical-align:middle;
	}
	
	/* The slider */
	.slider {
	  position: absolute;
	  cursor: pointer;
	  top: 0;
	  left: 0;
	  right: 0;
	  bottom: 0;
	  background-color: #ccc;
	  -webkit-transition: .4s;
	  transition: .4s;
	}
	 
	.slider:before {
	  position: absolute;
	  content: "";
	  height: 26px;
	  width: 26px;
	  left: 4px;
	  bottom: 4px;
	  background-color: white;
	  -webkit-transition: .4s;
	  transition: .4s;
	}
	 
	input:checked + .slider {
	  background-color: #2196F3;
	}
	 
	input:focus + .slider {
	  box-shadow: 0 0 1px #2196F3;
	}
	 
	input:checked + .slider:before {
	  -webkit-transform: translateX(26px);
	  -ms-transform: translateX(26px);
	  transform: translateX(26px);
	}
	 
	/* Rounded sliders */
	.slider.round {
	  border-radius: 34px;
	}
	 
	.slider.round:before {
	  border-radius: 50%;
	}
	
	 
	/* 검색, 검색 결과 */ 
	#googleSearch{
		width:100%;
		border-radius:10px;
		margin-top: 10px;
		border: 1px solid #ccc;
		background-color: #eee;
	}
	
	/* 검색 입력 창 */
	#pac-input{
		display: block;
		margin: 6px auto;
		width: 93%;
	    background: url(/resources/img/searchBg.png) -5px center no-repeat;
 	    border: 1px solid #ccc; 
	    padding: 5px 3px 5px 35px;
	    background-color: white;
	}
	
	/* 검색 결과 창*/
	#searchResultView{
		padding:3px;
	}
	
	
	div.searchRes {
/* 	    border: 1px solid #dfdfdf; */
    	height: 94px;
 		margin-bottom: 2px;
 		background-color: white;
 		
 		-webkit-transform:scale(1);
	    -moz-transform:scale(1);
	    -ms-transform:scale(1); 
	    -o-transform:scale(1);  
	    transform:scale(1);
	    -webkit-transition:.1s;
	    -moz-transition:.1s;
	    -ms-transition:.1s;
	    -o-transition:.1s;
	    transition:.1s;
	}
	
	div.searchRes:hover {
		transform: scale(0.98);
		border: 2px solid #4FB99F;
	}
	
	div.searchResImg {
	    display: inline;
 	 	float: left;
		width: 90px;
		height: 90px;
		overflow: hidden;
	}
	
	img.placeImg {
		object-fit: cover;
		width: auto;
		height: 100%;
		
	}
	
	.searchResInfo {
	    padding: 2px 2px 2px 95px;
	    height: 90px;
	}
	
	.infoName {
		font-weight: bold;
		line-height: 1.2em;
 		height: 3.6em;
		display: -webkit-box;
    	-webkit-line-clamp: 3;
		-webkit-box-orient: vertical;
		overflow: hidden;
		text-overflow: ellipsis; /* 글자 ... 처리*/
/* 		white-space:nowrap; /*공백문자가 있는 경우 줄바꿈하지 않고 한줄로 나오게 처리 */ 
	}
	
	.infoAddr {
		margin-top: 2px;
		font-size: small;
		color: #999;
		height: 41%;
		overflow:hidden;
		text-overflow:ellipsis; /* 글자 ... 처리*/
		line-height: 1.2em;
	}
	
	/* 일정 보기 버튼*/
	#planViewModeBtn{
		margin-bottom: 5px;
		background: #eee;
		border: none;
		border-radius: 6px;
		height: 30px;
		width: 100%;
	}
	#planViewModeBtn:hover {
		background: #dadada;
	}
	
	/* 저장버튼 활성화 버전*/
	#planSaveBtn:not([disabled]){
		width: 100%;
	    height: 34px;
 		background: #4FB99F; 
	    font-weight: bold;
	    color: #fff; 
	    border: none;
	    font-size: 18px;
	    border-radius: 6px;
	}
	
	/* 저장버튼 활성화/hover 버전*/
	#planSaveBtn:not([disabled]):hover{
	    background: #429480;
	    background-image: linear-gradient(to bottom, #50b69c 0,#429480 100%);
/* 		background-image: -webkit-linear-gradient(top, #56C9AD, #4BB097); */
/* 		background-image: -moz-linear-gradient(top, #56C9AD, #4BB097); */
/* 		background-image: -ms-linear-gradient(top, #56C9AD, #4BB097); */
/* 		background-image: -o-linear-gradient(top, #56C9AD, #4BB097); */
/* 		background-image: linear-gradient(to bottom, #56C9AD, #4BB097); */
	}
	
	/* 저장버튼 비활성화 버전*/
	#planSaveBtn[disabled]{
		width: 100%;
		height: 34px;
		background: #eee;
		color: #ccc;
		border: none;
		font-size: 18px;
		border-radius: 6px;
	}
	
	/* 저장 경고창 */
	.jconfirm .jconfirm-box .jconfirm-buttons button.btn-blue {
    	background-color: #4FB99F;
    }
	.jconfirm .jconfirm-box .jconfirm-buttons button.btn-blue:hover {
    	background-color: #429480;
    }
    
    /*공개 비공개 글씨*/
    #planInfoIsOpen{
    	color: #f9f9f9;
    }
    
    /*공개 비공개*/
    #editPlanInfoIsOpen{
	  	border-radius: 5px;
	    width: 200px;
	    display: inline;
	    margin-bottom: 5px;
	    background: #f9f9f9;
    }
    
     /*글제목 글씨*/
    #bannerTitle{
       	color: #f9f9f9;
	    margin-bottom: 46px;
	    font-size: 25px;
    }
  	 /*글제목 에딧박스*/
    #editBannerTitle{
		border-radius: 5px;
        width: 570px;
		margin-right: auto;
	    margin-left: auto;
	    margin-top: 8px;
	    margin-bottom: 60px;
        height: 66px;
  		font-size: 52px;
		text-align: center;
		background: #f9f9f9;
    }
    
    /*출발일 글씨*/
    #bannerStartDate{
      	color: #f9f9f9;
    }
    
    /*출발일 date*/
    #editBannerStartDate{
	  	border-radius: 5px;
	    width: 170px;
	    display: inline;
	    margin-right: 8px;
	    margin-bottom: 5px;
	    background: #f9f9f9;
    
    }
    
    
    /*도착일 글씨*/
    #bannerEndDate{
       	color: #f9f9f9;
    }
    
    /*도착일 date*/
    #editBannerEndDate{
	  	border-radius: 5px;
	    width: 170px;
	    display: inline;
	    margin-bottom: 5px;
	    background: #f9f9f9;
    }
    
    /*여행 전후 글씨*/
    #bannerBeforeAfter{
       	color: #f9f9f9;
    
    }
    /*여행전후 셀렉트*/
    #editBannerBeforeAfter{
	  	border-radius: 5px;
	    width: 200px;
	    display: inline;
	    background: #f9f9f9;
    }
    
    /*일정정보폼*/
    #planForm{
	    width: 800px;
	    padding-top: 9px;
	    margin-right: 61px;
	    margin-right: auto;
	    margin-left: auto;
    }
    
    /* 배너 사진 교체 버튼 */
	#fileModify {
		float:left;
		text-decoration:none;
		color: #ffffff;
		font-size:35px;
		cursor:pointer;
	    position: relative;
	    right: 135px;
	    top: -10px;
	}
	
	#fileModify:hover{
		color:#4FB99F;
	}
    
</style>

<script>


	function fn_press_han(obj)
	{
	    //좌우 방향키, 백스페이스, 딜리트, 탭키에 대한 예외
	    if(event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39
	    || event.keyCode == 46 ) return;
 	    obj.value = obj.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
	}

	
	//숫자만 입력
	function Numberchk() { 
		if (event.keyCode < 46 || event.keyCode > 57) {
			event.returnValue = false;
			} 
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

	//공백제거
	function trim(str) { 
		return str.replace(/(^\s*)|(\s*$)/g, ""); 
	} 

	function getNumber(str) { 
		str = "" + str.replace(/,/gi,''); // 콤마 제거 
		str = str.replace(/(^\s*)|(\s*$)/g, ""); // trim 
		return (new Number(str)); 
	} 
	
</script>

<script>

// 서버에서 넘어온 일정의 시작, 끝 날짜 정보
var planStartDate = '${planView.start_date}';
var planEndDate = '${planView.end_date}';
// 서버에서 넘어온 timetable, location 정보들
var ttbList = ${ttbList };
var locList = ${locList };

var plan_idx = ${planView.plan_idx};

var isModify = 1;
var isStayWriteMode = false;

var isAlreadyAlert = false;

var fileURL = "";
</script>
<script>
//저장하기
function store(miniTimetables){
	// 탭 바뀌지 않게 하기
	setCookie("isCookieTabClear", "false");
	
	activeStoreBtn(false);
	
	// 캘린더에 있는 모든 이벤트 정보 가져오기
	var events = $("#calendar").fullCalendar('clientEvents');
// 	console.log("저장할 때 events목록");
// 	console.log(events);
	
	var timetables = [];
	if(miniTimetables != null) {
		timetables = miniTimetables;
		
	}else {
		// form input 생성(넘겨줄 값)
		events.forEach(function(event){ // 모든 리스트 돌면서 timetable json 하나씩 생성
			// timetable json 생성
			var timetable = getTtbJsonForServer(event);
			timetables.push(timetable);
		});
	}
	
	// submit
// 	console.log("---store()----")
// 	console.log(timetables);
	
	var succ = false;
	
	// submit 시 가로채서 ajax 통신으로 변환
	$("#planForm").ajaxForm({
// 		url: "/plan/update"
// 		, type: "post"
		async: false
		, dataType: "text"
		, data: {
			events: JSON.stringify(timetables)
			, bannerURL: fileURL
		}
		, success: function(){
			// 저장 후 수정모드 유지 변수가 false이면, 읽기 모드로 보냄
			if(!isStayWriteMode){
				window.location = "/plan?plan_idx=" + ${planView.plan_idx};
			}
			isStayWriteMode = false; 
			succ = true;
		}
		, error: function(e){
			succ = false;
		}
	});

	$("#planForm").submit();
	
	return succ;
}

$(document).ready(function(){

})

//마우스오버시 색바꾸기
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

</script>

<script>
// 읽기모드일때, 검색창 on/off
$(document).ready(function() {
	// isCookieTabClear 플래그가 true 이고
	// 새로고침 된게 아닌 경우 = 다른 페이지에서 넘어온 경우 (performance.navigation.type == 1 : 새로고침)
	if(getCookie("isCookieTabClear") == 'true' && performance.navigation.type != 1){
		setCookie('tab','');
	}
	
	// 한 번 탭 변경하지 않고 넘어갔으면 그 다음엔 탭 정보 저장 쿠키 다시 삭제하게
	setCookie("isCookieTabClear", "true");
	
	initFullCalendar(planStartDate, planEndDate, true);
	$('#calendar').fullCalendar('option', 'editable', true); // 수정 가능하게
	$('#calendar').fullCalendar('option', 'droppable', true); // 드롭할 수 있게
	
	// 	수정모드일 때, 공개유무버튼
	$("#isChecked").click(function(){
			
// 		  $("p").toggle();
		  check = $("input[type='checkbox']").is(':checked');
		  
		  if(check) {
			  check = 1;
			document.getElementById("isClose").style.display= "none";
			document.getElementById("isOpen").style.display= "block";
		  } else {
			  check = 0;
			document.getElementById("isClose").style.display= "block";
			document.getElementById("isOpen").style.display= "none";
		  }
			console.log(check);
			
			return check;
		});

	
	// 일정 일자 변경할때의 처리
	var beforeStartDate = planStartDate;
	var beforeEndDate = planEndDate;
	$(".planInfo").on("change", function(){
		// 바뀐 날짜 값 받아오기
		var changedStartDate = $(".planInfo[name='editStartDate']").val();
		var changedEndDate = $(".planInfo[name='editEndDate']").val();
		
		// 예외처리
		if(changedStartDate > changedEndDate){
			alert("일정의 마지막일이 시작일보다 작을 수 없습니다.");
			$(".planInfo[name='editStartDate']").val(beforeStartDate);
			$(".planInfo[name='editEndDate']").val(beforeEndDate);
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
								$(".planInfo[name='editStartDate']").val(beforeStartDate);
							}else if(alertEndDate){
								$(".planInfo[name='editEndDate']").val(beforeEndDate);
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
	
	// 처음 탭 선택하여 띄워주기
    // 쿠키값이 없거나 tab-ttb 인 경우
	if(getCookie('tab')==null || getCookie('tab')=='' ||getCookie('tab')=='tab-ttb'){
		$("#tab-main li").removeClass("active");
	    $("#tab-main li[rel='tab-ttb']").addClass("active");
		$(".tab-content").css('display', 'none');
	    $(".tab-content.tab-ttb").show();
	
	// 쿠키값이 tab-story인 경우    
	}else if(getCookie('tab')=='tab-story'){
		$("#tab-main li").removeClass("active");
		$("#tab-main li[rel='tab-story']").addClass("active");
		$(".tab-content").css('display', 'none');
		$(".tab-content.tab-story").show();
		
		// ajax 통신으로 내용 불러오기
		displayStoryView();
	}
	    
    // 탭 선택 시 속성값, 탭 쿠키값 변경
	$('#tab-main li').click(function(){
		var clickTab = $(this);
		// 저장버튼 활성화 상태이면 탭 안넘어가도록 경고창
		if($("#planSaveBtn").attr('disabled') == null){
			// 저장 확인 창 띄움
			$.confirm({
			    title: '저장하시겠습니까?',
			    content: '저장하시지 않으면 작성 중인 정보를 잃습니다.',
			    buttons: {
			    	confirm: { // 확인 버튼
			    		text: '저장'
			    		, btnClass: 'btn-blue'
			    		, action: function(){
			    			isStayWriteMode = true; // 수정모드 유지
			    			var succ = store(); // 저장 동작
				            if(succ) {	changeTab(clickTab); }  // 탭 변경
			    		}
			        }, 
		        	취소: function () {} // 취소 버튼
			    }
			});
		} else { // 저장버튼 비활성화 상태면 그냥 진행
			isStayWriteMode = true; // 수정모드 유지
			changeTab(clickTab); // 탭 변경
		}
		
	}); // tab on click 이벤트 설정
	
	
	// 일정 정보 수정하는 경우 저장버튼 활성화
	$("#planForm").on('change',function(){
		activeStoreBtn(true);
	});
	
	$("#fileModify").click(function(){
		$("#fileBtn").click();
	});
	
	// 배너 사진 변경
	$("#fileBtn").change(function(){
		var bannerImg = $('#planInfoBanner');
		var upload = $("#fileBtn")[0];
		var file = upload.files[0], reader = new FileReader();
		
		reader.onload = function (event) {
		    var img = new Image();
		    img.src = event.target.result;
		    // 새로운 배너 사진으로 그려줌
		    bannerImg.html('');
		    bannerImg.append(img);
		  };
	 	reader.readAsDataURL(file);
	 	fileURL = "/upload/banner/"+file.name;
	 	activeStoreBtn(true);
	});
}); // $(document).ready() End

// 탭 변경해주기
function changeTab(clickTab){
	// active클래스 속성 변경
	$("#tab-main li").removeClass("active");
	clickTab.addClass("active");
    
    // 선택한탭의 내용 띄워지게
    $(".tab-content").hide();
    var activeTab = clickTab.attr("rel");
    $("." + activeTab).show();
    
    if(activeTab == 'tab-ttb'){ // 타임테이블 탭 선택한 경우
		setCookie('tab','tab-ttb');
		initFullCalendar(planStartDate, planEndDate, false);
		$('#calendar').fullCalendar('option', 'editable', true); // 수정 가능하게
		$('#calendar').fullCalendar('option', 'droppable', true); // 드롭할 수 있게
		initMap();
    }else if(activeTab == 'tab-story'){ // 스토리 탭 선택한 경우
		setCookie('tab','tab-story');
    	// ajax 통신으로 내용 불러오기
		displayStoryView();
    }
} 

// 스토리 뷰 ajax통신으로 띄워주기
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

function viewMini(event){
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
			$("#miniWriteTitle").text(event.title); // 타이틀 = 장소이름
			$("#miniWritePlace").text(event.title); // 장소 이름
			$("#miniWriteAddress").text(event.address);  // 주소
			$("#miniWriteImg").attr("src", event.photo_url); // 이미지
			
			// ttb정보 json String 형태로 넘겨줌
			$("input[name=ttbJson]").val(JSON.stringify(getTtbJsonForServer(event)));
			
			// story 정보 json String 형태로 넘겨줌
			$('input[name=JSON]').val(JSON.stringify({ 
				plan_idx: plan_idx // plan_idx
				, ttb_idx: event.id // ttb_idx
			}));
			
			// 스토리 내용 띄워주기
			if(story.content != null){
				$("#miniWriteContent").froalaEditor('html.set', story.content);
			} else{
				$("#miniWriteContent").froalaEditor('html.set', '');
			}
			
	 		var ttbJson = JSON.parse($('input[name=ttbJson]').val());
	 		
	 		//Account Seletion 값 넣어주기
			
	 		var count = 0; //처음 한번은 append 안해주기 위해서
	 		// account 있는 수만큼 가계부 입력공간 추가
	 		for (var i = 0; i < accountList.length; i++) {
	 				if( ttbJson.ttb_idx == accountList[i].ttb_idx){
	 					var accountView = $("#w-min_accountView").clone();
	 					if(count != 0){
	 						$("#w-miniModalAccount").append(accountView);
	 					}
	 					count = count+1;
	 				}
	 		}
			
	 		var size = document.getElementsByName("w-min_accountViewName").length;
			
	  		for(var i = 0; i < size; i++){
	 	        var obj = document.getElementsByName("w-min_accountViewName")[i];
	
	 	        $(obj).find(".w-accountPlus").css("display","none");
	 	        $(obj).find(".w-accountRemove").css("display","block");
		        
	 	        var ch = false;
	 	        for (var j = 0; j <accountList.length; j++) {
	 				if( ttbJson.ttb_idx == accountList[j].ttb_idx){
	 					ch = true;
	 				}
	 	        }
		        
	 	        if (ch == false) {
	 			   $(obj).find(".w-min_accType").val('airfare');
	 		        $(obj).find(".w-min_currSymbol").val(1);
	 				$(obj).find(".w-min_cost").val("");
	 	        }else{
	 		        $(obj).find(".w-min_accType").val(accountList[i].accType);
	 		        $(obj).find(".w-min_currSymbol").val(accountList[i].curr_idx);
	 		    	var cost = accountList[i].origin_cost;
	 		    	if(accountList[i].curr_idx == 1){
			       		$(obj).find(".w-min_cost").val(numberWithCommas(cost));	
			       	}else{
			       		$(obj).find(".w-min_cost").val(numberWithCommas(parseInt(cost)));	
			       	}
	 	        }

	 	        if (i == size-1){
	 			    $(obj).find(".w-accountPlus").css("display","block");
	 	        }
		        
	 	        if (size == 5 && i == size-1){
	 		    	 $(obj).find(".w-accountPlus").css("display","none");
	 	        }
	 	        
	 	       if (size == 1){ // 1개일때
		        	// -버튼 안보여주기
		        	$(obj).find(".w-accountRemove").css("display","none");
		        	$(obj).find(".w-accountPlus").css("display","block");
		        }
	 		 }
		  		
			// 모달 창 닫힌 경우
			$("#miniWriteModal").on('hidden.bs.modal', function () {
				// 기존 스토리내용 삭제
				$("#miniWriteContent").froalaEditor('html.set', '');
			});
		}
		, error: function(){
			console.log("Mini-view Ajax 통신 실패");
		}
	}); // ajax end
}

// 읽기모드로 보내주기
function changeViewMode(){
	// 저장버튼 활성화 상태이면  경고창 띄워줌
	if($("#planSaveBtn").attr('disabled') == null){
		// 저장 확인 창 띄움
		$.confirm({
		    title: '저장하시겠습니까?',
		    content: '저장하시지 않으면 작성 중인 정보를 잃습니다.',
		    buttons: {
		    	confirm: { // 확인 버튼
		    		text: '저장'
		    		, btnClass: 'btn-blue'
		    		, action: function(){
		    			var succ = store(); // 저장 동작
			            if(succ){ // 저장 성공 시 읽기 모드로 보내줌
				            window.location = "/plan?plan_idx=" + ${planView.plan_idx};
			    		}
		    		}
		        }, 
	        	취소: function (){} // 취소 버튼
		    }
		});
	} else { // 저장버튼 비활성화 상태면 바로 읽기모드로
		setCookie("isCookieTabClear", "false"); // 탭 바꾸지 않기
		window.location = "/plan?plan_idx=" + ${planView.plan_idx};
	}
}

// 저장버튼 활성화/비활성화 상태 바꿔주는 메소드
function activeStoreBtn(isEdit){
	if(isEdit){ // 수정했으면 버튼 활성화
		$("#planSaveBtn").removeAttr("disabled");
	}else{ // 저장 상태이면 버튼 비활성화
		$("#planSaveBtn").attr("disabled", 'disabled');
	}
}

// 페이지나가거나 새로고침 시 경고 메세지 띄워주기
window.onbeforeunload = function(){
	// 저장버튼 활성화 상태이고, 경고창을 아직 띄우지 않았을 경우
	if($("#planSaveBtn").attr('disabled') == null && isAlreadyAlert == false){
		return false;
	}
};

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
	<div id="editTitle" >
	
	<!-- 플래너 대문 정보(공개유무, 수정버튼, 일정제목 등 UI) -->
		<div style="text-align:center;margin:0px auto;width:100%;">
			<form action="/plan/update" method="post" id="planForm" enctype="multipart/form-data">
			
				<input id="fileBtn" type="file" name="uploadFile" style="display:none"/>
				<span id="fileModify" class = "glyphicon glyphicon-picture" ></span>
				
				
				<span id="bannerTitle">여행제목</span><br><input id="editBannerTitle" name="editTitleView" class="form-control planInfo" type="text" value="${planView.title }"/><br>
				<b id="planInfoIsOpen">공개유무 : </b>
				<select id = "editPlanInfoIsOpen"name="editOpened" class ="form-control planInfo">
				
					<c:if test="${planView.opened eq 1 }">
						<option value="1" selected="selected">공개</option>
						<option value="0">비공개</option>
					</c:if>
					<c:if test="${planView.opened eq 0 }">
						<option value="1" >공개</option>
						<option value="0" selected="selected">비공개</option>
					</c:if>
				</select><br>
				
		<!-- 			<div> -->
				<input type="hidden" name="isSendWriteMode" value="false">
				<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
				<input type="hidden" name="user_idx" value="${planView.user_idx}" />
				
				<span id="bannerStartDate">출발일 : </span> <input id = "editBannerStartDate"name="editStartDate" class ="form-control planInfo" type="date" value="${planView.start_date }"/>
				
				<span id="bannerEndDate">도착일 : </span> <input id ="editBannerEndDate" name="editEndDate" class ="form-control planInfo" type="date" value="${planView.end_date }"/><br>
				
				<span id="bannerBeforeAfter">여행전후 : </span>
				<select id ="editBannerBeforeAfter" name="editTraveled" class ="form-control planInfo">
					<c:if test="${planView.traveled eq 1 }">
						<option value="1" selected="selected">여행 전</option>
						<option value="0">여행 후</option>
					</c:if>
					<c:if test="${planView.traveled eq 0 }">
						<option value="1" >여행 전</option>
						<option value="0" selected="selected">여행 후</option>
					</c:if>
				</select>
	<!-- 			</div> -->
			</form>
		</div>
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
	
	<!-- 일정 읽기 모드-->
	<button id="planViewModeBtn" onclick="changeViewMode()">일정 보기</button>
	<!-- 일정 저장 -->
	<button id="planSaveBtn" onclick="store();" disabled="true">저장 </button>
	
	<!-- 검색 INPUT DIV -->
	<div id="googleSearch" class="tab-content tab-ttb">
	<input id="pac-input" class="controls" type="text" placeholder="장소 검색">
	    <div id="searchResultView">
    	</div>
	</div><br>
</div>
</nav>

<!-- 우측 일정 & 타임테이블정보 (지도, 일정탭 & 타임테이블탭 등 )-->
<section>
<div id="planMainSection">
	<!-- 일정 / 스토리 탭  -->
	<ul id="tab-main">
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

<!-- Maps JavaScript API 로드 -->
<script async defer
 src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&libraries=places&language=ko&callback=initMap">
</script>

<jsp:include page="../layout/footer.jsp" />
