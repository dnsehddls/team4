<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 상세</title>

    <link rel="stylesheet" href="../../resources/css/board/boardDetail-style.css">
    <link rel="stylesheet" href="../../resources/css/board/reply-style.css">

    <script src="https://kit.fontawesome.com/296924b572.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
        <section class="board-detail">  
                <div class="title-box">
                    <h1 class="board-title">${boardDetail.boardtitle}</h1>
                </div>
                <div class="board-header">
                    <div class="board-writer">
                        <img src="../images/profile.png">
                        <span>${boardDetail.memberNickname}</span>
                    </div>
                    <div class="board-writeDate">
                        <span>${boardDetail.createDate}</span>                                                  
                    </div>
                    <div class="board-info">
                        <span>${boardDetail.readCount}</span>
                        <span>추천 : 12</span> <!-- 이거아직안함 -->
                    </div>
                </div>

                <div class="board-content">
                    <img src="../images/sample.jpg"><br>
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
                    <div class="reply-title">
                        <h3>댓글</h3>
                    </div>
                    <ul>
                        <jsp:include page="/WEB-INF/views/board/replyList.jsp"/>
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
    



</body>
</html>