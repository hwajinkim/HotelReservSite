<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소노러스_마이페이지</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/memInfo.css" />
</head>
<body>
<%@ include file="../header.jsp" %>
<div class="container" align="center">
	<h3>회원정보</h3>
	<table class="table table-bordered" style="width : 300px">
		<tr>
			<td>성명</td>
			<td>${result.kName }</td>
		</tr>
		<tr>	
			<td>ID</td>
			<td>${result.mId }</td>
		</tr>
		<tr>	
			<td>이메일</td>
			<td>${result.email }</td>
		</tr>
	</table> 
</div>
<%@ include file="../footer.jsp" %>	
</body>
</html>