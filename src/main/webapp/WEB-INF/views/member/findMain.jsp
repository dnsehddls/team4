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
                    <form action="#" method="POST" style="background-color: white;">

                        <table class="tb">
                            <tr>
                                <th>
                                    <button class="selectBtn"><a href="#">아이디 찾기</a></button>
                                </th>
                            </tr>
                            <tr>
                                <th>
                                    <button class="selectBtn"><a href="#">비밀번호 찾기</a></button>
                                </th>
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