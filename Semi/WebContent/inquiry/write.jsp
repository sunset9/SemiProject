<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="/layout/headerNoMenu.jsp" />

<script type="text/javascript" src="/resources/smarteditor2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function () {
	$("#btnWrite").click(function () {
		// 유효성 검사
		if($("#title").val() == ""){
			alert("제목을 입력하세요.");
			title.focus(); //포커싱
			return;
		}
		// 에디터 내용 적용하기 
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD",[]);

		if ($("#content").val()=='<p><br></p>'){
			alert("내용을 입력하세요.");
			oEditors.getById["content"].exec("FOCUS"); //포커싱
			return;
		}
		// 스마트 에디터 내용으로 <textarea>적용 
		submitContents($("#btnWrite"));
		
		//submit
		$("#inquiryForm").submit();
		
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

.winfo {
	background:rgba(79	,185,159,0.5) ;
	text-align: center;

}
#btnWrite{
/* 	background-color: #4FB99F; */
  	width:80px;
    background-color: #FFCB37;
    border: none;
    color:#fff;
	padding: 5px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    margin: 4px;
    border-radius:10px;
}
#btnCancel{
/* 	background-color: #4FB99F; */
  	width:80px;
    background-color: #ED553B;
    border: none;
    color:#fff;
	padding: 5px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    margin: 4px;
    border-radius:10px;
}
.inqContent{
  	border: 1px solid #ddd;
	border-radius: 10px;
}

td{
    border-top: 1px solid #ddd;
	padding: 8px;
}

table{
    width: 100%;
    max-width: 100%;

    display: table;
	border-collapse: collapse;
    border-spacing: 0;
}


</style>

<div class="container">

<h2><strong>문의사항 쓰기</strong></h2>
<hr>

<div>
<form action="/inquiry/write" method="post" enctype="multipart/form-data" id="inquiryForm">
<div class="inqContent">
<c:if test="${user.sns_idx == 1 || socialUser.sns_idx == 1}">
	<table >
	<tr style="border-top-style: hidden;"><td class ="winfo" style="border-top-left-radius:10px;">아이디</td><td>${user.id}</td></tr>
	<tr><td class ="winfo">닉네임</td><td>${user.nickname }</td></tr>
	<tr><td class ="winfo">제목</td><td><input type="text"name ="title" style="width:100%"/></td></tr>
	<tr><td class ="winfo" style="border-bottom-left-radius:10px;">본문</td><td><textarea id="content" name ="content"></textarea></td></tr>
	</table>
</c:if>
<c:if test="${socialUser.sns_idx != 1 && user.sns_idx != 1}">
	<table >
	<tr style="border-top-style: hidden;"><td class ="winfo" style="border-top-left-radius:10px;">아이디</td><td>${socialUser.id}</td></tr>
	<tr><td class ="winfo">닉네임</td><td>${socialUser.nickname }</td></tr>
	<tr><td class ="winfo">제목</td><td><input type="text" id="title"name ="title" style="width:100%"/></td></tr>
	<tr><td class ="winfo" style="border-bottom-left-radius:10px;">본문</td><td><textarea id="content" name ="content"></textarea></td></tr>
	</table>
</c:if>
</div>
<label>첨부파일 : <input type ="file" name ="file"/></label>


</form>
</div>

<div class="text-center">
	<button type ="button" id ="btnWrite" >작성</button>
	<button type ="button" id ="btnCancel">취소</button>
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

<jsp:include page="../layout/footer.jsp" />