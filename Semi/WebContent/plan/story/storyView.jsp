<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dto.story.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<style type="text/css">
	
#slidemenu{
	background:#12cf3d;
	position:absolute;
	width:100px;
	top:50px;
	right:10px;
}

div.vertical-line{
	width: 3px; /* Line width */
	background-color: gray; /* Line color */
	height: 100%; /* Override in-line if you want specific height. */
	float: right; /* Causes the line to float to left of content.
	You can instead use position:absolute or display:inline-block
	if this fits better with your design */
}
	
tr,td{
padding: 2px;
}


</style>
<script type="text/javascript">
	
	$(document).ready(function(){
	    var currentPosition = parseInt($("#slidemenu").css("top"));
	    
	    $(window).scroll(function() {
	        var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
	        $("#slidemenu").stop().animate({"top":position+currentPosition+"px"},1000);
	    });
	    
		$("#btnSave").click(function() {
			$("form").submit();
		})
		

		$(".storyPlus").click(function() {
			
			
			var place_name = $(this).data("place");
			
			$(".modalPlaceName").text(place_name);
			
			var ttb_idx = $(this).data("ttbidx");
			
			$(".ttb_idx").val(ttb_idx);	
			
			var plan_idx = $(this).data("planidx");
			$(".plan_idx").val(plan_idx);	
		})
		
		
// 		$(".storySaveBtn").click(function() {
			
// 			var JSON = {
// 				"ttb_idx": //이름으로 넘겨주기
// 				"plan_idx": 
// 				"content_idx":
// 			}
			
// 			$.ajax({
				
// 				type : "POST"
// 				, url : "/story/write"
// 				, data : {"story_Idx":storyIdx}
// 				, success : function (res) {
// 					$("#viewStory").html(res);
// 				}
// 				, error: function (e) {
// 					console.log(e);
// 				}
				
// 			})
			
// // 			$(".ModalForm").submit();
// 		})

	});
	
	
	    
   function plusmover(num) {
   	
   	var id = "plus"+num;
   	
	var plus = document.getElementById(id);
	plus.style.color='orange';
	
	}
	
   
   function plusmdown(num){
   	var id = "plus"+num;
   	
	var plus = document.getElementById(id);
	plus.style.color='blue';
	   
   }
   
	function plusmleave(num) {
		var id = "plus"+num;
		var plus = document.getElementById(id);
		plus.style.color='black';
	}


	function storyDelete(storyIdx) {
		
		$.ajax({
			
			type : "POST"
			, url : "/story/delete"
			, data : {"story_Idx":storyIdx}
			, success : function (res) {
				$("#viewStory").html(res);
			}
			, error: function (e) {
				console.log(e);
			}
			
		})
		
	}
	
	
</script>	



</head>
<body>
	
<input type="hidden" id = "calcDay"/>
<div>
  <div class="col-lg-8">
	<!-- Day Foreach문 -->
		<div id = "Day">
		</div>
			<c:forEach var='day' begin = "1" end="${diffDays}">
			<div id = "DayDiv${day}" ><h1>-Day ${day}</h1></div>
			
			<c:forEach items='${ttbList}' var = 'ttb'>
				<c:if test="${ttb.day eq day }">
			
				<c:if test="${ttb.is_story eq true}">
					<c:forEach items='${storyList}' var='story'>
						<c:if test="${story.ttb_idx eq ttb.ttb_idx}">
						<span>${story.start_time }</span>
				    		<table width="70%" style="border-bottom: 1px solid black; border-right: 1px solid black; border-top: 1px solid black; border-left: 1px solid black" >
				    			<tr>
				     			<td colspan="12">
								  <font size="5"><span class = "glyphicon glyphicon-remove" style="float: right" onclick="storyDelete(${story.story_idx})"></span></font>
								  <div> <h2><span class="glyphicon glyphicon-map-marker"></span>&nbsp;${story.place_name}</h2>
								  <hr>
								  </div>
						 		  <!-- froala align 적용 되게 하려면 content 표시할 div에 fr-view class를 반영 해줘야함 -->
						 		  <div class = "fr-view" width="100%" style="overflow:auto; height:300px">${story.content}</div>
								</td>
				    			</tr>
				    			<tr>
				     			<td colspan="12">
									<hr><font size="2" color="#B9ADAE">[이미지] 오락 | USD 70</font> 
					 		    </td>
								</tr>
								<tr>
					  			<td colspan="12">
						  			<font size="2" color="#B9ADAE">[이미지] 식비 | KRW 8000</font> 
					  			</td>
				    			</tr>
								<tr>
								<td colspan="12"><hr><a href="#"><font size="2" color="#B9ADAE">덧글 1개</font></a></td>
								</tr>
								<tr>
								<td colspan="10">
									<hr>
									<textarea style ="resize: none; overflow:visible;" rows="2" cols="100" placeholder="댓글을 입력하세요"></textarea>
								</td>	
								<td colspan="2" style="padding-bottom:20px;">
								<hr style="padding: 4px;">
								<button type="button" class="btn btn-sm" style="margin-bottom: -7px;">등록</button>
								</td>
								</tr>
							<!-- ajax이용, 댓글 리스트 foreach문 -->
								<tr>
								<td colspan="3" align="center" ><img src="#" class="img-circle" width="50px" height="50px"></td>
								<td colspan="5" rowspan="2"><font size="2">&nbsp;&nbsp;&nbsp;피가 하여도 무엇을 말이다. 풀밭에 착목한는 소금이라 이상의 맺어, 새 같지 때문이다.</font></td>
								<td colspan="3" rowspan="2" style="padding:20px"><font size ="1"> 2018-10-12 AM 09:03 </font></td>
								<td colspan="1" rowspan="2"><span class="glyphicon glyphicon-remove-sign"></span></td>
								</tr>
								<tr>
								<td colspan="3" align="center"><font size="2">닉네임</font></td>
								</tr>
							</table>					
													
						</c:if>			
					</c:forEach>
					</c:if>
					<c:if test="${ttb.is_story eq false}">
						<br>
						<div><h2><span class="glyphicon glyphicon-map-marker"></span>&nbsp;${ttb.place_name}</h2></div>
						<br>
						<font size="10" color="black">
							<span id = "plus${day}" class ="glyphicon glyphicon-plus-sign storyPlus" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-place="${ttb.place_name}" data-ttbidx="${ttb.ttb_idx}" data-planidx="${ttb.plan_idx}"  onmouseover="plusmover(${day})" onmouseleave="plusmleave(${day})" onmousedown="plusmdown(${day})"></span>
<%-- 							<span id = "plus${day}" class ="glyphicon glyphicon-plus-sign" onclick= "show(${ttb.place_name})" onmouseover="plusmover(${day})" onmouseleave="plusmleave(${day})" onmousedown="plusmdown(${day})"></span> --%>
						</font>
					</c:if>
				</c:if>
			</c:forEach>
			</c:forEach>
		</div> <!-- col-lg-8 끝 구간 -->
 <div class="col-lg-4"></div>
	
	
 <!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title"><span class="glyphicon glyphicon-map-marker modalPlaceName" id ="placename" style="font-weight: bold;font-size: 25px"><font size="5">place_name</font></span></h4>
        </div>
        <div class="modal-body">
		 
		  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>
			<form class = "ModalForm" action="/story/write" method="POST">
			
			<!-- ttb_idx 값 숨겨두기 -->
			<input type="hidden" value="" name ="ttb_idx" class = "ttb_idx" />
			<input type="hidden" value="" name ="plan_idx" class = "plan_idx" />
			<div style="height: auto; width:500px; padding: 20px;" id ="StoryWriteDiv">
				<table>
				<tr>
					<td colspan="4">
						<div style="border: 1px solid #B6B7FA; width: auto; height: auto" >
							<textarea id = "edit" name="content"></textarea>
						</div>
					</td>
				</tr>
				<tr>	
					<td>
					<select>
						<option>교통</option>
						<option>식비</optoin>
						<option>오락</option>
					</select>
					</td>
					<td>
						<select>
						<option>USD</option>
						<option>KRW</optoin>
						<option>CAD</option>
						<option>CNY</option>
					</select>
					</td>
					<td>
					<input type="text" size="40"/>
					</td>
					<td>
						<span class="glyphicon glyphicon-plus"></span>
					</td>
					<td>
						<span class="glyphicon glyphicon-remove"></span>
					</td>
				</tr>
				</table>
			</div>
        </form>
        </div> <!-- modal 바디끝 -->
        <div class="modal-footer" style="text-align: center">
          <button class ="storySaveBtn" type="button" class="btn btn-warning">Save</button>
        </div>
        
      </div>
      
    </div>
  </div>
	
				
  <!-- 퀵 메뉴바 -->
  <div id ="slidemenu">
  	<ul style="list-style:none;">
  		<c:forEach var = "day" begin="1" end="${diffDays}" step="1">
  		<c:set var = "name" value="DayDiv${day}"/>
  			<a href="#${name}"><li> Day ${day} </li></a>
  		</c:forEach>
	</ul>
  </div>
 </div>	

<!-- 프로알라관련 --> 

<script>

//write 창에 x표시 추가

    $(function() {
    $.FroalaEditor.COMMANDS.imageAlign.options.justify = 'Center';
    $('#edit').froalaEditor({
        // Set the image upload URL.
        enter: $.FroalaEditor.ENTER_DIV,
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
	    heightMin: 300,
        heightMax: 300,
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

