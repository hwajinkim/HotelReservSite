<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/memInfo.css" />

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>

var goUpdPage = function(id){
	$("#viewForm").attr("action","/roomUpdatePage.do?roomId="+id).submit();
}

var goDel = function(id){
	$("#viewForm").attr("action","/roomDelete.do?roomId="+id).submit();
}

var goList = function(){
	location.href="/roomList.do";
}
</script>
</head>
<%@ include file="../header.jsp" %>
<body>
<!-- bo_seq_no가 있으면 수정, bo_seq_no가 0이면 새로 등록 -->
<div class="container">
	<form name="viewForm" id="viewForm" method="post" enctype="multipart/form-data">	
		<div id="content_pop">
	    	<table style="">
	    		<caption>객실 상세보기 테이블</caption>
				<colgroup>
					<col style="width: 20%;">
					<col style="width: 30%;">
					<col style="width: 20%;">
					<col style="width: 30%;">
				</colgroup>
				<tbody>
					<tr>
					<c:forEach var="img" items="${imgList}">
						<img src="/upload/${img.roomImagePath}/${img.roomImageName}" style="width:250px; height:250px;">
					</c:forEach>
					</tr>
		    		<tr>
		    			<th><span id="vali">*</span>객실명</th>
		    			<td colspan="3"><c:out value="${roomInfo.roomName}" /></td>
		    		</tr>
		    		<tr>
		    			<th><span id="vali">*</span>객실요금</th>
		    			<td><c:out value="${roomInfo.roomPrice}" />&nbsp;원</td>
		    			<th><span id="vali">*</span>객실수</th>
		    			<td><c:out value="${roomInfo.roomAmount}" /></td>
		    		</tr>
		    		<tr>
		    			<th><span id="vali">*</span>객실크기</th>
		    			<td><c:out value="${roomInfo.roomSize}" />&nbsp;㎡</td>
		    			<th><span id="vali">*</span>최대인원수</th>
		    			<td><c:out value="${roomInfo.peopleNum}" /></td>
		    		<tr>
		    			<th><span id="vali">*</span>침대타입</th>
		    			<td colspan="3"><c:out value="${roomInfo.bedType}" /></td>
		    		</tr>
		    		<tr>
		    			<th>특이사항</th>
		    			<td colspan="3"><c:out value="${roomInfo.roomSpec}" /></td>
		    		</tr>
	    		</tbody>
	    	</table>
	    </div>
	    <div class="btn">
			<button onClick="javascript:goUpdPage('${roomInfo.roomId}');" class="btn_blue w_120">수정</button>
			<button onClick="javascript:goDel('${roomInfo.roomId}');" class="btn_red w_120">삭제</button>
			<button onClick="javascript:goList();" class="btn_yellow w_120">목록</button>
		</div>
	</form>
</div>
</body>
<%@ include file="../footer.jsp" %>
</html>