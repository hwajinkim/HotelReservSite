<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<aside>
	<article id ="notice">
		<div id="notice_title">마이페이지</div>
		<c:set var="data" value="${fn:substring(LOGIN_USER.mId,0,1)}"/>
		<ul>
			<li><a href="${pageContext.request.contextPath}/memberInfo.do">회원정보</a></li>
			<c:if test="${data ne 'A'}">
				<li><a href="${pageContext.request.contextPath}/reservInfo.do">예약정보</a></li>
			</c:if>
		</ul>	
	</article>	
</aside>