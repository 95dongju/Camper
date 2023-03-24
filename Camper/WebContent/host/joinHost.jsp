<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Camper 회원가입</title>
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
						url: '${conPath}/hidConfirm.do',
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
						url: '${conPath}/hemailConfirm.do',
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
	  		var patternBisnum = /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/;
			$('#bisnum').keyup(function(){
				var bisnum = $(this).val();
				if(!bisnum){
					$('#bisnumConfirmResult').html(' &nbsp; ');
				}else if (!bisnum.match(patternBisnum)){
					$('#bisnumConfirmResult').html('<b>올바른 사업자등록번호를 입력해 주세요</b>');
				}else {
					$.ajax({
						url: '${conPath}/bisnumConfirm.do',
						type: 'post',
						data: 'bisnum='+bisnum,
						dataType: 'html',
						success: function(data){
							$('#bisnumConfirmResult').html(data);
						},
					});					
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
 	  			var bisnumConfirmResult = $('#bisnumConfirmResult').text().trim();
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
 	  			}else if(bisnumConfirmResult != '사용 가능한 사업자등록번호입니다'){
 	  				alert('사업자등록번호를 확인해 주세요');
 	  				$('#bisnum').focus();
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
</head>
<body>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div id="joinAndModify_table">
		<form action="${conPath }/hostJoin.do" method="post" id="joinAndModify_form" enctype="multipart/form-data">
			<table>
				<caption>회원가입</caption>
				<tr>
					<td>
						<input type="text" name="mname" id="mname" required="required" placeholder="이름 (실명)*" value="${s_hname }">
						<div id="mnameConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="mtel" id="mtel" required="required" placeholder="휴대폰번호 (- 포함하여 입력) *" value="${s_htel }">
						<div id="mtelConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="mid" id="mid" required="required" placeholder="아이디 *" value="${s_hid }">
						<div id="midConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="memail" id="memail" required="required" placeholder="이메일 *" value="${s_hemail }">
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
						<input type="text" name="bisname" id="bisname" required="required" placeholder="사업자명 *">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="bisnum" id="bisnum" required="required" placeholder="사업자등록번호 (- 포함하여 입력) *">
						<div id="bisnumConfirmResult"> &nbsp; </div>
					</td>
				</tr>
				<tr>
					<td>
						<div id="div_bispic">사업자등록증 *</div>
						<input type="file" name="bispic" id="bispic" required="required">
						<div id="div_bispic_desc"> ※ 5MB 이하의 파일만 첨부 가능합니다. </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="haddr" id="haddr" required="required" placeholder="사업장 주소 *">
					</td>
				</tr>
				<tr>
					<td>
						<select name="hacc_bankname" id="hacc_bankname" required="required">
							<option selected disabled>은행 선택 *</option>
							<option>KEB하나은행</option>
							<option>SC제일은행</option>
							<option>국민은행</option>
							<option>기업은행</option>
							<option>신한은행</option>
							<option>외환은행</option>
							<option>우리은행</option>
							<option>시티뱅크</option>
							<option>경남은행</option>
							<option>광주은행</option>
							<option>카카오뱅크</option>
							<option>부산은행</option>
							<option>수협</option>
							<option>대구은행</option>
							<option>토스뱅크</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="haccount" id="haccount" required="required" placeholder="계좌번호 *">
					</td>
				</tr>
				<tr>
					<td>
						<div id="div_accpic"> 통장 사본 *</div>
						<input type="file" name="accpic" id="accpic" required="required">
						<div id="div_accpic_desc"> ※ 5MB 이하의 파일만 첨부 가능합니다. </div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" class="btn" value="호스트로 회원가입">
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" class="btn" value="게스트로 회원가입" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>