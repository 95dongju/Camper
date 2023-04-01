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
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
		<div id="wrap">
			<div id="div_list_wrap">
				<nav id="nav_rez">
					<ul>
						<li>수락 대기</li>
						<li>예약 완료</li>
						<li>체크아웃</li>
					</ul>
				</nav>
				<div id="">
					
				</div>
			</div>
		</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>