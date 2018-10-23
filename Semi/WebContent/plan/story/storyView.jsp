<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dto.story.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>무제 문서</title>

<script src = "https://code.jquery.com/jquery-2.2.4.min.js"></script>

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.css">
 
  <!-- Include Editor style. -->

<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1/css/froala_style.min.css" rel="stylesheet" type="text/css" />

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src='/resources/timetable/moment.min.js'></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/mode/xml/xml.min.js"></script>


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
		
		
		$('#myModal').on('show.bs.modal', function (e){
			 
			console.log("dd");

		  })

	});
	
	function show(place_name){
		
		
		var pname = place_name;
		
		console.log(pname);
		
		
		
		$("#myModal").modal('show');
	}
	    
	    
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

	
</script>	



</head>
<body>
	
<input type="hidden" id = "calcDay"/>
<div>
  <div class="col-lg-2">
   <div class ="vertical-line"></div>
  </div>
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
								  <div> <h2><span class="glyphicon glyphicon-map-marker"></span>&nbsp;${story.place_name}</h2><hr></div>
						 		  <div width="100%" style="overflow:auto; height:300px">${story.content}</div>
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
					<c:if test="${ttb.is_story eq false }">
						<br>
						<div><h2><span class="glyphicon glyphicon-map-marker"></span>&nbsp;${ttb.place_name}</h2></div>
						<br>
						<font size="10" color="black">
<%-- 							<span id = "plus${day}" class ="glyphicon glyphicon-plus-sign" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-place="${ttb.place_name}" onmouseover="plusmover(${day})" onmouseleave="plusmleave(${day})" onmousedown="plusmdown(${day})"></span> --%>
							<span id = "plus${day}" class ="glyphicon glyphicon-plus-sign" onclick= "show(${ttb.place_name})" onmouseover="plusmover(${day})" onmouseleave="plusmleave(${day})" onmousedown="plusmdown(${day})"></span>
						</font>
					</c:if>
				</c:if>
			</c:forEach>
			</c:forEach>
		</div> <!-- col-lg-8 끝 구간 -->
 <div class="col-lg-2"></div>
	
	
 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title"><span class="glyphicon glyphicon-map-marker" id ="placename" style="font-weight: bold;font-size: 25px"><font size="5">place_name</font></span></h4>
        </div>
        <div class="modal-body">
        
         <!-- Include external JS libs. -->

		 
		  <!-- Include Editor JS files. -->
<!-- 		  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script> -->
			<div style="height: auto; width:500px; padding: 20px;" id ="StoryWriteDiv">
			
			<form action="/story/write" method="POST">
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
			</form>
			</div>
        
        </div> <!-- modal 바디끝 -->
        <div class="modal-footer" style="text-align: center">
          <button type="button" class="btn btn-warning">Save</button>
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
      $('#edit').froalaEditor({
        // Set the image upload URL.
        toolbarButtons: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
        toolbarButtonsXS: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
        toolbarButtonsSM: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
        toolbarButtonsMD: ['fontFamily','bold', 'italic', 'underline','align','|','insertLink','insertImage','|', 'undo', 'redo'],
        imageEditButtons: ['imageDisplay', 'imageAlign', 'imageInfo', 'imageRemove'],
        imageUploadURL: '/upload_image',
        imageUploadParams: {
          id: 'my_editor'
        },
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
                url: "/image_delete",
       
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
