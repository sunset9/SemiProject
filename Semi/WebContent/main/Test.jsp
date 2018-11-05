<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
html, body, h1, p, a {padding:0;margin:0;line-height:1} 

img {border: 0} 

div.newestContents {
	position:relative;
	width:300px;
	height:200px;
	font-family:’Dotum’,’돋움’,sansserif;
	border: 1px solid #eee} 

div.newestContents a {text-decoration: none} 

h1.nContentsTitle {
	position:absolute;
	z-index:1;
	left:5px;
	bottom:30px} 

h1.nContentsTitle a{
	font-size:18px;
	font-weight:bold;
	color:#fff;} 

h1.nContentsTitle a:hover, h1.nContentsTitle a:focus {text-decoration:underline;} 

p.nContent a {
	font-size:11px;
	color:#ccc;} 

span.nContentNick {
	position:absolute;
	display:block;
	left:0;bottom:0;
	width:295px;
	height:25px;
	padding:35px 0 0 5px;
	background: rbga(0, 0, 0, .5);}

img {width: 300px; height: 200px;}
</style>
</head>
<body>
<!-- 
<div class="imgTopic">
	 <h1 class="title"><a href="#">큰 제목</a></h1>
	 <p class="content">
	 	<a href="#">
	 		<img src="./image.jpg" alt="" />
	 		<span class="date">2013년02월27일 오후3:00</span>
	 	</a>
	 </p>
</div>
 -->

<div>
	<c:forEach var="nList" items="${newestPlanList}" varStatus="nStatus">
		<div class="newestContents">
			<h1 class="nContentsTitle">${nList.title}</h1>
			<p class="nContent">
				<a>
					<img src="${nList.bannerURL}"/>
					<span class="nContentNick">${nList.nick}</span>
				</a>
			</p>
		</div>
		<c:if test="${nStatus.count%3 eq 0 }"><br></c:if>
	</c:forEach>
</div>

</body>
</html>