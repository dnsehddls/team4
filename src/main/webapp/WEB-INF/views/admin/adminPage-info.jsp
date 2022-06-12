<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/admin/adminPage.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
</head>
<body>

    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="adminPage-content">
            
            <!-- 왼쪽 사이드 메뉴 -->
            <jsp:include page="/WEB-INF/views/admin/admin-sideMenu.jsp"/>


            <section class="adminPage-main">

                <h1 class="adminPage-title">관리자 정보</h1>
                <span class="adminPage-explanation">원하는 관리자 정보를 수정할 수 있습니다</span>

                <form action="info" method="POST" name="profile-form" onsubmit="return infoValidate()">

                    <div class="adminPage-row">
                        <label>닉네임</label>
                        <input type="text" name="memberNickname" value="${loginMember.memberNickname}" maxlength="10">
                    </div>

                    <div class="adminPage-row">
                        <label>전화번호</label>
                        <input type="text" name="memberTel" value="${loginMember.memberTel}" maxlength="11">
                    </div>

                    <!-- 주소
                    <div class="adminPage-row info-title">
                        <span>주소</span>
                    </div>

                    <div class="adminPage-row info-address">
                        <input type="text" name="memberAddress" value="${addr[0]}" maxlength="6">

                        <button type="button" id="info-address-btn">검색</button>
                    </div>

                    <div class="adminPage-row info-address">
                        <input type="text" name="memberAddress" value="${addr[1]}">
                    </div>

                    <div class="adminPage-row info-address">
                        <input type="text" name="memberAddress" value="${addr[2]}">
                    </div> -->

                    <button id="info-update-btn">수정하기</button>

                </form>

            </section>           

        </section>

        
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="${contextPath}/resources/js/member/admin-info.js"></script>
</body>
</html>