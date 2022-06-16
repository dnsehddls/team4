<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member/myInfo.css">

</head>
<body>

    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    
    <main>


        <section class="myPage-content">
            
            <!-- 왼쪽 사이드 메뉴 -->
            <jsp:include page="/WEB-INF/views/admin/admin-sideMenu.jsp"/>


            <section class="myPage-main">
                <h2 class="title">
                    <span>내 정보</span>
                </h2>
        
                <div class="line"></div>
                
                <form action="info" method="POST" name="myPage-form" onsubmit="return changeInfoValidate()">
                    <table name="signUp-form" class="tb">
                        <tr>
                            <th>아이디</th>
                            <td>${loginMember.memberID}</td>
                        </tr>
        
                        <tr>
                            <th>이름</th>
                            <td>${loginMember.memberName}</td>
                        </tr>
        
                        <tr>
                            <th>닉네임</th>
                            <td>
                                <input type="text" name="memberNickname" value="${loginMember.memberNickname}" id="memberNick" placeholder="영어/숫자/한글 2~10글자 사이로 작성해주세요.">
                                <button class="member_btn" id="nicknameBtn" type="button">중복확인</button>
                            </td>
                        </tr>
        
                        <tr>
                            <th>전화번호</th>
                            <td>
                                <input type="tel" name="memberTel" value="${loginMember.memberTel}" id="memberTel" maxlength="13" placeholder="번호를 입력해 주세요">
                            </td>
                        </tr>
        
                        <tr>
                            <th>이메일</th>
                            <td>${loginMember.memberEmail}</td>
                        </tr>
        
                        
                    </table>
        
                    <button id="update">수정하기</button>
                    
                </form>
        
                <div class="line"></div>
            </section>         

        </section>

        
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/js/member/myInfo.js"></script>
</body>
</html>