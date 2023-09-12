<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소노러스_마이페이지</title>
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
 <link rel="stylesheet" href="/resources/demos/style.css">
 <link rel="stylesheet" type="text/css" href="../css/main.css" />
 <link rel="stylesheet" type="text/css" href="../css/memInfo.css" />
 <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
 <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
 <script>
 $(function(){
	$("#datepicker").datepicker({
		showOn : 'button',
		dateFormat : 'yy-mm-dd',
		prevText : '이전 달',
		
		nextText : '다음 달',
		monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames : ['일','월','화','수','목','금','토'],
		dayNamesShort : ['일','월','화','수','목','금','토'],
		
		dayNamesMin : ['일','월','화','수','목','금','토'],
		showMonthAfterYear : true,
		yearSuffix: '년'
	});
	
	$("#datepicker2").datepicker({
		showOn : 'button',
		dateFormat : 'yy-mm-dd',
		prevText : '이전 달',
		
		nextText : '다음 달',
		monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames : ['일','월','화','수','목','금','토'],
		dayNamesShort : ['일','월','화','수','목','금','토'],
		
		dayNamesMin : ['일','월','화','수','목','금','토'],
		showMonthAfterYear : true,
		yearSuffix: '년'
	});
 });
 
 function doSearch(){
	 var frm = document.searchForm;
	 
	 /* if(frm.startDate.value == ""){
		 alert("시작일을 입력해주세요.");
		 frm.startDate.focus();
		 return false;
	 }
		 
	 if(frm.endDate.value == ""){
		 alert("종료일을 입력해주세요.");
		 frm.endDate.focus();
		 return false;
	 }else if(frm.endDate.value != ""){
		if(frm.startDate.value >= frm.endDate.value){
			 alert("종료일을 시작일보다 크게 입력하세요.");
			 frm.endDate.value = "";
			 frm.endDate.focus();
			 return false;
		}		 
	 }  */
	 frm.action="reservInfo.do";
	 frm.submit();
 }
 function doSubmit(seq){
	 var frm = document.searchForm;
	 frm.reqSeq.value=seq;
	 frm.action = "reservCansel.do";
	 frm.submit();
 }
 </script>
</head>
<body>
<%@ include file="../../header.jsp" %>
<%@ include file="../../aside.jsp" %>
<div class="container" align="center">
	<h3>예약내역</h3><br>
	<form name="searchForm" method="post">
		<input type="hidden" name="reqSeq" id="reqSeq" value="">
		조회 시작일 : <input type="text" name="startDate" id="datepicker" value="${param.startDate }">
		조회 종료일 : <input type="text" name="endDate" id="datepicker2" value="${param.endDate }">
		<input type ="button" value="검색" onclick="doSearch()">
			
	</form>
	<br>
	<table class="table table-bordered" style="width : 500px">
		<thead>
			<tr>
				<td>예약번호</td>
				<td>호텔</td>
				<td>객실명</td>
				<td>체크인</td>
				<td>체크아웃</td>
				<td>예약일</td>
				<td>예약상태</td>
				<td>기능</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty reservList }">
				<c:forEach items="${reservList}" var="reserv">
					<tr>
						<td>${reserv.reqSeq }</td>
						<td>소노러스호텔</td>
						<td>${reserv.roomName }</td>
						<td>${reserv.checkInDate }</td>
						<td>${reserv.checkOutDate }</td>
						<td>${reserv.resInsDate }</td>
						<c:if test="${reserv.resState eq 'W'}">
							<td>결재대기</td>		
						</c:if>
						<c:if test="${reserv.resState eq 'S'}">
							<td>결재완료</td>		
						</c:if>
						<c:if test="${reserv.resState eq 'C'}">
							<td>예약취소</td>		
						</c:if>
						<c:if test="${reserv.resState eq 'R'}">
							<td>예약반려</td>		
						</c:if>
						<c:if test="${reserv.resState eq 'I'}">
							<td>숙박중</td>		
						</c:if>
						<c:if test="${reserv.resState eq 'O'}">
							<td>숙박완료</td>		
						</c:if>
						
						<td><input type="button" value="예약취소" onclick="doSubmit(${reserv.reqSeq})"></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty reservList }">
				<tr>
					<td colspan="6" align="center">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>
		
		</tbody>
	</table> 
</div>
<%@ include file="../../footer.jsp" %>	
</body>
</html>