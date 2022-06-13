<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="memberList" value="${map.memberList}"/>
<c:set var="pagination" value="${map.pagination}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원목록 관리</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
    
    <link rel="stylesheet" href="${contextPath}/resources/css/admin/memberList.css">

</head>
<body>
    
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <main>

        <section class="adminPage-content">
           
            <jsp:include page="/WEB-INF/views/admin/admin-sideMenu.jsp"/>

            <section class="memberList">
                <section class="member-list">

                    <h1 class="member-name">회원목록 조회</h1>
        
                    <div class="list-wrapper">
                        <table class="list-table">                   
                            <thead>
                                <tr>
                                    <th>회원번호</th>
                                    <th>아이디</th>
                                    <th>가입날짜</th>
                                    <th>탈퇴여부</th>
                                </tr>
                            </thead>

                            <tbody id="memberList">
                                <c:choose>
                                    <c:when test="${empty memberList}">
                                        <tr>
                                            <th colspan="5">회원 목록이 존재하지 않습니다</th>
                                        </tr>
                                    </c:when>

                                    <c:otherwise>
                                        <c:forEach var="member" items="${memberList}">
                                            <tr>
                                                <td>${member.memberNo}</td>    
                                                <td>
                                                    <a href="#">${member.memberId}</a>
                                                </td>
                                                <td>${member.enrollDate}</td>            
                                                <td>${member.secessionFl}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>

                        </table>
                    </div>
        
        
                    <div class="pagination-area">

                        <c:set var="url" value="list?cp="/>
                        <ul class="pagination">
                            <!-- 첫 페이지로 이동 -->
                            <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
        
                            <!-- 이전 목록 마지막 번호로 이동 -->
                            <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>
                            
        
                            <!-- 범위가 정해진 일반 for문 사용 -->
                            <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
        
                                <c:choose>
                                    <c:when test="${i == pagination.currentPage}">
                                        <li><a class="current">${i}</a></li>
                                    </c:when>
        
                                    <c:otherwise>
                                        <li><a href="${url}${i}${sURL}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
        
                            </c:forEach>
        
                            <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>
                            <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
                        </ul>
                    </div>

                    <form action="memberList" method="get">

                        <div id="memberSearch">
                            <input type="text" name="query" id="in1" placeholder="이메일을 입력해주세요.">
        
                             <button id="select1">검색</button>
                        </div>

                    </form>
        
            </section>

        </section>
    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>