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
  			if (oldPw != '${guest.s_gpw}' && mpwNew != ''){
  				alert('비밀번호를 확인하세요');
  				return false;
  			}else if (oldPw != '${guest.s_gpw}' && mpwChkResult != '비밀번호가 확인되었습니다'){
  				alert('변경할 비밀번호를 확인하세요');
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
			location.href='${conPath}/guest/loginGuest.jsp';
		</script>
	</c:if>
	<c:if test="${not empty modifyResult }">
		<script>
			alert('${modifyResult}');
		</script>
	</c:if>
	<c:if test="${not empty modifyErrorMsg }">
		<script>
			alert('${modifyErrorMsg}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"></jsp:include>
	<div id="wrap">
		<div id="div_modify_frm">
			<form action="${conPath }/modifyGuest.do" method="post" id="modify_form" enctype="multipart/form-data">
				<input type="hidden" name="dbPw" value="${guest.s_gpw }">
				<input type="text" name="dbPic" value="${guest.s_gphoto }">
				<table>
					<caption>정보 수정</caption>
					<tr>
						<td colspan="2">
							<div class="photo">
								<img src="${conPath }/mPicUpload/${guest.s_gphoto}">
								<div class="modify_pic">
									<label for="file-input">
										<img src="${conPath }/img/camper_camera_icon.png">
									</label>
									<input type="file" name="s_gphoto" id="file-input" style="display:none;">
								</div>							
							</div>
						</td>
					</tr>
					<tr>
						<td>
							아이디
						</td>
						<td>
							<input type="text" name="mid" value="${guest.s_gid }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							이름
						</td>
						<td>
							<input type="text" name="mname" value="${guest.s_gname }" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							닉네임
						</td>
						<td>
							<input type="text" name="mnick" required="required" placeholder="닉네임 " value="${guest.s_gnick }">
						</td>
					</tr>
					<tr>
						<td>
							전화번호
						</td>
						<td>
							<input type="text" name="mtel" required="required" readonly="readonly" value="${guest.s_gtel }">
						</td>
					</tr>
					<tr>
						<td>
							이메일
						</td>
						<td>
							<input type="text" name="memail" required="required" readonly="readonly" value="${guest.s_gemail }">
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
							<span><a href="${conPath }/withdrawGuestView.do">회원 탈퇴</a></span>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"></jsp:include>
</body>
</html>