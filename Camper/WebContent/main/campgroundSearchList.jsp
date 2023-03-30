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
		#wrap #div_cgList {
			width: 90%;
			margin: 0 auto;
		}
		#wrap #div_cgList .each_campground {
			width: 100%;
			height: 160px;
			line-height: 50px;
			background-color: #EEEEEE;
			border-radius: 20px;
			box-sizing: border-box;
			margin: 15px;
			padding: 15px;
			cursor: pointer;
		}
		#wrap #div_cgList .each_campground .cg_mainpic {
			width: 200px;
			margin: 0 auto;
			height:130px;
			border-radius: 20px;
			float: left;
			overflow: hidden;
			text-align: center;
		}
		#wrap #div_cgList .each_campground .cg_mainpic img {
			width: 200px;
		}
		#wrap #div_cgList .each_campground .cg_info {
			width: 500px;
			float: left;
			margin: 13px;
		}
		#wrap #div_cgList .each_campground .btn {
			width: 100px;
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
	<div id="wrap">
		<div id="div_cgList">
			<c:if test="${totCnt == 0 }">
				<p>해당 명의 캠핑장이 없습니다</p>
			</c:if>
			<c:if test="${totCnt != 0 }">
				<c:forEach items="${searchNameList }" var="list">
					<div class="each_campground">
						<div class="cg_mainpic"><img src="${conPath }/campgroundUpload/${list.s_camp_mainpic}"></div>
						<div class="cg_info">
							<h2>${list.s_camp_name}</h2>
							<p>${list.s_camp_addr }</p>
						</div>
						<div class="btn">
							<button>예약하기</button>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>