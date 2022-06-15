/**
 * 
 */
 
const searchBtn = document.getElementById("search-btn");
const receiver = document.getElementById("receiver");
const searchForm = document.getElementsByName("search-form")[0];
const dataForm = document.getElementsByName("data-form")[0];
const sendNickname = document.getElementsByName("sendNickname")[0];
const inputMessage = document.getElementById("inputMessage");
const sendBtn = document.getElementById("sendBtn");
const receiveNo = document.getElementsByName("receiveNo")[0];

// // 회원 닉네임 조회
// searchBtn.addEventListener("click", function(){

//     // // 입력되지 않은 경우
//     // if(memberNickname.value.length == 0){
//     //     nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
//     //     nicknameMessage.classList.remove("confirm", "error");

//     //     checkObj.memberNickname = false; // 유효 X 기록
//     //     return;
//     // }

//     // const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

//     // if( regExp.test(memberNickname.value) ){ // 유효한 경우
        
	
// 	});
// });







// 특정 회원 조회 - 클릭 + 엔터
// searchBtn.addEventListener("click", function()){
function btnClick(){

	$.ajax({
		url : contextPath + "/sendView/findNo",  // 필수 작성 속성
		data : { "memberNickname" : receiver.value }, // 서버로 전달할 값(파라미터)
		type : "GET", // 데이터 전달 방식(기본값 GET)
	
		success : function(res){ // 비동기 통신 성공 시(에러 발생 X)

			// 매개변수 res : Servlet에서 응답으로 출력된 데이터가 저장

			// 닉네임 조회 실패
			// if(!res > 0){
			// 	alert("일치하는 회원이 없습니다.");
			// 	receiver.value = "";
			// 	receiver.focus();
			// 	return;
			// }

			var nn = JSON.parse(res);
			console.log(nn.memberNickname);
			console.log(nn.receiveNo);

			// 닉네임 미입력시
			if(receiver.value.trim().length == 0){
				alert("닉네임을 입력해주세요.");
				receiver.value = "";
				receiver.focus();
				return;
			};
			
			// 없는 회원일 경우
			if(nn.memberNickname == undefined){
				alert("일치하는 회원이 없습니다.");
				receiver.focus();
				return;
			}
			receiveNo.value = nn.receiveNo;
		}
	})
};

// 보내기 버튼 클릭
sendBtn.addEventListener("click", function(){ // 댓글 등록 버튼이 클릭이 되었을 때

    // 2) 댓글 내용이 작성되어있나?
    if(inputMessage.value.trim().length == 0){ // 미작성인 경우
        alert("내용을 작성한 후 버튼을 클릭해주세요.");

        inputMessage.value = ""; // 띄어쓰기, 개행문자 제거
        inputMessage.focus();
        return;
    }

    // 3) AJAX를 이용해서 댓글 내용 DB에 저장(INSERT)
    $.ajax({
        url : contextPath + "/sendView/sendData",
        data : {"inputMessage" : inputMessage.value,
                "receiveNo" : receiveNo,
            	},
        type : "post",
        success : function(result){

            if(result > 0){ // 등록 성공
                alert("쪽지가 성공적으로 보내졌습니다.");

                inputMessage.value = ""; // 작성했던 댓글 삭제
				receiver.value = ""; // 받는사람 닉네임 삭제

            } else { // 실패
                alert("댓글 등록에 실패했습니다...");
            }

        },


    });

});


// enterkey 이벤트 -> 제거
// function enterkey(e){
// 	// 닉네임 미입력시
// 	if(e.keyCode == 13) {
// 		if(receiver.value.trim().length == 0){
// 			alert("닉네임을 입력해주세요.enter");
// 			receiver.value = "";
// 			receiver.focus();
// 			return;
// 		}
		
// 		// 없는 회원일 경우
// 		if(receiver.value == null){
// 			alert("일치하는 회원이 없습니다.");
// 			receiver.focus();
// 			return;
// 		}
// 		receiveNickname.value = receiver.value();
// 		receiveNickname.value(receiver.value());
// 		receiveNickname.value(receiver.value);
// 		//searchForm.submit();
// 	}
//     // sendNickname.value(receiver.value);
// };


