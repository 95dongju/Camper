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
	<c:if test="${empty host}">
		<script>
			alert('로그인 후 이용 가능합니다.');
			location.href='${conPath}/member/login.jsp';
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>