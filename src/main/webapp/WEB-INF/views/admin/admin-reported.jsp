<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>신고 게시판</title>

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

                    <h1 class="member-name">신고된 게시글</h1>
        
                    <div class="list-wrapper">
                        <table class="list-table">                   
                            <thead>
                                <tr>
                                    <th>신고번호</th>
                                    <th>신고내용</th>
                                    <th>신고게시글</th>
                                    <th>신고자</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${empty reportList}">
                                        <tr>
                                            <th colspan="5">신고 목록이 존재하지 않습니다</th>
                                        </tr>
                                    </c:when>

                                    <c:otherwise>
                                        <c:forEach var="report" items="${reportList}">
                                            <tr>
                                                <td>${report.reportNo}</td>    
                                                <td>${report.reportContent}</td> 
                                                <td>
                                                    <a href="#">${board.boardNo}</a>
                                                </td>          
                                                <td>${member.memberNo}</td>      
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                            </tbody>
                        </table>
                    </div>
        
        
                    <div class="pagination-area">

                        <c:set var="url" value="reportList?cp="/>
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
        
                    <form action="#" method="get" id="memberSearch">
                        <select name="key">
                            <option value="rNo">신고번호</option>
                            <option value="mNo">신고 회원번호</option>
                            <option value="bNo">신고 게시글 번호</option>
                        </select>


                        <input type="text" name="query" placeholder="검색어를 입력해주세요.">
        
                        <button>검색</button>

                    </form>
            </section>

        </section>
    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
</body>
</html>