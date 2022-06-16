<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<section class="left-side">
	    <ul class="list-group">
	        <p>쪽지</p>
	        <li> <a href="${contextPath}/sendView">쪽지 보내기</a> </li>
	        <li> <a href="${contextPath}/messageList?t=r">받은 쪽지함</a> </li>
	        <li> <a href="${contextPath}/messageList?t=s">보낸 쪽지함</a> </li>

			<c:forEach var="i" begin="${param.startPageNo}" end="${param.endPageNo}" step="1">
				<c:choose>
	
					<c:when test="${i eq param.pageNo}">
					<a href="javascript:goPage(${i})" class="choice" name ="num" >${i}</a>
					</c:when> 
	
					<c:otherwise>
					<a href="javascript:goPage(${i})" >${i}</a>
					</c:otherwise>
	
				</c:choose>
			</c:forEach>

	    </ul>
    </section>

</body>
</html>