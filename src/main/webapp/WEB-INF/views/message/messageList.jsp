<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pagination" value="${map.pagination}" />
<c:set var="memberNickname" value="${map.memberNickname}" />
<c:set var="memberNickname" value="${map.messageContent}" />
<c:set var="memberNickname" value="${map.messageDate}" />
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
   <script>
        console.log("${mList.size()}");
        
    </script>
</head>
<body>
    
    <section class="entire">
    
	<jsp:include page="/WEB-INF/views/message/sideMenu.jsp"></jsp:include>

        <section class="right-side">
            
            <form action="messageList" method="POST" name="rMessageList">
            
                <c:if test="${param.t == 'r'}">
                    <h1 class="name">받은 쪽지함</h1>
                </c:if>
            
                <c:if test="${param.t == 's'}">
                    <h1 class="name">보낸 쪽지함</h1>
                </c:if>
                
                
                
                <div class="list-wrapper">
                    <table class="list-table">
                            <thead>
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
                            </thead>
                            <tbody>
                                <input type="hidden" name="messageNo" value="${message.messageNo}">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="data" >
                                    </td>
                                    <td>${message.memberNickname}</td>
                                    <td><a href="#">${message.messageContent}</a></td>
                                    <td>${message.messageDate}</td>
                                    <c:if test="${param.t == 's'}">
                                        <td>${message.receiveDate}</td>
                                    </c:if>
                                </tr>
                                
                                <tr>
                                    <td>
                                        <input type="checkbox" name="data">
                                    </td>
                                    <td>성태킴</td>
                                    <td><a href="${contextPath}/messageDetail"}>내asd용</a></td>
                                    <td>2022년 05월 01일</td>
                                    <c:if test="${param.t == 's'}">
                                        <td>읽지않음</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="checkbox" name="data">
                                    </td>
                                    <td>성태킴</td>
                                    <td><a href="">내용</a></td>
                                    <td>2022년 05월 01일</td>
                                    <c:if test="${param.t == 's'}">
                                        <td>2022년 06월 21일</td>
                                    </c:if>
                                </tr>
                            </tbody>
                        </table>

                        <table id="emptyM1">
                            <c:choose>
                                <c:when test="${empty messagList}">
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
                            
                            
                                    <thead>
                                        <tr>
                                            <td>
                                                <input type="hidden" name="messageNo" value="${message.messageNo}">
                                                <input type="checkbox" name="data" onclick="checkAll1(this)">
                                            </td>
                                            <c:if test="${param.t == 'r'}">
                                                <th id="sender">보낸 사람</th>
                                            </c:if>
                                        </td>
                                            <c:if test="${param.t == 's'}">
                                            <th id="sender">받은 사람</th>
                                            </c:if>
                                            <th>내용</th>
                                            <th>날짜</th>
                                            <c:if test="${param.t == 's'}">
                                            <th>수신확인</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    
                                    
                                    <tbody>
                                        <input type="hidden" name="messageNo" value="${message.messageNo}">
                                        <tr>
                                            <td>
                                                <input type="checkbox" name="checkbox">
                                            </td>
                                            <td>${member.memberNickname}</td>
                                            <td><a href="#">${message.messageContent}</a></td>
                                            <td>${message.messageDate}</td>
                                            <c:if test="${param.t == 's'}">
                                            <td>${message.receiveDate}</td>
                                            </c:if>
                                        </tr>
                                        
                                        <tr>
                                            <td>
                                                <input type="checkbox" name="data">
                                            </td>
                                            <td>성태킴</td>
                                            <td><a href="${contextPath}/messageDetail"}>내용</a></td>
                                            <td>2022년 05월 01일</td>
                                            <c:if test="${param.t == 's'}">
                                                <td>읽지않음</td>
                                            </c:if>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input type="checkbox" name="data">
                                            </td>
                                            <td>성태킴</td>
                                            <td><a href="">내용</a></td>
                                            <td>2022년 05월 01일</td>
                                            <c:if test="${param.t == 's'}">
                                                <td>2022년 06월 21일</td>
                                            </c:if>
                                        </tr>
                                    </tbody>
                                </c:otherwise>
                            </c:choose>
                        </table>
                
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