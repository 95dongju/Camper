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
	<link href="${conPath }/css/mypage.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${empty host}">
		<script>
			alert('로그인 후 이용 가능합니다.');
			location.href='${conPath}/host/loginHost.jsp';
		</script>
	</c:if>
	<div id="div_mypage">
		<h2>마이페이지</h2>
		<div id="div_myinfo">
			<div class="member_info">
				<p id="member_photo"><img src="${conPath }/img/${host.s_hpic}"></p>
				<br>
				<p><b>${host.s_hbis_name }</b> 호스트 님, 어서오세요!</p>
				<br>
				<p id="modify_myinfo"> <a href="${conPath}/modifyHostView.do"> 내 정보 수정 > </a></p>
			</div>
			<div class="myinfo_lnb">
				<ul>
					<li><a href=""><img src="${conPath }/img/camper_booking_icon.png"><br>예약 현황</a></li>
					<li><a href=""><img src="${conPath }/img/camper_review_icon.png"><br>내 캠핑장 후기</a></li>
					<li><a href="${conPath }/campgroundListView.do?s_hid=${host.s_hid}"><img src="${conPath }/img/camper_campground_icon.png"><br>캠핑장 관리</a></li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>