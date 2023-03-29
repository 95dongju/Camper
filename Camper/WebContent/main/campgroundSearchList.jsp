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
		#div_cgList {
			width: 1000px;
			margin: 0 auto;
			padding-top: 100px;
		}
	</style>
	<script>
		$(document).ready(function(){
			var pageCnt = Number('${pageCnt}');
			var totCnt = Number('${totCnt}');
			if(totCnt <= 5){
				$('button').css('display','none');
			}
			$('button.append').click(function(){
				pageNum = Number($('.pageNum').last().val());
				if(isNaN(pageNum)){
					pageNum = 1;
				}
				$.ajax({
					url : '${conPath}/campNameListAppend.do',
					type : 'get',
					dataType : 'html',
					data : {'pageNum': (pageNum+1)},
					success : function (data) {
						$('#appendDiv').append(data);
						pageNum = Number($('.pageNum').last().val());
						if(pageCnt <= pageNum){
							$('button.append').css('display','none');
						}
					},
				});
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="div_cgList">
		<c:if test="${totCnt == 0 }">
			<p>해당 명의 캠핑장이 없습니다</p>
		</c:if>
		<c:if test="${totCnt != 0 }">
			<c:forEach items=${searchNameList } var="list">
			<div>
				${list. }
			</div>
		</c:forEach>
		</c:if>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>