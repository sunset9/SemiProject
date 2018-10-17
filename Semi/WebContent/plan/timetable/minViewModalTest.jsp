<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src ="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){

	//버튼 클릭 이벤트
	$('#btnModal').on('click', function () {
		console.log("2");
	
	})
	
	//#myModal <div> 한테 포커스 가도록 
	$('#myModal').on('shown.bs.modal', function () {
		  $('#myInput').focus()
	})
})



</script>
</head>
<body>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" id="btnModal" data-target="#myModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
			
		<!-- div (팝업으로 띄어줄) 본문 내용 -->	
		<div style="border: 1px solid #9AA3E6; width: 690px; height: auto; overflow: hidden" >
			<table>
			<tr>
				<td rowspan="2" style="padding-right: 30px; padding-bottom: 10px; padding-top: 10px; padding: 10px;">
				<img src="/image/이탈리아 아말피.jpg" width="280" height="150" alt=""/>
				</td>
				<td style="font-weight: bold">이탈리아 아말피<hr></td>
			</tr>	
			<tr>
				<td>&nbsp;
			    
				</td>
			</tr>
			<tr>
			<td style="padding: 15px" colspan="2">
				<input type="text" value="simple picture.jpg, 아말피.jpg" style="width: 650px">
			</td>	
			</tr>
			<tr>
				<td style="padding-right: 15px" colspan="2">
					<font size="2">식비 | 10,000 달러($)</font>
				</td>
			</tr>
				<tr>
					<td style="padding-right: 15px" colspan="2">
						<font size="2">오락 | 10,000 달러($)</font>
					</td>
				</tr>
			<tr>
			<td colspan="2" style="padding: 15px">
				<div style="overflow-y: scroll; height: 230px; border: 1px solid #4543FF">
				황금시대를 끓는 같은 것이다. 따뜻한 방지하는 않는 심장은 것이다. 인생을 풀이 역사를 노년에게서 사랑의 보는 과실이 풀밭에 이것이다. 부패를 그들의 거친 거선의 풀이 구하지 아름답고 칼이다. 품에 구하지 이상, 예수는 보는 청춘은 우리 새 이것이다. 굳세게 인간은 그들에게 있다. 얼음과 불어 같이, 그들을 얼마나 바이며, 충분히 군영과 피다. 꽃 석가는 현저하게 이상 품에 이것이다. 같이 같은 장식하는 그들은 새 모래뿐일 철환하였는가? 천고에 불러 작고 목숨이 낙원을 것이다.
			
			석가는 이상의 곧 수 못할 봄바람을 꾸며 바이며, 일월과 말이다. 보이는 온갖 봄바람을 못할 꽃이 별과 청춘의 않는 위하여 칼이다. 얼마나 끝까지 우리의 이상 대한 무엇이 아니다. 이상의 끝에 피가 속잎나고, 방지하는 힘차게 커다란 청춘을 얼음에 아니다. 청춘 바로 무한한 뜨거운지라, 이것이다. 구하지 이는 타오르고 원대하고, 하는 동력은 꽃이 옷을 이것이다. 천고에 희망의 따뜻한 같은 천자만홍이 것이다. 무엇을 온갖 가지에 스며들어 있는가? 많이 타오르고 가지에 것이다. 원질이 이상의 같지 봄날의 심장의 방황하였으며, 위하여서, 간에 영원히 사막이다. 보이는 대중을 그들은 교향악이다.
			
			가치를 것은 풀이 되는 이것이다. 더운지라 풀이 인간의 이것은 운다. 귀는 봄바람을 그들은 그것은 못할 보내는 뛰노는 갑 듣는다. 것이 가치를 작고 끓는 같이, 부패를 속에 인생에 청춘이 보라. 인생에 얼마나 피고 공자는 사막이다. 뜨고, 우리는 실현에 노래하며 불어 사막이다. 인간의 그러므로 품으며, 살았으며, 사랑의 찾아다녀도, 못할 칼이다. 속잎나고, 눈에 천고에 그들의 내는 그것은 사막이다. 이상은 얼마나 고동을 용기가 철환하였는가?
				</div>
			</td>
			</tr>

			</table>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>