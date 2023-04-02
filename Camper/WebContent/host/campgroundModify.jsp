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
	<link href="${conPath }/css/campgroundRegist.css" rel="stylesheet">
	<style>
		#div_cg_rgst form fieldset table tr td span {
			font-size: 0.7em;
			color: gray;
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
	<c:if test="${not empty modifyErrorMsg}">
		<script>
			alert('${modifyErrorMsg}');
			history.back();
		</script>
	</c:if>
	<div id="div_cg_rgst">
		<form action="${conPath }/campgroundModify.do?s_camp_no=${param.s_camp_no}" method="post" enctype="multipart/form-data">
			<h2>캠핑장 수정하기</h2>
			<br>
			<fieldset>
				<legend>캠핑장 기본 정보 <b>*</b></legend>
				<table>
					<tr>
						<td>캠핑장 이름 </td>
						<td><input type="text" name="cgname" readonly="readonly" value="${cgView.s_camp_name }"></td>
					</tr>
					<tr>
						<td>캠핑장 설명 <b>*</b></td>
						<td><input type="text" name="cgdesc" required="required" value="${cgView.s_camp_desc }" placeholder="캠핑장에 대한 간단한 소개글을 입력해 주세요"></td>
					</tr>
					<tr>
						<td>캠핑장 주소 <b>*</b></td>
						<td><input type="text" name="cgaddr" required="required" value="${cgView.s_camp_addr }" placeholder="캠핑장 주소를 입력해 주세요"></td>
					</tr>
					<tr>
						<td>캠핑장 연락처 <b>*</b></td>
						<td><input type="text" name="cgtel" required="required" value="${cgView.s_camp_tel }" placeholder="캠핑장 연락처를 입력해 주세요"></td>
					</tr>
				</table>
			</fieldset>
			<br>
			<fieldset>
				<legend>캠핑장 시설 수정 <b>*</b></legend>
				<div id="div_fieldset">
					 <label for="bathroomChk"> 개별 화장실 </label><input type="checkbox" name="bathroomChk" id="bathroomChk" value="Y"> &nbsp; &nbsp;
					 <label for="showerboothChk"> 샤워장 </label><input type="checkbox" name="showerboothChk" id="showerboothChk" value="Y"> &nbsp; &nbsp;
					 <label for="storeChk"> 매점 </label><input type="checkbox" name="storeChk" id="storeChk" value="Y"> &nbsp; &nbsp;
					 <label for="sinkChk"> 개수대 </label><input type="checkbox" name="sinkChk" id="sinkChk" value="Y"> &nbsp; &nbsp;
					 <label for="wifiChk"> 와이파이 </label><input type="checkbox" name="wifiChk" id="wifiChk" value="Y"> &nbsp; &nbsp;
					 <label for="playgroundChk"> 놀이터 </label><input type="checkbox" name="playgroundChk" id="playgroundChk" value="Y"> &nbsp; &nbsp;
					 <label for="with_petChk"> 애견 동반 </label><input type="checkbox" name="with_petChk" id="with_petChk" value="Y"> &nbsp; &nbsp;
					 <label for="swim_poolChk"> 수영장 </label><input type="checkbox" name="swim_poolChk" id="swim_poolChk" value="Y"> 
				</div>
			</fieldset>
			<br>
			<fieldset>
				<legend>캠핑장 사진 수정</legend>
				 <table>
				 	<tr>
						<td>캠핑장 메인 사진</td>
						<td>
							<input type="file" name="cg_mainpic">
							<input type="hidden" name="db_mainPic" value="${cgView.s_camp_mainpic }">
							<br>
							<span>등록한 사진명: ${cgView.s_camp_mainpic }</span>
						</td>
					</tr>
					<tr>
						<td>캠핑장 지도 사진</td>
						<td>
							<input type="file" name="cg_mappic">
							<input type="hidden" name="db_mapPic" value="${cgView.s_camp_mappic }">
							<br>
							<span>등록한 사진명: ${cgView.s_camp_mappic }</span>
						</td>
					</tr>
					<tr>
						<td>캠핑장 사진 1</td>
						<td>
							<input type="file" name="cg_pic1">
							<input type="hidden" name="db_cgPic1" value="${cgView.s_camp_pic1 }">
							<br>
							<span>등록한 사진명: ${cgView.s_camp_pic1 }</span>
						</td>
					</tr>
					<tr>
						<td>캠핑장 사진 2</td>
						<td>
							<input type="file" name="cg_pic2">
							<input type="hidden" name="db_cgPic2" value="${cgView.s_camp_pic2 }">
							<br>
							<span>등록한 사진명: ${cgView.s_camp_pic2 }</span>
						</td>
					</tr>
					<tr>
						<td>캠핑장 사진 3</td>
						<td>
							<input type="file" name="cg_pic3">
							<input type="hidden" name="db_cgPic3" value="${cgView.s_camp_pic3 }">
							<br>
							<span>등록한 사진명: ${cgView.s_camp_pic3 }</span>
						</td>
					</tr>
					<tr>
						<td>캠핑장 사진 4</td>
						<td>
							<input type="file" name="cg_pic4">
							<input type="hidden" name="db_cgPic4" value="${cgView.s_camp_pic4 }">
							<br>
							<span>등록한 사진명: ${cgView.s_camp_pic4 }</span>
						</td>
					</tr>
					<tr>
						<td>캠핑장 사진 5</td>
						<td>
							<input type="file" name="cg_pic5">
							<input type="hidden" name="db_cgPic5" value="${cgView.s_camp_pic5 }">
							<br>
							<span>등록한 사진명: ${cgView.s_camp_pic5 }</span>
						</td>
					</tr>
				 </table>
			</fieldset>
			<br>
			 <div id="div_btn">
			 	<input type="submit" class="btn" value="수정하기">
			 	<input type="button" class="btn" value="목록 보기" onclick="location.href='${conPath}/campgroundListView.do'">
			 </div>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>