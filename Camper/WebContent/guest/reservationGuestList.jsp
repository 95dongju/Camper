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
	<link href="${conPath }/css/div_list.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${empty guest}">
		<script>
			alert('게스트 로그인 후 이용 가능합니다.');
			location.href='${conPath}/guest/loginGuest.jsp';
		</script>
	</c:if>
	<div id="div_List">
		<nav id="nav_rez">
			<ul>
				<li><a href="${conPath }/reservationGuestList.do?s_gid=${guest.s_gid}&gr_status=Y" class="nav_li">예약 완료</a></li>
				<li><a href="${conPath }/reservationCOGuestList.do?s_gid=${guest.s_gid}" class="nav_li">체크아웃</a></li>
				<li><a href="${conPath }/reservationGuestList.do?s_gid=${guest.s_gid}&gr_status=N" class="nav_li">취소된 예약</a></li>
			</ul>
		</nav>
		<div id="table_wrap">
			<c:if test="${totCnt == 0 }">
				<p>예약이 없습니다</p>
			</c:if>
			<c:if test="${totCnt != 0 }">
				<table>
					<tr class="tr_title">
						<td>예약번호</td>
						<td>캠핑장명</td>
						<td>데크번호</td>
						<td>체크인 날짜</td>
						<td>예약한 날짜</td>
						<c:if test="${empty param.gr_status}">
						<td>리뷰 남기기</td>
						</c:if>
					</tr>
					<c:forEach items="${rezList }" var="list">
					<tr class="tr_content">
						<td onclick="location.href='${conPath}/campgroundView.do?s_camp_no=${list.s_camp_no }'">${list.s_rez_no }</td> 
						<td onclick="location.href='${conPath}/campgroundView.do?s_camp_no=${list.s_camp_no }'">${list.s_camp_name }</td>
						<td onclick="location.href='${conPath}/campgroundView.do?s_camp_no=${list.s_camp_no }'">${list.s_site_no }</td>
						<td onclick="location.href='${conPath}/campgroundView.do?s_camp_no=${list.s_camp_no }'">${list.d_select }</td>
						<td onclick="location.href='${conPath}/campgroundView.do?s_camp_no=${list.s_camp_no }'">${list.d_rez_date }</td>
						<c:if test="${empty param.gr_status}">
						<td><button onclick="location.href='${conPath}/writeReviewView.do?s_camp_no=${list.s_camp_no }'">작성</button></td>
						</c:if>
					</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				<a href="${conPath }/campgroundGuestListView.do?pageNum=${startPage-1 }&s_gid=${param.s_gid}&gr_status=${param.gr_status}"> ◀ </a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> ${i }</b>
				</c:if>
				<c:if test="${i != pageNum }">
					<a href="${conPath }/reservationGuestList.do?pageNum=${i}&s_gid=${param.s_gid}&gr_status=${param.gr_status}"> ${i }</a>
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt }">
				<a href="${conPath }/reservationGuestList.do?pageNum=${endPage + 1 }&s_gid=${param.s_gid}&gr_status=${param.gr_status}"> ▶ </a>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>