<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
<!DOCTYPE html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="../../resources/css/message/slideMessage-style.css">
    <header>
        <section class="logo">
            <a href="${contextPath}"><img src="${contextPath}/resources/images/updatelogo.jpg" alt="logo" class="logo"></a>
        </section>
        <section class="main-title">
        	<a href="${contextPath}"><img src="${contextPath}/resources/images/title-banner.jpg" alt="banner" id="title-banner"></a>
        </section>
    </header>

    <nav>
        <div>
            <ul>
                <li><a href="#">공지사항</a></li>
                <li><a href="#">인기게시판</a></li>
                <li><a href="#">운동게시판</a></li>
                <li><a href="#">자유게시판</a></li>
                <li><a href="#">정모게시판</a></li>
            </ul>
        </div>
        <div>
            <img src="" alt="등급이미지">
            <div class="topMenu">
                <ul class="dept01">
                    <div class="messageSpan"><img src="${contextPath}/resources/images/letter-removebg-preview_negative.png" alt="" id="messageIcon"></div>
                    <span id="box">
                        <span class="dept02">   
                            <li id="sendMessage">
                                <a href="${contextPath}/sendView">쪽지 보내기</a>
                            </li>
                            <li id="Message1">
                                <a href="${contextPath}/rMessageList">받은 쪽지함</a>
                            </li>
                            <li id="Message2">
                                <a href="${contextPath}/sMessageList">보낸 쪽지함</a>
                            </li>
                        </span>
                    </span>
                </ul>
            </div>
            <!-- <img src="${contextPath}/resources/images/letter-removebg-preview_negative.png" alt=""> -->
            
            <!-- <a href="${contextPath}/findUser"><img src="${contextPath}/resources/images/letter-removebg-preview_negative.png" alt=""></a> -->
            <a href="#"><img src="${contextPath}/resources/images/pngwing.com.png" alt="" id="people"></a>
            <input type="text" name="search-area" id="search" >
            <button type="button" class="fa-solid fa-magnifying-glass"></button>
        </div>
    </nav>

    <script src="${contextPath}/resources/js/message/slideMessage.js"></script>