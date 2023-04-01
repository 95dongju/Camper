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
	<script>
		$(function(){
			$('#mpwNew, #mpwNewChk').keyup(function(){
	  			var pw = $('#mpwNew').val();
	  			var pwChk = $('#mpwNewChk').val();
	  			if(pw == pwChk){
	  				$('#mpwChkResult').text('비밀번호가 확인되었습니다');
	  			}else{
	  				$('#mpwChkResult').html('<b>비밀번호를 확인해 주세요</b>');
	  			}
	  		});
			$('form').submit(function(){
	  			var oldPw = $('#mpw').val();
	  			var mpwNew = $('#mpwNew').val();
	  			var mpwChkResult = $('#mpwChkResult').text().trim();
	  			if (oldPw != '${host.s_hpw}' && mpwNew != ''){
	  				alert('비밀번호를 확인하세요');
	  				return false;
	  			}else if (oldPw != '${host.s_hpw}' && mpwChkResult != '비밀번호가 확인되었습니다'){
	  				alert('변경할 비밀번호를 확인하세요');
	  				return false;
	  			}
  	  	});
	});
	</script>
	<style>
		#wrap #div_modify_frm #modify_form table tr td span {
			font-size: 0.8em;
		}
		#wrap #div_modify_frm #modify_form table tr td select {
			width: 300px;
			height: 35px;
			padding: 4px;
			border: 1px solid gray;
			box-sizing: border-box;
		}
	</style>
</head>
<body>
 	<c:if test="${empty host}">
		<script>
			alert('로그인 후 이용 가능합니다.');
			location.href='${conPath}/host/loginHost.jsp';
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div id="wrap">
		<div id="div_modify_frm">
			<form action="${conPath }/modifyHost.do" method="post" id="modify_form" enctype="multipart/form-data">
				<input type="hidden" name="dbPw" value="${host.s_hpw }">
				<input type="hidden" name="dbPic" value="${host.s_hpic }">
				<input type="hidden" name="dbBisPic" value="${host.s_hbis_pic }">
				<input type="hidden" name="dbAccPic" value="${host.s_hacc_pic }">
				<table>
					<caption>정보 수정</caption>
					<tr>
						<td colspan="2">
							<div class="photo">
								<img src="${conPath }/mPicUpload/${host.s_hpic}">
								<div class="modify_pic">
									<label for="file-input">
										<img src="${conPath }/img/camper_camera_icon.png">
									</label>
									<input type="file" name="s_hpic" id="file-input" style="display:none;">
								</div>							
							</div>
						</td>
					</tr>
					<tr>
						<td>
							아이디
						</td>
						<td>
							<input type="text" name="mid" value="${host.s_hid }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							이름
						</td>
						<td>
							<input type="text" name="mname" value="${host.s_hname }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							전화번호
						</td>
						<td>
							<input type="text" name="mtel" required="required" readonly="readonly" value="${host.s_htel }">
						</td>
					</tr>
					<tr>
						<td>
							이메일
						</td>
						<td>
							<input type="text" name="memail" required="required" readonly="readonly" value="${host.s_hemail }">
						</td>
					</tr>
					<tr>
						<td>
							기존 비밀번호
						</td>
						<td>
							<input type="password" name="mpw" id="mpw" required="required" placeholder="기존 비밀번호 *">
						</td>
					</tr>
					<tr>
						<td>
							변경할 비밀번호
						</td>
						<td>
							<input type="password" name="mpwNew" id="mpwNew" placeholder="변경할 비밀번호">
						</td>
					</tr>
					<tr>
						<td>
							비밀번호 확인
							<div> &nbsp; </div>
						</td>
						<td>
							<input type="password" name="mpwNewChk" id="mpwNewChk" placeholder="비밀번호 확인 ">
							<div id="mpwChkResult"> &nbsp; </div>
						</td>
					</tr>
					<tr>
						<td>
							사업자명
						</td>
						<td>
							<input type="text" name="bisname" placeholder="아이디 *" value="${host.s_hbis_name}">
						</td>
					</tr>
					<tr>
						<td>
							사업자등록번호
						</td>
						<td>
							<input type="text" name="bisnum" value="${host.s_hbis_num }">
						</td>
					</tr>
					<tr>
						<td>
							사업자등록증
							<div> &nbsp; </div>
						</td>
						<td>
							<input type="file" name="bispic" readonly="readonly">
							<br><span>등록한 파일명: ${host.s_hbis_pic }</span>
						</td>
					</tr>
					<tr>
						<td>
							사업장주소
						</td>
						<td>
							<input type="text" name="bisaddr" value="${host.s_haddr }">
						</td>
					</tr>
					<tr>
						<td>
							은행명
							<input type="hidden" name="bankname" value="${host.s_hacc_bankname }">
						</td>
						<td>
							<select name="hacc_bankname">
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
							<br><span>등록한 은행명: ${host.s_hacc_bankname }</span>
						</td>
					</tr>
					<tr>
						<td>
							계좌번호
						</td>
						<td>
							<input type="text" name="haccount" value="${host.s_haccount}">
						</td>
					</tr>
					<tr>
						<td>
							통장 사본
							<div> &nbsp; </div>
						</td>
						<td>
							<input type="file" name="haccpic">
							<br><span> 등록한 파일명: ${host.s_hacc_pic}</span>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" class="btn" value="정보 수정하기">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" class="btn" value="이전 페이지" onclick="history.back()">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<span><a href="${conPath }/withdrawHostView.do">회원 탈퇴</a></span>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>