<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link href="${conPath }/css/style.css" rel="stylesheet">
	<style>
		#main_content {
			width: 1000px;
			margin:0 auto;
			padding-top: 100px;
			z-index: 10;
			position: relative;
		}
		#main_content #upper_banner {
			margin:10 auto;
			box-sizing: border-box;
			text-align: center;
			border: 1px solid gray;
			height: 500px;
			line-height: 500px;
		}
	</style>
</head>
<body>
	<c:if test="${not empty loginErrorMsg }">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="main_content">
		<div id="upper_banner">
			BANNER
		</div>
		<div id="">
			
		</div>
		<div id="review_list">
			
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>