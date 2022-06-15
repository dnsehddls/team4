<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contentList" value="${map.contentList}"/>
<c:set var="pagination" value="${map.pagination}"/>

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
                <h2 class="title" style="margin-bottom : 5px;">
                    <span>내 글 관리</span>
                </h2>
                <h5 style="margin-top : 5px;">내가 쓴 게시글을 조회할 수 있습니다.</h5>
        
                <!-- + 선택 시 버튼색 변화 -->
                <button type="submit" class="index" id="content"><a href="myContent">내가 쓴 게시글</a></button>
                <button type="submit" class="index" id="reply" style="background-color : #eee;"><a href="myContent-reply">내가 쓴 댓글</a></button>
                <div class="line">
        
                </div>
                
                <div>
                    <table name="myContent" class="tb">
        
                        <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>카테고리</th>
                                <th>제목</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                
                        <tbody>
                            <c:choose>
                                <c:when test="${empty contentList}">
                                    <!-- 내 게시글 조회결과가 비어있다면  -->
                                    <tr>
                                        <th colspan="4">작성한 게시글이 없습니다.</th>
                                    </tr>
                                </c:when>

                                <c:otherwise>
                                    <c:forEach var="content" items="${contentList}">
                                        <tr>
                                            <td>${content.boardNo}</td>
                                            <td><a href="#">${content.boardName}</a></td>
                                            <td>${content.boardTitle}</td>
                                            <td>${content.createDate}</td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                    
                </form>
        
                <div class="page">

                    <!--페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
                    <c:set var="url" value="myContent?&cp="/>
    
                    <ul class="page">
                        <!-- 첫페이지로 이동 -->
                        <li><a href="${url}1${sURL}">&lt;&lt;</a></li> 
                        
                        <!-- 이전 목록 마지막 번호로 이동 -->
                        <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>
    
                        <!-- 범위가 정해진 일반 for문 -->
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
    
                        <!-- 다음 목록 시작 번호로 이동 -->
                        <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>
    
                        <!-- 끝 페이지로 이동 -->
                        <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
                    </ul>
                </div>

                <div class="line"></div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    
</body>
</html>