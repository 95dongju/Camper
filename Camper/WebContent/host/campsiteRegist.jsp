<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script>
		$(function(){
			var i = 1;
			$('.add_btn').click(function(){
				i++;
				$('#cgsite_table').append('<tr><td>'+i+'. 사이트 명 <b>* </b><input type="text" name="sitename_'+i+'"> &nbsp; 사이트 금액 <b>* </b><input type="text" name="siteprice_'+i+'"> 원  &nbsp; </td></tr>');
				$('.siteCnt').attr('value', i);
			});
		});
	</script>
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
		#div_cg_rgst form fieldset {
			width: 800px;
			margin: 0 auto;
			border: 1px solid gray;
			background-color: #F0F5EF;
			padding: 20px;
			box-sizing: border-box;
			border-raius: 10px 10px 10px 10px;
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
		#div_cg_rgst .add_btn {
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
	<c:if test="${not empty registErrorMsg }">
		<script>
			alert('${registErrorMsg}');
			history.back();
		</script>
	</c:if>
	<div id="div_cg_rgst">
		<form method="post" action="${conPath }/campsiteRegist.do">
			<h2>캠핑장 등록하기</h2>
			<br>
			<fieldset>
				<input type="button" class="add_btn" value="사이트 추가">
				<input type="text" name="siteCnt" class="siteCnt" value="1">
				<legend>캠핑 사이트 <b>*</b></legend>
				<table id="cgsite_table">
					<tr>
						<td>
						1. 사이트 명 <b>* </b><input type="text" name="sitename_1"> &nbsp; 
						사이트 금액 <b>* </b><input type="text" name="siteprice_1"> 원  &nbsp; 
						</td>
					</tr>
				</table>
			</fieldset>
			<br>
			 <div id="div_btn">
			 	<input type="submit" class="btn" id="site_submit" value="등록하기">
			 	<input type="button" class="btn" value="목록 보기" onclick="location.href='${conPath}/campgroundListView.do'">
			 </div>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>