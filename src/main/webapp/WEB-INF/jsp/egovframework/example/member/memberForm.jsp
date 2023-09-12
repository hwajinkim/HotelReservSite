<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="#">
<link rel="stylesheet" type="text/css" href="../css/main.css" />
<link rel="stylesheet" type="text/css" href="../css/memForm.css" />
<script src="${pageContext.request.contextPath }/js/jquery-3.6.1.min.js"></script>
<script>

var duplicateCheck = false;

$(function(){
	$("#btn_idCheck").click(function(){
		fn_idCheck();
	});
	
	$(".btn-default").click(function(){
		alert(1);
		doSubmit();
	});
	function fn_idCheck(){
		var frm = document.memberForm;
		
		if(frm.mId.value==""){
			alert("ID를 입력하세요.");
			return;
		}
		
		var params = {'mId' : frm.mId.value };
		
		$.ajax({
			type : 'post',
			url : '/memberExists.do',
			data: params,
			dataType : 'JSON',
			success: function(res){
				
				console.log("res:"+res);
				console.log("res.result:"+res.result);
		    },
		    error: function (request, status, error) {
		        console.log("code: " + request.status);
		        console.log("message: " + request.responseText);
		        console.log("error: " + error);
		    }
		}); //ajax end	
	} // fn_idCheck();
	
	
	function doSubmit(){
		alert(2);
		var frm = document.memberForm;
		if(frm.mId.value == ""){
			alert("아이디를 입력하세요.");
			frm.mId.focus();
			return false;
		}
		if(frm.mPw.value == ""){
			alert("비밀번호를 입력하세요.");
			frm.mPw.focus();
			return false;
		}
		if(frm.mPw_confirm.value == ""){
			alert("비밀번호 확인을 입력해주세요.");
			frm.mPw_confirm.focus();
			return false;
		}else{
			if(frm.mPw.value!=frm.mPw_confirm.value){
				alert("비밀번호를 다시 확인해주세요.");
				return false;
			}
		}
		if(frm.country.value == ""){
			alert("국가를 입력해주세요.");
			frm.country.focus();
			return false;
		}
		if(frm.eName.value == ""){
			alert("영문 이름을 입력해주세요.");
			frm.eName.focus();
			return false;
		}
		if(frm.kName.value == ""){
			alert("국문 이름을 입력해주세요.");
			frm.kName.focus();
			return false;
		}
		if(frm.birth.value == ""){
			alert("생년월일을 입력해주세요.");
			frm.birth.focus();
			return false;
		}
		if(frm.phone01.value == ""){
			alert("전화번호 첫째 자리를 입력해주세요.");
			frm.phone01.focus();
			return false;
		}
		if(frm.phone02.value == ""){
			alert("전화번호 둘째 자리를 입력해주세요.");
			frm.phone02.focus();
			return false;
		}
		if(frm.phone03.value == ""){
			alert("전화번호 셋째 자리를 입력해주세요.");
			frm.phone03.focus();
			return false;
		}
		if(frm.email01.value == ""){
			alert("이메일 첫째 자리를 입력해주세요.");
			frm.email01.focus();
			return false;
		}
		if(frm.email02.value == ""){
			alert("이메일 둘째 자리를 입력해주세요.");
			frm.email02.focus();
			return false;
		}
		
		$("#phone").val($("#phone01").val()+'-'+$("#phone02").val()+'-'+$("#phone03").val());
		$("#email").val($("#email01").val()+'@'+$("#email02").val()); 
		
		frm.action = "/memberInsert.do";
		frm.submit();
	}
}); //function end;
</script>
<title>소노러스_회원가입 </title>
</head>
<body>
<%@ include file="../header.jsp" %>
	<form name="memberForm" method="post" id="memberForm">
		<div id="memForm">
			<input type="hidden" name="phone" id="phone"/>
		<input type="hidden" name="email" id="email"/>
		
		<table class="table table-bordered">
			<tr>
				<td><label for="mId">아이디*</label></td>
				<td><input type="text" name="mId" size="20"/>
					<button type="button" id="btn_idCheck">중복확인</button>
					<label id="lbl_result"></label>
				</td>
				
			</tr>
			<tr>
				<td><label for="mPw">비밀번호*</label></td>
				<td><input type="password" name="mPw" size="20" /></td>
			</tr>
			<tr>
				<td><label for="mPw_confirm">비밀번호 확인*</label></td>
				<td><input type="password" name="mPw_confirm" size="20" autocomplete="new-password"/></td>
			</tr>
			<tr>
				<td><label for="country">국가*</label></td>
				<td><input type="text" name="country" size="20"/></td>
			</tr>
			<tr>
				<td><label for="eName">영문 이름* </label></td>
				<td><input type="text" name="eName" size="20"/></td>
			</tr>
			<tr>
				<td><label for="kName">국문 이름* </label></td>
				<td><input type="text" name="kName" size="20"/></td>
			</tr>
			<tr>
				<td><label for="birth">생년월일* </label></td>
				<td><input type="text" name="birth" size="20"/></td>
			</tr>
			<tr>
				<td><label for="phone">연락처* </label></td>
				<td>
					<select name="phone01" id="phone01">
						<option value="" selected="selected">선택</option>
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select>
					- <input type="text" name="phone02" id="phone02" size="20"/>
					- <input type="text" name="phone03" id="phone03" size="20"/>
				</td>
			</tr>
			<tr>
				<td><label for="email">이메일* </label></td>
				<td>
					<input type="text" name="email01" id="email01" size="20"/>
					@
					<select name="email02" id="email02">
						 <option value="" selected="selected">직접입력</option>
						 <option value="naver.com" >naver.com</option>
						 <option value="hanmail.net">hanmail.net</option>
						 <option value="hotmail.com">hotmail.com</option>
						 <option value="nate.com">nate.com</option>
						 <option value="yahoo.co.kr">yahoo.co.kr</option>
						 <option value="empas.com">empas.com</option>
						 <option value="dreamwiz.com">dreamwiz.com</option>
						 <option value="freechal.com">freechal.com</option>
						 <option value="lycos.co.kr">lycos.co.kr</option>
						 <option value="korea.com">korea.com</option>
						 <option value="gmail.com">gmail.com</option>
						 <option value="hanmir.com">hanmir.com</option>
						 <option value="paran.com">paran.com</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" class="btn-default" value="회원가입"/>
				</td>
			</tr>
		</table>
		</div>
	</form>
<%@ include file="../footer.jsp" %>	
</body>
</html>