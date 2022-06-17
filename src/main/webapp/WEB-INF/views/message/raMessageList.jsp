<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pagination" value="${map.pagination}" />
<c:set var="memberNickname" value="${map.memberNickname}" />
<c:set var="rMessageList" value="${map.rmessageList}" />


<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>받은 쪽지함</title>
   <link rel="stylesheet" href="">
   <link rel="stylesheet" href="${contextPath}/resources/css/message/side-menu.css">
   <link rel="stylesheet" href="${contextPath}/resources/css/message/messageList-style.css">

</head>
<body>

    <section class="entire">
    
	<jsp:include page="/WEB-INF/views/message/sideMenu.jsp"></jsp:include>

        <section class="right-side">

            <form action="messageList" method="POST" name="rMessageList">
            	<%
            	if("쪽지구분".equals('0')) {
            	%>
                	<h1 class="name">받은 쪽지함</h1>
                <% } else { %>
                	<h1 class="name">보낸 쪽지함</h1>
                <% } %>
                <div class="list-wrapper">
                    <table class="list-table">
                        <%-- <c:choose>
                            <c:when test="${empty rMessagList}">
                                <th colspan="5">쪽지가 존재하지 않습니다.</th>
                            </c:when>

                            <c:otherwise>
                            <div class="top-buttons">
                                <button id="delete-btn">삭제</button>
                                <button id="reply-btn" oncclick="">답장</button>
                            </div>

                            <thead>
                                <tr>
                                    <td>
                                        <input type="checkbox" id="checkAll" onclick="checkAll(this)">
                                    </td>
                                    <th id="sender">보낸 사람</th>
                                    <th>내용</th>
                                    <th>날짜</th>
                                </tr>
                            </thead>
            
                            <tbody>

                                <tr>
                                    <input type="hidden" name="messageNo" value="${message.messageNo}">
                                    <td>
                                        <input type="checkbox" name="checkbox">
                                    </td>
                                    <td>${member.memberNickname}</td>
                                    <td><a href="#">${message.messageContent}</a></td>
                                    <td>${message.messageDate}</td>
                                </tr>
                                
                                </tr>
                            </tbody>
                            </c:otherwise>
                        </c:choose> --%>
                        
                     		
                            <div class="top-buttons">
                                <button id="delete-btn">삭제</button>
                                <button id="reply-btn">답장</button>
                            </div>
                          
                            <div class="top-buttons">
                                <button id="delete-btn">삭제</button>
                            </div>
                            
                            
							<%
			            	if("쪽지구분".equals('0')) {
			            	%>
                            <thead>
                                <tr>
                                    <input type="hidden" name="messageNo" value="${message.messageNo}">
                                    <td>
                                        <input type="checkbox" name="data" onclick="checkAll1(this)">
                                    </td>
                                    <th id="sender">보낸 사람</th>
                                    <th>내용</th>
                                    <th>날짜</th>
                                </tr>
                            </thead>
                            
            				<% } else { %>
                            
                            <thead>
                                <tr>
                                    <td>
                                        <input type="checkbox" name="data" onclick="checkAll1(this)">
                                    </td>
                                    <th id="sender">받은 사람</th>
                                    <th>내용</th>
                                    <th>날짜</th>
                                </tr>
                            </thead>
            				<% } %>
            				
                            <tbody>

                                    <input type="hidden" name="messageNo" value="${message.messageNo}">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="checkbox">
                                    </td>
                                    <td>${member.memberNickname}</td>
                                    <td><a href="#">${message.messageContent}</a></td>
                                    <td>${message.messageDate}</td>
                                </tr>
                            </tbody>
                           
                    </table>
                </div>
            </form>



            <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
                <c:set var="url" value="list?type=${param.type}&cp="/>
    
    
                <ul class="pagination">
                    <!-- 첫 페이지로 이동 -->
                    <li><a href="${url}1">&lt;&lt;</a></li>
    
                    <!-- 이전 목록 마지막 번호로 이동 -->
                    <li><a href="${url}${pagination.prevPage}">&lt;</a></li>
    
                    <!-- 범위가 정해진 일반 for문 사용 -->
                    <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
    
                        <c:choose>
                            <c:when test="${i == pagination.currentPage}">
                                <li><a class="current">${i}</a></li>
                            </c:when>
    
                            <c:otherwise>
                                <li><a href="${url}${i}">${i}</a></li>        
                            </c:otherwise>
                        </c:choose>
    
                    </c:forEach>
                    
                    <!-- 다음 목록 시작 번호로 이동 -->
                    <li><a href="${url}${pagination.nextPage}">&gt;</a></li>
    
                    <!-- 끝 페이지로 이동 -->
                    <li><a href="${url}${pagination.maxPage}">&gt;&gt;</a></li>
    
                </ul>
            </div>

        </section>
    </section>
   
    <script>
        const contextPath = "${contextPath}";
    </script>

    <script src="${contextPath}/resources/js/message/rMessageList.js"></script>
    			 
</body>
</html>