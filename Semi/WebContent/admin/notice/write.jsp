<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="/layout/headerNoMenu.jsp" />

<script type="text/javascript" src="/resources/smarteditor2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function () {
	$("#btnWrite").click(function () {
		// 스마트 에디터 내용으로 <textarea>적용 
		submitContents($("#btnWrite"));
		
		//submit
		$("form").submit();
		
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});

</script>

<style type="text/css">
#content {
	width: 99%;
}
</style>

<span><h1><a href ="/admin/main"><strong>관리자 페이지</strong></a></h1></span>
<div class="container">

<h2><strong>공지사항 쓰기</strong></h2>
<hr>

<div>
<form action="/admin/notice/write" method="post" enctype="multipart/form-data">
<table class="table table-bordered">
<tr><td class="bg-success">아이디</td><td>${user.id}</td></tr>
<tr><td class ="bg-success">닉네임</td><td>${user.nickname }</td></tr>
<tr><td class ="bg-success">제목</td><td><input type="text"name ="title" style="width:100%"/></td></tr>
<tr><td colspan="2"><textarea id="content" name ="content"></textarea></td></tr>

</table>

<label>첨부파일 : <input type ="file" name ="file"/></label>


</form>
</div>

<div class="text-center">
	<button type ="button" id ="btnWrite" class="btn btn-warning">작성</button>
	<button type ="button" id ="btnCancel"  class="btn btn-danger">취소</button>
</div>

</div>

<script type="text/javascript">
// 스마트 에디터 스킨 적용 
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //<textarea>의 id 입력
	sSkinURI: "/resources/smarteditor2/SmartEditor2Skin.html",
	fCreator: "createSEditor2",
	htParams: {
		bUseToolbar: true, //툴바 사용여부
		bUseVerticalResizer: false, //입력창 크기 조절 바
		bUseModeChanger: false //모드 탭
	}
});

//<form>의 submit에 맞춰 스마트에디터 내용 적용
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}



</script>
</body>
</html>