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
	<style>
		#div_List {
			width: 1000px;
			margin: 0 auto;
			padding-top: 100px;
		}
		#div_List #table_wrap {
			margin: 0 auto;
			padding: 10px;
			background-color: #F8F8F8;
			height: 100%;
		}
		#div_List table caption {
			color: #596E37;
			font-weight: bold;
			font-size: 2em;
			margin: 20px 0 20px 0;
		}
		#div_List table tr td img{
			width: 100px;
			height: 50px;
			object-fit: cover;
		}
		#div_List table tr {
			height: 50px;
			over-flow: hidden;
			display: inline-box;
		}
	</style>
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
		<div id="table_wrap">
			<c:if test="${totCnt == 0 }">
				<p>예약이 없습니다</p>
			</c:if>
			<c:if test="${totCnt != 0 }">
				<table>
					<caption>나의 리뷰</caption>
					<tr class="tr_title">
						<td>첨부사진</td>
						<td>캠핑장명</td>
						<td>제목</td>
						<td>작성일</td>
					</tr>
					<c:forEach items="${rvList }" var="list">
					<tr class="tr_content">
						<td onclick="location.href='${conPath}/reviewView.do?i_rev_no=${list.i_rev_no }'"><img src="${conPath }/reviewUpload/${list.s_rev_mainpic}"></td>
						<td onclick="location.href='${conPath}/reviewView.do?i_rev_no=${list.i_rev_no }'">${list.s_camp_name }</td> 
						<td onclick="location.href='${conPath}/reviewView.do?i_rev_no=${list.i_rev_no }'">${list.s_rev_title }</td>
						<td onclick="location.href='${conPath}/reviewView.do?i_rev_no=${list.i_rev_no }'">${list.d_rdate }</td>
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