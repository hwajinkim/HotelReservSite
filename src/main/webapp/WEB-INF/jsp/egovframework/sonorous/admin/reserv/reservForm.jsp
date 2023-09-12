<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소노러스_관리자페이지_예약관리(수정)</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/memInfo.css" />
<script>
function doSubmit(){
	var frm = document.reservForm;
	if(frm.roomName.value==""){
		alert("객실명을 입력하세요.");
		frm.roomName.focus();
		return false;
	}
	if(frm.resState.value==""){
		alert("예약상태를 선택하세요.");
		frm.resState.focus();
		return false;
	}
	frm.action="reservManageUpdate.do";
	frm.submit();
}
</script>
</head>
<%@ include file="../../header.jsp" %>
<body>
	<div class="container">
		<form name="reservForm" method="post">
		<input type="hidden" name="reqSeq" value="${reserv.reqSeq }"/>
		<table class="table table-bordered">
			<tr>
				<td>객실명</td>
				<td><input type="text" name="roomName" size=20 value="${reserv.roomName }" readonly></td>
			</tr>
			<tr>
				<td>호텔명</td>
				<td>소노러스</td>
			</tr>
			<tr>
				<td>예약자명</td>
				<td>${reserv.kName }</td>
			</tr>
			<tr>
				<td>인원수</td>
				<td></td>
			</tr>
			<tr>
				<td>체크인</td>
				<td>${reserv.checkInDate }</td>
			</tr>
			<tr>
				<td>체크아웃</td>
				<td>${reserv.checkOutDate }</td>
			</tr>
			<tr>
				<td>예약상태</td>
				<td>
					<select name="resState">
						<option value="">선택</option>
						<option value="W"<c:if test="${reserv.resState eq 'W'}">selected</c:if>>결재대기</option>
						<option value="S"<c:if test="${reserv.resState eq 'S'}">selected</c:if>>결재완료</option>
						<option value="C"<c:if test="${reserv.resState eq 'C'}">selected</c:if>>예약취소</option>
						<option value="R"<c:if test="${reserv.resState eq 'R'}">selected</c:if>>예약반려</option>
						<option value="I"<c:if test="${reserv.resState eq 'I'}">selected</c:if>>숙박중</option>
						<option value="O"<c:if test="${reserv.resState eq 'O'}">selected</c:if>>숙박완료</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>객실요금</td>
				<td>${reserv.roomPrice }</td>
			</tr>
				<tr>
				<td>전화번호</td>
				<td>${reserv.phone }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${reserv.email }</td>
			</tr>
				<tr>
				<td>예약일</td>
				<td>${reserv.resInsDate }</td>
			</tr>
			<tr>
				<td>요청사항</td>
				<td>${reserv.addReq }</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="수정하기" class="btn btn-default" onclick="doSubmit()">
					<input type="reset" value="취소" class="btn btn-default">
					<input type="reset" value="목록" class="btn btn-default" onclick="location.href='reservManageList.do'">
					
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
<%@ include file="../../footer.jsp" %>
</html>