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
		#mypage {
			width: 1000px;
			margin: 0 auto;
			padding-top: 100px;
			text-align: center;
		}
		#mypage table {
			width: 1000px;
		}
		#mypage table caption {
			font-size: 2em;
			padding: 10px;
			color: #596E37;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${empty guest}">
		<script>
			alert('로그인 후 이용 가능합니다.');
			location.href='${conPath}/member/login.jsp';
		</script>
	</c:if>
	<div id="mypage">
		<table>	
			<caption>마이페이지</caption>
			<tr>
				<td></td>
			</tr>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>