<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.storyContent{
	box-shadow: 0px 5px 28px -9px grey;  /* h-shadow v-shadow blur spread color*/
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
        <h4 class="modal-title">Modal title</h4>
      </div>
      
      <div class="modal-body">
		<!-- div (팝업으로 띄어줄) 본문 내용 -->	
		<div style="border: 1px solid #9AA3E6; height: auto; overflow: hidden" >
			<table style="width: 100%;">
			<tr>
				<td rowspan="2" style="padding: 10px 15px; width: 60%;">
				<img class="miniImg" width="280" height="150" alt=""/>
				</td>
				<td class="miniTitle" style="font-weight: bold; width: 40%;"><hr></td>
			</tr>	
			<tr>
				<td>
				추가 정보 란
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
				<div class="storyContent" style="overflow-y: scroll; height: 230px; border: 1px solid gray;"></div>
			</td>
			</tr>

			</table>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>