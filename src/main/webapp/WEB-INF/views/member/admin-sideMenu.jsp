<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 왼쪽 사이드 메뉴 -->
<section class="left-side">
    사이드메뉴
    <ul class="list-group">
        <li><a href="${contextPath}/member/adminPage/profile">프로필</a></li>
        
        <!-- /community/member/myPage/info -->
        <li><a href="${contextPath}/member/adminPage/info">내 정보</a></li>
        
        <!-- /community/member/myPage/changePw -->
        <li><a href="${contextPath}/member/adminPage/changePw">비밀번호 변경</a></li>
        
        <!-- /community/member/myPage/secession -->
        <li><a href="${contextPath}/member/adminPage/memberList">회원 목록</a></li>

        <li><a href="${contextPath}/member/adminPage/reported">신고된 게시글</a></li>
    </ul>
</section>