<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>

<!-- miniViewWrite CSS -->
<style>
#miniModalBody{
    padding: 27px 30px 14px 30px;
}

.modal-open {
    overflow-y: scroll; 
}

.modal-header {
	background: #4FB99F;
	padding: 10px 15px;
	border-radius: 6px 6px 0px 0px;
}
.modal-footer {
	padding: 10px 15px;
}

#miniModalBody > tbody tr{
	height: 40px;
}


#miniWriteTitle{
    width: 460px;
    overflow:hidden;
    padding-left: 18px;
	text-overflow:ellipsis; /* 글자 ... 처리*/
	white-space:nowrap; /*공백문자가 있는 경우 줄바꿈하지 않고 한줄로 나오게 처리 */ 
}

#miniWriteModal .glyphicon-map-marker {
	float: left;
    top: 7px;
    left: 5px;
    font-size: 25px;
}

#miniWriteImg{
	width: 280px;
	height: 150px;
}

#miniWritePlace{
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

#miniWriteContent{
	border: 1px solid gray;
	overflow-y: auto; 
	height: 250px;
	margin-top: 20px;
}

#w-miniModalAccount{
	margin-top: 15px;
}

/* 닫기 버튼 */
button.close{
    opacity: .7; 
	color: white;
    font-size: 30px;
    margin-top: 6px !important;
}
/* 닫기 버튼 위에 마우스 */
button.close:focus, button.close:hover{
    opacity: .7;
    color: black;
    font-size: 30px;
}

/* 저장 버튼 */
#btnMiniWriteSave {
    background-image: linear-gradient(to bottom, #50b69c 0,#429480 100%);
	border-color: #28715e;
}

/* 저장 버튼 위에 마우스*/
#btnMiniWriteSave:hover {
    background: #429480;
	border-color: #28715e;
}

/* 가계부 입력 폼 */
#w-min_accountView{
	margin-bottom: 1px;
}

.form-control {
	height: 26px;
    padding: 4px 11px;
}

/* 가계부 카테고리 select */
#w-min_accountView td:nth-child(1) {
    width: 88px;
    padding-right: 3px;
}
/* 가계부 화폐단위 select */
#w-min_accountView td:nth-child(2) {
	width: 82px;
	padding-right: 3px;
}
/* 금액 입력 */
#w-min_accountView td:nth-child(3) {
    width: 338px;
    padding-right: 20px;
}

/* 가계부 목록 삭제, 추가 버튼 */
#w-min_accountView span.glyphicon{
	color: #bbb;
}

/* 가계부 목록 삭제*/
.w-accountRemove {
	display: inline;
	position: relative;
	top: 0px;
	left: -1px;
}
.w-accountRemove:hover {
	color: #444 !important;
}

/* 가계부 목록 추가 */
.w-accountPlus {
	display: inline;
	position: relative;
	top: 0px;
	left: -10px;
}

.w-accountPlus:hover {
	color: #48a38d !important;
}

</style>
<script type="text/javascript">
//환율정보
var USD_rate=0;
var KRW_rate=0;
var JPY_rate=0;

//환율정보 가져오는 api
$.ajax({ 
  url: "http://api.manana.kr/exchange/rate/KRW/JPY,KRW,USD.json", 
  type: "GET", 
  crossDomain: true, 
  dataType: "json", 
  success: function (data) { 
	   	 JPY_rate = data[0].rate; 
        KRW_rate = data[1].rate;
        USD_rate = data[2].rate;
    },
  error : function (e) {
    console.log(e);

	}
}); 

$(document).ready(function(){	
	//모달 숨겨질때
	$('#miniWriteModal').on('hidden.bs.modal',function(e){
		
	//현재추가된acountDiv의 갯수
		var size = document.getElementsByName("w-min_accountViewName").length
		
		//한개 빼고 전부 remove
		for(var i = size-1; i > 0; i--){
        	var obj = document.getElementsByName("w-min_accountViewName")[i];
       	 	obj.remove();
		}
		
		//첫번째 accountDivView 가져오기
		var obj1 = document.getElementsByName("w-min_accountViewName")[0]
		
		//정보 리셋
		$(obj1).find(".w-min_accType").val(1);
       	$(obj1).find(".w-min_currSymbol").val(1);
		$(obj1).find(".w-min_cost").val("");
		
		//+버튼 보여주기
		//-버튼 안보여주기
		$(obj1).find(".w-accountPlus").css("display","block");
        $(obj1).find(".w-accountRemove").css("display","none");
        
        //카운트값 초기화(가계부 입력공간 개수)
        cnt = 0;
        
        
        //읽기모드인 경우  (= 미니뷰 수정 상황)
    	if(!isModify){
	    	// 미니뷰 수정모드 모달 삭제
			$("#miniWriteModal").remove();
    	}
	})	
	
	// 미니뷰 저장 버튼 리스너
	$("#btnMiniWriteSave").on("click", function(){
		if(isModify){ // 수정모드인 경우 (읽기모드인 경우도 있음 = 미니뷰 수정)
			// 메인 저장 버튼 비활성화
			activeStoreBtn(false);
		}
		
		// submit 할 객체들 json형태로 받기
		var ttbJson = JSON.parse($('input[name=ttbJson]').val());
		var storyJson = JSON.parse($('input[name=JSON]').val());
		storyJson.content = $('#miniWriteContent').froalaEditor('html.get'); // story json형태에 스토리 내용도 추가(plan_idx,ttb_idx만 존재)
		
		// json -> string
		var storyJsonStr = JSON.stringify(storyJson);
		
		//account 내용
		var accTypeLen = $("select[name='w-min_accType']").length;
	    var accType = new Array(accTypeLen);
	    var currSymbol = new Array(accTypeLen);
	    var cost = new Array(accTypeLen);
	    
	    for(var i=0; i<accTypeLen; i++){                          
	    	accType[i] = $("select[name='w-min_accType']")[i].value;
	    	currSymbol[i] = $("select[name='w-min_currSymbol']")[i].value;
	    	cost[i] = $("input[name='w-min_cost']")[i].value;
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
				if(isModify){ // 수정 모드인 경우
					isStayWriteMode = true;	
					
					var timetables = [];
					var events = $("#calendar").fullCalendar('clientEvents');
					events.forEach(function(event){ // 모든 리스트 돌면서 timetable json 하나씩 생성
						var timetable = getTtbJsonForServer(event);
						timetables.push(timetable);
					});

					// ttb_idx바꿔야하는 타임테이블 찾아서 ttb_idx값 변경
					var beforeTtbIdx = ttbJson.ttb_idx;
					var afterTtbIdx = d.ttb_idx;
					if(beforeTtbIdx!=null && afterTtbIdx != null){
						for(var i = 0; i<timetables.length; i++){
							if(timetables[i].ttb_idx == beforeTtbIdx){
								timetables[i].ttb_idx = afterTtbIdx;
								break;
							}
						}
					}
					
					// 미니뷰 저장 성공 시 , 미니뷰 작성한 타임테이블의 이전 idx와 저장 후 idx값 넘겨줌
					var succ = store(timetables);
					
					// 뒤에 타임테이블 다시 그려주기
					if(succ){
						location.reload();
					}
					
					// 모달 닫기
					$("#miniWriteModal").modal('hide');
				} else{ // 읽기 모드인 경우 (= 미니뷰 수정 상황)
					// 미니뷰 수정모드 모달 삭제
					$("#miniWriteModal").modal('hide');
					
					// 일정의 가계부 파트 다시 그려주기
					$.ajax({
						url: "/plan/accView"
						, type: "GET"
						, dataType: "json"
						, data: {
							plan_idx: plan_idx
						}, success: function(accCostJson){
							// 가계부 내용 다시 그려주기
							displayAcc(accCostJson);
						}, error: function(){
							console.log("가계부 그려주기 ajax 실패");
						}
					});
				}
			}
			,  error: function(){
				console.log("Mini-view Write Ajax 통신 실패");
			}
		});
	}); // #btnMiniWriteSave onclick() end
}); // $(document).ready end
</script>
<script type="text/javascript">
var cnt = 0; //가계부 5개까지만 추가 하기 위한 count
//가계부 추가
function miniAppendAccount() {
	if (cnt < 4){
		var accountView = $("#w-min_accountView").clone();
		$("#w-miniModalAccount").append(accountView);
			//cnt 증가
		cnt = cnt+1;

			//현재까지 추가된 가계부 갯수 
		var size = $('[name=w-min_accountViewName]').length;
		
		for(var i = 0; i < size; i++){
			var obj = $("[name=w-min_accountViewName]")[i];
	        
	        // + 버튼 안보여주기
	        // - 버튼 보여주기
	        $(obj).find(".w-accountPlus").css("display","none");
	        $(obj).find(".w-accountRemove").css("display","block");
	        
	        // i == size-1 (현재 추가된 가계부중 마지막의 가계부일때)
	        if (i == size-1){
	        	//+버튼 보여주기
			    $(obj).find(".w-accountPlus").css("display","block");
	        	
	        	//비용,가계부타입, 환율코드 초기화
	        	$(obj).find(".w-min_cost").val("");
	        	$(obj).find(".w-min_accType").val('airfare');
	        	$(obj).find(".w-min_currSymbol").val(1);
	        }
	        
	        //현재 추가된 가계부중 마지막 이면서 총 갯수가 5일때
	        if (size == 5 && i == size-1){
	        	//+ 버튼 안보여주기
		    	 $(obj).find(".w-accountPlus").css("display","none");
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
	var size = $('[name=w-min_accountViewName]').length;
	
	for(var i = 0; i < size; i++){
        var obj = $("[name=w-min_accountViewName]")[i];
        
        //삭제하고 남은 가계부의 개수가 1개일때
        if (size == 1){
        	// - 버튼 안보여주기
        	$(obj).find(".w-accountRemove").css("display","none");
        }
        
        // + 버튼 안보여주기
        $(obj).find(".w-accountPlus").css("display","none");
        
        // 현재 가계부가 마지막 가계부일때
        if (i == size-1){
        	//+버튼 보여주기
		     $(obj).find(".w-accountPlus").css("display","block");
        }
        
        //현재 가계부가 마지막 이면서 총 갯수가 5일때
        if (size == 5 && i == size-1){
        	//+ 버튼 안보여주기
	    	 $(obj).find(".w-accountPlus").css("display","none");
        }
	}
}
	
</script>

</head>
<body>

<!-- Modal -->
<div class="modal fade" id="miniWriteModal" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class='glyphicon glyphicon-map-marker'></span><h4 id="miniWriteTitle">Modal title</h4>
      </div>
      
      <div class="modal-body" id= "miniModalBody">
		<!-- div (팝업으로 띄어줄) 본문 내용 -->	
			<input type='hidden' name='plan_idx' value='${planView.plan_idx}'> <!-- 스토리 -->
			<input type='hidden' name='JSON'> <!-- 스토리 -->
			<input type='hidden' name='ttbJson'> <!-- 해당 타임테이블 -->
			<input type='hidden' name='events'> <!-- 전체 타임테이블 -->
			<table style="width: 100%;">
			<tr>
				<td rowspan="2">
				<img id="miniWriteImg" alt=""/>
				</td>
				<td id="miniWritePlace"><hr></td>
			</tr>	
			<tr>
				<td>
					<div id="miniWriteAddress"></div>
				</td>
			</tr>
			</table>
			<table style="width: 100%;">
				<tr>
					<td colspan="2">
						<div id="miniWriteContent"></div>
					</td>
				</tr>
			</table>
			<div id ="w-miniModalAccount">
					<div id = "w-min_accountView" name = "w-min_accountViewName">
						<table style="width: 100%;">
							<tr>	
								<td>
								<select name = "w-min_accType" class="form-control w-min_accType">
									<option value="airfare">항공료</option>
									<option value="traffic">교통</optoin>
									<option value="stay">숙박</option>
									<option value="admission">입장료</option>
									<option value="food">음식</option>
									<option value="play">오락</option>
									<option value="shop">쇼핑</option>
									<option value="etc">기타</option>
								</select>
								</td>
								<td>
								<select name = "w-min_currSymbol" class="form-control w-min_currSymbol">
									<option value = "1">USD</option>
									<option value = "2">KRW</optoin>
									<option value = "3">JPY</option>
								</select>
								</td>
								<td>
									<input type="text" size="40" name = "w-min_cost" class="form-control w-min_cost" onkeypress="Numberchk()" onkeyup="vComma(this)" style = "text-align:right;"/>
								</td>
								<td>
									<span class="glyphicon glyphicon-plus w-accountPlus" onclick = "miniAppendAccount()"></span>
								</td>
								<td>
									<span class="glyphicon glyphicon-remove w-accountRemove" name = "w-min_removeAcoountName" onclick = "miniRemoveAccount()"></span>
								</td>
							</tr>
						</table>
					</div>
	
			</div> <!-- miniModalAccount end -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        <button type="button" id="btnMiniWriteSave" class="btn btn-primary">저장</button>
      </div>
    </div>
  </div>
</div>

<!-- froala 에디터 적용 -->
<script>
$(function() {
$.FroalaEditor.COMMANDS.imageAlign.options.justify = 'Center';

$('#miniWriteContent').froalaEditor({
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
 heightMin: 205,
    heightMax: 205,
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
</body>
</html>