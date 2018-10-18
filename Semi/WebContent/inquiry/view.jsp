<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<jsp:include page ="../layout/headerWithMenu.jsp"/>

<script type = "text/javascript">

</script>

<div>

<h3>문의 사항 상세보기</h3>
<hr>

<div>
<table class="table table-bordered">
<tr>
<td class = "info"> 글번호</td><td>${inquiry.inq_idx }</td>
<td class = "info"> 제목 </td> <td colspan="2">${inquiry.title }</td>
</tr>

<tr>
<td class ="info">이메일</td> <td>${writerEmail }</td>
<td class = "info">닉네임</td> <td colspan="2">${writerNick }</td>
</tr>

<tr><td class ="info">본문</td> <td colspan="4">${inquiry.content }</td>

<tr>
<td class ="info">조회수</td><td>${inquiry.hit }</td>

<td class ="info">답변여부</td>

<td>
<c:if test="${inquiry.answer eq 0}">
답변 예정
</c:if>
<c:if test="${inquiry.answer eq 1}">
답변 완료
</c:if>
</td>

</tr>

<tr>
<td class ="info">작성일</td><td colspan="4">${inquiry.create_date }</td>
</tr>
</table>

</div>

</div>

</body>
</html>