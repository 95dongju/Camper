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
			text-align: center;
		}
		#wrap h2 {
			color: #596E37; 
			margin: 20px;
		}
		#wrap #review {
			width: 800px;
			margin: 0 auto;
			background-color: #F8F8F8;
			border-radius: 50px 50px 50px 50px;
			padding: 20px;
		}
		#wrap #review h2, h5, p {
			margin: 10px;
			text-align: left;
		}
		#wrap #review #div_img {
			width: 700px;
			margin: 0 auto;
			text-align: center;
			display: inline-box;
		}
		#wrap #review #div_img img {
			margin: 0 auto;
			text-align: center;
		}
		#wrap span {
			cursor: pointer;
			color: gray;
			font-size: 0.8em;
		}
		#wrap .btn {
			width: 300px;
			height: 40px;
			border: 1px solid #596E37;
			background-color: #596E37;
			color: white;
			cursor: pointer;
			margin: 10px;
		}
		#wrap .btn:hover {
			border: none;
			background-color: #92B35E;
		}
		#wrap .btn:active {
			border: none;
		}
	</style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<c:if test="${empty rvView }">
	<script>
		alert('잘못된 접근입니다');
		history.back();
	</script>
</c:if>
<c:if test="${not empty rvView }">
	<div id="wrap">
<h2>${rvView.s_gnick } 님의 캠핑 리뷰</h2>
		<div id="review">
			<h2>${rvView.s_rev_title }</h2>
			<h5>작성자: ${rvView.s_gnick }</h5>
			<h5>작성일: ${rvView.d_rdate}</h5>
			<br>
			<hr>
			<br>
			<p>${rvView.s_rev_content }</p>
			<c:if test="${rvView.s_rev_mainpic != 'noimg.jpg' }">
			<div class="div_img">
				<img src="${conPath }/reviewUpload/${rvView.s_rev_mainpic}">
			</div>
			</c:if>
			<c:if test="${rvView.s_rev_pic1 != 'noimg.jpg' }">
			<div class="div_img">
				<img src="${conPath }/reviewUpload/${rvView.s_rev_pic1}">
			</div>
			</c:if>
			<c:if test="${rvView.s_rev_pic2 != 'noimg.jpg' }">
			<div class="div_img">
				<img src="${conPath }/reviewUpload/${rvView.s_rev_pic2}">
			</div>
			</c:if>
			<c:if test="${rvView.s_rev_pic3 != 'noimg.jpg' }">
			<div class="div_img">
				<img src="${conPath }/reviewUpload/${rvView.s_rev_pic3}">
			</div>
			</c:if>
		</div>
		<button class="btn" onclick="history.back()">이전 페이지</button>
		<br>
		<c:if test="${rvView.s_gid == guest.s_gid }">
		<span onclick="location.href='${conPath}/deleteReview.do?i_rev_no=${param.i_rev_no }'">리뷰 삭제하기</span>
		</c:if>
	</div>
</c:if>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>