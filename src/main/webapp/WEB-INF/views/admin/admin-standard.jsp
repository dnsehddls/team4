<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 좋아요 관리</title>

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
                        <span>좋아요</span>
                    </h2>
            
                    <div class="line"></div>
                    


                        <table name="signUp-form" class="tb">
                            <tr>
                                <th>현재 좋아요 수</th>
                                <td>
                                    ${standardNo}
                                </td>
                            </tr>
            
                            <tr>
                                <th>좋아요 수 변경하기</th>
                                    <td>
                                        <input type="text" name="changeNo">
        
                                        <button id="changeNo">변경</button>
                                    </td>  
                            </tr>                        
                        </table>

                    

        
                        

            
                    <div class="line"></div>
                </section>
            </section>
        </main>


        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>


        <script src="${contextPath}/resources/js/member/admin-standard.js"></script>

    </body>
</html>