<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Camper</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<style>
		#div_join_host {
			width: 1000px;
			margin: 0 auto;
			padding-top: 100px;
			text-align: center;
		}
		#div_join_host img {
			width: 1000px;
		}
		#div_join_host #div_join_host_btn #join_host {
			width: 500px;
			height: 60px;
			top: 900px;
			left: 500px;
			border: 1px solid #17183C;
			background-color: #17183C;
			border-radius: 10px 10px 10px 10px;
			color: white;
			font-size: 1.6em;
			cursor: pointer;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div id="div_join_host">
		<img src="${conPath }/img/camper_host_join_page.png">
		<div id="div_join_host_btn">
			<button id="join_host" onclick="location.href='${conPath}/hostJoinView.do'">호스트 회원가입</button>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>