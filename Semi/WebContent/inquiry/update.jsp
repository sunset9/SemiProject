<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<jsp:include page ="../layout/headerWithMenu.jsp"/>

<div>
<h3>게시글 수정</h3>
<hr>

<div>
<form action="/inquiry/update" mehtod="post" enctype="multipart/form-data">
<input type="hidden" name = "inq_idx" value= "${inquiry.inq_idx }"/>
<table> 
<tr><td class ="info">아이디</td><td>${userid }</td></tr>
<tr><td class ="info">닉네임</td><td>${nickname }</td></tr>
<tr><td class ="info">제목</td><td><input type="text"name ="title" style="width:100%" value="${iquuiry.title }"/></td></tr>
<tr><td class="info" colspan="2">본문</td></tr>
<tr><td colspan="2"><textarea id="content" name ="content"></textarea></td></tr>

</table>

<c:if test="${inqFile.origin_name ne null}">
<div id="fileSection"> 기존 첨부파일 : ${inqFile.origin_name }<button id ="fileDelete" type="button">삭제</button><br>
</c:if>
<c:if test="${inqFile.origin_name eq null}">
<label>첨부파일 : <input type="file" name="file" /></label>
</c:if>

</div>

</form>
</div>

<div class="text-center">	
	<button type="button" id="btnUpdate" >수정</button>
	<button type="button" id="btnCancel" >취소</button>
</div>
</div>

</body>
</html>