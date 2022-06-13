<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>계정 찾기</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member/find.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <main>
        <section class="myPage-content">

            <section class="myPage-main">
                <h2 class="title">
                    <span>계정 찾기</span>
                </h2>

                <div class="line"></div>
                

                <div class="find-main">

                    <span id="emailMessage">
                        이메일로 요청하신 정보가 전송되었습니다.<br>
                        이메일을 확인해주세요.
                    </span>

                </div>

                <div class="btnArea">
                    <button id="mainBtn"><a href="${contextPath}">메인페이지로</a></button>
                </div>
                


                <div class="line"></div>

                </section>

        </section>

    </main>

    
    
</body>
</html>