<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 상세</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/board/boardDetail-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board/reply-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board/reply-test2-style.css">
    <script src="https://kit.fontawesome.com/296924b572.js" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <main>
        <section class="board-detail">  
                <div class="title-box">
                    <h1 class="board-title">${boardDetail.boardTitle}</h1>
                </div>
                <div class="board-header">
                    <div class="board-writer">
                        <img src="#">
                        <span>${boardDetail.memberNickname}</span>
                    </div>
                    <div class="board-writeDate">
                        <span>${boardDetail.createDate}</span>                                                  
                    </div>
                    <div class="board-info">
                        <span>${boardDetail.readCount}</span>
                        <span>${boardDetail.goodCount}</span> <!-- 이거아직안함 -->
                    </div>
                </div>

                <div class="board-content">
                    <img src="#"><br>
					${boardDetail.boardContent}
                </div>
    
                <div class="board-sub">
                    <span class="sub-location">
                        <a href="#">제임스 짐 휘트니스 IFC점</a>
                    </span>
                   
                    <p>
                        <span class="sub-scrap"> <a href="#">북마크</a></span>| <!-- 아직안함 -->
                        <span class="sub-declare"> <a href="#">신고하기</a></span>
                    </p>
                </div>
                <div class="board-btn-area">
    			<c:if test="${loginMember.memberNickname == boardDetail.memberNickname }">
                    <button id="updateBtn">수정</button>
                    <button id="deleteBtn">삭제</button>
    			</c:if>
                    <button id="goToListBtn">목록</button>
                </div>
	
                <div id="reply-area">
                    <div class="reply-title-main">
                        <h3>댓글</h3>
                    </div>
                    <ul id="reply-main">
                        <c:if test="${!empty rList}">
                           	<jsp:include page="/WEB-INF/views/board/replyList.jsp"/>
                        </c:if>
                        <c:if test="${empty rList}">
                            <h2>현재 댓글이 존재하지 않습니다.</h2>
                        </c:if>
                    </ul>
                    
                    <div class="reply-write-area">
                        <textarea id="replyContent"></textarea>
                        <button id="addReply">
                            댓글<br>
                            등록
                        </button>
                    </div>
                </div>
        </section>
    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
        const contextPath = "${contextPath}";
		const boardNo = "${boardDetail.boardNo}";
        console.log(contextPath);
        console.log(boardNo);
		const loginMember = "${sessionScope.loginMember.memberNo}"
        </script>
    <script src="${contextPath}/resources/js/board/board.js"></script>
	<script src="${contextPath}/resources/js/board/reply.js"></script>
</body>
</html>