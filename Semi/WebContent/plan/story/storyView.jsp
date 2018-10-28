<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dto.story.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	
tr,td{
padding: 2px;
}

.vl {
    border-left: 4px solid #D3D3D3;
    height: 100%;
    position: absolute;
    left: 36px; 
    margin-left: -3px;
    top: 20px;
    z-index: -100;
}

hr{
	border: 1px solid #D3D3D3;
}

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
}

.bubble:after 
{
	content: '';
	position: absolute;
	border-style: solid;
	border-width: 7px 11px 7px 0;
	border-color: transparent #DCDCDC;
	display: block;
	width: 0;
/* 	z-index: 1; */
	left: -11px;
	top: 16px;
}

</style>
<script type="text/javascript">
	
	$(document).ready(function(){
	    
	    //edit 모드일때, 수정버튼삭제버튼추가버튼 보여주지 않음
	    function EditMode() {
	    	
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
	    
// 	    EditMode();
	    
	    
	    //slidmenu 고정
	    $(window).scroll(function() {
	        var position = $(this).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
	        var header = $("#header").offset();
	        var height = $("#header").height();
	        
	        if(position > 260){
	        	$("#slidemenu").css("position","fixed");
	        	document.getElementById("slidemenu").style.top ="10px";
	        }else if (position <= 260){
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
			
			$(".ttb_idx").val(ttb_idx);	
			
			var plan_idx = $(this).data("planidx");
			$(".plan_idx").val(plan_idx);	
		})
		
		
		//수정하기
		$(".updateStory").click(function() {
			
			var place_name =$(this).data("place");
			var story_idx = $(this).data("storyidx");
			var planidx = plan_idx;
			var content = $(this).data("content");
			
			$(".up_modalPlaceName").text(place_name);
			$(".up_story_idx").val(story_idx);	
			
			$('.up_content').froalaEditor('html.set', content);
			$(".up_plan_idx").val(planidx);	
			
		})
		
			
		//저장하기
	$(".storySaveBtn").click(function() {
			
			var storyJSON = {
				ttb_idx  : $(".ttb_idx").val()
				, plan_idx : $(".plan_idx").val() 
				, content  : $(".content").val()
			};
			
			var jsonData = JSON.stringify(storyJSON);
			
			$.ajax({
				type : "POST"
				, url : "/story/write"
				, data : {JSON : jsonData}
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
 		
 		
 		$(".storyUpdateBtn").click(function () {
 			var storyJSON = {
 					story_idx : $(".up_story_idx").val() 
 					, content  : $(".up_content").val()
 					, plan_idx :$(".up_plan_idx").val()
 				};
 				
 				var jsonData = JSON.stringify(storyJSON);
 				
 				$.ajax({
 					type : "POST"
 					, url : "/story/update"
 					, data : {JSON : jsonData}
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
	

	 
    // 마우스오버시 색바꾸기
    function plusmover(obj) {
 	   obj.css( "color", "orange" );
 	}
    
    function plusmdown(obj){
 	   obj.css( "color", "blue" );
    }
    
 	function plusmleave(obj) {
 		obj.css("color", "black");
 	}

	
</script>	



<input type="hidden" id = "calcDay"/>
<div>
  <div class="col-lg-8">
	<!-- Day Foreach문 -->
		<div id = "Day">
		</div>
			<c:forEach var='day' begin = "1" end="${diffDays}">
			<div id = "DayDiv${day}" >
			<h1><span class="glyphicon glyphicon-plane Dayimage"style="color: #555555"></span> Day ${day}</h1>
			<div class="vl"></div>
			</div>
			
			<c:forEach items='${ttbList}' var = 'ttb'>
				<c:if test="${ttb.day eq day }">
				<c:if test="${ttb.is_story eq true}">
					<c:forEach items='${storyList}' var='story'>
						<c:if test="${story.ttb_idx eq ttb.ttb_idx}">
						<font size ="3"><span class="glyphicon glyphicon-minus" style="margin-left: 15px; color: #D3D3D3;">${story.start_time}</span></font>
				    		<table class="bubble" style="margin-left: 60px;" >
				    			<tr>
				     			<td colspan="5">
								  <font size="5"><span class = "glyphicon glyphicon-remove removeStory" style="float: right;" onclick="storyDelete(${story.story_idx})"  onmouseover="plusmover($(this))" onmouseleave="plusmleave($(this))" onmousedown="plusmdown($(this))"></span>
								  <span class = "glyphicon glyphicon-pencil updateStory" 
									  style="float: right;"  
									  onmouseover="plusmover($(this))" onmouseleave="plusmleave($(this))" onmousedown="plusmdown($(this))" 
									  data-toggle="modal" data-target="#myModal_update" 
									  data-backdrop="static" data-storyidx="${story.story_idx}" 
									  data-place="${story.place_name}" data-content= '${story.content}'>
								  </span>
								  </font>
								  <div> <h2><span class="glyphicon glyphicon-map-marker"></span>&nbsp;${story.place_name}</h2>
								  <hr>
								  </div>
						 		  <!-- froala align 적용 되게 하려면 content 표시할 div에 fr-view class를 반영 해줘야함 -->
						 		  <div class = "fr-view" width="100%" style="overflow:auto; height:auto; padding: 10px;">${story.content}</div>
								</td>
				    			</tr>
				    			<tr>
				     			<td colspan="5">
				     				<hr>
									<font size="2" color="#999999">[이미지] 오락 | USD 70</font> 
					 		    </td>
								</tr>
								<tr>
					  			<td colspan="5">
						  			<font size="2" color="#999999">[이미지] 식비 | KRW 8000</font> 
					  			</td>
				    			</tr>
								<tr>
								<td colspan="5"><hr><a href="#"><font size="2" color="#999999">덧글 1개</font></a></td>
								</tr>
								<tr>
								<td colspan="4">
									<textarea style ="resize: none; overflow:visible;" rows="2" cols="100" placeholder="댓글을 입력하세요"></textarea>
								</td>	
								<td colspan="1" style="padding-bottom:20px;">
								<button type="button" class="btn btn-secondary" style="margin-bottom: -7px;">등록</button>
								</td>
								</tr>
							<!-- ajax이용, 댓글 리스트 foreach문 -->
								<tr>
									<td colspan="1" align="center" ><img src="#" class="img-circle" width="50px" height="50px"></td>
									<td colspan="2" rowspan="2"><font size="2">&nbsp;&nbsp;&nbsp;피가 하여도 무엇을 말이다. 풀밭에 착목한는 소금이라 이상의 맺어, 새 같지 때문이다.</font></td>
									<td colspan="1" rowspan="2" style="padding:20px"><font size ="1"> 2018-10-12 AM 09:03 </font></td>
									<td colspan="1" rowspan="2"><span class="glyphicon glyphicon-remove-sign"></span></td>
								</tr>
								
								<tr>
									<td colspan="1" align="center"><font size="2">닉네임</font></td>
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
						<div style="margin-left: 60px"><h2><span class="glyphicon glyphicon-map-marker"></span>&nbsp;${ttb.place_name}</h2></div>
						<br>
						<font size="10" color="black">
							<span class ="glyphicon glyphicon-plus-sign storyPlus" 
							data-toggle="modal" data-target="#myModal" data-backdrop="static" 
							data-place="${ttb.place_name}" data-ttbidx="${ttb.ttb_idx}" 
							data-planidx="${ttb.plan_idx}" onmouseover="plusmover($(this))" 
							onmouseleave="plusmleave($(this))" onmousedown="plusmdown($(this))"
							style="margin-left: 60px">
							</span>
						</font>
					</c:if>
					<br><br>
				</c:if>
			</c:forEach>
			</c:forEach>
		</div> <!-- col-lg-8 끝 구간 -->
 <div class="col-lg-4"></div>
	
  <!-- 퀵 메뉴바 -->
  <div id ="slidemenu">
  <div class="vl" style="margin-top: -20px; margin-left: -13px;"></div>
  	<ul style="list-style:none;">
  		<c:forEach var = "day" begin="1" end="${diffDays}" step="1">
  		<c:set var = "name" value="DayDiv${day}"/>
  			<a href="#${name}"><li> Day ${day} </li></a>
  		</c:forEach>
	</ul>
  </div>
  
  
 </div>	




