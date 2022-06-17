
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
<!-- <link rel="stylesheet" href="../../resources/css/message/slideMessage-style.css"> -->
<!DOCTYPE html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
                <li><a href="${contextPath}/board/list?type=1">공지사항</a></li>
                <li><a href="${contextPath}/board/list?type=5">인기게시판</a></li>
                <li><a href="${contextPath}/board/list?type=2">운동게시판</a></li>
                <li><a href="${contextPath}/board/list?type=3">자유게시판</a></li>
                <li><a href="${contextPath}/board/list?type=4">정모게시판</a></li>
                <li><a href="${contextPath}/board/list?type=6">최근게시글</a></li>
            </ul>
        </div>
        <div>

            <c:if test="${!empty loginMember}">
                <img src="" alt="등급이미지">
                <a href="#"><img src="${contextPath}/resources/images/letter-removebg-preview_negative.png" alt=""></a>
                <c:choose>
                    <c:when test="${loginMember.grade eq '관리자'}">
                        <a href="${contextPath}/admin/info}"><img src="${contextPath}/resources/images/pngwing.com.png" alt="" id="people"></a>
                    </c:when>
                    
                    <c:otherwise>
                        <a href="${contextPath}/member/myPage/info"><img src="${contextPath}/resources/images/pngwing.com.png" alt="" id="people"></a>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${empty loginMember}">
                <a href="#"><img src="${contextPath}/resources/images/pngwing.com.png" alt="" id="people"></a>
            </c:if>
<!-- =======
            <img src="" alt="등급이미지">
<<<<<<< HEAD
            <div class="topMenu">
                <ul class="dept01">
                    <div class="messageSpan"><img src="${contextPath}/resources/images/letter-removebg-preview_negative.png" alt="" id="messageIcon"></div>
                    <span id="box">
                        <span class="dept02">   
                            <li id="sendMessage">
                                <a href="${contextPath}/sendView">쪽지 보내기</a>
                            </li>
                            <li id="Message1">
                                <a href="${contextPath}/messageList?t=r">받은 쪽지함</a>
                            </li>
                            <li id="Message2">
                                <a href="${contextPath}/messageList?t=s">보낸 쪽지함</a>
                            </li>
                        </span>
                    </span>
                </ul>
            </div>
            <!-- <img src="${contextPath}/resources/images/letter-removebg-preview_negative.png" alt=""> -->
            
            <!-- <a href="${contextPath}/findUser"><img src="${contextPath}/resources/images/letter-removebg-preview_negative.png" alt=""></a> -->
           <!--  <a href="#"><img src="${contextPath}/resources/images/pngwing.com.png" alt="" id="people"></a> -->
            <input type="text" name="search-area" id="search" >
            <button type="button" class="fa-solid fa-magnifying-glass"></button>
        </div>
    </nav>

    <script src="${contextPath}/resources/js/message/slideMessage.js"></script>