<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소노러스_관리자페이지_예약관리</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/memInfo.css" />
<script src="${pageContext.request.contextPath }/js/jquery-3.6.1.min.js"></script>

<link rel="shortcut icon" href="#">
</head>
<script>
function doSearch(page){
	var frm = document.searchForm;
	
	/* if(frm.searchWord.value == ""){
		alert("검색어를 입력하세요.");
		frm.searchWord.focus();
		return;
	} */
	frm.currentPage.value=page;
	frm.action="reservManageList.do";
	frm.submit();
}
</script>
<%@ include file="../../header.jsp" %>
<body>
	<div class="container">
		<p align = "center">
		<form name="searchForm" method="post">
			<input type="hidden" name="currentPage" value="${param.currentPage }"/>
			<input type="text" size="20" name="searchWord" value="${param.searchWord }">
			<input type="button" value="검색" onclick="doSearch(1);">
		</form>
		
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<td>순번</td>
					<td>객실이름</td>
					<td>예약자명</td>
					<td>예약인원</td>
					<td>체크인</td>
					<td>체크아웃</td>
					<td>예약일</td>
					<td>예약상태</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty reservManageList }">
					<c:forEach var="rs" items="${reservManageList }">
						
						<tr>
							<td>${rs.reqSeq }</td>
							<td><a href="${pageContext.request.contextPath}/reservManageForm.do?reqSeq=${rs.reqSeq}">${rs.roomName }</a></td>
							<td>${rs.kName }</td>
							<td></td>
							<td>${rs.checkInDate }</td>
							<td>${rs.checkOutDate }</td>
							<td>${rs.resInsDate }</td>
							<c:if test="${rs.resState eq 'W'}">
								<td>결재대기</td>		
							</c:if>
							<c:if test="${rs.resState eq 'S'}">
								<td>결재완료</td>		
							</c:if>
							<c:if test="${rs.resState eq 'C'}">
								<td>예약취소</td>		
							</c:if>
							<c:if test="${rs.resState eq 'R'}">
								<td>예약반려</td>		
							</c:if>
							<c:if test="${rs.resState eq 'I'}">
								<td>숙박중</td>		
							</c:if>
							<c:if test="${rs.resState eq 'O'}">
								<td>숙박완료</td>		
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty  reservManageList}">
					<tr>
						<td colspan="8" align="center">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div style="text-align: center;">
			<ul class ="pagination">
				${pagingUtil.pageHtml }
			</ul>
		</div>
		</p>
	</div>
</body>
<%@ include file="../../footer.jsp" %>
</html>