<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Content</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member/myContent.css">

</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <main>

        <section class="myPage-content" style="margin: 50px 155px;">

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
                    <span>내 글 관리</span>
                </h2>
        
                <!-- + 선택 시 버튼색 변화 -->
                <button type="submit" class="index" id="content"><a href="myContent">내가 쓴 게시글</a></button>
                <button type="submit" class="index" id="reply" style="background-color : #eee;"><a href="myContent-reply">내가 쓴 댓글</a></button>
                <div class="line">
        
                </div>
                
                <div>
                    <table name="myContent" class="tb">
        
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>카테고리</th>
                                <th>제목</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                
                        <tbody>
                            <c:choose>
                                <c:when test="${empty myContentList}">
                                    <!-- 내 게시글 조회결과가 비어있다면  -->


                                </c:when>

                            </c:choose>
            
                            <!-- <tr>
                                <td>1</td>
                                <td>운동게시판</td>
                                <td>게시글 1입니다.</td>
                                <td>2022-04-28</td>
                            </tr> -->
                        </tbody>
                    </table>
                </div>
                    
                </form>
        
                <div class="page">
                    <div>&lt;</div>
                    <div>1</div>
                    <div>2</div>
                    <div>3</div>
                    <div>4</div>
                    <div>&gt;</div>
                </div>
                <div class="line"></div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/member/myContent.js"></script>
    
</body>
</html>