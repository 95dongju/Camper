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
	<link href="${conPath }/css/modify.css" rel="stylesheet">
	<style>
		#modify_form tr td {
			margin: 40px;
		}
	</style>
	<script>
		$(function(){
  		$('form').submit(function(){
  			var pw = $('#mpw').val();
  			if(pw != '${guest.s_gpw}'){
  				alert('비밀번호를 확인하세요');
  				$('#mpw').focus();
  				return false;
  			}
  	  	});
	});
	</script>
</head>
<body>
 	<c:if test="${empty guest}">
		<script>
			alert('로그인 후 이용 가능합니다.');
			location.href='${conPath}/member/login.jsp';
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div id="wrap">
		<div id="div_modify_frm">
			<form action="${conPath }/withdrawGuest.do" method="post" id="modify_form">
				<table>
					<caption>탈퇴하시겠습니까?</caption>
					<tr>
						<td>
							비밀번호를 입력하세요
						</td>
					</tr>
					<tr>
						<td>
							<input type="password" name="mpw" id="mpw">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" class="btn" value="탈퇴하기">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" class="btn" value="돌아가기" onclick="history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>