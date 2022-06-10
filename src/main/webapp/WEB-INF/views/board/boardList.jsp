<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>운동 공유 게시판</title>



    <link rel="stylesheet" href="${contextPath}/resources/css/board/boardList-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <script src="https://kit.fontawesome.com/296924b572.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <section class="board-list">

            <h1 class="board-name">운동 공유 게시판</h1>
            <span class="sub-name">운동 정보를 공유하는 게시판입니다.</span>
            <div class="list-wrapper">
                <table class="list-table">
                    
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                            <th>추천수</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>   
                                <a href="#">10번째 게시글</a>
                            </td>
                            <td>유저일</td>
                            <td>2022-05-23</td>
                            <td>50</td>
                            <td>10</td>
                        </tr>
                    </tbody>
                </table>
            </div>


            <div class="btn-area">
                <button id="insertBtn">글쓰기</button>
            </div>

            
            <div class="pagination-area">

                <ul class="pagination">
                    <li><a href="#">&lt;&lt;</a></li>
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
                    <li><a href="#">&gt;&gt;</a></li>
                </ul>
            </div>


            <form action="#" method="get" id="boardSearch">

                <select name="key">
                    <option value="t">제목</option>
                    <option value="c">내용</option>
                    <option value="tc">제목+내용</option>
                    <option value="w">작성자</option>
                </select>

                <input type="text" name="query" placeholder="검색어를 입력해주세요.">

                <button>검색</button>
            </form>


        </section>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </main>
    




</body>
</html>