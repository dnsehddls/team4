<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SignUp</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member/signUp.css">

</head>
<body>  
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <main>
	    <section class="signUp-main">
	        <h2 class="title">
	            <span class="pull-left">회원가입</span>
	        </h2>
	
	        <div class="line"></div>
			
	        <form action="signUp" method="POST" onsubmit="return signUpValidate()">
	            <table name="signUp_form" class="tb">
	                <tr>
	                    <th>아이디</th>
	                    <td>
	                        <input type="text" name="memberId" id="memberId"  placeholder="아이디를 입력해 주세요" autocomplete="off">
	                        <button class="member_btn" id="idBtn" type="button">중복확인</button>
	                        <br><span class="signUp-message" id="idMessage">영어소문자,숫자,특수문자(-,_) 4~10글자 사이로 작성해주세요.</span>
	                    </td>
	                </tr>
	
	                <tr>
	                    <th rowspan="2">비밀번호</th>
	                    <td style="border-bottom: none">
	                        <input type="password" name="memberPw" id="memberPw" placeholder="비밀번호를 입력해 주세요." autocomplete="off">
	                    </td>
	                </tr>
	
	                <tr>
	                    <td style="border-top: none">
	                        <input type="password" name="memberPw2" id="memberPw2" placeholder="비밀번호를 한번 더 입력해 주세요" autocomplete="off">
	                        <br><span class="signUp-message" id="pwMessage">영어,숫자,특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.</span>
	                    </td>
	                </tr>
	
	                <tr>
	                    <th>이름</th>
	                    <td>
	                        <input type="text" id="memberName" name="memberName" placeholder="이름을 입력해 주세요" autocomplete="off">
	                    </td>
	                </tr>
	
	                <tr>
	                    <th>닉네임</th>
	                    <td>
	                        <input type="text" name="memberNickname" value="" id="memberNickname" placeholder="닉네임(0글자 이하)을 입력해 주세요">
	                        <button class="member_btn" id="nicknameBtn" type="button">중복확인</button>
	                        <br><span class="signUp-message" id="nicknameMessage">영어/숫자/한글 2~10글자 사이로 작성해주세요.</span>
	                    </td>
	                </tr>
	
	                <tr>
	                    <th>전화번호</th>
	                    <td>
	                        <input type="tel" name="memberTel" value="" id="memberTel" maxlength="13" placeholder="번호를 입력해 주세요">
	                        <br><span class="signUp-message " id="telMessage">전화번호를 입력해주세요.(-제외)</span>
	                    </td>
	                </tr>
	
	                <tr>
	                    <th>이메일</th>
	                    <td>
	                        <input type="email" name="memberEmail" id="memberEmail" placeholder="이메일을 입력해 주세요">
	                        <button class="member_btn" id="checkBtn" type="button">중복확인</button>
	                        <button class="member_btn" id="sendBtn" type="button">인증번호 받기</button>
	                        <br><span class="signUp-message" id="emailMessage">메일을 받을 수 있는 이메일을 입력해주세요.</span>
	                    </td>
	                </tr>
	
	                <tr>
	                    <th>인증번호</th>
	                    <td>
	                        <input type="text" id="cNumber" placeholder="인증번호를 입력해 주세요">
	                        <button class="member_btn" type="button" id="cBtn">인증하기</button>
	                        <br><span class="signUp-message" id="cMessage"></span>
	                    </td>
	                </tr>
	                
	            </table>
	
	            <div class="terms">
	                <textarea name="terms" id="terms" cols="10" rows="10" style="resize: none">개인정보취급방침

현행 시행일자: 2022년 6월 15일
운동인은 “정보통신망 이용촉진 및 정보보호”에 관한 법률 등 서비스제공자가 준수하여야 할 관련 법령상의 개인정보보호 규정을 준수하며, 관련 법령에 의거한 개인정보취급방침을 정하여 이용자 권익 보호에 최선을 다하고 있습니다.
본 방침은 : 2022년 6월 15일 부터 시행됩니다.

1. 수집하는 개인정보 항목 본 기관은 회원가입 등을 위해 아래와 같은 개인정보를 수집하고 있습니다.
• 수집항목
	- 본 기관은  회원가입, 각종 서비스의 제공을 위해 최초 회원가입 당시 아래와 같은 개인정보를 수집하고 있습니다.
· '[회원가입] 성명, 이메일, ID, 비밀번호, 전화번호

2. 개인정보의 수집 및 이용목적
본 기관은  수집한 개인정보를 다음의 목적을 위해 활용합니다.
• 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산
	-회원제 서비스 이용에 따른 본인확인, 개인 식별 , 불량회원의 부정 이용 방지와 비인가 사용 방지 , 가입 의사 확인, 가입 및 가입횟수 제한

3. 개인정보의 공유 및 제공
본 기관은  이용자들의 개인정보를 "2. 개인정보의 수집목적 및 이용목적"에서 고지한 범위내에서 사용하며, 이용자의 사전 동의 없이는 동 범위를 초과하여 이용하거나 원칙적으로 이용자의 개인정보를 외부에 공개하지 않습니다. 다만, 아래의 경우에는 예외로 합니다.
• 이용자들이 사전에 공개에 동의한 경우
• 법령의 규정에 의거하거나, 수사 목적으로 법령에 정해진 절차와 방법에 따라 수사기관의 요구가 있는 경우
• 서비스의 제공에 관한 계약의 이행을 위하여 필요한 개인정보로서 경제적/기술적인 사유로 통상의 동의를 받는 것이 현저히 곤란한 경우

4. 개인정보의 보유 및 이용기간
원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.
• 본 기관 내부 방침에 의한 정보보유 사유
	-부정이용기록
	-보존 근거 : 불량 회원의 부정한 이용의 재발을 방지하기 위해
	-보존 기간 : 1년
• 관련법령에 의한 정보보유 사유
관계법령의 규정에 의하여 보존할 필요가 있는 경우 본 기관은  아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다. 이 경우 본 기관은  보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다
	-본인확인에 관한 기록 : 6개월 (정보통신망 이용촉진 및 정보보호 등에 관한 법률)
	-웹사이트 방문기록 : 3개월 (통신비밀보호법)

5. 거부권 및 불이익 귀하는 위와 같은 개인정보 수입이용에 동의하지 않으실 수 있습니다.
동의 거부 시 서비스는 가능하나 회원전용 서비스는 제한될 수 있습니다.
(단, 회원가입을 위한 최소한의 정보인 필수정보는 미입력시 회원가입 불가)

6. 개인정보의 파기절차 및 방법 본 기관은 원칙적으로 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체없이 파기합니다. 파기절차 및 방법은 다음과 같습니다.
• 파기절차
	-회원님이 회원가입 등을 위해 입력하신 정보는 목적이 달성된 후 별도의 DB로 옮겨져(종이의 경우 별도의 서류함) 내부 방침 및 기타 관련 법령에 의한 정보보호 사유에 따라(보유 및 이용기간 참조) 일정 기간 저장된 후 파기됩니다.
	-동 개인정보는 법률에 의한 경우가 아니고서는 보유되는 이외의 다른 목적으로 이용되지 않습니다.
• 파기방법
	-종이에 출력된 개인정보는 분쇄기로 분쇄하거나 소각을 통하여 파기합니다.
	-전자적 파일형태로 저장된 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다.

7. 이용자 및 법정대리인의 권리와 그 행사방법
• 이용자 및 법정대리인은 언제든지 등록되어 있는 자신 혹은 당해 만14세 미만 아동의 개인정보를 조회하거나 수정할 수 있으며 가입해지를 요청할 수도 있습니다.
• 이용자의 개인정보 조회나 수정을 위해서는 마이페이지(회원정보수정 등)의 개인정보 관리 메뉴의 ‘회원정보변경’을, 가입해지를 위해서는 “회원탈퇴”를 클릭하여 본인 확인 절차를 거치신 후 직접 열람, 정정 또는 탈퇴가 가능합니다. 혹은 개인정보관리책임자에게 서면, 전화 또는 이메일로 연락하시면 지체없이 조치하겠습니다.
• 단. 이용자 개인정보의 오류에 대한 정정을 요청하신 경우에는 정정을 완료하기 전까 지 당해 개인정보를 이용 또는 제공하지 않습니다. 또한 잘못된 개인정보를 제3자 에게 이미 제공한 경우에는 정정 처리결과를 제3자에게 지체없이 통지하여 정정이 이루어지도록 하겠습니다.
• 본 기관은  이용자 혹은 법정 대리인의 요청에 의해 해지 또는 삭제된 개인정보는 ‘회사’ 가 수집하는 개인정보의 보유 및 이용기간”에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리하고 있습니다.

8. 개인정보 자동수집 장치의 설치, 운영 및 그 거부에 관한 사항 본 기관은  귀하의 정보를 수시로 저장하고 찾아내는 ‘쿠키(cookie)’ 등을 운용합니다. 쿠키는 웹사이트를 운영하는데 이용되는 서버가 귀하의 브라우저에 보내는 아주 작은 텍스트 파일로서 귀하의 컴퓨터 하드디스크에 저장됩니다.
귀하는 쿠키 설치에 대한 선택권을 가지고 있습니다. 따라서, 귀하는 웹브라우저에서 옵션을 설정함으로써 모든 쿠키를 허용하거나, 쿠키가 저장될 때마다 확인을 거치거나, 아니면 모든 쿠키의 저장을 거부할 수도 있습니다.
• 쿠키 설정/운영 거부 방법 이용자는 쿠키 설치에 대한 선택권을 가지고 있습니다. 따라서 웹 브라우저의 옵션을 선택함으로써 모든 쿠키를 허용하거나 쿠키를 저장할 때마다 확인을 거치거나, 모든 쿠키의 저장을 거부할 수 있습니다 • 다만, 쿠키 설치를 거부하였을 경우 서비스 제공에 어려움이 있을 수 있습니다.
	-쿠키설치 허용여부를 지정하는 방법(인터넷 익스플로어의 경우)
	-웹 브라우저 상단의 도구 > 인터넷 옵션 > 개인정보 > 개인정보 취급 수준 설정

기타 개인정보침해에 대한 신고나 상담이 필요하신 경우에는 아래 기관에 문의하시기 바랍니다.
본 기관은  수집한 개인정보를 다음의 목적을 위해 활용합니다..
• 개인정보침해신고센터 (www.118.or.kr / 118)
• 정보보호마크인증위원회 (www.eprivacy.or.kr / 02-580-0533~4)
• 대검찰청 첨단범죄수사과 (www.spo.go.kr / 02-3480-2000)
• 경찰청 사이버테러대응센터 (www.ctrc.go.kr / 02-392-0330)

9. 고지의 의무 현 개인정보취급방침 내용 추가, 삭제 및 수정이 있을 시에는 개정 최소 7일전부터 홈페이지의 ‘공지사항’을 통해 고지할 것입니다.
• 공고일자 : 2022년 6월 15일
• 시행일자 : 2022년 6월 15일

</textarea>
	            </div>

				<div class="agree">
					<input type="checkbox" id="agree"> 동의합니다
				</div>
	
	            <div class="confirm">
	                <button id="cancel" type="button"><a href="${contextPath}">취소</a></button>
	                <button id="signUpConfirm">회원가입</button>
	            </div>
	            
	
	        </form>
	        <div class="line"></div>
	    </section>
		
    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <!-- js -->
    <script src="${contextPath}/resources/js/member/signUp.js"></script>
</body>
</html>