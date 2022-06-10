<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
    
    <header>
        <section class="logo">
            <a href="#"><img src="${contextPath}/resources/images/updatelogo.jpg" alt="logo" class="logo"></a>
        </section>
        <section class="main-title">
        	<a href="#"><img src="${contextPath}/resources/images/title-banner.jpg" alt="banner" id="title-banner"></a>
        </section>
    </header>

    <nav>
        <div>
            <ul>
                <li><a href="${contextPath}/board/list">공지사항</a></li>
                <li><a href="${contextPath}/board/list">인기게시판</a></li>
                <li><a href="${contextPath}/board/list">운동게시판</a></li>
                <li><a href="${contextPath}/board/list">자유게시판</a></li>
                <li><a href="${contextPath}/board/list">정모게시판</a></li>
            </ul>
        </div>
        <div>
            <img src="" alt="등급이미지">
            <a href="#"><img src="${contextPath}/resources/images/letter-removebg-preview_negative.png" alt=""></a>
            <a href="#"><img src="${contextPath}/resources/images/pngwing.com.png" alt="" id="people"></a>
            <input type="text" name="search-area" id="search" >
            <button type="button" class="fa-solid fa-magnifying-glass"></button>
        </div>
    </nav>
