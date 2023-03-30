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
		header {
			margin: 0 auto;
			width: 1000px;
		}
		header #header_nav {
			position: fixed;
			min-width: 1000px;
			padding-top: 10px;
			z-index: 20;
			background-color: white;
		}
		header #header_nav .logo {
			float: left;
			display: block;
		}
		header #header_nav .gnb{
			margin: 0 auto;
			margin-left: 10px;
		}
		header #header_nav .gnb ul li {
			float: right;
		}
		header #header_nav .gnb ul li a {
			padding: 0 10px;
			height: 50px;
			line-height: 50px;
			text-align: center;
		}
		header #header_nav .logo img {
			height: 50px;
		}
		header #header_nav .searchbox {
			margin: 10px;
			margin-left: 20px;
			float: left;
			width: 400px;
			height: 30px;
			border: 3px solid #596E37;
			border-radius: 2px 2px 2px 2px;
			padding-left: 5px;
			overflow: hidden;
		}
		header #header_nav .searchbox #searchtxt {
			 position: relative;
			 box-sizing: border-box;
			 height: 30px;
			 width: 370px;
			 border: none;
			 outline: none;
		}
		header #header_nav .searchbox #searchBtn {
			cursor: pointer;
		}
		header #header_nav .searchbox img {
			 position: relative;
			 float: right;
			 width: 25px;
			 padding: 2px;
		}
		header #header_nav .gnb .lnb {	
			width: 130px;
			text-align: center;
			font-size: 0.9em;
			background-color: white;
			position:absolute;
			display: none;
		}
		header #header_nav .gnb ul > li:hover .lnb {
			display: block;
		}
	</style>
	<script>
		$(function(){
			$('#searchBtn').click(function(){
				var searchtxt = $('#searchtxt').val();
				location.href= '${conPath}/searchCamp.do?cgName='+searchtxt;
			});
		});
	</script>
</head>
<body>
	<header>
			<div id="header_nav">
				<a href="${conPath }/main.do" class="logo">
					<img src="${conPath }/img/camperLogo.png"/>
				</a>
				<form action="${conPath}/searchCamp.do" method="get">
					<div class="searchbox">
						<input type="text" id="searchtxt" placeholder="캠핑장 명을 검색하세요" value="${cgName }"><img src="${conPath }/img/free-icon-magnifying-glass-49116.png" id="searchBtn">
					</div>
				</form>
				<c:if test="${empty guest and empty host and empty admin}">
					<div class="gnb">
						<ul>
							<li>
								<a>회원가입</a>
								<ul class="lnb">
									<li><a href="${conPath }/guest/joinGuest.jsp">게스트 회원가입</a></li>
									<li><a href="${conPath }/host/joinHost.jsp">호스트 회원가입</a></li>
								</ul>
							</li>
							<li><a href="${conPath }/campgroundRegistView.do">캠핑장 등록</a></li>
							<li><a href="${conPath }/loginView.do">로그인</a></li>
						</ul>
					</div>
				</c:if>
				<c:if test="${not empty guest and empty host and empty admin}">
					<div class="gnb">
						<ul>
							<li><a href="${conPath }/mypageGuest.do">마이페이지</a></li>
							<li><a href="${conPath }/campgroundRegistView.do">캠핑장 등록</a></li>
							<li><a href="${conPath }/logoutGuest.do">로그아웃</a></li>
						</ul>
					</div>
				</c:if>
				<c:if test="${empty guest and not empty host and empty admin}">
					<div class="gnb">
						<ul>
							<li><a href="${conPath }/mypageHost.do">마이페이지</a></li>
							<li><a href="${conPath }/reservationList.do">예약 목록</a></li>
							<li><a href="${conPath }/campgroundListView.do?s_hid=${host.s_hid}">캠핑장 관리</a></li>
							<li><a href="${conPath }/logoutHost.do">로그아웃</a></li>
						</ul>
					</div>
				</c:if>
				<c:if test="${not empty admin}">
					<div class="gnb">
						<ul>
							<li><a href="${conPath }/memberList.do">회원 관리</a></li>
							<li><a href="${conPath }/reviewList.do">리뷰 관리</a></li>
							<li><a href="${conPath }/logoutAdmin.do">로그아웃</a></li>
						</ul>
					</div>
				</c:if>
			</div>
	</header>
</body>
</html>