<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="${pageContext.request.contextPath }/js/jquery-3.6.1.min.js"></script>
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
	<a href="${pageContext.request.contextPath}/login/logout.do">로그아웃</a> | <a href="${pageContext.request.contextPath}/memberInfo.do">마이페이지</a>
	<% } %>
	</nav>
	<c:set var="data" value="${fn:substring(LOGIN_USER.mId,0,1)}"/>
	
<%-- 	<c:if test="${data eq 'A'}">
	<nav id ="main_menu">
		<ul>
			<li><a href="#">소개</a></li>
			<li class="admin_manage"><a href="#">관리</a></li>
			<li><a href="#">공지사항</a></li>
		</ul>
	</nav>
	</c:if>
	<c:if test="${data ne 'A'}">
	<nav id ="main_menu">
		<ul>
			<li><a href="#">소개</a></li>
			<li><a href="${pageContext.request.contextPath}/rsvList.do">예약2222</a></li>
			<li><a href="#">공지사항</a></li>
		</ul>
	</nav>
	</c:if> --%>
	<div class="dropmenu">
		<c:if test="${data eq 'A'}">
			<ul>
				<li><a href="#">소개</a></li>
				<li><a href="#" id="current">관리</a>
					<ul>
						<li><a href="${pageContext.request.contextPath}/roomList.do">객실관리</a></li>
						<li><a href="${pageContext.request.contextPath}/reservManageList.do">예약관리</a></li>
					</ul>
				</li>
				<li><a href="#">공지사항</a></li>
			</ul>
		</c:if>
		<c:if test="${data ne 'A'}">	
			<ul>
				<li><a href="#">소개</a></li>
				<li><a href="${pageContext.request.contextPath}/rsvList.do" id="current">예약</a></li>
				<li><a href="#">공지사항</a></li>
			</ul>
		</c:if>
	</div>
	
<!-- 	<div class="dropmenu">
	<ul>
		<li><a href="#">Home</a></li>
		<li><a href="#" id="current">main01</a>
		<ul>
			<li><a href="#">menu1</a></li>
			<li><a href="#">menu2</a></li>
			<li><a href="#">menu3</a></li>
		</ul>
		</li>
		<li><a href="#">main02</a></li>
	</ul>
	
	</div> -->
	
</header>
