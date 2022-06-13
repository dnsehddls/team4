<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/admin/admin-sideMenu.css">
</head>
<body>

    <section class="left-side">
        사이드메뉴
        <ul class="list-group">
            <li><a href="${contextPath}/admin/profile">프로필</a></li>
            
            <!-- /community/member/myPage/info -->
            <li><a href="${contextPath}/admin/info">내 정보</a></li>
            
            <!-- /community/member/myPage/changePw -->
            <li><a href="${contextPath}/admin/changePw">비밀번호 변경</a></li>
            
            <!-- /community/member/myPage/secession -->
            <li><a href="${contextPath}/admin/memberList">회원 목록</a></li>

            <li><a href="${contextPath}/admin/reported">신고된 게시글</a></li>
        </ul>
    </section>
</body>
</html>

<!-- 왼쪽 사이드 메뉴 -->
