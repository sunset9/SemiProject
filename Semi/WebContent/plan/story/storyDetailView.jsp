<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<span>${story.start_time }</span>
	    <table width="70%" style="border-bottom: 1px solid black; border-right: 1px solid black; border-top: 1px solid black; border-left: 1px solid black" >
		<div>
	    <tr>
	      <td colspan="12">
			  <div>
				  <h2>${story.place_name}</h2>
			  <hr> 
			  </div>
			  <div width="100%" style="overflow:auto; height:300px">
				${story.content}
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
</body>
</html>