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
	<link href="${conPath }/css/style.css" rel="stylesheet">
	<link href="${conPath }/css/joinAndModify.css" rel="stylesheet">
	<script>
		$(function(){
			var patternMemail = /^[a-zA-Z0-9_\.]+@[a-zA-Z0-9_]+(\.\w+){1,2}$/;
			$('#memail').keyup(function(){
				var memail = $(this).val();
				if(!memail){
					$('#memailConfirmResult').html(' &nbsp; ');
				}else if(!memail.match(patternMemail)){
					$('#memailConfirmResult').html('<b>올바른 이메일을 입력해 주세요</b>');
				}else {
					$.ajax({
						url: '${conPath}/gemailConfirm.do',
						type: 'post',
						data: 'memail='+memail,
						dataType: 'html',
						success: function(data){
							$('#memailConfirmResult').html(data);
						},
					});					
				}
			});
			$('#mpwNew, #mpwNewChk').keyup(function(){
	  			var pw = $('#mpwNew').val();
	  			var pwChk = $('#mpwNewChk').val();
	  			if(pw == pwChk){
	  				$('#mpwChkResult').text('비밀번호가 확인되었습니다');
	  			}else{
	  				$('#mpwChkResult').html('<b>비밀번호를 확인해 주세요</b>');
	  			}
	  		});
			var patternMname = /^[가-힣]{2,8}$/;
			$('#mname').keyup(function(){
	  			var mname = $('#mname').val();
	  			if(mname.match(patternMname)){
	  				$('#mnameConfirmResult').html('확인되었습니다');
	  			}else{
	  				$('#mnameConfirmResult').html('<b>올바른 이름을 입력해 주세요</b>');
	  			}
	  		});
			var patternMtel = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
			$('#mtel').keyup(function(){
	  			var mtel = $('#mtel').val();
	  			if(mtel.match(patternMtel)){
	  				$('#mtelConfirmResult').html('확인되었습니다');
	  			}else{
	  				$('#mtelConfirmResult').html('<b>올바른 휴대폰 번호를 입력해 주세요</b>');
	  			}
	  		});
  		$('form').submit(function(){
  	  			var mpwChkResult = $('#mpwChkResult').text().trim();
  	  			var memailConfirmResult = $('#memailConfirmResult').text().trim();
	  			var mtelConfirmResult = $('#mtelConfirmResult').text().trim();
  	  			if(mpwChkResult != '비밀번호가 확인되었습니다'){
  	  				alert('비밀번호를 확인해 주세요');
  	  				$('#mpw').focus();
  	  				return false;
  	  			}else if(memailConfirmResult != '사용 가능한 이메일입니다'){
  	  				alert('이메일을 확인해 주세요');
  	  				$('#memail').focus();
  	  				return false;
  	  			}else if(mtelConfirmResult != '확인되었습니다'){
 	  				alert('전화번호를 확인해 주세요');
 	  				$('#mtel').focus();
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
	<div id="joinAndModify_table">
		<form action="${conPath }/guestModify.do" method="post" id="joinAndModify_form">
			<table>
				<caption>회원가입</caption>
				<tr>
					<td>
						<input type="text" name="mid" id="mid" placeholder="아이디 *" value="${guest.s_gid }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="mname" id="mname" value="${guest.s_gname }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="mnick" id="mnick" required="required" placeholder="닉네임 *" value="${guest.s_gnick }">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="mtel" id="mtel" required="required" placeholder="휴대폰번호 (- 포함하여 입력) *" value="${guest.s_gtel }">
						<div id="mtelConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="memail" id="memail" required="required" placeholder="이메일 *" value="${guest.s_gemail }">
						<div id="memailConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="mpw" id="mpw" required="required" placeholder="기존 비밀번호 *">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="mpwNew" id="mpwNew" placeholder="변경 비밀번호">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="mpwNewChk" id="mpwNewChk" placeholder="변경 비밀번호 확인 ">
						<div id="mpwChkResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" class="btn" value="정보 수정하기">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="이전 페이지" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>