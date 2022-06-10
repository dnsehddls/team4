<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUp</title>

    <link rel="stylesheet" href="../../resources/css/myPage/signUp.css">

</head>
<body>  
    <section class="signUp-main">
        <h2 class="title">
            <span class="pull-left">회원가입</span>
        </h2>

        <div class="line"></div>
		
        <form action="#" method="POST" onsubmit="return signUpValidate()">
            <table name="signUp_form" class="tb">
                <tr>
                    <th>아이디</th>
                    <td>
                        <input type="text" name="memberId" id="memberId"  placeholder="아이디를 입력해 주세요" autocomplete="off">
                        <button class="member_btn" id="idBtn" type="button" onclick="">중복확인</button>
                        <br><span class="signUp-message" id="idMessage">영어소문자,숫자,특수문자(-,_) 4~10글자 사이로 작성해주세요.</span>
                    </td>
                </tr>

                <tr>
                    <th rowspan="2">비밀번호</th>
                    <td style="border-bottom: none">
                        <input type="password" name="memberPw" id="memberPw" placeholder="비밀번호를 입력해 주세요." autocomplete="off">
                    </td>
                </tr>

                <tr>
                    <td style="border-top: none">
                        <input type="password" name="memberPw2" id="memberPw2" placeholder="비밀번호를 한번 더 입력해 주세요" autocomplete="off">
                        <br><span class="signUp-message" id="pwMessage">영어,숫자,특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.</span>
                    </td>
                </tr>

                <tr>
                    <th>이름</th>
                    <td>
                        <input type="text" id="memberName" name="memberName" placeholder="이름을 입력해 주세요" autocomplete="off">
                    </td>
                </tr>

                <tr>
                    <th>닉네임</th>
                    <td>
                        <input type="text" name="memberNickname" value="" id="memberNickname" placeholder="닉네임(0글자 이하)을 입력해 주세요">
                        <button class="member_btn" id="nicknameBtn" type="button" onclick="">중복확인</button>
                        <br><span class="signUp-message" id="nicknameMessage">영어/숫자/한글 2~10글자 사이로 작성해주세요.</span>
                    </td>
                </tr>

                <tr>
                    <th>전화번호</th>
                    <td>
                        <input type="tel" name="memberHp" value="" id="memberHp" maxlength="13" placeholder="번호를 입력해 주세요">
                        <br><span class="signUp-message " id="hpMessage">전화번호를 입력해주세요.(-제외)</span>
                    </td>
                </tr>

                <tr>
                    <th>이메일</th>
                    <td>
                        <input type="email" name="memberEmail" id="memberEmail" placeholder="이메일을 입력해 주세요">
                        <button class="member_btn" id="sendBtn" type="button" onclick="">인증번호 받기</button>
                        <br><span class="signUp-message" id="emailMessage">메일을 받을 수 있는 이메일을 입력해주세요.</span>
                    </td>
                </tr>

                <tr>
                    <th>인증번호</th>
                    <td>
                        <input type="text" id="cNumber" placeholder="인증번호를 입력해 주세요">
                        <button class="member_btn" type="button" id="cBtn">인증하기</button>
                        <br><span class="signUp-message" id="cMessage"></span>
                    </td>
                </tr>
                
            </table>

            <div class="terms">
                <textarea name="terms" id="terms" cols="10" rows="10">약관내용입니다.</textarea>
            </div>

            <div class="agree">
                <input type="checkbox"> 동의합니다.
            </div>

            <div class="confirm">
                <button id="cancle">취소</button>
                <button id="signUpConfirm">회원가입</button>
            </div>
            

        </form>
        <div class="line"></div>
    </section>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- js -->
    <script src="${contextPath}/resources/js/member/signUp.js"></script>
    <script>
        alert("${message}");
    </script>
    <c:remove var="message" scope="session"/>
        
</body>
</html>