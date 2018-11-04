<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

#miniModalBody{
	padding: 30px;
}
.modal-open {
    overflow-y: scroll; 
}

.modal-header {
	background: #4FB99F;
	border-radius: 6px 6px 0px 0px;
}

#miniModalBody > tbody tr{
	height: 40px;
}

#miniViewTitle{
    width: 500px;
    overflow:hidden;
	text-overflow:ellipsis; /* 글자 ... 처리*/
	white-space:nowrap; /*공백문자가 있는 경우 줄바꿈하지 않고 한줄로 나오게 처리 */ 
}

#miniViewImg{
	width: 280px;
	height: 150px;
}

#miniViewPlace{
	font-weight: bold;
	font-size: large;
	width: 42%;
	
}

#miniViewAddress {
	font-size: 14px;
	color: #888;
	color: #b1b0b0;
    line-height: 1.2em;
    overflow: hidden;
}

#miniViewContent{
	border: 1px solid gray;
	box-shadow: 0px 5px 28px -9px grey;  /* h-shadow v-shadow blur spread color*/
	overflow-y: auto; 
	height: 230px;
	margin-top: 26px;
	padding: 6px;
}

#miniModalAccount{
	margin-top: 15px;
}

/* 수정 버튼*/
#miniEditModeBtn{
	float: right;
    opacity: .7; 
	color: white;
    font-size: 19px;
    margin-right: 5px;
    margin-top: 7px;
}
/* 수정 버튼 위에 마우스 놓을때 */
#miniEditModeBtn:focus, #miniEditModeBtn:hover{
	float: right;
	opacity: .7; 
	color: black;
    cursor: pointer;
    text-shadow: 0 1px 0 #fff;	
}

/* 닫기 버튼 */
button.close{
    opacity: .7; 
	color: white;
    font-size: 30px;
    margin-top: 4px !important;
}
/* 닫기 버튼 위에 마우스 */
button.close:focus, button.close:hover{
    opacity: .7;
    color: black;
    font-size: 30px;
}

</style>
<script type="text/javascript">

$(document).ready(function(){	
	// 모달 창 닫히면 가계부 지우기
	$('.modal').on('hidden.bs.modal',function(e){
		var obj = document.getElementById("miniModalAccount");
		$(obj).html("");
	})
	
	// 수정버튼 클릭 리스너
	$('#miniEditModeBtn').on('click',function(){
		$.ajax({
			url: "/story/mini/modeChange"
			, type: "GET"
			, dataType: "html"
			, success: function(writeHtml){
				displayMiniWrite(writeHtml);
			}
		});
	});
});

// 수정모드의 미니뷰 보여주기
function displayMiniWrite(writeHtml){
	// 수정 모드 모달 추가
	$("#miniViewModal").after(writeHtml);
	// 바로 바뀌게 하기 위해 fade 효과제거
// 	$("#miniWriteModal").removeClass('fade');
	// 모달 띄워줌
	$("#miniWriteModal").modal('show');
		
	// 바로 사라지게 하기 위해 fade 효과 제거
	$("#miniViewModal").removeClass('fade');
	// 읽기 모드 모달 숨기기
	$("#miniViewModal").modal('hide');
	// 다시 fade 효과 추가해놓음
	$("#miniViewModal").addClass('fade');
	
	
	// ajax로 story 정보 가져옴 (content 정보)
	$.ajax({
		url: "/story/mini/view"
		, type: "GET"
		, data: {
			JSON: JSON.stringify({
					plan_idx: plan_idx
					, ttb_idx: eventMini.id
				})
		}
		, dataType: "json"
		, success: function(obj){
			var story = JSON.parse(obj.story);
			var accountList = JSON.parse(obj.accountList);
			
			// miniView modal에 값 채워줌
			$("#miniWriteTitle").text(eventMini.title); // 타이틀 = 장소이름
			$("#miniWritePlace").text(eventMini.title); // 장소 이름
			$("#miniWriteAddress").text(eventMini.address);  // 주소
			$("#miniWriteImg").attr("src", eventMini.photo_url); // 이미지
			
			// ttb정보 json String 형태로 넘겨줌
			$("input[name=ttbJson]").val(JSON.stringify(getTtbJsonForServer(eventMini)));
			
			// story 정보 json String 형태로 넘겨줌
			$('input[name=JSON]').val(JSON.stringify({ 
				plan_idx: plan_idx // plan_idx
				, ttb_idx: eventMini.id // ttb_idx
			}));
			
			// 스토리 내용 띄워주기
			$("#miniWriteContent").froalaEditor('html.set', story.content);
			
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
			
// 	 		var size = document.getElementsByName("min_accountViewName").length;
	 		var size = $('[name=w-min_accountViewName]').length;
	 		console.log(size);
	  		for(var i = 0; i < size; i++){
	  			var obj = $("[name=w-min_accountViewName]")[i];
	
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
	 				$(obj).find(".w-min_cost").val(accountList[i].origin_cost);
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
				$(this).find($("#miniWriteContent")).html('');
			});
		}
		, error: function(){
			console.log("Mini-view Ajax 통신 실패");
		}
	}); // ajax end
} // displayMiniWrite() end
</script>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="miniViewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span id="miniEditModeBtn" class="glyphicon glyphicon-pencil"></span>
        <h4 id="miniViewTitle">Modal title</h4>
      </div>
      
		<!-- div (팝업으로 띄어줄) 본문 내용 -->	
      <div class="modal-body" id= "miniModalBody">
      		<input type='hidden' name='miniEventInfo' /> 
			<table style="width: 100%;">
			<tr>
				<td rowspan="2">
				<img id="miniViewImg" alt=""/>
				</td>
				<td id="miniViewPlace"><hr></td>
			</tr>	
			
			<tr>
				<td>
					<div id="miniViewAddress"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" >
					<div id="miniViewContent"></div>
				</td>
			</tr>
			<tr>
				<td>
					<div id = "miniModalAccount"></div>
				</td>
			</tr>

			</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>