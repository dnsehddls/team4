<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pagination" value="${map.pagination}" />


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
   <script>
        
    </script>
</head>
<body>
    
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <section class="entire">
        <jsp:include page="/WEB-INF/views/message/sideMenu.jsp"></jsp:include>
    
        <section class="right-side">
            
            <form action="messageList" method="GET" name="rMessageList">
            
                <c:if test="${param.t == 'r'}">
                    <h1 class="name">받은 쪽지함</h1>
                </c:if>
            
                <c:if test="${param.t == 's'}">
                    <h1 class="name">보낸 쪽지함</h1>
                </c:if>
                
                <div class="list-wrapper">
                    <table class="list-table">
                        <thead>
                            <c:choose>
                                <c:when test="${empty mList}">
                                    <th id="emptyM2" colspan="5">쪽지가 존재하지 않습니다.</th>
                                </c:when>

                                <c:otherwise>
                                    <c:if test="${param.t == 'r'}">
                                        <div class="top-buttons">
                                            <button id="delete-btn">삭제</button>
                                            <button id="reply-btn">답장</button>
                                        </div>
                                    </c:if>
                            
                                    <c:if test="${param.t == 's'}">
                                        <div class="top-buttons">
                                            <button id="delete-btn">삭제</button>
                                        </div>
                                    </c:if>
                                
                            

                                    <tr>
                                        <td>
                                            <input type="checkbox" name="data" onclick="checkAll1(this)">
                                        </td>
                                        <c:if test="${param.t == 'r'}">
                                            <th id="sender" class="r">보낸 사람</th>
                                            <th class="r">내용</th>
                                            <th class="r">날짜</th>
                                        </c:if>
                                        
                                        <c:if test="${param.t == 's'}">
                                            <th id="sender" class="s">받은 사람</th>
                                            <th class="s">내용</th>
                                            <th class="s">날짜</th>
                                            <th class="s">확인</th>
                                        </c:if>
                                    </tr>
                                    <c:forEach var="m" items="${mList}">
                                        <tr>
                                            <td>
                                                <input type="checkbox" class="checkbox" name="data" value="${m.memberNickname}">
                                            </td>
                                           
                                                <td>${m.memberNickname}</td>
                                            
                                        
                                            <td><a href="${contextPath}/messageContent?messageNo=${m.messageNo}&t=${param.t}" class="tda">${m.messageContent}</a></td>
                                            
                                            <td>${m.messageDate}</td>

                                            <c:if test="${param.t == 's'}">
                                                <td>${m.receiveDate}</td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </thead>
                    </table>
                        
                </div>
            </form>

            <c:if test="${param.t == 's' || param.t == 'r'}">
                <div id="pArea"></div>
            </c:if>
            <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
                <c:set var="url" value="list?c=${param.c}&cp="/>
    
    
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
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
<script src="${contextPath}/resources/js/message/rMessageList.js"></script>
</body>
</html>