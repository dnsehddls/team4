<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bookmark</title>
    
    <link rel="stylesheet" href="${contextPath}/resources/css/member/bookmark.css">

</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <main>
        <section class="myPage-content">

            <section class="left-side">
                <ul class="list-group">
                    <li><a href="info">내 정보</a></li>
                    <li><a href="myContent">내 글 관리</a></li>
                    <li><a href="like">좋아요 관리</a></li>
                    <li><a href="bookmark">북마크 관리</a></li>
                    <li><a href="secession">회원 탈퇴</a></li>
                    <li><a href="#">쪽지</a></li>
                </ul>
            </section>
            
            <section class="myPage-main">
                <h2 class="title">
                    <span>북마크 관리</span>
                </h2>
        
                <div class="line">
        
                </div>
                
                <table name="myContent" class="tb">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>카테고리</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th></th>
                        </tr>
                    </thead>
            
                    <tbody>
                        <tr>
                            <td>10</td>
                            <td>자유게시판</td>
                            <td>게시글 10입니다.</td>
                            <td>user9</td>
                            <td>2022-05-06</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>9</td>
                            <td>자유게시판</td>
                            <td>게시글 9입니다.</td>
                            <td>유저팔</td>
                            <td>2022-05-06</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>8</td>
                            <td>자유게시판</td>
                            <td>게시글 8입니다.</td>
                            <td>유저7</td>
                            <td>2022-05-04</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>7</td>
                            <td>자유게시판</td>
                            <td>게시글 7입니다.</td>
                            <td>user6</td>
                            <td>2022-05-03</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>6</td>
                            <td>자유게시판</td>
                            <td>게시글 6입니다.</td>
                            <td>유저오</td>
                            <td>2022-05-03</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>5</td>
                            <td>운동게시판</td>
                            <td>게시글 5입니다.</td>
                            <td>유저4</td>
                            <td>2022-05-02</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>4</td>
                            <td>운동게시판</td>
                            <td>게시글 4입니다.</td>
                            <td>user3</td>
                            <td>2022-05-02</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>3</td>
                            <td>운동게시판</td>
                            <td>게시글 3입니다.</td>
                            <td>유저이</td>
                            <td>2022-05-02</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>2</td>
                            <td>운동게시판</td>
                            <td>게시글 2입니다.</td>
                            <td>유저1</td>
                            <td>2022-05-01</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
        
                        <tr>
                            <td>1</td>
                            <td>운동게시판</td>
                            <td>게시글 1입니다.</td>
                            <td>user1</td>
                            <td>2022-05-01</td>
                            <td>
                                <input type="checkbox">
                            </td>
                        </tr>
                    </tbody>
                </table>

                <div class="btn-area">
                    <button type="submit" id="deleteBtn">삭제</button>
                </div>
        
                
                <div class="pagination-area">
                    <ul class="pagination">
                        <li><a href="#">&lt;</a></li>
                        <li><a class="current">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">6</a></li>
                        <li><a href="#">7</a></li>
                        <li><a href="#">8</a></li>
                        <li><a href="#">9</a></li>
                        <li><a href="#">10</a></li>
                        <li><a href="#">&gt;</a></li>
                    </ul>
                </div>
                
                <div class="line"></div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- js -->
    <script src="${contextPath}/resources/js/member/bookmark.js"></script>
    
</body>
</html>