<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	var goRsv = function(){
		
		var frm = document.insertForm;
		alert($("#insertForm").serialize());
		
		if(!confirm("예약하시겠습니까?")) {
			return false;
		};
		
		frm.action = "/rsvInsert.do";
	    frm.submit();
	}
</script>
</head>
<body>
<!-- bo_seq_no가 있으면 수정, bo_seq_no가 0이면 새로 등록 -->
<%@ include file="../header.jsp" %>
<div>
	<form name="insertForm" id="insertForm" method="post">
		<input type="hidden" id="searchRoomType" name="searchRoomType" value="${searchInfo.searchRoomType}" />
		<input type="hidden" id="searchCheckIn" name="searchCheckIn" value="${searchInfo.searchCheckIn}" />
		<input type="hidden" id="searchCheckOut" name="searchCheckOut" value="${searchInfo.searchCheckOut}" />
		<input type="hidden" id="searchPeople" name="searchPeople" value="${searchInfo.searchPeople}" />
		<input type="hidden" id="roomId" name="roomId" value="${roomInfo.roomId}" />
		
		<h3>예약 정보</h3>
		<div id="content_pop">
	    	<table style="">
	    		<caption>예약정보 테이블</caption>
				<colgroup>
					<col style="width: 20%;">
					<col style="width: 30%;">
					<col style="width: 20%;">
					<col style="width: 30%;">
				</colgroup>
				<tbody>
					<tr>
		    			<th>예약자명</th>
		    			<td colspan="3"><c:out value="" /></td>
		    		</tr>
		    		<tr>
		    			<th>객실명</th>
		    			<td colspan="3"><c:out value="${roomInfo.roomName}" /></td>
		    		</tr>
		    		<tr>
		    			<th>체크인날짜</th>
		    			<td colspan="3"><c:out value="${searchInfo.searchCheckIn}" /></td>
		    		</tr>
		    		<tr>
		    			<th>체크아웃날짜</th>
		    			<td colspan="3"><c:out value="${searchInfo.searchCheckOut}" /></td>
		    		</tr>
		    		<tr>
		    			<th>최종결제가격</th>
		    			<td colspan="3"><c:out value="${roomInfo.roomPrice}" /></td>
		    		</tr>
		    		<tr>
		    			<th>추가요청사항</th>
		    			<td colspan="3"><textarea id="addReq" name="addReq"></textarea></td>
		    		</tr>
	    		</tbody>
	    	</table>
	    </div>
	    <div class="btn">
			<button onClick="javascript:goRsv();" class="btn_blue w_120">예약하기</button>
			<button onClick="javascript:history.back();" class="btn_yellow w_120">취소</button>
		</div>
	</form>
</div>
<%@ include file="../footer.jsp" %>	
</body>
</html>