<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="../layout/headerWithMenu.jsp" />

<script type="text/javascript" src="/resources/smarteditor2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#btnUpdate").click(function() {
		//스마트 에디터 내용으로 <textarea> 적용 
		submitContents($("#btnUpdate"));
		
		//submit
		$("form").submit();
		
	});
	
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	$("#fileDelete").click(function() {
		$("#fileSection").html('<label>새 첨부파일 : <input type="file" name="file" /></label>');
		
		
	});
	
});

</script>

<style type="text/css">
#content {
	width: 98%;
}
.winfo {
	background:rgba(79	,185,159,0.5) ;
	text-align: center;

}
#btnUpdate{
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
#fileDelete{
  	width:50px;
    background-color: #ED553B;
    border: none;
    color:#fff;
	padding: 3px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 10px;
    margin: 3px;
    border-radius:5px;
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

<h2><strong>게시글 수정</strong></h2>
<hr>

<div>
<form action="/inquiry/update"  method="post" enctype="multipart/form-data">
<input type="hidden" name = "inq_idx" value= "${inquiry.inq_idx }"/>
<div class="inqContent">
<table > 
<tr style="border-top-style: hidden;"><td class ="winfo" style="border-top-left-radius:10px;">아이디</td><td>${userid }</td></tr>
<tr><td class ="winfo">닉네임</td><td>${writerNick }</td></tr>
<tr><td class ="winfo">제목</td><td><input type="text"  name ="title" style="width:100%" value="${inquiry.title }"/></td></tr>
<tr><td class="winfo"  style="border-bottom-left-radius:10px;">본문</td><td><textarea id="content" name ="content">${inquiry.content}</textarea></td></tr>

</table>
</div>
<c:if test="${inqFile.origin_name ne null}">
<div id="fileSection"> 기존 첨부파일 : ${inqFile.origin_name }<button id ="fileDelete" type="button">삭제</button></div><br>
</c:if>

<c:if test="${inqFile.origin_name eq null}">
<label>첨부파일 : <input type="file" name="file" /></label>
</c:if>

</form>
</div>


<div class="text-center">	
	<button type="button" id="btnUpdate" class="btn btn-warning">수정</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div><br>
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