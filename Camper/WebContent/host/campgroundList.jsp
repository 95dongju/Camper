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
	<script>
		$(function(){
			$("tr").click(function(){
				location.href="${conPath}/campgroundView.do?s_hid=${s_hid}";
			});
		});
	</script>
	<style>
		#div_cgList  {
			width: 1000px;
			padding-top: 100px;
			margin: 0 auto;
			text-align: center;
		}
		#div_cgList p {
			font-size: 1.6em;
			color: grey;
			height: 300px;
			line-height: 300px;
		}
		#div_cgList table {
			width: 600px;	
			margin: 0 auto;	
			border: none;
		}
		#div_cgList table caption {
			font-size: 2em;
			padding: 10px;
			color: #596E37;
		}
		#div_cgList table tr {
			background-color: pink;
			border: none;
		}
		#div_cgList table tr:hover:not(.tr_title) {
			color: grey;
			background-color: white;
			cursor: pointer;
		}
		#div_cgList table tr td {
			padding: 10px;
			border: none;
		}
		#div_cgList .paging {
			margin: 20px;
		}
		#div_cgList .btn {
			width: 300px;
			height: 40px;
			border: 1px solid #596E37;
			background-color: #596E37;
			color: white;
			cursor: pointer;
		}
		#div_cgList .btn:hover {
			border: none;
			background-color: #92B35E;
		}
		#div_cgList .btn:active {
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
	<div id="div_cgList">
		<c:if test="${totCnt == 0 }">
			<p>등록된 캠핑장이 없습니다</p>
		</c:if>
		<c:if test="${totCnt != 0 }">
			<table>
			<caption>캠핑장 목록</caption>
				<tr class="tr_title">
					<td>고유번호</td>
					<td>이름</td>
				</tr>
				<c:forEach items="${cgList }" var="cgList">
				<tr>
					<td>${cgList.s_camp_no } </td> 
					<td>${cgList.s_camp_name }</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
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
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>