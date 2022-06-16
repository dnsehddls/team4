<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link rel="stylesheet" href="${contextPath}/resources/css/admin/memberDetail.css">


</head>
<body>    

    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    
    <main>



        <section class="myPage-content">


            <jsp:include page="/WEB-INF/views/admin/admin-sideMenu.jsp"/>

        
            <section class="myPage-main">
                <h2 class="title">
                    <span>회원 정보</span>
                </h2>
        
                <div class="line"></div>
                

                <table name="signUp-form" class="tb">
                    <tr>
                        <th>아이디</th>
                        <td>
                            ${memberDetail.memberID}
                        </td>
                    </tr>
    
                    <tr>
                        <th>이름</th>
                        <td>
                            ${memberDetail.memberName}
                        </td>
                    </tr>      
    
                    <tr>
                        <th>이메일</th>
                        <td>
                            ${memberDetail.memberEmail}
                        </td>
                    </tr>
    
                    <tr>
                        <th>탈퇴 여부</th>

                            <td>
                                <c:choose>
                                    <c:when test="${memberDetail.secessionFlag eq 'Y'}">
                                        탈퇴 회원
                                        <button id="flagN">복구</button>
                                    </c:when>
                                    <c:otherwise>                                        
                                        회원
                                        <button id="flagY">탈퇴</button>
                                    </c:otherwise>
                                </c:choose> 
                            </td>  
                    </tr>
                    
                </table>
    
                    

        
                <div class="line"></div>
            </section>
        </section>
    </main>


    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>


    <script src="${contextPath}/resources/js/member/admin-memberDetail.js"></script>

</body>
</html>