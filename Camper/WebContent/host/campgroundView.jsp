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
		#wrap{
			width: 1000px;
			padding-top: 100px;
			margin: 0 auto;
		}
	</style>
</head>
<body>
	<c:if test="${empty s_camp_no }">
		alert('잘못된 접근입니다');
		<!-- location.href='${conPath}/main/main.jsp'; -->
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="wrap">
		<div id="div_cg_content">
		<c:if test="${cgView.s_camp_mainpic != 'noimg.jpg' }">
			<div class="main_pic">
				<img src="${conPath }/campgroundUpload/${cgView.s_camp_mainpic}">
			</div>
		</c:if>
			<div class="camp_info">
				<h2>${cgView.s_camp_name }</h2>
				<p>주소: ${cgView.s_camp_addr }</p>
				<p>${cgView.s_camp_desc }</p>
				<div>
					<p>
						<c:if test="${cgView eq 'Y' }">
							<
						</c:if>
					</p>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>