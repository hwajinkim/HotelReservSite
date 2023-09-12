<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header>
	<a href="${pageContext.request.contextPath}/main.do"><img id="logo" src="${pageContext.request.contextPath}/images/logo.png"></a>
	<nav id ="top_menu">
	<!-- 저장해둔 세션 userId값 가져와서 있으면 로그아웃 없으면 로그인  -->
	<%
    request.setCharacterEncoding("utf-8"); //한글 깨짐 방지

    //세션값이 null인경우, 저장된 값이 어직 없는경우
    if(session.getAttribute("LOGIN_USER") == null) {
    %>
   
	<a href="${pageContext.request.contextPath}/login/loginForm.do"> 로그인 | <a href="${pageContext.request.contextPath}/addMember.do">회원가입 
	<% } else{ %>
	<a href="/login/logout.do">로그아웃</a> | <a href="/memberInfo.do">마이페이지</a>
	<% } %>
	</nav>
	<nav id ="main_menu">
		<ul>
			<li><a href="#">소개</a>
			<li><a href="#">예약</a>
			<li><a href="#">공지사항</a>
		</ul>
	</nav>
</header>
