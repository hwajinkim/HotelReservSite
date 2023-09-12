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
	var goList = function(){
		var frm = document.infoForm;
		frm.action = "/rsvList.do";
	    frm.submit();
	}
</script>
</head>
<body>
<%@ include file="../header.jsp" %>
<!-- bo_seq_no가 있으면 수정, bo_seq_no가 0이면 새로 등록 -->
<div class="container">
	<form name="infoForm" id="infoForm" method="post">
		<input type="hidden" id="searchRoomType" name="searchRoomType" value="${searchInfo.searchRoomType}" />
		<input type="hidden" id="searchCheckIn" name="searchCheckIn" value="${searchInfo.searchCheckIn}" />
		<input type="hidden" id="searchCheckOut" name="searchCheckOut" value="${searchInfo.searchCheckOut}" />
		<input type="hidden" id="searchPeople" name="searchPeople" value="${searchInfo.searchPeople}" />
		<input type="hidden" id="roomId" name="roomId" value="${roomInfo.roomId}" />
	
		<div id="content_pop">
	    	<table style="">
	    		<caption>객실 정보 테이블</caption>
				<colgroup>
					<col style="width: 20%;">
					<col style="width: 30%;">
					<col style="width: 20%;">
					<col style="width: 30%;">
				</colgroup>
				<tbody>
		    		<tr>
		    			<th>객실명</th>
		    			<td colspan="3"><c:out value="${roomInfo.roomName}" /></td>
		    		</tr>
		    		<tr>
		    			<th>최대인원수</th>
		    			<td colspan="3"><c:out value="${roomInfo.peopleNum}" /></td>
		    		</tr>
		    		<tr>
		    			<th>크기</th>
		    			<td colspan="3"><c:out value="${roomInfo.roomSize}" /></td>
		    		</tr>
		    		<tr>
		    			<th>침대타입</th>
		    			<td colspan="3"><c:out value="${roomInfo.bedType}" /></td>
		    		</tr>
		    		<tr>
		    			<th>특이사항</th>
		    			<td colspan="3"><c:out value="${roomInfo.roomSpec}" /></td>
		    		</tr>
		    		<tr>
		    			<th>객실가격</th>
		    			<td colspan="3"><c:out value="${roomInfo.roomPrice}" /></td>
		    		</tr>
		    		<tr>
		    			<th></th>
		    			<td colspan="3" align="center" class="listtd">
            				<span class="btn_blue_l"><a href="/rsvInsertPage.do?roomId=${roomInfo.roomId}&searchRoomType=${searchInfo.searchRoomType}&searchCheckIn=${searchInfo.searchCheckIn}&searchCheckOut=${searchInfo.searchCheckOut}&searchPeople=${searchInfo.searchPeople}">예약하기</a></span>&nbsp;
	                    </td>
		    		</tr>
	    		</tbody>
	    	</table>
	    </div>
	    <div class="btn">
			<button onClick="javascript:goList();" class="btn_blue w_120">목록</button>
		</div>
	</form>
</div>
<%@ include file="../footer.jsp" %>	
</body>
</html>