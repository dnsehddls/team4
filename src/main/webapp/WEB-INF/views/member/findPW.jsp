<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디/패스워드 찾기</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member/find.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <main>
        <section class="myPage-content">

            <section class="myPage-main">
                <h2 class="title">
                    <span>비밀번호 찾기</span>
                </h2>

                <div class="line"></div>
                
                <span id="message">
                    <h4>가입시 입력한 고객님의 이메일을 통해 찾을 수 있습니다.</h4>
                </span>

                <div class="find-main">
                    <form action="#" method="POST">

                        <table class="tb">
                            <tr>
                                <th><input type="text" id="inputPw" name="inputPw" placeholder="성함"></th>
                                <th rowspan="2"><button>확인</button></th>
                                <!-- 버튼 클릭 시 alert로
                                    "이메일로 요청하신 정보가 전송되었습니다.
                                     이메일을 확인해주세요." 출력 -->
                            </tr>
                            <tr>
                                <th><input type="email" id="inputEmail" name="inputEmail" placeholder="이메일"></th>
                            </tr>
                        </table>

                    </form>

                </div>


                <div class="line"></div>

                </section>

        </section>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- js -->
    <script src="${contextPath}/resources/js/member/find.js"></script>
    
</body>
</html>