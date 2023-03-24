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
<style>
	#div_login {
		padding-top: 100px;
		text-align: center;
	}
	#div_login #login_form{
		width: 1000px;
		margin: 0 auto;
	}
	#div_login #login_form table {
		width: 400px;
		margin: 0 auto;
	}
	#div_login #login_form table caption{
		font-size: 2em;
		padding: 10px;
		color: #596E37;
	}
	#div_login #login_form table tr td {
		padding: 5px;
	}
	#div_login #login_form table tr td input:not(.btn){
		width: 300px;
		height: 35px;
		padding: 4px;
		border: 1px solid gray;
		box-sizing: border-box;
	}
	#div_login #login_form table tr td input:focus{
		outline: 1px solid #596E37;
	}
	#div_login #login_form table .btn {
		width: 300px;
		height: 40px;
		border: 1px solid #596E37;
		background-color: #596E37;
		color: white;
		cursor: pointer;
	}
	#div_login #login_form table .btn:hover {
		border: 1px solid #92B35E;
		background-color: #92B35E;
	}
</style>
<body>
		<jsp:include page="../main/header.jsp"></jsp:include>
		<div id="div_login">
			<form action="${conPath }/login.do" method="post" id="login_form">
				<table>
					<caption>로그인</caption>
					<tr>
						<td><input type="text" id="mid" required="required" placeholder="아이디" value="${mid }"></td>
					</tr>
					<tr>
						<td><input type="password" id="mpw" required="required" placeholder="비밀번호"></td>
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
				</table>
			</form>
		</div>
		<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>