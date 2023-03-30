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
		<div id="wrap">
			<div id="div_cgList">
				<c:forEach items="${searchNameList }" var="list">
					<div class="each_campground">
						<div class="cg_mainpic"><img src="${conPath }/campgroundUpload/${list.s_camp_mainpic}"></div>
						<div class="cg_info">
							<h2>${list.s_camp_name}</h2>
							<p>${list.s_camp_addr }</p>
						</div>
						<div class="btn">
							<button>예약하기</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</c:if>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>