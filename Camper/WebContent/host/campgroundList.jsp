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
		margin: 0 auto;
		text-align: center;
		height: 100%;
	}
	#div_List #table_wrap caption {
		font-size: 1.5em;
		color: #596E37;
	}
	#div_List .paging {
		margin: 20px auto;
	}
	#div_List .btn {
		width: 300px;
		display: inline-box;
		height: 40px;
		border: 1px solid #596E37;
		background-color: #596E37;
		color: white;
		cursor: pointer;
	}
	#div_List .btn:hover {
		border: none;
		background-color: #92B35E;
	}
	#div_List .btn:active {
		border: none;
	}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${empty host}">
		<script>
			alert('호스트 로그인 후 이용 가능합니다.');
			location.href='${conPath}/host/joinHostPage.jsp';
		</script>
	</c:if>
	<c:if test="${not empty registCgResult }">
		<script>
			alert('${registCgResult}');
		</script>
	</c:if>
	<c:if test="${not empty registErrorMsg }">
		<script>
			alert('${registErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty deleteCGResult }">
		<script>
			alert('${deleteCGResult}');
		</script>
	</c:if>
	<c:if test="${not empty deleteFailCuzRez }">
		<script>
			alert('${deleteFailCuzRez}');
			history.back();
		</script>
	</c:if>
	<div id="div_List">
		<div id="table_wrap">
			<c:if test="${totCnt == 0 }">
				<p>등록된 캠핑장이 없습니다</p>
			</c:if>
			<c:if test="${totCnt != 0 }">
				<table>
				<caption>캠핑장 목록</caption>
					<tr class="tr_title">
						<td>고유번호</td>
						<td>이름</td>
						<td>캠핑장 수정</td>
						<td>캠핑장 삭제</td>
					</tr>
					<c:forEach items="${cgList }" var="cgList">
					<tr class="tr_content">
						<td onclick="location.href='${conPath}/campgroundView.do?s_camp_no=${cgList.s_camp_no }&s_hid=${host.s_hid }'">${cgList.s_camp_no }</td> 
						<td onclick="location.href='${conPath}/campgroundView.do?s_camp_no=${cgList.s_camp_no }&s_hid=${host.s_hid }'">${cgList.s_camp_name }</td>
						<td><button onclick="location.href='${conPath}/campgroundModifyView.do?s_camp_no=${cgList.s_camp_no }'">수정</button></td>
						<td><button onclick="location.href='${conPath}/campgroundDelete.do?s_camp_no=${cgList.s_camp_no }'">삭제</button></td>
					</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				<a href="${conPath }/campgroundListView.do?pageNum=${startPage-1 }"> ◀ </a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> ${i }</b>
				</c:if>
				<c:if test="${i != pageNum }">
					<a href="${conPath }/campgroundListView.do?pageNum=${i}"> ${i }</a>
				</c:if>
			</c:forEach>
			<c:if test="${endPage < pageCnt }">
				<a href="${conPath }/campgroundListView.do?pageNum=${endPage + 1 }"> ▶ </a>
			</c:if>
		</div>
		<button class="btn" onclick="location.href='${conPath}/campgroundRegistView.do'">캠핑장 등록하기</button>
	</div>
</body>
</html>