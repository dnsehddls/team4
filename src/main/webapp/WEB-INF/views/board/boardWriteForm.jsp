<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="boardName" value="${map.boardName}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 등록</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/board/boardWriteForm-style.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <script src="https://kit.fontawesome.com/296924b572.js" crossorigin="anonymous"></script>
</head>
<body>

    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <main>
        <div class="board-detail">

            <form action="write" enctype="multipart/form-data" method="POST" class="board-Write" onsubmit="return writeValidate()">
                
                <div class="title-box">
                    <h1 class="board-title">게시글 등록</h1>
                </div>

                <!-- 제목 -->
                <h2 class="board-title">
                    <input type="text" name="boardTitle" placeholder="글 제목">
                </h2>
    
                <h3 class="board-title" id="searchMap">
                    <input type="text" id="address" placeholder="주소" name="map" maxlength="50" readonly>
                    <button type="button" id="SearchMapBtn"  onclick = goPopup()>주소검색</button>
                </h3>
    
                <!-- 내용 -->
                <div class="board-content">
                    <textarea name="boardContent" placeholder="글 내용"></textarea>
                </div>
    
                <h3 class="board-title">
                    <input type="file" id="img1" name="1" accept="image/*">
                    <span class="delete-image">&times;</span>
                </h3>

                <div class="board-btn-area">
                    <button type="submit" id="writebtn">등록</button>
    
                    <c:if test="${param.mode == 'insert'}">
                        <button type="button" id="goToListBtn">목록으로</button>
                    </c:if>
                    
                    <c:if test="${param.mode == 'update'}">
                        <button type="button" onclick="location.href='${header.referer}'">이전으로</button>                           
                    </c:if>
    
    
                </div>
        
                <input type="hidden" name="mode" value="${param.mode}">

                <!-- 게시판 구분 -->
                <input type="hidden" name="type" value="${param.type}">
    
                <!-- 게시글 번호 -->
                <input type="hidden" name="no" value="${param.no}">
                
                <!-- 현재 페이지 -->
                <input type="hidden" name="cp" value="${param.cp}">
                
                <input type="hidden" name="deleteList" id="deleteList" value="">

            </div>    
              <input type="hidden" name="deleteList" id="deleteList" value="">
            </form>
         

    </main>

    <script>
        function goPopup(){
            // 주소검색을 수행할 팝업 페이지를 호출합니다.
            // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
            var pop = window.open("${contextPath}/board/searchMap","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
            
            // 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
            //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
        }


        function jusoCallBack(roadFullAddr){
            // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
            document.querySelectorAll("#address")
            document.form.roadFullAddr.value = roadFullAddr;
            
        }
    </script>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="${contextPath}/resources/js/board/board.js"></script>
    
    <script src="${contextPath}/resources/js/board/boardWriteForm.js"></script>
</body>
</html>