<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
    <section class="entire">

            	<jsp:include page="/WEB-INF/views/message/sideMenu.jsp"></jsp:include>

        <section class="right-side">
            <div>받는 사람<span>누구누구</span></div>
            <div>보낸 날짜<span>2010년 01월 05일</span></div>
            
            <div>보낸 사람<span>누구누구</span></div>
            <div>받은 날짜<span>2010년 01월 05일</span></div>
            <div>내용</div>
            <textarea name="" id="" cols="70" rows="10"></textarea>

            <section class="bottom">
                <div class="btn-area">
                    <button id="goToListBtn">목록으로</button>
                </div>
            </section>
        </section>
    </section>
    
        <script src="${contextPath}/resources/js/messageContent.js"></script>
    
</body>
</html>