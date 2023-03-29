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
	<c:if test="${not empty error }">
		<script>alert('${error}');</script>
	</c:if>
	<c:if test="${empty error }">
		<c:forEach items=${searchNameList } var="list">
			<div>
				${list. }
			</div>
		</c:forEach>
	</c:if>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>