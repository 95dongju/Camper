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
		#div_cg_rgst form table {
			width: 500px;
			margin: 0 auto;
		}
		#div_cg_rgst form table caption {
			font-size: 2em;
			padding: 10px;
			color: #596E37;
		}
		#div_cg_rgst form table tr td {
			padding: 5px;
		}
		#div_cg_rgst form table tr td input:not(.btn){
			width: 300px;
			height: 35px;
			padding: 4px;
			border: 1px solid gray;
			box-sizing: border-box;
		}
		#div_cg_rgst form table tr td .btn{
			width: 300px;
			height: 40px;
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
			alert('호스트 로그인 후 사용 가능한 메뉴입니다');
			location.href='${conPath}/loginView.do';
		</script>
	</c:if>
	<div id="div_cg_rgst">
		<form action="cg_rgst_form" method="post" enctype="multipart/form-data">
			<table>
				<caption>캠핑장 등록하기</caption>
				<tr>
					<th>캠핑장 이름 *</th>
					<td><input type="text" name="cgname" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 설명 *</th>
					<td><input type="text" name="cgdesc" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 주소 *</th>
					<td><input type="text" name="cgaddr" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 메인 사진 *</th>
					<td><input type="file" name="cg_mainpic" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 지도 사진 *</th>
					<td><input type="file" name="cg_mappic" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 사진 1 *</th>
					<td><input type="file" name="cg_mainpic" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 사진 2 *</th>
					<td><input type="file" name="cg_mainpic" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 사진 3 *</th>
					<td><input type="file" name="cg_mainpic" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 사진 4 *</th>
					<td><input type="file" name="cg_mainpic" required="required"></td>
				</tr>
				<tr>
					<th>캠핑장 사진 5 *</th>
					<td><input type="file" name="cg_mainpic" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" class="btn" value="등록하기">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" class="btn" value="뒤로가기" onclick="location.href='history.back()'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>