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
	<link href="${conPath }/css/login.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty joinResult }">
		<script>
			alert('${joinResult}');
		</script>
	</c:if>
	<c:if test="${not empty joinErrorMsg }">
		<script>
			alert('${joinErrorMsg}');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"></jsp:include>
		<div id="div_login">
			<form action="${conPath }/loginGuest.do" method="post" id="login_form">
				<table>
					<caption>게스트 로그인</caption>
					<tr>
						<td><input type="text" name="mid" required="required" placeholder="아이디" value="${s_gid }"></td>
					</tr>
					<tr>
						<td><input type="password" name="mpw" required="required" placeholder="비밀번호"></td>
					</tr>
					<tr>
						<td>
							<input type="submit" class="btn" id="guest_login" value="게스트로 로그인">
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" class="btn" value="호스트 회원가입" onclick="location.href='${conPath}/hostJoinView.do'">
						</td>
					</tr>
					<tr>
						<td>
							<a href="${conPath}/searchIdPw.do">아이디/비밀번호 찾기</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>