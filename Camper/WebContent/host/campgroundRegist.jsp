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
		#div_cg_rgst {
			width: 1000px;
			padding-top: 100px;
			margin: 0 auto;
		}
		#div_cg_rgst form {
			min-width: 1000px;
			text-align: center;
		}
		#div_cg_rgst form h2 {
			font-size: 2em;
			padding: 10px;
			color: #596E37;
		}
		#div_cg_rgst form fieldset {
			width: 800px;
			margin: 0 auto;
			border: 1px solid gray;
			background-color: #F0F5EF;
			padding: 20px;
			box-sizing: border-box;
			border-raius: 10px 10px 10px 10px;
		}
		#div_cg_rgst form fieldset p {
			font-size: 0.9em;
			color: gray;
		}
		#div_cg_rgst form fieldset table {
			margin: 0 auto;
		}
		#div_cg_rgst form fieldset table tr td {
			padding: 5px;
		}
		#div_cg_rgst form fieldset table tr td input:not(.btn){
			width: 300px;
			height: 35px;
			padding: 4px;
			border: 1px solid gray;
			box-sizing: border-box;
		}
		#div_cg_rgst form #div_btn .btn{
			width: 300px;
			margin: 10px;
			height: 40px;
			border: 1px solid #596E37;
			background-color: #596E37;
			color: white;
			cursor: pointer;
		}
		#div_cg_rgst b {
			color: red;
			font-weight: normal;
		}
		#div_cg_rgst form #div_btn .btn:hover{
			border: none;
			background-color: #92B35E;
		}
		#div_cg_rgst form #div_btn .btn:active{
			border: none;
		}
		#div_cg_rgst #cgsite_table input {
			width: 150px;
			height: 30px;
			border: 1px solid gray;
			box-sizing: border-box;
		}
		#div_cg_rgst fieldset .add_btn {
			width: 80px;
			height: 30px;
			border: 1px solid #596E37;
			background-color: #596E37;
			color: white;
			cursor: pointer;
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
	<div id="div_cg_rgst">
		<form action="${conPath }/campgroundRegist.do" method="post" enctype="multipart/form-data">
			<h2>캠핑장 등록하기</h2>
			<br>
			<fieldset>
				<legend>캠핑장 기본 정보 <b>*</b></legend>
				<table>
					<tr>
						<td>캠핑장 이름 <b>*</b></td>
						<td><input type="text" name="cgname" required="required" placeholder="캠핑장 이름을 입력해 주세요"></td>
					</tr>
					<tr>
						<td>캠핑장 설명 <b>*</b></td>
						<td><input type="text" name="cgdesc" required="required" placeholder="캠핑장에 대한 간단한 소개글을 입력해 주세요"></td>
					</tr>
					<tr>
						<td>캠핑장 주소 <b>*</b></td>
						<td><input type="text" name="cgaddr" required="required" placeholder="캠핑장 주소를 입력해 주세요"></td>
					</tr>
				</table>
			</fieldset>
			<br>
			<fieldset>
				<legend>캠핑장 시설 <b>*</b></legend>
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
				<legend>캠핑장 사진 <b>*</b></legend>
				<p>※ 별표(*) 표시된 곳에는 반드시 캠핑장 사진을 첨부해 주세요</p>
				 <table>
				 	<tr>
						<td>캠핑장 메인 사진 <b>*</b></td>
						<td><input type="file" name="cg_mainpic" required="required"></td>
					</tr>
					<tr>
						<td>캠핑장 지도 사진 <b>*</b></td>
						<td><input type="file" name="cg_mappic" required="required"></td>
					</tr>
					<tr>
						<td>캠핑장 사진 1 <b>*</b></td>
						<td><input type="file" name="cg_pic1" required="required"></td>
					</tr>
					<tr>
						<td>캠핑장 사진 2 <b>*</b></td>
						<td><input type="file" name="cg_pic2" required="required"></td>
					</tr>
					<tr>
						<td>캠핑장 사진 3</td>
						<td><input type="file" name="cg_pic3"></td>
					</tr>
					<tr>
						<td>캠핑장 사진 4</td>
						<td><input type="file" name="cg_pic4"></td>
					</tr>
					<tr>
						<td>캠핑장 사진 5</td>
						<td><input type="file" name="cg_pic5"></td>
					</tr>
				 </table>
			</fieldset>
			<br>
			 <div id="div_btn">
			 	<input type="submit" class="btn" value="다음 ">
			 	<input type="button" class="btn" value="목록 보기" onclick="location.href='${conPath}/campgroundListView.do'">
			 </div>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>