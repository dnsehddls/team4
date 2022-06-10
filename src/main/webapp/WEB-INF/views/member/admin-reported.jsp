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
    
    <link rel="stylesheet" href="${contextPath}/resources/css/memberList.css">
</head>
<body>

    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="adminPage-content">


            <jsp:inclue page="/WEB-INF/views/memeber/admin-sideMenu.jsp"/>

            <section class="memberList">
                <section class="member-list">

                    <h1 class="member-name">신고된 게시글</h1>
        
                    <div class="list-wrapper">
                        <table class="list-table">                   
                            <thead>
                                <tr>
                                    <th>글번호</th>
                                    <th>글제목</th>
                                    <th>작성일</th>
                                    <th>신고 사유</th>
                                    <th>신고 회원</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>
                                        <a href="#">맛없어요</a>
                                    </td>
                                    <td>2022-5-23</td>
                                    <td>욕설</td>
                                    <td>user01</td>
                                </tr>

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
        
                    <form action="#" method="get" id="memberSearch">
                        <select name="key">
                            <option value="id">아이디</option>
                            <option value="no">회원번호</option>
                            <option value="joinDate">가입 날짜</option>
                            <option value="secession">탈퇴여부</option>
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