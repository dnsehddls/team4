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

// 특정 회원 조회 - 클릭
function btnClick(){

	$.ajax({
		url : contextPath + "/sendView/findNo",  // 필수 작성 속성
		data : { "memberNickname" : receiver.value }, // 서버로 전달할 값(파라미터)
		type : "GET", // 데이터 전달 방식(기본값 GET)
	
		success : function(res){ // 비동기 통신 성공 시(에러 발생 X)

			var nn = JSON.parse(res);
			console.log(nn.memberNickname);
			console.log(nn.receiveNo);

			// 닉네임 미입력시
			if(receiver.value.trim().length == 0){
				alert("닉네임을 입력해주세요.");
				receiver.value = "";
				receiver.focus();
				return false;
			};
			
			// 없는 회원일 경우
			if(nn.memberNickname == undefined){
				alert("일치하는 회원이 없습니다.");
				receiver.focus();
				return false;
			}

			// 회원 조회 성공 시
			if(receiveNo.value = nn.receiveNo){
				alert(nn.memberNickname + "님 조회 완료 " + " (내용을 입력후 쪽지를 보내주세요.)");
			}
			receiveNo.value = nn.receiveNo;
		}
	})
};


// 보내기 버튼 클릭
document.getElementById("sendForm").addEventListener("submit", function(e){ // 댓글 등록 버튼이 클릭이 되었을 때
	 
    if(receiver.value.trim().length == 0){ // 미작성인 경우
        alert("닉네임을 작성해주세요.");

        receiver.value = ""; // 띄어쓰기, 개행문자 제거
        receiver.focus();
		e.preventDefault();

    } else if (inputMessage.value.trim().length == 0){ // 미작성인 경우
        
		alert("내용을 작성한 후 버튼을 클릭해주세요.");

        inputMessage.value = ""; // 띄어쓰기, 개행문자 제거
        inputMessage.focus();
		e.preventDefault();
    }

	
});