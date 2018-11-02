<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>


<style>
#miniModalContent{
	border: 1px solid #9AA3E6;
	height: auto;
	overflow: hidden;
}

#miniModalImg{
	width: 280px;
	height: 150px;
}
</style>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="miniViewWriteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="miniModalTitle">Modal title</h4>
      </div>
      
      <div class="modal-body" id= "miniModalBody">
		<!-- div (팝업으로 띄어줄) 본문 내용 -->	
			<input type='hidden' name='plan_idx' value='${planView.plan_idx}'> <!-- 스토리 -->
			<input type='hidden' name='JSON'> <!-- 스토리 -->
			<input type='hidden' name='ttbJson'> <!-- 해당 타임테이블 -->
			<input type='hidden' name='events'> <!-- 전체 타임테이블 -->
			<table style="width: 100%;">
			<tr>
				<td rowspan="2" style="padding: 10px 15px; width: 60%;">
				<img id="miniModalImg" alt=""/>
				</td>
				<td id="miniModalPlace" style="font-weight: bold; width: 40%;"><hr></td>
			</tr>	
			<tr>
				<td>
				추가 정보 란
				</td>
			</tr>
			</table>
			<table style="width: 100%;">
				<tr>
					<td colspan="2" style="padding: 15px">
						<div id="miniModalContent" style="height: 230px;"></div>
					</td>
				</tr>
			</table>
			<div id ="min_accountViewList">
					<div id = "min_accountView" name = "min_accountViewName">
						<table style="width: 100%;">
							<tr>	
								<td>
								<select name = "min_accType" class="min_accType">
									<option value="1">항공료</option>
									<option value="2">교통</optoin>
									<option value="3">숙박</option>
									<option value="4">입장료</option>
									<option value="5">음식</option>
									<option value="6">오락</option>
									<option value="7">쇼핑</option>
									<option value="8">기타</option>
								</select>
								</td>
								<td>
								<select name = "min_currSymbol" class="min_currSymbol">
									<option value = "1">USD</option>
									<option value = "2">KRW</optoin>
									<option value = "3">JPY</option>
								</select>
								</td>
								<td>
									<input type="text" size="48" name = "min_cost" class="min_cost" onkeypress="Numberchk()" onkeyup="vComma(this)" style = "text-align:right;"/>
								</td>
								<td>
									<span class="glyphicon glyphicon-plus 	accountPlus" onclick = "miniAppendAccount()"></span>
								</td>
								<td>
									<span class="glyphicon glyphicon-remove accountRemove" name = "min_removeAcoountName" onclick = "miniRemoveAccount()" style="display: none"></span>
								</td>
							</tr>
						</table>
					</div>
	
			</div> <!-- min_accountViewList end -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-primary" id="btnMiniWriteSave">저장</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
$("#btnMiniWriteSave").on("click", function(){
	// 미니뷰 저장 버튼 비활성화
	$(this).attr('disabled',"disabled");
	// 메인 저장 버튼 비활성화
	activeStoreBtn(false);
	
	// submit 할 객체들 json형태로 받기
	var ttbJson = JSON.parse($('input[name=ttbJson]').val());
	var storyJson = JSON.parse($('input[name=JSON]').val());
	storyJson.content = $('#miniModalContent').froalaEditor('html.get'); // story json형태에 스토리 내용도 추가(plan_idx,ttb_idx만 존재)
	
	// json -> string
	var storyJsonStr = JSON.stringify(storyJson);
	
	//account 내용
	var accTypeLen = $("select[name='min_accType']").length;
    var accType = new Array(accTypeLen);
    var currSymbol = new Array(accTypeLen);
    var cost = new Array(accTypeLen);
    
    for(var i=0; i<accTypeLen; i++){                          
    	accType[i] = $("select[name='min_accType']")[i].value;
    	currSymbol[i] = $("select[name='min_currSymbol']")[i].value;
    	cost[i] = $("input[name='min_cost']")[i].value;
    }

    //ajax로 배열 전송하기 위한 방식 
    $.ajaxSettings.traditional = true
	// 미니뷰 스토리 업데이트 작업 ajax로 실행
	$.ajax({
		url: "/story/mini/update"
		, async: false
		, type: "POST"
		, data: {
			plan_idx: plan_idx
			, JSON: storyJsonStr
			, ttbJson: JSON.stringify(ttbJson)
			, "accType": accType
			, "currSymbol" :currSymbol
			, "cost":cost
			, "USD_rate":USD_rate
			, "KRW_rate":KRW_rate
			, "JPY_rate":JPY_rate
		}
		, dataType: "json"
		, success: function(d){
			// 미니뷰 저장 성공 시 
			// 미니뷰 작성한 타임테이블의 이전 idx와 저장 후 idx값 넘겨줌
			store(ttbJson.ttb_idx, d.ttb_idx, isSendWriteMode=true); 
		}
		,  error: function(){
			console.log("Mini-view Write Ajax 통신 실패");
		}
	});
});
</script>
<script>
$(function() {
$.FroalaEditor.COMMANDS.imageAlign.options.justify = 'Center';

$('#miniModalContent').froalaEditor({
    // Set the image upload URL.
    enter: $.FroalaEditor.ENTER_DIV,
    charCounterCount: false,
    pluginsDisable: ["quickInsert"],
    //모달 사용할때 발생하는 문제 : image edit menu가 안뜸, -> 해결법 : zIndex를 높여라
    zIndex: 2501,
    toolbarButtons: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
    toolbarButtonsXS: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
    toolbarButtonsSM: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
    toolbarButtonsMD: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
    imageUploadURL: '/story/upload_image',
    imageUploadParams: {
      id: 'my_editor'
    },
 imageEditButtons : ['imageAlign', 'imageRemove', 'imageLink','imageSize','imageDisplay'],
 heightMin: 185,
    heightMax: 185,
  }).on('froalaEditor.image.error', function (e, editor, error, response) {
	  console.log(error);
	  console.log(response);
	}).on('froalaEditor.image.removed', function (e, editor, $img) {
        $.ajax({
            // Request method.
            method: "POST",
   
            // Request URL.
            url: "/story/image_delete",
   
            // Request params.
            data: {
              src: $img.attr('src')
            }
          })
          .done (function (data) {
            console.log ('image was deleted');
            console.log($img.attr('src'));
          })
          .fail (function () {
            console.log ('image delete problem');
          })
        });
});

</script> 

<script type="text/javascript">
	
	var cnt = 0; //가계부 5개까지만 추가 하기 위한 count
	//가계부 추가
	function miniAppendAccount() {
		if (cnt < 4){
			var accountView = $("#min_accountView").clone();
 			$("#min_accountViewList").append(accountView);
 			
 			//cnt 증가
			cnt = cnt+1;
		
 			//현재까지 추가된 가계부 갯수 
			var size = document.getElementsByName("min_accountViewName").length;

			for(var i = 0; i < size; i++){
		        var obj = document.getElementsByName("min_accountViewName")[i];
		        
		        // + 버튼 안보여주기
		        // - 버튼 보여주기
		        $(obj).find(".accountPlus").css("display","none");
		        $(obj).find(".accountRemove").css("display","block");
		        
		        // i == size-1 (현재 추가된 가계부중 마지막의 가계부일때)
		        if (i == size-1){
		        	//+버튼 보여주기
				    $(obj).find(".accountPlus").css("display","block");
		        	
		        	//비용,가계부타입, 환율코드 초기화
		        	$(obj).find(".min_cost").val("");
		        	$(obj).find(".min_accType").val(1);
		        	$(obj).find(".min_currSymbol").val(1);
		        }
		        
		        //현재 추가된 가계부중 마지막 이면서 총 갯수가 5일때
		        if (size == 5 && i == size-1){
		        	//+ 버튼 안보여주기
			    	 $(obj).find(".accountPlus").css("display","none");
		        }
		 }
	
	}
}
	
	//가계부 삭제
	function miniRemoveAccount() {
		
		var removeObj = window.event.target.parentElement.parentElement.parentElement.parentElement.parentElement; //가계부 div 객체
		//가계부 div 삭제
		removeObj.remove();	
		
		//가계부 5개까지만 추가 하기 위한 count 감소
		cnt = cnt-1;
		
		//var removeObj 라고 메모리에 할당되어있으면 완벽하게 삭제가 되지 않음
		//메모리에서도 가계부 div를 삭제 시켜줌
		delete removeObj;
		
		//삭제되고 남은 가계부 총 개수
		var size = document.getElementsByName("min_accountViewName").length;
		
		for(var i = 0; i < size; i++){
	        var obj = document.getElementsByName("min_accountViewName")[i];
	        
	        //삭제하고 남은 가계부의 개수가 1개일때
	        if (size == 1){
	        	// - 버튼 안보여주기
	        	$(obj).find(".accountRemove").css("display","none");
	        }
	        
	        // + 버튼 안보여주기
	        $(obj).find(".accountPlus").css("display","none");
	        
	        // 현재 가계부가 마지막 가계부일때
	        if (i == size-1){
	        	//+버튼 보여주기
			     $(obj).find(".accountPlus").css("display","block");
	        }
	        
	        //현재 가계부가 마지막 이면서 총 갯수가 5일때
	        if (size == 5 && i == size-1){
	        	//+ 버튼 안보여주기
		    	 $(obj).find(".accountPlus").css("display","none");
	        }
		}
	}
	
	$(document).ready(function(){	
		
	//모달 숨겨질때
		$('.modal').on('hidden.bs.modal',function(e){
			
		//현재추가된acountDiv의 갯수
			var size = document.getElementsByName("min_accountViewName").length
			
			//한개 빼고 전부 remove
			for(var i = size-1; i > 0; i--){
	        	var obj = document.getElementsByName("min_accountViewName")[i];
	       	 	obj.remove();
			}
			
			//첫번째 accountDivView 가져오기
			var obj1 = document.getElementsByName("min_accountViewName")[0]
			
			//정보 리셋
			$(obj1).find(".min_accType").val(1);
        	$(obj1).find(".min_currSymbol").val(1);
			$(obj1).find(".min_cost").val("");
			
			//+버튼 보여주기
			//-버튼 안보여주기
			$(obj1).find(".accountPlus").css("display","block");
	        $(obj1).find(".accountRemove").css("display","none");
	        
	        //카운트값 초기화(가계부 입력공간 개수)
	        cnt = 0;
	        
		})	
	
	});


</script>

</body>
</html>