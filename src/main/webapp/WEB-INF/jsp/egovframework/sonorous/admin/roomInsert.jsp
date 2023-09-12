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

function checkFiles(){
	alert("@@");
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");//정규식 추가, 안 되는 확장자 체크
	var maxSize = 10485760;
	var files = $("input[name='uploadFiles']")[0].files;
	
	for(var i = 0; i<files.length; i++){
		var fileName = files[i].name;
		var fileSize = files[i].size;
		//"test.test.TXT" split('.') test test TXT pop()  -> TXT -> txt 
		var ext = fileName.split('.').pop().toLowerCase();
		
		//개발자 도구(F12)에 출력하는 내용
		console.log("fileName : "+ fileName);
		console.log("fileSize : "+ fileSize);
		console.log("확장자 : "+ (regex.test(fileName) ? "괜찮아" : "안돼"));
		
		if(fileSize >= maxSize){
			alert("파일 사이즈는 10mb를 초과할 수 없습니다.");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 확장자는 업로드할 수 없습니다.");
			return false;
		}
		if($.inArray(ext,['gif', 'png', 'jpg', 'jpeg']) == -1){ //목록에 해당 확장자가 있는지 비교를 해주는 부분
			alert("gif, png, jpg, jpeg 파일만 업로드 할 수 있습니다.");
			return false;
		}
		
	}
	return true;
}//checkFiles() end

//파일추가
var addFile = function(){
	$("#fileList").append(
			'<div id="ff">'+
			'<input type="file" name="uploadFiles" multiple="multiple" style="display: inline-block;" >'+
			'<button type="button" class="btn btn-danger btn-xs btn-delete-file">x</button>'+
			'</div>'		
	);
}

$(document).ready(function(){
	//파일삭제
	$("#fileList").on("click", ".btn-delete-file", function(){
		$(this).parent().remove();
	});
});

var goInsert = function(){
	
	var frm = document.insertForm;
	alert($("#insertForm").serialize());
	if(!checkFiles()){
		alert("등록이 가능하지 않은 파일입니다.");
		return false;
	}
	
	if(!confirm("등록하시겠습니까?")) {
		return false;
	};
	
	frm.action = "/roomInsert.do";
    frm.submit();
}
</script>
</head>
<%@ include file="../header.jsp" %>
<body>
<!-- bo_seq_no가 있으면 수정, bo_seq_no가 0이면 새로 등록 -->
<div class="container">
	<form name="insertForm" id="insertForm" method="post" enctype="multipart/form-data">	
		<div id="content_pop">
	    	<table style="">
	    		<caption>객실등록 테이블</caption>
				<colgroup>
					<col style="width: 20%;">
					<col style="width: 30%;">
					<col style="width: 20%;">
					<col style="width: 30%;">
				</colgroup>
				<tbody>
		    		<tr>
		    			<th><span id="vali">*</span>객실명</th>
		    			<td colspan="3"><input type="text" id="roomName" name="roomName" /></td>
		    		</tr>
		    		<tr>
		    			<th><span id="vali">*</span>객실요금</th>
		    			<td><input type="text" id="roomPrice" name="roomPrice" />&nbsp;원</td>
		    			<th><span id="vali">*</span>객실수</th>
		    			<td><input type="number" id="roomAmount" name="roomAmount" /></td>
		    		</tr>
		    		<tr>
		    			<th><span id="vali">*</span>객실크기</th>
		    			<td><input type="number" id="roomSize" name="roomSize" step="0.01" />&nbsp;㎡</td>
		    			<th><span id="vali">*</span>최대인원수</th>
		    			<td><input type="number" id="peopleNum" name="peopleNum" /></td>
		    		<tr>
		    			<th><span id="vali">*</span>침대타입</th>
		    			<td colspan="3"><input type="text" id="bedType" name="bedType" /></td>
		    		</tr>
		    		<tr>
		    			<th>특이사항</th>
		    			<td colspan="3"><textarea id="roomSpec" name="roomSpec"></textarea></td>
		    		</tr>
		    		<tr>
		    			<th>첨부파일</th>
		    			<td colspan="3">
		    				<p>
							<c:forEach var="fileItem" items="${fileList }">
								<div>
									${fileItem.file_name }
									${fileItem.file_fancy_size }
									<button type="button" class="btn btn-danger btn-xs btn-delete-exist"
									data-file_seq_no="${fileItem.file_seq_no }">x</button>
								</div>
							
							</c:forEach>
							<!-- update 수정할 때 업로드된 파일 목록 보여주기 end -->
							
							<!-- insert 첨부파일 추가하는 곳 start -->
							</p>
							<p><button type="button" onClick="javascript:addFile();" class="btn btn-primary btn-xs btn-new-file">추가</button>
							<!-- accept=".gif, .jpg, .png" -->
							<div id="fileList">
								<div>
									<!-- inline은 붙여서, inline-block은 블록 형태, 약간 여백 있음 -->
									<input type="file" name="uploadFiles" multiple="multiple" style="display: inline-block;" accept="">
									<button type="button" class="btn btn-danger btn-xs btn-delete-file">x</button>
								</div>
							</div>
							</p>
		    			</td>
		    		</tr>
	    		</tbody>
	    	</table>
	    </div>
	    <div class="btn">
			<button onClick="javascript:goInsert();" class="btn_blue w_120">등록</button>
			<button onClick="javascript:history.back();" class="btn_yellow w_120">취소</button>
		</div>
	</form>
</div>
</body>
<%@ include file="../footer.jsp" %>
</html>