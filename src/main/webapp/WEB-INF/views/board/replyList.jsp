<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <c:forEach var="reply" items="${rList}">
        <li class="row">
            <div class="reply-container">
                <strong class="reply-title">
                    <img src="#" alt="이">
                    <span class="reply-nick">${reply.memberNickname}</span>
                    <span class="reply date">${reply.createDate}</span>
                    <c:if test="${!empty reply.updateDate}">
                        <span class="reply-update date">${reply.updateDate}</span>
                    </c:if>
                </strong>
                <div class="goodBadBtnArea">
                    <span class="good">${reply.likeCount}</span>
                    <span class="bad">${reply.reportCount}</span>
                </div>
                <div class="comment">
                    <span class="content">
                        ${reply.replyContent}
                    </span>
                </div>
                <div class="bottom-area">
                    <span style="display: none;">${reply.replyNo}</span>
                </div>
            </div>
        </li> 
	</c:forEach>