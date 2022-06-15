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

            <jsp:include page="/WEB-INF/views/admin/admin-sideMenu.jsp"/>
        
            <section class="myPage-main">
                <h2 class="title">
                    <span>비밀번호 변경</span>
                </h2>
        
                <div class="line"></div>
                
                <form action="changePw" method="POST" name="myPage-form" onsubmit="return changePwValidate()">
                    <table name="signUp-form" class="tb" style="height: 150px;">
                        
                        <tr>
                            <th>현재 비밀번호</th>
                            <td>
                                <input type="password" name="currentPw" id="currentPw" placeholder="현재 비밀번호를 입력해 주세요." autocomplete="off">
                            </td>
                        </tr>

                        <tr>
                            <th>새 비밀번호</th>
                            <td>
                                <input type="password" name="newPw" id="newPw" placeholder="새 비밀번호를 입력해 주세요." autocomplete="off">
                            </td>
                        </tr>
        
                        <tr>
                            <th>새 비밀번호 확인</th>
                            <td>
                                <input type="password" name="newPw2" id="newPw2" placeholder="새 비밀번호를 한번 더 입력해 주세요" autocomplete="off">
                            </td>
                        </tr>
        

                        
                    </table>
        
                    <button id="update">수정하기</button>
                    
                </form>
        
                <div class="line"></div>
            </section>
        </section>
    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/member/myInfo.js"></script>
</body>
</html>