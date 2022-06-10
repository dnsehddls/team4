<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원목록 관리</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
    
    <link rel="stylesheet" href="${contextPath}/resources/css/memberList.css">

</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="adminPage-content">
           
            <jsp:inclue page="/WEB-INF/views/memeber/admin-sideMenu.jsp"/>

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
                                    <th>게시글 작성 수</th>
                                    <th>탈퇴여부</th>
                                </tr>
                            </thead>

                            <tbody id="memberList">
                                <!-- <c:choose>
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
                                                <td>${member.게시글 작성 수}</td>
                                                <td>${memeber.secessionFl}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose> -->
                            </tbody>

                        </table>
                    </div>
        
        
                    <div class="pagination-area">
                        <ul class="pagination">
                            <li><a href="#">&lt;&lt;</a></li>
                            <li><a href="#">&lt;</a></li>
                            
                            <li><a class="current">1</a></li>
        
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>       
        
                            <li><a href="#">&gt;</a></li>
                            <li><a href="#">&gt;&gt;</a></li>
                        </ul>
                    </div>
        
                    <div id="memberSearch">
                        <input type="text" name="query" id="in1" placeholder="이메일을 입력해주세요.">
    
                         <button id="select1">검색</button>
                    </div>
            </section>

        </section>
    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>


    <script src="${contextPath}/resources/js/member/admin-memberList.js"></script>
</body>
</html>