<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>무제 문서</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<script src = "https://code.jquery.com/jquery-2.2.4.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">

div.vertical-line{
	width: 3px; /* Line width */
	background-color: gray; /* Line color */
	height: 100%; /* Override in-line if you want specific height. */
	float: right; /* Causes the line to float to left of content.
	You can instead use position:absolute or display:inline-block
	if this fits better with your design */
	}
	
#slidemenu{
	background:#12cf3d;
	position:absolute;
	width:100px;
	top:50px;
	right:10px;
}

</style>
<script type="text/javascript">
	
$(document).ready(function(){
    var currentPosition = parseInt($("#slidemenu").css("top"));
    
    $(window).scroll(function() {
        var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
        $("#slidemenu").stop().animate({"top":position+currentPosition+"px"},1000);
    });
});

	
</script>	
</head>
<body>
	
<div>
  <div class="col-lg-2">
   <div class ="vertical-line"></div>
  </div>
  <div class="col-lg-8">
	<!-- Day Foreach문 -->
    <p><h1> - Day 1 </h1></p><br>
	<!-- 포스트 Foreach문(장소)-->
	<span>AM 7:00</span>
    <table width="70%" style="border-bottom: 1px solid black; border-right: 1px solid black; border-top: 1px solid black; border-left: 1px solid black" >
	<div>
    <tr>
      <td colspan="12">
		  <div>
			  <h2>장소이름</h2>
		  <hr> 
		  </div>
		  <div width="100%" style="overflow:auto; height:300px">
			  그들의 인간은 뜨거운지라, 작고 군영과 피부가 이상의 끓는 피다. 것은 남는 피부가 우리는 생생하며, 이것을 충분히 가장 때에, 것이다. 영원히 인생을 이것이야말로 발휘하기 그들의 새가 이상은 것이다. 그들의 그들의 인도하겠다는 청춘에서만 바로 충분히 청춘의 듣는다. 그들은 있는 현저하게 위하여, 찾아 있음으로써 그러므로 지혜는 청춘에서만 듣는다. 얼음에 기쁘며, 보내는 위하여서 얼마나 쓸쓸한 교향악이다. 거선의 같은 구하지 사막이다. 위하여 찾아다녀도, 얼마나 있는 곧 관현악이며, 든 얼음에 약동하다. 보배를 이성은 같은 새 때문이다. 밝은 무한한 끓는 뜨고, 같으며, 있는가? 방지하는 기쁘며, 싸인 피가 인생의 부패뿐이다.

			그와 반짝이는 새가 석가는 약동하다. 심장은 맺어, 이상 가는 쓸쓸하랴? 별과 두손을 뭇 천지는 가는 발휘하기 동산에는 찾아다녀도, 이것이다. 부패를 이상의 넣는 것이 방지하는 철환하였는가? 불어 봄바람을 밥을 따뜻한 인생의 부패뿐이다. 반짝이는 관현악이며, 그것은 넣는 안고, 그들에게 청춘을 가진 이상 듣는다. 우리의 살 오아이스도 과실이 눈에 찾아다녀도, 미인을 위하여 것이다. 위하여, 별과 사랑의 풀밭에 우리는 현저하게 철환하였는가? 이 청춘의 것이 것은 우리 피부가 피는 이것이다.

			수 얼마나 방황하여도, 봄바람이다. 오아이스도 별과 피고, 것이다. 밝은 못할 가는 것이다. 어디 대고, 그들의 때문이다. 영원히 뜨고, 불어 이상, 쓸쓸하랴? 그들은 풀이 이 것이다.보라, 바로 귀는 석가는 말이다. 영락과 이상의 이상 아니더면, 그들은 있다. 힘차게 피어나기 오직 청춘의 같은 불러 보배를 없는 것이다. 하는 밝은 천하를 아름답고 길을 사는가 든 속잎나고, 철환하였는가?
		  </div>
	</td>
    </tr>
	<!-- 가계부 foreach문 이용 -->
    <tr>
      <td colspan="12">
		<hr>
		<font size="2" color="#B9ADAE">[이미지] 오락 | USD 70</font> 
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
	</div>
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
<!-- 		<div id ="CommentList"> -->
			<!-- ajax이용, 댓글 리스트 foreach문 -->
		<tr>
			<td colspan="3" align="center" ><img src="/Koala.jpg" class="img-circle" width="50px" height="50px"></td>
			<td colspan="5" rowspan="2"><font size="2">&nbsp;&nbsp;&nbsp;피가 하여도 무엇을 말이다. 풀밭에 착목한는 소금이라 이상의 맺어, 새 같지 때문이다.</font></td>
			<td colspan="3" rowspan="2" style="padding:20px"><font size ="1"> 2018-10-12 AM 09:03 </font></td>
<!-- 			<td rowspan="2"><button type="button" class="btn btn-sm">삭제</button></td> -->
			<td colspan="1" rowspan="2"><span class="glyphicon glyphicon-remove-sign"></span></td>
		</tr>
		<tr>
			<td colspan="3" align="center"><font size="2">닉네임</font></td>
		</tr>
<!-- 		</div> -->
		
</table>		
</div>
  <div class="col-lg-2"></div>
  <div id ="slidemenu">
  	<ul style="list-style:none;">
		<li>Day 1</li>
		<li>Day 2</li>
		<li>Day 3</li>
		<li>Day 4</li>
		<li>Day 5</li>
	</ul>
  </div>
</div>
</body>
</html>
