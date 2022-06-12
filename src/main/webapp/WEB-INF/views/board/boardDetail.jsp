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
                    <h1 class="board-title">게시글 제목</h1>
                </div>
    
                <div class="board-header">
                    <div class="board-writer">
                        <img src="../images/profile.png">
                        <span>닉네임</span>
                    </div>
    
                    <div class="board-writeDate">
                        <span> 2022년 05월 26일 10:23:30 </span>                                                  
                    </div>
    
                    <div class="board-info">
                        <span>조회 : 58</span>
                        <span>추천 : 12</span>
                    </div>
                </div>
    

                <div class="board-content">
                    <img src="../images/sample.jpg"><br>
                    내용입니다.<br>
                    내용입니다.<br>
                    내용입니다.<br>
                    내용입니다.<br>
                    내용입니다.<br>
                    내용입니다.<br>
                </div>
    
                <div class="board-sub">
                    <span class="sub-location">
                        <a href="#">제임스 짐 휘트니스 IFC점</a>
                    </span>
                   
                    <p>
                        <span class="sub-scrap"> <a href="#">게시글 스크랩</a></span>|
                        <span class="sub-declare"> <a href="#">신고하기</a></span>
                    </p>
                </div>
    
                <div class="board-btn-area">
                    <button id="updateBtn">수정</button>
                    <button id="deleteBtn">삭제</button>
                    <button id="goToListBtn">목록</button>
                </div>

                

                <div id="reply-area">
                    <div class="reply-title">
                        <h3>댓글</h3>
                    </div>

                    <div class="reply-list-area">       
                        <ul id="reply-list">
                            <li class="reply-row">
                                <p class="reply-writer">
                                    <img src="../images/profile.png">
                                    <span>닉네임</span>
                                    <span class="reply-date">작성일</span>
                                </p>
                                
                                <p class="reply-content">내용</p>
            
                                <c:if test="${loginMember.memberNo == reply.memberNo}">
                                    <div class="reply-btn-area">
            
                                        <button>수정</button>     
            
                                        <button>삭제</button>
                                    </div>
                                </c:if>
                            </li>
                        </ul>
                    </div>
                    
                
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