<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

#miniModalContent{
	border: 1px solid #9AA3E6;
	box-shadow: 0px 5px 28px -9px grey;  /* h-shadow v-shadow blur spread color*/
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
<div class="modal fade " id="miniViewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="miniModalTitle">Modal title</h4>
      </div>
      
		<!-- div (팝업으로 띄어줄) 본문 내용 -->	
      <div class="modal-body" id= "miniModalBody">
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
			<tr>
				<td>
					<div id = "accountList"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding: 15px">
					<div id="miniModalContent" style="overflow-y: scroll; height: 230px; border: 1px solid gray;"></div>
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

<script type="text/javascript">

$(document).ready(function(){	
	$('.modal').on('hidden.bs.modal',function(e){
		var obj = document.getElementById("accountList");
		
		$(obj).html("");
	
		
	})
});


</script>

</body>
</html>