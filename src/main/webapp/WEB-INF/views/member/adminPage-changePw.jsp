<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 변경(관리자)</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/adminPage.css">

</head>
<body>

    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp"/>


        <!-- 관리자 페이지  -->
        <section class="adminPage-content">
            
            <!-- 왼쪽 사이드 메뉴 -->
            <jsp:include page="/WEB-INF/views/member/admin-sideMenu.jsp"/>

            <section class="adminPage-main">

                <h1 class="adminPage-title">비밀번호 변경</h1>
                <span class="adminPage-explanation">현재 비밀번호가 일치하는 경우 새 비밀번호로 변경할 수 있습니다.</span>

                <form action="changePw" method="POST" name="changePw-form" onsubmit="return changePwValidateValidate()">

                    <div class="adminPage-row">
                        <label>현재 비밀번호</label>
                        <input type="password" name="currentPw" maxlength="30">
                    </div>

                    <div class="adminPage-row">
                        <label>새 비밀번호</label>
                        <input type="password" name="newPw" maxlength="30">
                    </div>

                    <div class="adminPage-row">
                        <label>새 비밀번호 확인</label>
                        <input type="password" name="newPwConfirm" maxlength="30">
                    </div>

                    <button id="info-update-btn">변경하기</button>

                </form>

            </section>




        </section>


    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    

    <script src="${contextPath}/resources/js/member/admin-info.js"></script>
</body>
</html>