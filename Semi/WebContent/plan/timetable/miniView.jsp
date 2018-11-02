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

#miniModalBody > tbody tr{
	height: 40px;
}

#miniModalImg{
	width: 280px;
	height: 150px;
}

#miniModalPlace{
	font-weight: bold;
	font-size: large;
	width: 40%;
	
}

#miniModalContent{
	border: 1px solid gray;
	box-shadow: 0px 5px 28px -9px grey;  /* h-shadow v-shadow blur spread color*/
	overflow-y: auto; 
	height: 230px;
	margin-top: 15px;
}

#miniModalAccount{
	margin-top: 15px;
}

</style>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="miniViewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
				<td rowspan="2">
				<img id="miniModalImg" alt=""/>
				</td>
				<td id="miniModalPlace"><hr></td>
			</tr>	
			
			<tr>
				<td>
					<div id="miniModalAddress"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2" >
					<div id="miniModalContent"></div>
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

<script type="text/javascript">

$(document).ready(function(){	
	$('.modal').on('hidden.bs.modal',function(e){
		var obj = document.getElementById("miniModalAccount");
		
		$(obj).html("");
	
		
	})
});


</script>

</body>
</html>