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
		#wrap table {
			margin: 10px auto;
			text-align: center;
			border: 1px solid #BBBBBB;
			padding: 5px;
			border-radius: 20px;
		}
		#wrap table tr td {
			padding: 5px;
		}
	</style>
	<script>
		$(document).ready(function(){
			$('select[name="year"], select[name="month"]').change(function(){
				$('form').submit();
			});
			
			$('td.reservationTd').click(function(){
				// 오늘 날짜
				var now = new Date();
				var nowYear = now.getFullYear();
				var nowMonth = now.getMonth()+1;
				var nowDay = now.getDate();
				var today = nowYear + '-' + (nowMonth<10 ? "0"+nowMonth : nowMonth) + '-' + (nowDay<10 ? "0"+nowDay : nowDay);
				// 파라미터로 넘길 날짜
				var day = $(this).attr('id');
				month = '${month}';
				if(month<10){
					month = '0'+month;
				}
				if(day<10){
					day = '0'+day;
				}
				reservationDate = '${year}-' + month + '-' + day;
				if(today < reservationDate){
					alert(reservationDate+'에 예약하시겠습니까?');
					location.href = '${conPath}/reservation.do?year=${year}&month=${month}&s_gid=${guest.s_gid}&s_site_no=${param.s_site_no}&selectDate='+reservationDate;
				}else if(today == reservationDate) {
					alert('당일 예약은 불가능합니다');
				}else{
					alert('이전 일자는 예약이 불가능합니다');
				}
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="wrap">
	<c:if test="${empty guest}">
		<script>
			alert('로그인 후 이용 가능합니다.');
			location.href='${conPath}/guest/loginGuest.jsp';
		</script>
	</c:if>
	<c:if test="${reservationResult == 1}">
		<script>alert('예약되었습니다');</script>
	</c:if>
	<c:if test="${reservationResult == 0}">
		<script>alert('예약에 실패하였습니다');</script>
	</c:if>
		<table>
			<tr>
				<td colspan="7">
					<form action="${conPath }/reservationView.do">
							<input type="hidden" name="s_site_no" value="${param.s_site_no }">
							<input type="hidden" name="s_gid" value="${guest.s_gid }">
							<select name="year">
								<c:forEach var="i" begin="${year }" end="${year+10 }">
									<c:if test="${i eq year }">
										<option selected="selected">${i }</option>
									</c:if>
									<c:if test="${i != year }">
										<option>${i }</option>
									</c:if>
								</c:forEach>
							</select> 년
							<select name="month">
								<c:forEach var="i" begin="1" end="12">
									<c:if test="${i eq month+1 }">
										<option selected="selected">${i }</option>
									</c:if>
									<c:if test="${i != month }">
										<option>${i }</option>
									</c:if>
								</c:forEach>
							</select> 월
						</form>
				</td>
			</tr>
			<tr>
				<c:forEach var="t" items="${calPrint.title }">
					<th>${t }</th>
				</c:forEach>
			</tr>
			<c:forEach var="i" begin="0" end="5">
				<tr>
					<c:forEach var="j" begin="0" end="6">
						<c:if test="${empty calPrint.calDate[i][j] }">
							<td style="background-color: white;"></td>
						</c:if>
						<c:if test="${not empty calPrint.calDate[i][j] }">
								<c:set var="reserved" value="0"/>
								<c:forEach var="reservation" items="${reservations }">
									<c:if test="${calPrint.calDate[i][j] == reservation.day }">
										<td style="color:#cccccc; background-color: #FFE271; cursor: default;">
											<h3>${calPrint.calDate[i][j] }</h3>
											<span>이미 예약된 날짜입니다</span><br>
										</td>
										<c:set var="reserved" value="1"/>
									</c:if>
								</c:forEach>
								<c:if test="${reserved == 0 }">
									<td style="color:black;" class="reservationTd" id="${calPrint.calDate[i][j] }">
											<h3>${calPrint.calDate[i][j] }</h3>
											<span> &nbsp; &nbsp; &nbsp; </span><br>
										</td>
								</c:if>
						</c:if>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>