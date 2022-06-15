<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>slideMessage</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../../resources/css/message/slideMessage-style.css">
    
    <script>
    $(document).on('click', '.messageSpan', function () {
        $('.dept02').slideDown(200);
    });
    $(document).on('mouseleave', '#box', function () {
        if (!$(this).hasClass('topMenu')) {
            $('.dept02').slideUp(200);
        }
    });
    </script>

</head>
<body>
    <section>
        <div class="topMenu">
            <ul class="dept01">
                <div class="messageSpan"><img src="../../resources/images/messageIcon.jpg" alt="" id="messageIcon"></div>
                <span id="box">
                    <span class="dept02">   
                        <li id="sendMessage">
                            <a href="#">쪽지 보내기</a>
                        </li>
                        <li id="Message1">
                            <a href="#">받은 쪽지함</a>
                        </li>
                        <li id="Message2">
                            <a href="#">보낸 쪽지함</a>
                        </li>
                    </span>
                </span>
            </ul>
        </div>
    </section>

</body>
</html>