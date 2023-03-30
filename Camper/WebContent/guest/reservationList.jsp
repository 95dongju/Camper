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
	<script type="text/javascript" src="${conPath }/js/calender.js"></script>
	<style>
		#wrap {
			width: 1000px;
			padding-top: 100px;
			margin: 0 auto;
		}
		#wrap #booking_frm {
			width: 700px;
			margin: 10px auto;
			text-align: center;
		}
		#wrap #booking_frm #booking_data {
			margin: 20px;
		}
		#wrap #booking_frm #booking_data p {
			margin: 30px;
		}
		#wrap #booking_frm #booking_data input {
			width: 200px;
			height: 25px;
			padding: 4px;
			border: 1px solid gray;
			box-sizing: border-box;
		}
		#wrap #booking_frm #calendar {
			margin: 10px auto;
			text-align: center;
			border: 1px solid gray;
			padding: 5px;
		}
		#wrap #booking_frm #calendar tr td {
			padding: 5px;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="wrap">
		<div id="booking_frm">
			<form method="post">
				<table id="calendar" align="center">
					<tr>
						<td align="center"><label onclick="prevCalendar()"> ◀ </label></td>
						<td colspan="5" align="center" id="calendarTitle">yyyy년 m월</td>
						<td align="center"><label onclick="nextCalendar()"> ▶ </label></td>
					</tr>
					<tr>
						<td align="center"><font color ="#F79DC2">일</font></td>
						<td align="center">월</td>
						<td align="center">화</td>
						<td align="center">수</td>
						<td align="center">목</td>
						<td align="center">금</td>
						<td align="center"><font color ="skyblue">토</font></td>
					</tr>
					<script type="text/javascript">buildCalendar();</script>
				</table>
				<div id="booking_data">
				<p> 예약일 <input id="selectedDate" name="selectedDate" value="" readonly="readonly"></p>
				<p> 금액 <input id="totalPrice" name="totalPrice" value="" readonly="readonly"> 원 </p>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>