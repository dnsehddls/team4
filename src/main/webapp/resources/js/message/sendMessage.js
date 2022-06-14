/**
 * 
 */
 
const searchBtn = document.getElementById("search-btn");
const receiver = document.getElementById("receiver");
const searchForm = document.getElementsByName("search-form");
const dataForm = document.getElementsByName("data-form");
const sendNickname = document.getElementsByName("sendNickname");
const inputMessage = document.getElementById("inputMessage");
const sendBtn = document.getElementsByName("sendBtn");

// 닉네임 유효성 검사
memberNickname.addEventListener("input", function(){

    // // 입력되지 않은 경우
    // if(memberNickname.value.length == 0){
    //     nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
    //     nicknameMessage.classList.remove("confirm", "error");

    //     checkObj.memberNickname = false; // 유효 X 기록
    //     return;
    // }

    // const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    // if( regExp.test(memberNickname.value) ){ // 유효한 경우
        
	$.ajax({
            url : contextPath + "/findUser/findNo",  // 필수 작성 속성
            data : { "memberNickname" : receiver.value }, // 서버로 전달할 값(파라미터)
            type : "GET", // 데이터 전달 방식(기본값 GET)

            success : function(res){ // 비동기 통신 성공 시(에러 발생 X)

                // 매개변수 res : Servlet에서 응답으로 출력된 데이터가 저장

				if(!res > 0){ // 닉네임 조회 실패
					alert("일치하는 회원이 없습니다.");
					receiver.value = "";
					receiver.focus();
					return;
				}
				alert("일치하는 회원입니다.");
			}
		});
});







// 특정 회원 조회 - 클릭 + 엔터
// searchBtn.addEventListener("click", function(){
// function btnClick(){	
// 	// 닉네임 미입력시
//     if(receiver.value.trim().length == 0){
// 		alert("닉네임을 입력해주세요.click");
// 	    receiver.value = "";
// 	    receiver.focus();
//         return;
// 	};
	
// 	// 없는 회원일 경우
// 	if(receiver.value == null){
// 		alert("일치하는 회원이 없습니다.");
// 		receiver.focus();
//         return;
// 	}
	
//     // sendNickname.value(receiver.value);
// };





function enterkey(e){
	// 닉네임 미입력시
	if(e.keyCode == 13) {
		if(receiver.value.trim().length == 0){
			alert("닉네임을 입력해주세요.enter");
			receiver.value = "";
			receiver.focus();
			return;
		}
		
		// 없는 회원일 경우
		if(receiver.value == null){
			alert("일치하는 회원이 없습니다.");
			receiver.focus();
			return;
		}
		receiveNickname.value = receiver.value();
		receiveNickname.value(receiver.value());
		receiveNickname.value(receiver.value);
		//searchForm.submit();
	}
    // sendNickname.value(receiver.value);
};


// 보내기 버튼 클릭
sendBtn.addEventListener("click", function(){

	// 닉네임 미입력시
	if(receiver.value.trim().length == 0){
		alert("닉네임을 입력해주세요.");
		receiver.value = "";
		receiver.focus();
		return;
	}
	
	// 내용 미입력시
	if(inputMessage.value.trim().length == 0){
		alert("내용을 입력해주세요.");
		inputMessage.value = "";
		inputMessage.focus();
		return;
	}
});
