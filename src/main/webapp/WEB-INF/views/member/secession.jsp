<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Secession</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member/secession.css">

</head>
<body>  
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    
    <main>
        <section class="myPage-content">

            <section class="left-side">
                <ul class="list-group">
                    <li><a href="info">내 정보</a></li>
                    <li><a href="myContent">내 글 관리</a></li>
                    <li><a href="like">좋아요 관리</a></li>
                    <li><a href="bookmark">북마크 관리</a></li>
                    <li><a href="secession">회원 탈퇴</a></li>
                    <li><a href="#">쪽지</a></li>
                </ul>
            </section>
        
            <section class="myPage-main">
                <h2 class="title">
                    <span>탈퇴하기</span>
                </h2>
        
                <div class="line"></div>
                
                <div>
                    <form action="#" method="POST">
                    
                        <span id="message">
                            <h4>회원 탈퇴 시 서비스 이용이 제한되며 이후 복구가 불가능합니다.</h4>
                        </span>
            
                        <br>
                        
                        <table class="tb">
                            <tr>
                                <th colspan="2" id="tbText">
                                    <div class="smallLine"></div>
                                    <span id="idMessage">탈퇴 요청하신 아이디</span>
                                </th>
                            </tr>
                            <tr>
                                <th>아이디</th>
                            </tr>
                            <tr>
                                <td>${loginMember.memberID}</td>
                            </tr>
                        </table>
            
                        <br>
    
                        <form action="#" method="POST" name="secession-form" onsubmit="secessionValidate()">
                            <div class="myPage-row">
                                <label>비밀번호</label>
                                <input type="password" id="inputPw" name="inputPw" placeholder="비밀번호를 입력해주세요." style="border: 1px solid black; border-radius: 5px; height: 25px;">
                            </div>
                            
                            <div>
                                <!-- confirm 확인 -->
                                <button id="secessionBtn" style="cursor: pointer">탈퇴하기</button>
                            </div>
                        </form>
                        
            
                        
                    </form>
                </div>
    
                <div class="line"></div>
            </section>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>


    <script src="${contextPath}/resources/js/member/secession.js"></script>

</body>
</html>