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
	<link href="${conPath }/css/campgroundView.css" rel="stylesheet">
</head>
<style>
	#wrap_btn p {
		color: gray;
		font-size: 0.9em;
		cursor: pointer;
		text-align: center;
	}
	#wrap_btn p:hover {
		color: black;
	}
</style>
<body>
	<c:if test="${empty s_camp_no }">
		<script>
			alert('잘못된 접근입니다');
			/* location.href='${conPath}/main/main.jsp'; */
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="wrap">
		<div id="div_cg_content">
		<c:if test="${cgView.s_camp_mainpic != 'noimg.jpg' }">
			<div class="camp_mainpic">
				<img src="${conPath }/campgroundUpload/${cgView.s_camp_mainpic}">
			</div>
		</c:if>
			<div class="camp_info">
				<h2>${cgView.s_camp_name }</h2>
				<p>주소: ${cgView.s_camp_addr }</p>
				<p>연락처: ${cgView.s_camp_tel }</p>
				<div class="facility">
					<c:if test="${cgView.s_bathroom eq 'Y' }">
						<span>#개별화장실</span>
					</c:if>
					<c:if test="${cgView.s_showerbooth eq 'Y' }">
						<span>#샤워실</span>
					</c:if>
					<c:if test="${cgView.s_store eq 'Y' }">
						<span>#매점</span>
					</c:if>
					<c:if test="${cgView.s_sink eq 'Y' }">
						<span>#개수대</span>
					</c:if>
					<c:if test="${cgView.s_wifi eq 'Y' }">
						<span>#와이파이</span>
					</c:if>
					<c:if test="${cgView.s_playground eq 'Y' }">
						<span>#놀이터</span>
					</c:if>
					<c:if test="${cgView.s_with_pet eq 'Y' }">
						<span>#반려동물가능</span>
					</c:if>
					<c:if test="${cgView.s_swim_pool eq 'Y' }">
						<span>#수영장</span>
					</c:if>
				</div>
				<p>설명: ${cgView.s_camp_desc }</p>
					<c:if test="${not empty host }">
						<p>캠핑장 삭제하기</p>
					</c:if>
			</div>
		</div>
		<div id="div_cg_site">
			<c:if test="${not empty csList }">
				<table id="cslist_table">
					<tr>
						<c:forEach items="${csList }" var="csList" varStatus="cs">
							<c:if test="${cs.index%3==0 }">
							<tr></tr>
							</c:if>
								<td>
									<div class="campsite">
										${csList.s_sitename }
										<br>
										<h5>${csList.s_siteprice }원</h5>
										<button class="btn" onclick="${conPath}/reservation.do?s_site_no=${csList.s_site_no}">예약하기</button>
									</div>
								</td>
						</c:forEach>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>