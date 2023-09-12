<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소노러스호텔</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script>
	var index = 0;   //이미지에 접근하는 인덱스
	window.onload = function(){
	    slideShow();
	}
	
	function slideShow() {
	var i;
	var x = document.getElementsByClassName("slide1");  //slide1에 대한 dom 참조
	for (i = 0; i < x.length; i++) {
	   x[i].style.display = "none";   //처음에 전부 display를 none으로 한다.
	}
	index++;
	if (index > x.length) {
	    index = 1;  //인덱스가 초과되면 1로 변경
	}   
	x[index-1].style.display = "block";  //해당 인덱스는 block으로
	setTimeout(slideShow, 4000);   //함수를 4초마다 호출
	
	}
</script>
</head>
<body>
<%@ include file="header.jsp" %>

<section id="main">
	<section id = "main_image">
		<img class="slide1" src="${pageContext.request.contextPath}/images/hotel1.jpeg" width="100%">
		<img class="slide1" src="${pageContext.request.contextPath}/images/hotel2_bak.jpeg" width="100%">
		<img class="slide1" src="${pageContext.request.contextPath}/images/hotel3_bak.jpeg" width="100%">
	</section>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>
