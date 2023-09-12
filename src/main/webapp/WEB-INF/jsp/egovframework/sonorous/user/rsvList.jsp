<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<link rel="stylesheet" type="text/css" href="../css/main.css" />

<%@ include file="../header.jsp" %>	

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title.sample" /></title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link href="/js/calendar/css/datepicker.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	// 운영기간 달력
		$(function(){
			$("#searchCheckIn").datepicker({
				dateFormat: 'yy/mm/dd',
				closeText: '닫기',
				prevText: '이전 달',
				nextText: '다음 달',
				currentText: '오늘',
				yearRange: 'c-100:c+10',
				monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				dayNames: ['일','월','화','수','목','금','토'],
				dayNamesShort: ['일','월','화','수','목','금','토'],
				dayNamesMin: ['일','월','화','수','목','금','토'],
				weekHeader: 'Wk',
				firstDay: 0,
				isRTL: false,
				buttonImageOnly: true, //이미지표시
				showMonthAfterYear: true,
				showButtonPanel: true, 
				changeMonth: true, 
				changeYear: true,
				minDate: 0
			});
			
			$("#searchCheckOut").datepicker({
				dateFormat: 'yy/mm/dd',
				closeText: '닫기',
				prevText: '이전 달',
				nextText: '다음 달',
				currentText: '오늘',
				yearRange: 'c-100:c+10',
				monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				dayNames: ['일','월','화','수','목','금','토'],
				dayNamesShort: ['일','월','화','수','목','금','토'],
				dayNamesMin: ['일','월','화','수','목','금','토'],
				weekHeader: 'Wk',
				firstDay: 0,
				isRTL: false,
				buttonImageOnly: true, //이미지표시
				showMonthAfterYear: true,
				showButtonPanel: true, 
				changeMonth: true, 
				changeYear: true,
				minDate: 0,
				onSelect: function(dateText){
					console.log(dateText);
					var frm = document.listForm;
					
					if(frm.searchRoomType.value==""){
						alert("객실 타입을 선택하세요.");
						return false;
					
					}else if(frm.searchCheckIn.value==""){
						alert("체크인 날짜를 선택하세요.");
						return false;
					
					}else if(frm.searchCheckOut.value==""){
						alert("체크아웃 날짜를 선택하세요.");
						return false;
					
					}else{
						console.log("searchRoomType::"+frm.searchRoomType.value);
						
						$.ajax({
							type : 'post',
							url : 'getPeopleNum.do',
							data : {
								searchRoomType : frm.searchRoomType.value,
								searchCheckIn : frm.searchCheckIn.value, 
								searchCheckOut : frm.searchCheckOut.value
							},
							success:function(data, status){
								console.log(data);
								$("#searchPeople").val(data);
							},
							error:function(error){
								console.log(error);
								console.log(error.status);
							}
							
						});//ajax end
					
					}
					
					
					
				}
			});
			
		});
	
		//검색
		var goSearch = function(){
			alert($("#searchRoomType").val());
			$("#listForm").attr('action', "/rsvList.do").submit();
		}
	
		//상세보기
        var goView = function(id){
        	alert(id);
        	location.href="/rsvView.do?roomId="+id;
        }
    </script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
    <form:form commandName="listVO" id="listForm" name="listForm" method="post">
        <input type="hidden" name="selectedId" />
        <div id="content_pop">
        	<!-- 타이틀 -->
        	<div id="title">
        		<ul>
        			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>예약목록</li>
        		</ul>
        	</div>
        	<!-- // 타이틀 -->
        	<%-- <div id="search">
        		<ul>
        			<li>
        			    <label for="searchCondition" style="visibility:hidden;"><spring:message code="search.choose" /></label>
        				<form:select path="searchCondition" cssClass="use">
        					<form:option value="1" label="Name" />
        					<form:option value="0" label="ID" />
        				</form:select>
        			</li>
        			<li><label for="searchKeyword" style="visibility:hidden;display:none;"><spring:message code="search.keyword" /></label>
                        <form:input path="searchKeyword" cssClass="txt"/>
                    </li>
        			<li>
        	            <span class="btn_blue_l">
        	                <a href="javascript:fn_egov_selectList();"><spring:message code="button.search" /></a>
        	                <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
        	            </span>
        	        </li>
                </ul>
        	</div> --%>
        	
        	<div id="search">
        		<ul>
        			<li>객실</li>
        			<li>
        				<label for="searchRoomType"></label>
        				<select name="searchRoomType" id="searchRoomType">
        					<option value="" title="전체">--선택--</option>
        					<c:forEach var="room" items="${roomTypeList}">
        						<option value="${room.roomId}" title="${room.roomName}" <c:if test="${room.roomId eq searchInfo.searchRoomType}">selected</c:if>><c:out value="${room.roomName}" /></option>
        					</c:forEach>
        				</select>
        			</li>
        			<li>체크인</li>
        			<li>
        				<label for="searchCheckIn"></label>
        				<input type="text" id="searchCheckIn" name="searchCheckIn" value="${searchInfo.searchCheckIn}" readonly />
        			</li>
        			<li>체크아웃</li>
        			<li>
        				<label for="searchCheckOut"></label>
        				<input type="text" id="searchCheckOut" name="searchCheckOut" value="${searchInfo.searchCheckOut}" readonly/>
        			</li>
        			<li>인원수</li>
        			<li>
        				<label for="searchPeople"></label>
        				<input type="number" id="searchPeople" name="searchPeople" value="${searchInfo.searchPeople}" />
        			</li>
        			<li><a href="javascript:goSearch()">검색</a></li>
        		</ul>
        	</div>
        	
        	<!-- List -->
        	<div id="table">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블">
        			<caption style="visibility:hidden">카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블</caption>
        			<colgroup>
        				<col width="25%"/>
        				<col width="35%"/>
        				<col width="10%"/>
        				<col width="20%"/>
        				<col width="20%"/>
        			</colgroup>
        			<tr>
        				<th align="center">이미지</th>
        				<th align="center">객실명</th>
        				<th align="center">객실크기</th>
        				<th align="center">침대타입</th>
        				<th align="center"></th>
        			</tr>
        			<c:forEach var="room" items="${roomList}" varStatus="status">
            			<tr>
            				<td align="center" class="listtd">
							<img class="img-thumbnail" style="width: 100px;  height:100px" src="/upload/${room.imgPath}/${room.imgNm}"/></td>
            				<td align="center" class="listtd"><a href="javascript:goView('<c:out value="${room.roomId}"/>')"><c:out value="${room.roomName}"/></a></td>
            				<td align="center" class="listtd"><c:out value="${room.roomSize}"/>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${room.bedType}"/>&nbsp;</td>
            				<td align="center" class="listtd">
	            				<span class="btn_blue_l"><a href="/rsvView.do?roomId=${room.roomId}&searchRoomType=${searchInfo.searchRoomType}&searchCheckIn=${searchInfo.searchCheckIn}&searchCheckOut=${searchInfo.searchCheckOut}&searchPeople=${searchInfo.searchPeople}">상세보기</a></span>&nbsp;
		                    </td>
            			</tr>
        			</c:forEach>
        		</table>
        	</div>
        	<!-- /List -->
        	<div id="paging">
        		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
        		<form:hidden path="pageIndex" />
        	</div>
        </div>
    </form:form>
</body>
</html>
