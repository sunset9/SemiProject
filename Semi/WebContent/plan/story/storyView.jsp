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

#slidemenu{
/* 	background:#12cf3d; */
	background-color: rgba( 255, 255, 255, 0 );
	position:absolute;
	width:100px;
/*  	top:200px; */
	right:0;
/* 	opacity: 0; */
}
	
tr{
	padding: 2px;
}
td{
	padding: 2px;
	padding-left: 10px;
}

/*퀵메뉴, 스토리 수직선*/
.vl {
    border-left: 4px solid #D3D3D3;
    height: 100%;
    position: absolute;
    left: 36px; 
    margin-left: -3px;
    top: 20px;
    z-index: -100;
}

/*퀵메뉴 수직선 (따로적용해야 하는 값들)*/
#vl_slidemennu{
	margin-top: -20px;
	margin-left: -13px;
}

hr{
	border: 1px solid #D3D3D3;
}

/*말풍선*/
.bubble 
{
	position: relative;
	width: 280px;
	height: 175px;
	padding: 18px;
	background: #DCDCDC;
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
	border-color: transparent #DCDCDC;
	display: block;
	width: 0;
	left: -11px;
	top: 16px;
}

/*비행기이미지*/
.Dayimage{
	color: #555555;
}


/*일차글씨*/
.Daytext{

}

/*시작시간*/
.startTime{
	margin-left: 18px;
	color: #D3D3D3;
}

/*스토리 삭제버튼*/
.removeStory{
	float: right;
	cursor: pointer;
}

/*스토리 업데이트버튼*/
.updateStory{
	float: right;
	cursor:pointer;
}

/*장소이름*/
.storyPlaceName{

}

/* 장소 마커*/
.placemarker{

}

/*스토리본문쪽*/
.storycontent{
	overflow: auto;
	height: auto;
	padding: 10px;
	width: 100%;
}


/*스토리없을때 장소이름*/
.noStoryplaceName{
	width: 750px;
}

/*스토리없을때 장소마커*/
.noStoryplacemarker{
}

/*스토리 없을때 +버튼*/
.storyPlus{
	margin-left: 60px;
	cursor: pointer;
}


/*가계부 폰트*/
.accountText{
	color: #999999;
}

/*덧글갯수*/
.commentCnt{
	cursor:pointer;
	color: #999999;
}

/*덧글 TextArea*/
.commContent{
	resize: none;
	overflow: visible;
}

/*덧글 저장*/
.saveCommBtn{
	margin-bottom: -7px; 
	margin-left: 310px;
}

.commentView{
	display: none;
}





</style>
<script type="text/javascript">

var cnt = 0;
var up_cnt = 0;
	$(document).ready(function(){

	    
	    function EditMode() {
	     //edit 모드일때, 수정버튼삭제버튼추가버튼 보여주지 않음	
	     var removeStorys = document.getElementsByClassName("removeStory");
	     var updateStorys = document.getElementsByClassName("updateStory");
	     var plusStorys = document.getElementsByClassName("storyPlus");
	     
	     console.log("isModify::"+isModify);
	     
	    	if (isModify == 1){
   			  for(var i = 0; i < removeStorys.length; i++){
	    		  removeStorys[i].style.display = "block"; 
	    		  updateStorys[i].style.display = "block";
	    		 
	    	   }
   			  
   			  for(var i=0; i<plusStorys.length;i++){
   				 plusStorys[i].style.display = "block";
   			  }
   			  
	    	}else{
    		  for(var i = 0; i < removeStorys.length; i++){
	    		  removeStorys[i].style.display = "none"; 
	    		  updateStorys[i].style.display = "none";
	    		 
	    	   }
    		  
    		  for(var i=0; i<plusStorys.length;i++){
    			  plusStorys[i].style.display = "none";
    		  }
	    	}
			
		}
	    
	    EditMode();
	    
	    
	    //slidmenu 고정
	    $(window).scroll(function() {
	        var position = $(this).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
	        var header = $("#container").offset();
	        var height = $("#container").height();
	        
	        if(position > 600){
	        	$("#slidemenu").css("position","fixed");
	        	document.getElementById("slidemenu").style.top ="10px";
	        }else if (position <= 600){
	        	$("#slidemenu").css("position","absolute");
	        	document.getElementById("slidemenu").style.top = header.top + height +50+ "px";  		        
	        }
	        
	    });

		//추가하기
		$(".storyPlus").click(function() {
			
			
			var place_name = $(this).data("place");
			
			console.log(place_name);
			
			$(".modalPlaceName").text(place_name);
			
			var ttb_idx = $(this).data("ttbidx");
			
			$(".st_ttb_idx").val(ttb_idx);	
			
			var plan_idx = $(this).data("planidx");
			$(".st_plan_idx").val(plan_idx);	
		})
		

		
		
		//수정하기
		$(".updateStory").click(function() {
			
				var place_name =$(this).data("place");
				var story_idx = $(this).data("storyidx");
				var planidx = plan_idx;
				var content = $(this).data("content");
				var ttb_idx = $(this).data("ttbidx");
			
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
					accountCategory.push("${account.category}");
					accountCurridx.push("${account.curr_idx}");
					accountCost.push("${account.origin_cost}");
				</c:forEach>
				
				var count = 0;
				for (var i = 0; i <accountStoryidx.length; i++) {
						if( story_idx == accountStoryidx[i]){
							var accountView = $("#up_accountView").clone();
							
							if(count != 0){
								$("#up_accountViewList").append(accountView);	
							}
							count = count+1;
						}
				}
					
		 		var size = document.getElementsByName("up_accountViewName").length;
		 		
		 		var accCurrNameList = [];
		 		var accTypeList = [];
		 		var accCostList = [];
		 		
		 		for(var i = 0; i < size; i++){
			        var obj = document.getElementsByName("up_accountViewName")[i];
			        
			        $(obj).find(".accountPlus").css("display","none");
			        $(obj).find(".accountRemove").css("display","block");
			        
			        var ch = false;
			        for (var j = 0; j <accountStoryidx.length; j++) {
						if( story_idx == accountStoryidx[j]){
							accTypeList.push(accountCategory[j]);
							accCurrNameList.push(accountCurridx[j]);
							accCostList.push(accountCost[j]);
							ch = true;
						}
			        }
			        
			        if (ch == false) {
					   $(obj).find(".up_accType").val(1);
				        $(obj).find(".up_currSymbol").val(1);
						$(obj).find(".up_cost").val("");
			        }else{
				        $(obj).find(".up_accType").val(accTypeList[i]);
				        $(obj).find(".up_currSymbol").val(accCurrNameList[i]);
						$(obj).find(".up_cost").val(accCostList[i]);
			        }

			        if (i == size-1){
					    $(obj).find(".accountPlus").css("display","block");
			        }
			        
			        if (size == 5 && i == size-1){
				    	 $(obj).find(".accountPlus").css("display","none");
			        }
				 }
			 		
			
		})

		
	//스토리 새로 만드는 모달 save 버튼클릭 이벤트
	//스토리저장
	$(".storySaveBtn").click(function() {
			
			var storyJSON = {
				ttb_idx  : $(".st_ttb_idx").val()
				, plan_idx : $(".st_plan_idx").val() 
				, content  : $(".st_content").val()
			};
			
			console.log( $(".st_ttb_idx").val());
			console.log( $(".st_plan_idx").val());
			console.log( $(".st_content").val());
			
			var accTypeLen = $("select[name='st_accType']").length;
		    var accType = new Array(accTypeLen);
		    var currSymbol = new Array(accTypeLen);
		    var cost = new Array(accTypeLen);
		    
		    for(var i=0; i<accTypeLen; i++){                          
		    	accType[i] = $("select[name='st_accType']")[i].value;
		    	currSymbol[i] = $("select[name='st_currSymbol']")[i].value;
		    	cost[i] = $("input[name='st_cost']")[i].value;
		    }

			
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
 			var storyJSON = {
 					story_idx : $(".up_story_idx").val() 
 					, content  : $(".up_content").val()
 					, plan_idx :$(".up_plan_idx").val()
 					, ttb_idx : $(".up_ttb_idx").val()
 				};
 				
 				var jsonData = JSON.stringify(storyJSON);
 				
 				console.log($(".up_content").val());
 				
 				var accTypeLen = $("select[name='up_accType']").length;
 			    var accType = new Array(accTypeLen);
 			    var currSymbol = new Array(accTypeLen);
 			    var cost = new Array(accTypeLen);
 			    
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
 			
 			for(var i = size-1; i > 0; i--){
		        var obj = document.getElementsByName("up_accountViewName")[i];
		        obj.remove();
 			}
 			
 			var obj1 = document.getElementsByName("up_accountViewName")[0]
 			
 			$(obj1).find(".up_accType").val(1);
	        $(obj1).find(".up_currSymbol").val(1);
			$(obj1).find(".up_cost").val("");
			
			//--추가 모달
			var edit = document.getElementById("st_edit");
			
			$('.st_content').froalaEditor('html.set', "");
			
 			var size = document.getElementsByName("accountViewName").length
 			
 			for(var i = size-1; i > 0; i--){
		        var obj = document.getElementsByName("accountViewName")[i];
		        obj.remove();
 			}
 			
 			var obj1 = document.getElementsByName("accountViewName")[0]
 			
 			$(obj1).find(".st_accType").val(1);
	        $(obj1).find(".st_currSymbol").val(1);
			$(obj1).find(".st_cost").val("");
			
			$(obj1).find(".accountPlus").css("display","block");
	        $(obj1).find(".accountRemove").css("display","none");
	        
	        //모달 닫으면 카운트값 초기화(가계부입력공간 개수)
	        cnt = 0;
	        up_cnt = 0;
 		})
	});
	

	//삭제하기
	function storyDelete(storyIdx) {
		
		var r = confirm("스토리를 삭제 하시겠습니까?");
		
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
		if($(commentViewId).is(":visible")){
			$(commentViewId).css("display","none");
			
		}else{
			
			$(commentViewId).css("display","block");
			
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
	
    // 마우스오버시 색바꾸기
    function mover(obj) {
 	   obj.css( "color", "orange" );
 	}
    
    function mdown(obj){
 	   obj.css( "color", "blue" );
    }
    
 	function mleave(obj) {
 		obj.css("color", "black");
 	}
 	
 	function mleave_gray(obj) {
 		obj.css("color", "#999999");
	}

 	//댓글 세이브
 	function CommSave(story_idx,ttb_idx,plan_idx) {
 		
 		var id = "#CommContent"+story_idx;
 		
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
 		
 		if (cnt < 5){
			
			var accountView = $("#story_accountView").clone();
	 		$("#accountViewList").append(accountView);
	 		
			cnt = cnt+1;
			
			var size = document.getElementsByName("accountViewName").length;
	
			for(var i = 0; i < size; i++){
		        var obj = document.getElementsByName("accountViewName")[i];
		        
		        $(obj).find(".accountPlus").css("display","none");
		        $(obj).find(".accountRemove").css("display","block");
		        
		        if (i == size-1){
				    $(obj).find(".accountPlus").css("display","block");
		        	$(obj).find(".st_cost").val("");
		        	$(obj).find(".st_accType").val(1);
		        	$(obj).find(".st_currSymbol").val(1);
		        }
		        
		        if (size == 5 && i == size-1){
			    	 $(obj).find(".accountPlus").css("display","none");
			    	
		        }
			 }
		
		}
	}
 	
 	//가계부 삭제
 	function removeAccount() {
 		
 		var removeObj = window.event.target.parentElement.parentElement.parentElement.parentElement.parentElement;
 		
 		removeObj.remove();	
 		
 		cnt = cnt-1;
 		
 		delete removeObj;
 		
 		var size = document.getElementsByName("accountViewName").length;
 		
		for(var i = 0; i < size; i++){
	        var obj = document.getElementsByName("accountViewName")[i];
	        
	        if (size == 1){
	        	$(obj).find(".accountRemove").css("display","none");
	        }
	        
	        $(obj).find(".accountPlus").css("display","none");
	        
	        if (i == size-1){
			     $(obj).find(".accountPlus").css("display","block");
	        }
	        
	        if (size == 5 && i == size-1){
		    	 $(obj).find(".accountPlus").css("display","none");
	        }
		 }
	}
 	
 	
 	
 	//업데이트 가계부 추가
 	up_cnt = document.getElementsByName("up_accountViewName").length;
 	function UpappendAccount() {
 		if (up_cnt < 5){
 			
 			console.log(up_cnt);
 			
			var accountView = $("#up_accountView").clone();
	 		$("#up_accountViewList").append(accountView);
		    
	 		up_cnt = up_cnt+1;
			
			var size = document.getElementsByName("up_accountViewName").length;
	
			for(var i = 0; i < size; i++){
		        var obj = document.getElementsByName("up_accountViewName")[i];
		        
		        $(obj).find(".accountPlus").css("display","none");
		        $(obj).find(".accountRemove").css("display","block");
		        
		        if (i == size-1){
		        	console.log("1");
				    $(obj).find(".accountPlus").css("display","block");
		        	$(obj).find(".up_cost").val("");
		        	$(obj).find(".up_accType").val(1);
		        	$(obj).find(".up_currSymbol").val(1);
		        }
		        
		        if (size == 5 && i == size-1){
			    	 $(obj).find(".accountPlus").css("display","none");
		        }
			 }
		
		}
	}
 	
 	
 	//업데이트 가계부 삭제
	function UpremoveAccount() {
 		
 		var removeObj = window.event.target.parentElement.parentElement.parentElement.parentElement.parentElement;
 		
 		removeObj.remove();	
 		
 		up_cnt = up_cnt-1;
 		
 		delete removeObj;
 		
 		var size = document.getElementsByName("up_accountViewName").length;
 		
		for(var i = 0; i < size; i++){
	        var obj = document.getElementsByName("up_accountViewName")[i];
	        
	        if (size == 1){
	        	$(obj).find(".accountRemove").css("display","none");
	        }
	        
	        $(obj).find(".accountPlus").css("display","none");
	        
	        if (i == size-1){
			     $(obj).find(".accountPlus").css("display","block");
	        }
	        
	        if (size == 5 && i == size-1){
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
				<c:if test="${ttb.is_story eq true}">
					<c:forEach items='${storyList}' var='story'>
						<c:if test="${story.ttb_idx eq ttb.ttb_idx}">
						<font size ="3"><span class="glyphicon glyphicon-minus startTime">${story.start_time}</span></font>
				    		<table class="bubble">
				    			<tr>
				     			<td colspan="2">
								  <font size="5"><span class = "glyphicon glyphicon-remove removeStory" onclick="storyDelete(${story.story_idx})"  onmouseover="mover($(this))" onmouseleave="mleave($(this))" onmousedown="mdown($(this))"></span>
								  <span class = "glyphicon glyphicon-pencil updateStory"   
									  onmouseover="mover($(this))" onmouseleave="mleave($(this))" onmousedown="mdown($(this))" 
									  data-toggle="modal" data-target="#myModal_update" 
									  data-backdrop="static" data-storyidx="${story.story_idx}" 
									  data-place="${story.place_name}" data-content= '${story.content}' data-ttbidx='${story.ttb_idx}'>
								  </span>
								  </font>
								  <div><h2 class = "storyPlaceName"><span class="glyphicon glyphicon-map-marker placemarker"></span>&nbsp;${story.place_name}</h2>
								  <hr>
								  </div>
						 		  <!-- froala align 적용 되게 하려면 content 표시할 div에 fr-view class를 반영 해줘야함 -->
						 		  <div class = "fr-view storycontent">${story.content}</div>
								  <hr>
								</td>
				    			</tr>
				    			<c:if test="${story.accCnt eq 0}">
				    				<tr>
				    				<td colspan = "2">
				    					<font class="accountText" size="2"> 총액 | KRW 0원</font> 
				    				</td>
				    				</tr>
				    			</c:if>
			    				<c:if test="${story.accCnt ne 0}">
					    			<c:forEach var="account" items="${accountList}">
					    				<c:if test="${account.story_idx eq story.story_idx}">
							    			<tr>
							     			<td colspan="2">
							     			<c:if test="${account.curr_idx_name ne 'USD'}">
							     			    <fmt:parseNumber var = "caledOriginCost" value= "${account.origin_cost}" integerOnly="true"></fmt:parseNumber>
												<font class ="accountText" size="2"> ${account.category_name } | ${account.curr_idx_name} ${caledOriginCost}</font> 
											</c:if>
											<c:if test="${account.curr_idx_name eq 'USD'}">
												<font class ="accountText" size="2"> ${account.category_name } | ${account.curr_idx_name} ${account.origin_cost}</font>
											</c:if>
								 		    </td>
											</tr>
										</c:if>
									</c:forEach>
								</c:if>
								<tr>
								<td colspan="2">
								<hr>
								<font class ="commentCntText" size="2">
									<span class = "commentCnt"  onclick="CommentViewClick(${story.story_idx},${ttb.plan_idx})" onmousedown="mdown($(this))" onmouseleave="mleave_gray($(this))" onmouseover="mover($(this))">덧글 ${story.commCnt}개</span>
								</font>
								</td>
								</tr>
						
								<tr>
								<td colspan="1">
									<textarea id = "CommContent${story.story_idx}" class ="commContent" rows="2" cols="80" placeholder="댓글을 입력하세요"></textarea>
								</td>	
								<td colspan="1" style="padding-bottom:20px;">
								 <button id = "saveComm${story.story_idx}" type="button" class="btn btn-secondary saveCommBtn" onclick="CommSave(${story.story_idx},${story.ttb_idx},${story.plan_idx})">등록</button>
								</td>
								</tr>
								<tr>
								<td colspan="2">
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
  			<a href="#${name}"><li> Day ${day} </li></a>
  		</c:forEach>
	</ul>
  </div>
  
  
 </div>	




