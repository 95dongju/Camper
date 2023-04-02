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
	<c:if test="${empty guest}">
		<script>
			alert('로그인 후 이용 가능합니다.');
			location.href='${conPath}/guest/loginGuest.jsp';
		</script>
	</c:if>
	<div id="div_mypage">
		<h2>마이페이지</h2>
		<div id="div_myinfo">
			<div class="member_info">
				<p id="member_photo"><img src="${conPath }/mPicUpload/${guest.s_gphoto}"></p>
				<br>
				<p><b>${guest.s_gnick }</b> 님, 안녕하세요!</p>
				<br>
				<p id="modify_myinfo"> <a href="${conPath}/modifyGuestView.do"> 내 정보 수정 > </a></p>
			</div>
			<div class="myinfo_lnb">
				<ul>
					<li><a href="${conPath }/reservationGuestList.do?s_gid=${guest.s_gid}&gr_status=Y"><img src="${conPath }/img/camper_booking_icon.png"><br>예약 내역</a></li>
					<li><a href="${conPath }/reviewListforGuest.do?s_gid=${guest.s_gid}"><img src="${conPath }/img/camper_review_icon.png"><br>나의 후기</a></li>
					<li><a href=""><img src="${conPath }/img/camper_like_icon.png"><br>찜한 캠핑장</a></li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>