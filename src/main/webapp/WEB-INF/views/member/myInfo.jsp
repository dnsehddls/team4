<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Info</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member/myInfo.css">

</head>
<body>    
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

    <main>
        <section class="myPage-content">

            <section class="left-side">
                <ul class="list-group">
                    <li><a href="../../../webapp/html/member/myInfo.html">내 정보</a></li>
                    <li><a href="../../../webapp/html/member/myContent.html">내 글 관리</a></li>
                    <li><a href="../../../webapp/html/member/like.html">좋아요 관리</a></li>
                    <li><a href="../../../webapp/html/member/bookmark.html">북마크 관리</a></li>
                    <li><a href="../../../webapp/html/member/secession.html">회원 탈퇴</a></li>
                    <li><a href="#">쪽지</a></li>
                </ul>
            </section>
        
            <section class="myPage-main">
                <h2 class="title">
                    <span>내 정보</span>
                </h2>
        
                <div class="line"></div>
                
                <form action="#" method="POST" name="myPage-form" onsubmit="return changeInfoValidate()">
                    <table name="signUp-form" class="tb">
                        <tr>
                            <th>아이디</th>
                            <td>
                                user01
                            </td>
                        </tr>
        
                        <tr>
                            <th>이름</th>
                            <td>
                                유저일
                            </td>
                        </tr>
        
                        <tr>
                            <th>비밀번호</th>
                            <td>
                                <input type="password" name="newPw" id="newPw" placeholder="비밀번호를 입력해 주세요." autocomplete="off">
                            </td>
                        </tr>
        
                        <tr>
                            <th>비밀번호 확인</th>
                            <td>
                                <input type="password" name="newPw2" id="newPw2" placeholder="비밀번호를 한번 더 입력해 주세요" autocomplete="off">
                            </td>
                        </tr>
        
                        <tr>
                            <th>닉네임</th>
                            <td>
                                <input type="text" name="memberNick" value="" id="memberNick" placeholder="닉네임(0글자 이하)을 입력해 주세요">
                            <button class="memberBtn" type="button" onclick="">중복확인</button>
                            </td>
                        </tr>
        
                        <tr>
                            <th>전화번호</th>
                            <td>
                                <input type="tel" name="memberTel" value="" id="memberTel" maxlength="13" placeholder="번호를 입력해 주세요">
                            </td>
                        </tr>
        
                        <tr>
                            <th>이메일</th>
                            <td>
                                <input type="email" name="memberEmail" id="memberEmail" placeholder="이메일을 입력해 주세요">
                            </td>
                        </tr>
        
                        <tr></tr>
                        
                    </table>
        
                    <button id="update">수정하기</button>
                    
                </form>
        
                <div class="line"></div>
            </section>
        </section>
    </main>



    <script src="${contextPath}/resources/js/member/myInfo.js"></script>
</body>
</html>