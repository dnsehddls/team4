<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쪽지 보내기</title>
    
    <link rel="stylesheet" href="${contextPath}/resources/css/message/side-menu.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/message/sendMessage-style.css">
    
</head>
<body>
    <section class="entire">
        <jsp:include page="/WEB-INF/views/message/sideMenu.jsp"></jsp:include>

        <section class="right-side">
    
            <h1 class="name">쪽지 보내기</h1>
            <form action="#" id="sendMessage-area" name="search-form" method="GET">
                <div class="search-area">
                    <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
                    <!-- <form action="findUser2"> -->
                        <!-- onkeypress="enterkey(event)" -->
                    <fieldset>
                            <span>받는사람</span>
                            <input type="search" name="memberNickname" id="receiver"  autocomplete="off" placeholder="닉네임을 입력하세요">
                            <button type="button" id="search-btn" class="fa-solid fa-magnifying-glass" onclick="btnClick()"></button>
                        </fieldset>
                    <!-- </form> -->
                </div>
            </form>
            

            <form action="sendData" name="data-form" method="GET" >
                <input type="hidden" name="receiveNo">
                <div id="middle">
                    <textarea name="inputMessage" id="inputMessage" cols="70" rows="10"></textarea>
                </div>
                
                <div id="bottom">
                    <div id="buttons">
                        <button type="submit" id="sendBtn" class="btns">보내기</button>
                    </div>
                </div>  
            </form>
    
    
        </section>
    </section>
    
    <script>
        const contextPath = "${contextPath}";
    </script>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/message/sendMessage.js"></script>
    <script src="https://kit.fontawesome.com/9de911222f.js" crossorigin="anonymous"></script>

</body>
</html>
