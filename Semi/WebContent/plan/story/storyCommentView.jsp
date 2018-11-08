<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>

/*커멘드 삭제*/
.commentRemoveBtn{
	cursor:pointer;
}

</style>
<c:forEach items="${commentList}" var = 'comment'>
  <c:if test="${comment.story_idx eq story_idx}">
	<table>
		<tr class="storytr">
			<td class="storytd" colspan="1" align="center" ><img src="${comment.profile}" class="img-circle" width="50px" height="50px"></td>
			<td class="storytd" colspan="2" rowspan="2"><font size="2">&nbsp;&nbsp;&nbsp;${comment.content}</font></td>
			<td class="storytd" colspan="1" rowspan="2" style="padding:20px"><font size ="1"> ${comment.create_date} </font></td>
			<td class="storytd" colspan="1" rowspan="2">
			<c:if test="${user.user_idx eq comment.user_idx || user.grade eq '관리자'}">
				<span class="glyphicon glyphicon-remove-sign commentRemoveBtn" onmousedown="st_mdown($(this))" onmouseleave="st_mleave($(this))" onmouseover="st_mover($(this))" onclick="removeComm(${comment.comm_idx},${comment.story_idx},${comment.plan_idx})"></span>
			</c:if>
			</td>
		</tr>
		<tr class="storytr">
			<td class="storytd" colspan="1" align="center"><font size="2">${comment.nickname}</font></td>
		</tr>
	</table>
	</c:if>
</c:forEach>
