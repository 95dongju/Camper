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
			var patternMid = /^[A-za-z0-9]/g;
			$('#mid').keyup(function(){
				var mid = $(this).val();
				if(!mid){
					$('#midConfirmResult').html(' &nbsp; ');
				}else if (!mid.match(patternMid)){
					$('#midConfirmResult').html('<b>영문자 또는 숫자로 이루어진 아이디를 사용해 주세요</b>');
				}else if (mid.length < 5){
					$('#midConfirmResult').html('<b>5글자 이상의 아이디를 사용해 주세요</b>');
				}else if (mid.length > 15){
					$('#midConfirmResult').html('<b>아이디가 너무 길어요</b>');
				}else {
					$.ajax({
						url: '${conPath}/gidConfirm.do',
						type: 'post',
						data: 'mid='+mid,
						dataType: 'html',
						success: function(data){
							$('#midConfirmResult').html(data);
						},
					});					
				}
			});
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
			$('#mpw, #mpwChk').keyup(function(){
	  			var pw = $('#mpw').val();
	  			var pwChk = $('#mpwChk').val();
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
  					var midConfirmResult = $('#midConfirmResult').text().trim();
  	  			var mpwChkResult = $('#mpwChkResult').text().trim();
  	  			var memailConfirmResult = $('#memailConfirmResult').text().trim();
  	  			var mnameConfirmResult = $('#mnameConfirmResult').text().trim();
	  					var mtelConfirmResult = $('#mtelConfirmResult').text().trim();
  	  			if(midConfirmResult != '사용 가능한 아이디입니다'){
  	  				alert('아이디를 확인해 주세요');
  	  				$('#mid').focus();
  	  				return false;
  	  			}else if(mpwChkResult != '비밀번호가 확인되었습니다'){
  	  				alert('비밀번호를 확인해 주세요');
  	  				$('#mpw').focus();
  	  				return false;
  	  			}else if(memailConfirmResult != '사용 가능한 이메일입니다'){
  	  				alert('이메일을 확인해 주세요');
  	  				$('#memail').focus();
  	  				return false;
  	  			}else if(mnameConfirmResult != '확인되었습니다'){
	 	  				alert('이름을 확인해 주세요');
	 	  				$('#mname').focus();
	 	  				return false;
	 	  			}else if(mtelConfirmResult != '확인되었습니다'){
	 	  				alert('전화번호를 확인해 주세요');
	 	  				$('#mtel').focus();
	 	  				return false;
	 	  			}
  	  	});
		});
	</script>
	<script>
		window.onload = function(){
			document.getElementById('guest_join_btn').onclick = function(){
				$('#joinAndModify_form').attr("action", '${conPath}/guestJoin.do').submit();
			}
			document.getElementById('host_join_btn').onclick = function(){
				$('#joinAndModify_form').attr("action", '${conPath}/hostJoinView.do').submit();
			}
		}
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div id="joinAndModify_table">
		<form method="post" id="joinAndModify_form">
			<table>
				<caption>회원가입</caption>
				<tr>
					<td>
						<input type="text" name="mname" id="mname" required="required" placeholder="이름 (실명)*">
						<div id="mnameConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="mtel" id="mtel" required="required" placeholder="휴대폰번호 (- 포함하여 입력) *">
						<div id="mtelConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="mid" id="mid" required="required" placeholder="아이디 *">
						<div id="midConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="memail" id="memail" required="required" placeholder="이메일 *">
						<div id="memailConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="mpw" id="mpw" required="required" placeholder="비밀번호 *">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="mpwChk" id="mpwChk" required="required" placeholder="확인 비밀번호 *">
						<div id="mpwChkResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" id="guest_join_btn" value="게스트로 회원가입">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" id="host_join_btn" value="호스트로 회원가입">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>