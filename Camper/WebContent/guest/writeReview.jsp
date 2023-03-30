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
		#wrap {
			width: 1000px;
			margin: 0 auto;
			padding-top: 100px;
		}
		#wrap #review_frm table {
			width: 1000px;
			margin: 0 auto;
			border: 1px solid gray;
		}
		#wrap #review_frm table caption {
			font-size: 2em;
			padding: 10px;
			color: #596E37;
		}
		#wrap #review_frm table tr td {
			text-align: center;
		}
		#wrap #review_frm table input:not(.btn, .pic) {
			width: 800px;
			height: 35px;
			padding: 5px;
			border: 1px solid gray;
			box-sizing: border-box;
			font-size: 1.2em;
		}
		#wrap #review_frm table textarea{
			font-size: 1.1em;
			width: 800px;
			height: 400px;
			padding: 5px;
			border: 1px solid gray;
			box-sizing: border-box;
		}
		#wrap #review_frm table .btn {
			width: 300px;
			height: 40px;
			border: 1px solid #596E37;
			background-color: #596E37;
			color: white;
			cursor: pointer;
		}
		#wrap #review_frm table .pic {
			display: none;
		}
		#wrap #review_frm table .btn:hover {
			border: none;
			background-color: #92B35E;
		}
		#wrap #review_frm table .btn:active {
			border: none;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="wrap">
		<form action="" method="post" enctype="multipart/form-data" id="review_frm">
			<table>
				<caption>리뷰 작성하기</caption>
				<tr>
					<td>
						<input type="text" name="review_title" placeholder="제목을 입력하세요" required="required">
					</td>
				</tr>
				<tr>
					<td>
						<textarea name="review_content" required="required" placeholder="리뷰 내용을 작성하세요"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<label for="pic1">
						<div id="1" style="width: 100px; height: 100px; background-color: red; float:left; position: relative;"></div>
							<input type="file" class="pic" id="pic1" name="review_pic1">
						</label>
						<label for="pic2">
						<div id="2" style="width: 100px; height: 100px; background-color: blue; float:left; position: relative;"></div>
							<input type="file" class="pic" id="pic2" name="review_pic2">
						</label>
						<label for="pic3">
						<div id="3" style="width: 100px; height: 100px; background-color: green; float:left; position: relative;"></div>
							<input type="file" class="pic" id="pic3" name="review_pic3">
						</label>
						<label for="pic4">
						<div id="4" style="width: 100px; height: 100px; background-color: purple; float:left; position: relative;"></div>
							<input type="file" class="pic" id="pic4" name="review_pic4">
						</label>
						<label for="pic5">
						<div id="5" style="width: 100px; height: 100px; background-color: black; float:left; position: relative;"></div>
							<input type="file" class="pic" id="pic5" name="review_pic5">
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" class="btn" value="등록하기">
						<input type="button" class="btn" value="뒤로가기" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>