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
	<style>
		footer {
			color: #C2C2C2;
			margin: 10px auto;
			width: 1000px;
			font-size: 0.8em;
			padding: 10px;
			background-color: white;
		}
		footer hr {
			background: #EEEEEE;
			height: 1px;
			border: 0;
		}
		footer #footer_nav {
			min-width: 1000px;
			padding-top: 10px;
		}
		footer #footer_nav ul li {
			float: left;
			width: 25%;
			text-align: center;
			padding: 10px;
			box-sizing: border-box;
		}
		footer #footer_nav ul li a {
			color: #C2C2C2;
		}
		footer #camper_info {
			padding: 10px;
			text-align: center;
		}
		footer #camper_info span {
			font-size: 0.8em;
		}
		footer #customer_service {
			margin: 20px;
			text-align: center;
		}
		footer #customer_service p {
			margin-bottom: 10px;
		}
	</style>
</head>
<body>
	<footer>
		<div id="footer_nav">
			<hr>
				<ul>
					<li><a href="">회사소개</a></li>
					<li><a href="">이용약관</a></li>
					<li><a href="">개인정보처리방침</a></li>
					<li><a href="">사업자정보</a></li>
				</ul>
			<hr>
		</div>
		<div id="camper_info">
		<hr>
			<p>© Camper Corp. </p>
			<br>
			<span>캠퍼는 통신판매 중개자로서 통신판매의 당사자가 아니며 고객지원을 제외한 상품의 예약, 이용 등과 관련한 의무와 책임 등 모든 거래에 대한 책임은 가맹점에게 있습니다.</span>
		</div>
		<hr>
		<div id="customer_service">
			<p>고객센터 전화번호: 070-0139-0981 | 메일: cs.camper.com </p>
			<p>평일 : 오전9시 ~ 오후 5시 30분 (점심시간: 오후 12시 30분 ~ 1시 30분) / 주말·공휴일 휴무 </p>
		</div>
	</footer>
</body>
</html>