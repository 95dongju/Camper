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
		#div_List {
			width: 1000px;
			margin: 0 auto;
			padding-top: 100px;
		}
		#div_List #nav_rez {
			width: 1000px;
			margin: 0 auto;
			height: 60px;
		}
		#div_List #nav_rez ul li {
			width: 33.3%;
			float: left;
			text-align: center;
			padding: 10px;
			box-sizing: border-box;
		}
		#div_List #nav_rez ul li a {
			box-sizing: border-box;
			display: inline-block;
			width: 100%;
			height: 60px;
			line-height: 60px;
			font-size: 1.2em;
		}
		#div_List #nav_rez ul li a:hover, a:visited {
			background-color: #F8F8F8;
			border-radius: 10px 10px 0 0;
		}
		#div_List #table_wrap {
			margin: 0 auto;
			height: 430px;
			background-color: #F8F8F8;
		}
		#div_List #table_wrap p {
			text-align: center;
			height: 300px;
			line-height: 300px;
			font-size: 1.3em;
		}
		#div_List #table_wrap table {
			width: 950px;
			margin: 0 auto;
			text-align: center;
			padding: 5px;
		}
		#div_List #table_wrap table .tr_title {
			color: #596E37;
			font-weight: bold;
			height: 35px;
		}
		#div_List #table_wrap table tr:not(.tr_title) {
			height: 30px;
			font-size: 0.8em;
		}
		#div_List .paging {
			margin: 0 auto;
			height: 40px;
			line-height: 40px;
			text-align: center;
		}
		#div_List .paging a {
			margin: 10px;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${empty host}">
		<script>
			alert('호스트 로그인 후 이용 가능합니다.');
			location.href='${conPath}/host/loginHost.jsp';
		</script>
	</c:if>
	<c:if test="${not empty rejectResult}">
		<script>
			alert('${rejectResult}');
			location.href='${conPath }/reservationGuestListForHost.do?s_hid=${host.s_hid}&gr_status=N';
		</script>
	</c:if>
	<div id="div_List">
		<nav id="nav_rez">
			<ul>
				<li><a href="${conPath }/reservationGuestListForHost.do?s_hid=${host.s_hid}&gr_status=Y" class="nav_li">예약 완료</a></li>
				<li><a href="${conPath }/reservationCOGuestListForHost.do?s_hid=${host.s_hid}" class="nav_li">체크아웃</a></li>
				<li><a href="${conPath }/reservationGuestListForHost.do?s_hid=${host.s_hid}&gr_status=N" class="nav_li">거절한 예약</a></li>
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
						<td>게스트명</td>
						<td>연락처</td>
						<td>예약 날짜</td>
						<c:if test="${param.gr_status == 'Y'}">
						<td>예약 거절</td>
						</c:if>
					</tr>
					<c:forEach items="${rezList }" var="list">
					<tr>
						<td>${list.s_rez_no }</td> 
						<td>${list.s_camp_name }</td>
						<td>${list.s_site_no }</td>
						<td>${list.s_gname }</td>
						<td>${list.s_gtel }</td>
						<td>${list.d_select }</td>
						<c:if test="${param.gr_status == 'Y'}">
						<td><button onclick="location.href='${conPath}/rejectReservation.do?s_rez_no=${list.s_rez_no }'">거절</button></td>
						</c:if>
					</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				<a href="${conPath }/reservationGuestListForHost.do?pageNum=${startPage-1 }&s_hid=${param.s_hid}&gr_status=${param.gr_status}"> ◀ </a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> ${i }</b>
				</c:if>
				<c:if test="${i != pageNum }">
					<a href="${conPath }/reservationGuestListForHost.do?pageNum=${i}&s_hid=${param.s_hid}&gr_status=${param.gr_status}"> ${i }</a>
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt }">
				<a href="${conPath }/reservationGuestListForHost.do?pageNum=${endPage + 1 }&s_hid=${param.s_hid}&gr_status=${param.gr_status}"> ▶ </a>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>