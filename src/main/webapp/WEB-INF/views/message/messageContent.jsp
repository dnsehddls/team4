<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
    // console.log("mContent : " + $(mContent.messageNo));
    // console.log("param.t : " + $(param.t));
</script>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쪽지내용</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/message/side-menu.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/message/messageContent-style.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <section class="entire">

        <jsp:include page="/WEB-INF/views/message/sideMenu.jsp"></jsp:include>

        <section class="right-side">
            <c:if test="${param.t == 's'}">
            <div class="topdiv">받는 사람<span>${mContent.memberNickname}</span></div>
            <div class="topdiv">보낸 날짜<span>${mContent.messageDate}</span></div>
            </c:if>

            <c:if test="${param.t == 'r'}">
            <div class="topdiv">보낸 사람<span>${mContent.memberNickname}</span></div>
            <div class="topdiv">받은 날짜<span>${mContent.messageDate}</span></div>
            </c:if>

            <textarea name="" id="textarae" cols="70" rows="10" readonly>${mContent.messageContent}</textarea>

            <section class="bottom">
                <div class="btn-area">
                    <button id="goToListBtn" onclick="history.back()">목록으로</button>
                </div>
            </section>
        </section>
    </section>
    
        <!-- <script src="${contextPath}/resources/js/messageContent.js"></script> -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

</body>
</html>