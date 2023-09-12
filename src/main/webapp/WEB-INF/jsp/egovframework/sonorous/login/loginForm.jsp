<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="${pageContext.request.contextPath }/js/jquery-3.6.1.min.js"></script>

<meta charset="UTF-8">
<title>소노러스_로그인</title>
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/login.css" />

</head>
<script>
$(document).ready(function(){
	$("#mId").focus();
	
	$("#mPw").keydown(function(key){
		if(key.keyCode == 13){
			login();
		}
	});
});

function login(){
	var frm = document.loginForm;
	
	if(frm.mId.value == ""){
		alert("아이디를 입력하세요.");
		return false;
	}
	
	if(frm.mPw.value == ""){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	
	frm.action = "login.do";
	frm.submit();
}
</script>
<body>
<%@ include file="../header.jsp" %>
<div class="container" align="center">
	<h3>로그인</h3>
	
	<form name="loginForm" method="post">
		<table class="table table-bordered" style="width : 300px">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="mId" id="mId" size="20"/></td>
			</tr>
			<tr>	
				<td>패스워드</td>
				<td><input type="password" name="mPw" id="mPw" size="20"/></td>
			</tr>
			<tr>	
				<td colspan="2" align="center">
					<input type="button" value="로그인" class="btn btn-default" onclick="login();"/>
					<input type="button" value="회원가입" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/member/memberForm'"/>
				</td>
			</tr>
		</table> 
	</form>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>