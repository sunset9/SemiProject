<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dto.story.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/plan/story/storyWrite.jsp"/>
<jsp:include page="/plan/story/storyUpdate.jsp"/>

<style type="text/css">


/*슬라이드메뉴*/
#slidemenu{
	background-color: rgba( 255, 255, 255, 0 );
	position:absolute;
	width:100px;
	right:0;
	color:#112F41;
}
	
.storytr{
	padding: 2px;
}

.storytd{
	padding: 2px;
	padding-left: 15px;
}

/*퀵메뉴, 스토리 수직선*/
.vl {
    border-left: 4px solid #d4d4d4;
    height: 100%;
    position: absolute;
    left: 36px;
    margin-left: -3px;
    top: 20px;
    z-index: 0;
}

.vlstory {
 	border-left: 4px solid #ddd;
    height: inherit;
    position: absolute;
    left: 39px;
    margin-left: -3px;
    top: 72px;
    /* bottom: -49px; */
    /* margin: -6px; */
    margin-bottom: -39px;
}

/*퀵메뉴 수직선 (따로적용해야 하는 값들)*/
#vl_slidemennu{
	margin-top: -20px;
	margin-left: -13px;
}

hr{
	border: 0.5px solid #e8e7e7;
	margin-right: 20px;
    margin-left: 8px;
    margin-top: 5px;
    margin-bottom: 5px;
}

/*말풍선*/
.bubble 
{
	position: relative;
	width: 280px;
	height: 175px;
	padding: 18px;
	background: #f9f9f9;
	-webkit-border-radius: 14px;
	-moz-border-radius: 14px;
	border-radius: 14px;
	margin-left: 60px;
	width: 750px;
    table-layout: fixed;
    white-space: nowrap;
	 
}

/*말풍선 꼬리*/
.bubble:after 
{
	content: '';
	position: absolute;
	border-style: solid;
	border-width: 7px 11px 7px 0;
	border-color: transparent #f9f9f9;
	display: block;
	width: 0;
	left: -11px;
	top: 16px;
}

/*비행기이미지*/
.Dayimage{
	color: #888;
    z-index: 50;
}


/*일차글씨*/
.Daytext{
	color: #888;
}

/*시작시간*/
.startTime{
	    margin-left: 18px;
    color: #d4d4d4;
}

/*스토리 삭제버튼*/
.removeStory{
	float: right;
	cursor: pointer;
	color: #777;
}

/*스토리 업데이트버튼*/
.updateStory{
	float: right;
	cursor:pointer;
	color: #777;
}

/*장소이름*/
.storyPlaceName{
	color: #777777;
}

/* 장소 마커*/
.placemarker{
	color: #4fb99fab;
}

/*스토리본문쪽*/
.storycontent{
	overflow: auto;
    height: auto;
    width: 100%;
    color: #112F41;
    padding-top: 0px;
    margin-left: 14px;
}

.storycontenttd{
    display: inline-block;
    padding-left: 4px solid black;
/*     border-left: 4px solid black; */
    padding-left: 10px;
    margin-left: 31px;
}

/*스토리없을때 장소이름*/
.noStoryplaceName{
	color: #777777;
	width: 750px;
}

/*스토리없을때 장소마커*/
.noStoryplacemarker{
	color: #4fb99fab;
}

/*스토리 없을때 +버튼*/
.storyPlus{
	color: #777;
	margin-left: 60px;
	cursor: pointer;
	font-size: 40px;
}


/*가계부 폰트*/
.accountText{
	color: rgba( 25, 70, 97,0.5);
    padding-left: 10px;
/* 	border-left: 4px solid black; */
}

/*덧글갯수*/
.commentCnt{
	cursor:pointer;
	color: rgba( 25, 70, 97,0.5);
}

/*덧글 TextArea*/
.commContent{
	resize: none;
	overflow: visible;
	color: #112F41;
	width: 650px;
}

/*덧글 저장*/
.saveCommBtn{
	margin-bottom: -7px; 
	margin-left: 310px;
}
.saveCommBtn:hover{
/*     background: #429480; */
    background-image: linear-gradient(to bottom, #50b69c 0,#429480 100%);
}
.commentView{
	display: none;
	color: #112F41;
}


.savecomm{
	background: #87cebd;
    border-radius: 6px;
    margin-left: 296px;
    margin-top: 5px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 5px;
    padding-bottom: 5px;
    border:0;
    outline:0;
    color: #fff;
}


</style>
<script type="text/javascript">
//write 가계부 카운트 (초기값)
var cnt = 0;
//update 가계부 카운트 (초기값)
var up_cnt = 0;

	$(document).ready(function(){
		
		//edit 모드일때, 수정버튼삭제버튼추가버튼 보여주지 않음	
	    function EditMode() {
	     var removeStorys = document.getElementsByClassName("removeStory");
	     var updateStorys = document.getElementsByClassName("updateStory");
	     var plusStorys = document.getElementsByClassName("storyPlus");
	     
	    	if (isModify == 1){
	    		//isModify가 1일때 (수정모드일때)
   			  for(var i = 0; i < removeStorys.length; i++){
   				  //업데이트버튼,삭제버튼 보여주기
	    		  removeStorys[i].style.display = "block"; 
	    		  updateStorys[i].style.display = "block";
	    		 
	    	   }
   			  
   			  for(var i=0; i<plusStorys.length;i++){
   				  //스토리추가버튼보여주기
   				 plusStorys[i].style.display = "block";
   			  }
   			  
	    	}else{
	    		//isModify가 1이아닐때 (읽기모드일때)
    		  for(var i = 0; i < removeStorys.length; i++){
    			  
    			  //업데이트,삭제버튼 보여주지 않기
	    		  removeStorys[i].style.display = "none"; 
	    		  updateStorys[i].style.display = "none";
	    		 
	    	   }
    		  
    		  for(var i=0; i<plusStorys.length;i++){
    			  //플러스버튼 보여주지않기
    			  plusStorys[i].style.display = "none";
    		  }
	    	}
			
		}
	    
	    EditMode();
	    
	    
	    //slidmenu 고정
	    $(window).scroll(function() {
	        var position = $(this).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
	        var header = $("#planInfoHeader").offset(); //배너를 객체로 만들어 header에저장
	        var height = $("#planInfoHeader").height(); //배너의 높이
	        
	        if(position > 600){ //스크롤바의 위치가 600보다 클때
	        	//슬라이드메뉴 고정
	        	$("#slidemenu").css("position","fixed");
	        	document.getElementById("slidemenu").style.top ="10px";
	        }else if (position <= 600){
	        	//스크롤바의위치가 600보다 작거나 같을때
				//슬라이드바 움직이도록 
	        	$("#slidemenu").css("position","absolute");
	        	//배너의 상단+배너의높이+50 픽셀만큼 떨어진 위치
	        	document.getElementById("slidemenu").style.top = header.top + height +50+ "px";  		        
	        }
	        
	    });

		//스토리 추가 모달 띄어주는 버튼
		$(".storyPlus").click(function() {
			
			//모달에 place값 전달
			var place_name = $(this).data("place");
			
			//모달의 modalPlaceName(장소이름들어갈곳)에 값을 넣어줌
			$(".modalPlaceName").text(place_name);
			
			//모달에 ttbidx 값 전달
			var ttb_idx = $(this).data("ttbidx");
			
			//모달 hidden 객체인 st_ttb_idx에 ttb_idx값 넣어줌
			$(".st_ttb_idx").val(ttb_idx);	
			
			//모달에 planidx값전달
			var plan_idx = $(this).data("planidx");
			//모달 hidden 객체인 st_plan_idx에 값을 넣어줌
			$(".st_plan_idx").val(plan_idx);	
		})
		

		
		
		//스토리 수정 모달 띄어주는 버튼
		$(".updateStory").click(function() {
			
				//모달에 값 전달
				var place_name =$(this).data("place");
				var story_idx = $(this).data("storyidx");
				var planidx = plan_idx;
				var content = $(this).data("content");
				var ttb_idx = $(this).data("ttbidx");
			
				//각 전달해야 하는 모달객체에 값을 넣어줌
				$(".up_modalPlaceName").text(place_name);
				$(".up_story_idx").val(story_idx);	
				$(".up_ttb_idx").val(ttb_idx);
				$('.up_content').froalaEditor('html.set', content);
				$(".up_plan_idx").val(planidx);	
				
				var accountStoryidx = [];
				var accountCategory = [];
				var accountCurridx = [];
				var accountCost = [];

				//업데이트 모달 띄울때, account 표시 하기 위한 account값 가져오기
				<c:forEach items="${accountList}" var="account">
					accountStoryidx.push("${account.story_idx}");
					accountCategory.push("${account.accType.getIdx()}");
					accountCurridx.push("${account.curr_idx}");
					accountCost.push("${account.origin_cost}");
				</c:forEach>
				
				//가계부가 한개만 저장되어 있다면, 추가해주는 행위 해주지 않기위한 카운트
				var count = 0;
				for (var i = 0; i <accountStoryidx.length; i++) {
						if( story_idx == accountStoryidx[i]){
							var accountView = $("#up_accountView").clone();
							
							if(count != 0){
								//저장된 가계부의 갯수가 2개 이상일때
								$("#up_accountViewList").append(accountView);	
							}
							count = count+1;
						}
				}
					
				//현재 추가된 가계부 갯수
		 		var size = document.getElementsByName("up_accountViewName").length;
		 		
		 		var accCurrNameList = [];
		 		var accTypeList = [];
		 		var accCostList = [];
		 		
		 		for(var i = 0; i < size; i++){
			        var obj = document.getElementsByName("up_accountViewName")[i];
			        
			        //+버튼 안보여주기
			        //-버튼 보여주기
			        $(obj).find(".accountPlus").css("display","none");
			        $(obj).find(".accountRemove").css("display","block");
			      	
			        //가계부가 있는지 없는지 확인
			        var ch = false;
			        for (var j = 0; j <accountStoryidx.length; j++) {
			        	//가계부가 있을때
						if( story_idx == accountStoryidx[j]){
							accTypeList.push(accountCategory[j]);
							accCurrNameList.push(accountCurridx[j]);
							accCostList.push(accountCost[j]);
							ch = true;
						}
			        }
			        
			        if (ch == false) {
			        	//가계부가 없다면 모든값 초기화
					   $(obj).find(".up_accType").val(1);
				        $(obj).find(".up_currSymbol").val(1);
						$(obj).find(".up_cost").val("");
			        }else{
			        	//가계부가 있다면 해당 값 넣어주기
				        $(obj).find(".up_accType").val(accTypeList[i]);
				        $(obj).find(".up_currSymbol").val(accCurrNameList[i]);
						$(obj).find(".up_cost").val(accCostList[i]);
			        }
						
			        //현재 가계부가 추가된것중 마지막 가계부일때
			        if (i == size-1){
			        	//+버튼보여주기
					    $(obj).find(".accountPlus").css("display","block");
			        }
			        
			        //현재 가계부가 마지막가계부이면서 5번째일대
			        if (size == 5 && i == size-1){
			        	//+버튼 안보여주기
				    	 $(obj).find(".accountPlus").css("display","none");
			        }
			        if (size == 1){ // 1개일때
			        	// -버튼 안보여주기
			        	$(obj).find(".accountRemove").css("display","none");
			        	$(obj).find(".accountPlus").css("display","block");
			        }
				 }
			 		
			
		})

		
	//스토리 새로 만드는 모달 save 버튼클릭 이벤트
	//스토리저장
	$(".storySaveBtn").click(function() {
		
			var cost = $("input[name='st_cost']")[0].value
			var content = $(".st_content").val();
			if ((content == null || content == "") && (cost = null || cost == "")){
				alert("스토리의 본문내용 혹은 가계부를 써주세요");
				return;
			}
			
			//storyJSON으로 저장할 값 넣어주기
			var storyJSON = {
				ttb_idx  : $(".st_ttb_idx").val()
				, plan_idx : $(".st_plan_idx").val() 
				, content  : $(".st_content").val()
			};
			
			//가계부 값들(배열)로 전달 하기 위한 변수
			var accTypeLen = $("select[name='st_accType']").length;
		    var accType = new Array(accTypeLen);
		    var currSymbol = new Array(accTypeLen);
		    var cost = new Array(accTypeLen);
		    
		    //선언된 가계부변수에 값 넣어주기
		    for(var i=0; i<accTypeLen; i++){                          
		    	accType[i] = $("select[name='st_accType']")[i].value;
		    	currSymbol[i] = $("select[name='st_currSymbol']")[i].value;
		    	cost[i] = $("input[name='st_cost']")[i].value;
		    }

			
		    //jsonData data를 string으로
			var jsonData = JSON.stringify(storyJSON);
			
			//ajax로 배열 전송하기 위한 방식
			$.ajaxSettings.traditional = true
			
			$.ajax({
				type : "POST"
				, url : "/story/write"
				, data : {
					JSON : jsonData
					, "accType": accType
					, "currSymbol" :currSymbol
					, "cost":cost
					, "USD_rate":USD_rate
					, "KRW_rate":KRW_rate
					, "JPY_rate":JPY_rate
					}
				, dataType : "html"
				, success : function (res) {
					$("#viewStory").html(res);
					//스크롤바 사라지는거 방지
					$('body').removeClass('modal-open');
				}
				, error: function () {
					
					console.log("실패");
				}
				
 			})
			
 		});
 		
 		// 스토리 업데이트창 save버튼 클릭이벤트
 		// 스토리 업데이트
 		$(".storyUpdateBtn").click(function () {
 			
 			var cost = $("input[name='up_cost']")[0].value
			var content = $(".up_content").val();
			if ((content == null || content == "") && (cost = null || cost == "")){
				alert("스토리의 본문내용 혹은 가계부를 써주세요");
				return;
			}
 			
 			//업데이트할 스토리 정보 
 			var storyJSON = {
 					story_idx : $(".up_story_idx").val() 
 					, content  : $(".up_content").val()
 					, plan_idx :$(".up_plan_idx").val()
 					, ttb_idx : $(".up_ttb_idx").val()
 				};
 				
 				//jsonData data를 string으로
 				var jsonData = JSON.stringify(storyJSON);
 				
 				//가계부 값들(배열)로 전달 하기 위한 변수
 				var accTypeLen = $("select[name='up_accType']").length;
 			    var accType = new Array(accTypeLen);
 			    var currSymbol = new Array(accTypeLen);
 			    var cost = new Array(accTypeLen);
 			    
 			   //선언된 가계부변수에 값 넣어주기
 			    for(var i=0; i<accTypeLen; i++){                          
 			    	accType[i] = $("select[name='up_accType']")[i].value;
 			    	currSymbol[i] = $("select[name='up_currSymbol']")[i].value;
 			    	cost[i] = $("input[name='up_cost']")[i].value;
 			    }
 			    //배열 전송 하기 위한 옵션 
 			   $.ajaxSettings.traditional = true
 				
 			   $.ajax({
 					type : "POST"
 					, url : "/story/update"
 					, data : {JSON : jsonData,"accType": accType, "currSymbol" :currSymbol, "cost":cost,"USD_rate":USD_rate,"KRW_rate":KRW_rate,"JPY_rate":JPY_rate}
 					, dataType : "html"
 					, success : function (res) {
 						$("#viewStory").html(res);
 						//스크롤바 사라지는거 방지
 						$('body').removeClass('modal-open');
 					}
 					, error: function () {
 						console.log("실패");
 					}
 					
 	 			})
 		});
 		
 		//모달 숨겨질때
 		$('.modal').on('hidden.bs.modal',function(e){
 			//업데이트모달 데이터리셋
 			var size = document.getElementsByName("up_accountViewName").length
 			
 			//현재 추가되어있는 가계부 1개빼고 삭제
 			for(var i = size-1; i > 0; i--){
		        var obj = document.getElementsByName("up_accountViewName")[i];
		        obj.remove();
 			}
 			
 			//첫번째 가계부객체
 			var obj1 = document.getElementsByName("up_accountViewName")[0]
 			
 			$(obj1).find(".up_accType").val(1);
	        $(obj1).find(".up_currSymbol").val(1);
			$(obj1).find(".up_cost").val("");
			
			//추가 모달 데이터 리셋
			var edit = document.getElementById("st_edit");
			
			$('.st_content').froalaEditor('html.set', "");
			
 			var size = document.getElementsByName("accountViewName").length
 			
 			//현재 추가되어있는 가계부 1개빼고 삭제
 			for(var i = size-1; i > 0; i--){
		        var obj = document.getElementsByName("accountViewName")[i];
		        obj.remove();
 			}
 			
 			var obj1 = document.getElementsByName("accountViewName")[0]
 			
 			$(obj1).find(".st_accType").val(1);
	        $(obj1).find(".st_currSymbol").val(1);
			$(obj1).find(".st_cost").val("");
			
			//+버튼 보여주기, -버튼 안보여주기
			$(obj1).find(".accountPlus").css("display","block");
	        $(obj1).find(".accountRemove").css("display","none");
	        
	        //모달 닫으면 카운트값 초기화(가계부입력공간 개수)
	        cnt = 0;
	        up_cnt = 0;
 		})
	});
	

	//삭제하기
	function storyDelete(storyIdx) {
		
		//예/아니오 alart 창 띄우기
		var r = confirm("스토리를 삭제 하시겠습니까?");
		
		//예 눌렀을때 삭제
		if (r == true) {
			$.ajax({
				type : "POST"
				, url : "/story/delete"
				, data : {"story_Idx":storyIdx, "plan_idx" : plan_idx}
				, success : function (res) {
					$("#viewStory").html(res);
				}
				, error: function (e) {
					console.log(e);
				}
			})
			
		} else {
		}
	}
	
	//댓글 갯수 클릭할때 댓글 보여지기
	function CommentViewClick(storyIdx,plan_idx) {
		
		var commentViewId = "#CommentView"+storyIdx;
		
		if($(commentViewId).is(":visible")){//현재 커멘드가 보여지고 있다면
			
			//커멘드뷰 안보여주기
			$(commentViewId).css("display","none");
		}else{//현재 커멘드가 안보여지고 있다면
			
			//커멘드뷰 보여주기
			$(commentViewId).css("display","block");
			
			//댓글 list 불러오기
			$.ajax({
				//display : none일 경우
				type : "get"
				, url : "/story/comment/view"
	 			, data : {"story_idx":storyIdx,"plan_idx":plan_idx}
				, success : function (res) {
					$(commentViewId).html(res);
				}
				, error: function (e) {
					console.log(e);
				}
			})

		}
		
	}
	


 	//댓글 세이브
 	function CommSave(story_idx,ttb_idx,plan_idx) {
 		
 		var id = "#CommContent"+story_idx;
 		
 		if ($(id).val() == null || $(id).val() == ""){
			alert("댓글 내용을 써주세요.");
			return;
		}
 		
 		var commentViewId = "#CommentView"+story_idx;
 		var commJson = {
 				"story_idx" : story_idx
 				, "plan_idx" : plan_idx
 				, "ttb_idx" : ttb_idx
 				, "content" :  $(id).val()
 		};
 		
 		var jsonData = JSON.stringify(commJson);
 		
		$.ajax({
			type : "post"
			, url : "/story/comment/write"
 			, data : {"commJson":jsonData}
			, success : function (res) {
				$(commentViewId).html(res);
				$(commentViewId).css("display","block");
			}
			, error: function (e) {
				console.log(e);
			}
		})
		
		//댓글 추가하고 댓글 content textarea 값 초기화시켜주기
		$(id).val("");
	}
 	
 	

 	//댓글 삭제
 	function removeComm(comm_idx,story_idx,plan_idx) {
 		
 		var commentViewId = "#CommentView"+story_idx;
 		
 		$.ajax({
 			type : "post"
 			, url : "/story/comment/delete"
 				, data : {"comm_idx":comm_idx, "story_idx":story_idx, "plan_idx":plan_idx}
 			, success : function (res) {
 				$(commentViewId).html(res);
 			}
 			, error: function (e) {
 				console.log(e);
 			}
 		})
 		
 	}
 	
 
 	
 	//가계부 추가
 	function appendAccount() {
 		
 		//가계부가 5개 일때는 추가하면 안됨
 		if (cnt < 5){
			var accountView = $("#story_accountView").clone();
	 		$("#accountViewList").append(accountView);
	 		
			cnt = cnt+1;
			
			//현재 추가된 가계부의 개수
			var size = document.getElementsByName("accountViewName").length;
	
			for(var i = 0; i < size; i++){
		        var obj = document.getElementsByName("accountViewName")[i];
		    
		        
		        //+버튼 안보여주기 -버튼 보여주기
		        $(obj).find(".accountPlus").css("display","none");
		        $(obj).find(".accountRemove").css("display","block");
		        
		        //마지막가계부일때
		        if (i == size-1){
		        	//+버튼보여주기
				    $(obj).find(".accountPlus").css("display","block");
		        	
		        	//가계부내용 초기화
		        	$(obj).find(".st_cost").val("");
		        	$(obj).find(".st_accType").val(1);
		        	$(obj).find(".st_currSymbol").val(1);
		        }
		        
		        //마지막가계부이면서 5번째일때
		        if (size == 5 && i == size-1){
			    	//+버튼 안보여주기 
		        	$(obj).find(".accountPlus").css("display","none");
			    	
		        }
		        
		        
		        if (size == 1){ // 1개일때
		        	// -버튼 안보여주기
		        	$(obj).find(".accountRemove").css("display","none");
		        	$(obj).find(".accountPlus").css("display","block");
		        }
			 }
		
		}
	}
 	
 	//가계부 삭제
 	function removeAccount() {
 		
 		//삭제할 가계부 객체 찾기
 		var removeObj = window.event.target.parentElement.parentElement.parentElement.parentElement.parentElement;
 		
 		//가계부삭제
 		removeObj.remove();	
 		
 		//cnt 감소
 		cnt = cnt-1;
 		
 		//메모리에 저장된 removeObj 변수 삭제
 		delete removeObj;
 		
 		//현재 추가되어있는 가계부 개수
 		var size = document.getElementsByName("accountViewName").length;
 		
		for(var i = 0; i < size; i++){
	        var obj = document.getElementsByName("accountViewName")[i];
	        
	        if (size == 1){ // 1개일때
	        	// -버튼 안보여주기
	        	$(obj).find(".accountRemove").css("display","none");
	        }
	        
	        // +버튼 안보여주기
	        $(obj).find(".accountPlus").css("display","none");
	        
	        //마지막 가계부일때
	        if (i == size-1){
	        	//+버튼 보여주기
			     $(obj).find(".accountPlus").css("display","block");
	        }
	        
	        //마지막가계부이면서 5번째일때
	        if (size == 5 && i == size-1){
		    	//+버튼 안보여주기
	        	$(obj).find(".accountPlus").css("display","none");
	        }
		 }
	}
 	
 	
 	
 	//업데이트 가계부 추가
 	//up_cnt를 현재추가된 가계부 갯수로 
 	up_cnt = document.getElementsByName("up_accountViewName").length;
 	function UpappendAccount() {
 		
 		if (up_cnt < 5){ //현재 추가되 가계부 갯수가 5개 보다 적을때
			var accountView = $("#up_accountView").clone();
	 		$("#up_accountViewList").append(accountView);
		    
	 		//up_cnt증가
	 		up_cnt = up_cnt+1;
			
	 		//현재 추가된 가계부 갯수 
			var size = document.getElementsByName("up_accountViewName").length;
	
			for(var i = 0; i < size; i++){
		        var obj = document.getElementsByName("up_accountViewName")[i];
		        
		        //+버튼안보여주기, -버튼 보여주기
		        $(obj).find(".accountPlus").css("display","none");
		        $(obj).find(".accountRemove").css("display","block");
		        
		        //마지막가계부일때
		        if (i == size-1){
					//+버튼 보여주기
		        	$(obj).find(".accountPlus").css("display","block");
		        	//가계부정보 초기화
					$(obj).find(".up_cost").val("");
		        	$(obj).find(".up_accType").val(1);
		        	$(obj).find(".up_currSymbol").val(1);
		        }
		        
		        //마지막가계부이면서 5번째일때
		        if (size == 5 && i == size-1){
		        	
		        	//+버튼 보여주기
			    	 $(obj).find(".accountPlus").css("display","none");
		        }
			 }
		
		}
	}
 	
 	
 	//업데이트 가계부 삭제
	function UpremoveAccount() {
 		
 		//삭제할 가계부 객체 찾기
 		var removeObj = window.event.target.parentElement.parentElement.parentElement.parentElement.parentElement;
 		
 		//가계부객체 삭제
 		removeObj.remove();	
 		
 		//cnt 감소
 		up_cnt = up_cnt-1;
 		
 		//메모리에서 removeObj변수삭제
 		delete removeObj;
 		
 		//현재추가된 가계부갯수 
 		var size = document.getElementsByName("up_accountViewName").length;
 		
		for(var i = 0; i < size; i++){
	        var obj = document.getElementsByName("up_accountViewName")[i];
	        
	        //1번째일때
	        if (size == 1){
	        	//-버튼 안보여주기
	        	$(obj).find(".accountRemove").css("display","none");
	        }
	        
	        //+버튼 안보여주기
	        $(obj).find(".accountPlus").css("display","none");
	        
	        //마지막가계부일때
	        if (i == size-1){
	        	//+버튼보여주기
			     $(obj).find(".accountPlus").css("display","block");
	        }
	        
	        //마지막가계부이면서 5번째일때
	        if (size == 5 && i == size-1){
	        	//+버튼 안보여주기
		    	 $(obj).find(".accountPlus").css("display","none");
	        }
		 }
	}
 	
	
</script>	



<input type="hidden" id = "calcDay"/>
<div>
  <div class="col-lg-4">
	<!-- Day Foreach문 -->
		<div id = "Day">
		</div>
			<c:forEach var='day' begin = "1" end="${diffDays}">
			<div id = "DayDiv${day}" >
			<h1 class = "Daytext"><span class="glyphicon glyphicon-plane Dayimage"></span> Day ${day}</h1>
			<div class="vl"></div>
			</div>
			<c:forEach items='${ttbList}' var = 'ttb'>
				<c:if test="${ttb.day eq day }">
				<c:if test = "${ttb.is_story eq false}">
				<fmt:formatDate var="start_time" value="${ttb.start_time}" pattern="kk:mm" type="time"/>
				<font size ="3"><span class="glyphicon glyphicon-minus startTime"><c:out value="${start_time}"></c:out></span></font>
				</c:if>
				<c:if test="${ttb.is_story eq true}">
					<c:forEach items='${storyList}' var='story'>
						<c:if test="${story.ttb_idx eq ttb.ttb_idx}">
						<font size ="3"><span class="glyphicon glyphicon-minus startTime">${story.start_time}</span></font>
				    		<table class="bubble">
				    			<tr class = "storytr">
				     			<td colspan="2" class ="storytd">
								  <font size="5"><span class = "glyphicon glyphicon-remove removeStory" onclick="storyDelete(${story.story_idx})"  onmouseover="mover($(this))" onmouseleave="mleave($(this))" onmousedown="mdown($(this))"></span>
								  <span class = "glyphicon glyphicon-pencil updateStory"   
									  onmouseover="mover($(this))" onmouseleave="mleave($(this))" onmousedown="mdown($(this))" 
									  data-toggle="modal" data-target="#myModal_update" 
									  data-backdrop="static" data-storyidx="${story.story_idx}" 
									  data-place="${story.place_name}" data-content= '${story.content}' data-ttbidx='${story.ttb_idx}'>
								  </span>
								  </font>
								  <div><h2 class = "storyPlaceName"><span class="glyphicon glyphicon-map-marker placemarker"></span>&nbsp;${story.place_name}</h2>
								</div>
								  </td>
								  </tr>
								  <tr class="storytr">
								  <td class="storytd storycontenttd">
						 		  <!-- froala align 적용 되게 하려면 content 표시할 div에 fr-view class를 반영 해줘야함 -->
						 		  <c:if test="${!empty story.content}">
						 		   <div class = "fr-view storycontent" style="border-left: 4px solid black padding:0.5em">
						 		   ${story.content}
						 		   </div>
						 		  </c:if>
						 		  <c:if test="${empty story.content}">
						 		  <div class = "fr-view storycontent">
						 		  </div>
						 		  </c:if>
<!-- 								</td> -->
<!-- 				    			</tr> -->
									<br>
				    			<c:if test="${story.accCnt eq 0}">
<!-- 				    				<tr class = "storytr"> -->
<!-- 				    				<td colspan = "2" class = "storytd"> -->
				    					<font class="accountText" size="2">
				    					<span class = "glyphicon glyphicon-usd"></span>
				    					 총액 | KRW 0원
				    					</font> 
<!-- 				    				</td> -->
<!-- 				    				</tr> -->
										<br>
				    			</c:if>
			    				<c:if test="${story.accCnt ne 0}">
					    			<c:forEach var="account" items="${accountList}">
					    				<c:if test="${account.story_idx eq story.story_idx}">
<!-- 							    			<tr class "storytr"> -->
<!-- 							     			<td colspan="2" class = "storytd"> -->
							     			<c:if test="${account.curr_idx_name ne 'USD'}">
							     			    <fmt:parseNumber var = "caledOriginCost" value= "${account.origin_cost}" integerOnly="true"></fmt:parseNumber>
												<font class ="accountText" size="2"><span class = "glyphicon glyphicon-usd"></span>${account.accType.getName() } | ${account.curr_idx_name} ${caledOriginCost}</font> 
											</c:if>
											<c:if test="${account.curr_idx_name eq 'USD'}">
												<font class ="accountText" size="2"><span class = "glyphicon glyphicon-usd"></span>${account.accType.getName() } | ${account.curr_idx_name} ${account.origin_cost}</font>
											</c:if>
<!-- 								 		    </td> -->
<!-- 											</tr> -->
											<br>
										</c:if>
									</c:forEach>
								</c:if>
								</td>
								</tr>
								<tr class = "storytr">
								<td colspan="2" class="storytd">
								<hr>
								<font class ="commentCntText" size="2">
									<span class = "commentCnt"  onclick="CommentViewClick(${story.story_idx},${ttb.plan_idx})" onmousedown="mdown($(this))" onmouseleave="mleave_gray($(this))" onmouseover="mover($(this))">덧글 ${story.commCnt}개</span>
								</font>
								</td>
								</tr>
								<tr class = "storytr">
								<td colspan="1" class = "storytd">
									<textarea id = "CommContent${story.story_idx}" class ="form-control commContent" rows="2" cols="80" placeholder="댓글을 입력하세요"></textarea>
								</td>	
								<td class="storytd" colspan="1" style="padding-bottom:20px;">
								 <button class = "savecomm" id = "saveComm${story.story_idx}" type="button" class="btn btn-secondary saveCommBtn" onclick="CommSave(${story.story_idx},${story.ttb_idx},${story.plan_idx})">등록</button>
								</td>
								</tr>
								<tr class="storytr">
								<td colspan="2" class="storytd">
									<div id = "CommentView${story.story_idx}" class="commentView"></div>
								</td>
								</tr>
							</table>					
							<br>
							<br>
							<br>						
						</c:if>			
					</c:forEach>
					</c:if>
					<!-- 플러스버튼  -->
					<c:if test="${ttb.is_story eq false}">
						<br>
						<div style="margin-left: 60px"><h2 class = "noStoryplaceName"><span class="glyphicon glyphicon-map-marker noStoryplacemarker"></span>&nbsp;${ttb.place_name}</h2></div>
						<br>
						<font size="10" color="black">
								<span class ="glyphicon glyphicon-plus-sign storyPlus" 
							data-toggle="modal" data-target="#myModal" data-backdrop="static" 
							data-place="${ttb.place_name}" data-ttbidx="${ttb.ttb_idx}" 
							data-planidx="${ttb.plan_idx}" onmouseover="mover($(this))" 
							onmouseleave="mleave($(this))" onmousedown="mdown($(this))">
							</span>
						</font>
					</c:if>
					<br><br>
				</c:if>
			</c:forEach>
			</c:forEach>
		</div> <!-- col-lg-8 끝 구간 -->
 <div class="col-lg-8"></div>
	
  <!-- 퀵 메뉴바 -->
  <div id ="slidemenu">
  <div id = "vl_slidemennu" class="vl"></div>
  	<ul style="list-style:none;">
  		<c:forEach var = "day" begin="1" end="${diffDays}" step="1">
  		<c:set var = "name" value="DayDiv${day}"/>
  			<a href="#${name}" onmouseover="mover($(this))" onmouseleave="mleave($(this))" onmousedown="mdown($(this))"><li> Day ${day} </li></a>
  		</c:forEach>
	</ul>
  </div>
  
  
 </div>	




