// 유효성 검사 여부를 기록할 객체 생성
document.getElementById("cancel").addEventListener("click",function(){
    if(confirm("가입을 취소하시겠습니까?")){
        return location.href = "http://localhost:8080/team4";
    }
});

const checkObj = {
    "memberId"       : false,
    "memberPw"       : false,
    "memberPw2"      : false,
    "memberNickname" : false,
    "memberTel"      : false,
    "memberEmail"    : false,
    "sendEmail"      : false
};

// 아이디
const memberId = document.getElementById("memberId");
const idMessage = document.getElementById("idMessage");
const idBtn = document.getElementById("idBtn");

memberId.addEventListener("input", function(){

    if(memberId.value.length == 0){
        idMessage.innerText = "영어소문자,숫자,특수문자(-,_) 4~10글자 사이로 작성해주세요.";
        idMessage.classList.remove("confirm", "error");
        checkObj.memberId = false;
        return;
    }

    // 아이디 유효성 검사
    const regExp = /^[a-z0-9_-]{4,10}$/; // 아이디 정규식 (소문자 + 숫자 + 언더바/하이픈 허용 4~10자리)

    if(regExp.test(memberId.value)){ // 유효한 경우

        idBtn.addEventListener("click", function(){

            $.ajax({
                url : "idDupCheck",
                data : {"memberId" : memberId.value},
                type : "GET",
                success : function(result){
                    if(result == 0){ // 아이디 중복 X
                        idMessage.innerText = "사용 가능한 아이디입니다.";
                        idMessage.classList.add("confirm");
                        idMessage.classList.remove("error");
                        checkObj.memberId = true;
    
                    }else{ // 아이디 중복 O
                        idMessage.innerText = "이미 사용중인 아이디입니다.";
                        idMessage.classList.remove("confirm");
                        idMessage.classList.add("error");
                        checkObj.memberId = false;
                    }
                },
                error : function(){
                    console.log("에러 발생");
                }
            });
        });
        
    }else{
        idMessage.innerText = "아이디 형식이 올바르지 않습니다.";
        idMessage.classList.remove("confirm");
        idMessage.classList.add("error");
        checkObj.memberId = false;

    }
});

// 비밀번호
const memberPw = document.getElementById("memberPw");
const memberPw2 = document.getElementById("memberPw2");
const pwMessage = document.getElementById("pwMessage");

memberPw.addEventListener("input", function(){
    if(memberPw.value.length == 0){
        pwMessage.innerText = "영어,숫자,특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.";
        pwMessage.classList.remove("error", "confirm");
        checkObj.memberPw = false;
        return;
    }

    const regExp = /^[\w!@#_-]{6,30}$/;

    if(regExp.test(memberPw.vale)){ // 비밀번호 유효

        checkObj.memberPw = true;

        if(memberPw2.value.length == 0){ // 비밀번호 유효O, 비밀번호 확인 작성X
            pwMessage.innerText = "유효한 비밀번호 형식입니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");

        }else{ // 비밀번호 유효O, 비밀번호 확인 작성O
            checkPw(); // 비밀번호 일치 검사 함수 호출()
        }

    }else{
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
        checkObj.memberPw = false;
    }
});

// 비밀번호 확인 유효성 검사
memberPw2.addEventListener("input", checkPw);

function checkPw(){ // 비밀번호 일치 검사
    // 비밀번호 / 비밀번호 확인이 같을 경우
    if(memberPw.value == memberPw2.value){
        pwMessage.innerText = "비밀번호가 일치합니다.";
        pwMessage.classList.add("confirm");
        pwMessage.classList.remove("error");
        checkObj.memberPw2 = true;
    }else{ // 비밀번호 / 비밀번호 확인이 같지 않은 경우
        pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
        checkObj.memberPw2 = false;
    }
}

// 닉네임
const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");
const nicknameBtn = document.getElementById("nicknameBtn");


memberNickname.addEventListener("input", function(){

    // 입력되지 않은 경우
    if(memberNickname.value.length == 0){
        nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
        nicknameMessage.classList.remove("confirm", "error");
        checkObj.memberNickname = false;
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if( regExp.test(memberNickname.value) ){ // 유효한 경우

        nicknameBtn.addEventListener("click", function(){

            $.ajax({
                url : "nicknameDupCheck",
                data : {"memberNickname" : memberNickname.value},
                type : "GET",
                seccess : function(result){
                                // Servlet에서 응답으로 출력된 데이터가 저장
                    if(result == 0){
                        nicknameMessage.innerText = "사용 가능한 닉네임입니다.";
                        nicknameMessage.classList.add("confirm");
                        nicknameMessage.classList.remove("error");
                        checkObj.memberNickname = true;
                    }else{
                        nicknameMessage.innerText = "이미 사용중인 닉네임입니다.";
                        nicknameMessage.classList.add("error");
                        nicknameMessage.classList.remove("confirm");
                        checkObj.memberNickname = false;
                    }
                },
                error : function(){
                    console.log("에러 발생")
                }
            });

        });
    
    }else{
        nicknameMessage.innerText = "닉네임 형식이 유효하지 않습니다.";
        nicknameMessage.classList.add("error");
        nicknameMessage.classList.remove("confirm");
        checkObj.memberNickname = false;
    }
});


// 전화번호
const memberTel = document.getElementById("memberHp");
const telMessage = document.getElementById("telMessage");

memberHp.addEventListener("input", function(){

    // 전화번호 입력X 경우
    if(memberHp.value.length == 0){
        telMessage.innerText = "전화번호를 입력해주세요.(-제외)";
        telMessage.classList.remove("confirm", "error");
        checkObj.memberTel = false;
        return;
    }

    // 전화번호 유효성 검사
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/; // 전화번호 정규식
    
    if(regExp.text(memberHp.value)){ // 유효한 경우
        telMessage.innerText = "유효한 전화번호 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
        checkObj.memberTel = true;
    }else{ // 유효하지 않은 경우
        telMessage.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMessage.classList.remove("confirm");
        telMessage.classList.add("error");
        checkObj.memberTel = false;
    }
});

// 이메일
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");
const emailBtn = document.getElementById("emailBtn");

memberEmail.addEventListener("input", function(){

    if(memberEmail.value.length == 0){ // 입력되지 않은 경우
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        emailMessage.classList.remove("confirm", "error");
        checkObj.memberEmail = false;
        return;
    }
    
    // 이메일 유효성
    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

    if(regExp.test(memberEmail.value)){ // 유효한 경우
        
        emailBtn.addEventListener("click", function(){

            // 이메일 중복 검사
            $.ajax({
                url : "emailDupCheck",
                data : {"memberEmail" : memberEmail.value},
                type : "GET",
                seccess : function(result){
                    if(result == 1){ // 중복O
                        emailMessage.innerText = "이미 사용중인 이메일입니다.";
                        emailMessage.classList.remove("confirm");
                        emailMessage.classList.add("error");
                        checkObj.memberEmail = false; 
                    }else{ // 중복 X
                        emailMessage.innerText = "사용 가능한 이메일입니다.";
                        emailMessage.classList.add("confirm");
                        emailMessage.classList.remove("error");
                        checkObj.memberEmail = true; 
                    }
                },
                error : function(){
                    console.log("에러발생");
                }
            });

        });

    }else{ // 유효하지 않은 경우
        emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
        checkObj.memberEmail = false;
    }
});



// form태그 제출 시(회원가입 버튼 클릭 시) 유효성 검사가 완료되었는지 확인하는 함수
function signUpValidate(){
    let str;
    for(let key in checkObj){ // 객체용 향상된 for문
        if(!checkObj[key]){ // 현재 접근 중인 key의 value가 false인 경우
            switch(key){
                case "memberId" : str="아이디가"; break;
                case "memberPw" : str="비밀번호가"; break;
                case "memberPw2" : str="비밀번호 확인이"; break;
                case "memberNickname" : str="닉네임이"; break;
                case "memberTel" : str="전화번호가"; break;
                case "memberEmail" : str="이메일이"; break;
            }
            str += " 유효하지 않습니다.";
            alert(str);
            document.getElementById(key).focus();
            return false; // form태그 기본 이벤트 제거
        }
    }
    return true; // false결과가 없을 때 form태그 기본이벤트 수행
}


// 인증번호 보내기
const sendBtn = document.getElementById("sendBtn");
const cMessage = document.getElementById("cMessage");

// 타이머에 사용될 변수
let checkInterval; // setInterval을 저장할 변수
let min = 4;
let sec = 59;

sendBtn.addEventListener("click", function(){

    if(checkObj.memberEmail){
        $.ajax({
            url : "sendEmail",
            data : {"inputEmail" : memberEmail.value},
            type : "GET",
            success : function(result){
                console.log("이메일 발송 성공");
                console.log(result);
                checkObj.sendEmail = true;
            },
            error : function(){
                console.log("이메일 발송 실패");
            }
        });

        cMessage.innerText = "5:00"; // 초기값 5:00가 눈에 보이게 함
        min = 4;
        sec = 59;

        cMessage.classList.remove("confirm");
        cMessage.classList.remove("error");

        checkInterval = setInterval(function(){

            if(sec < 10) sec = "0" + sec;
            cMessage.innerText = min + ":" + sec;

            if(Number(sec) === 0){
                min--;
                sec = 59;
            }else{
                sec--;
            }

            if(min === -1){ // 만료
                cMessage.classList.add("error");
                cMessage.innerText = "인증번호가 만료되었습니다.";
                clearInterval(checkInterval);
            }

        }, 1000); // 1초 지연 후 수행

        alert("인증번호가 발송되었습니다. 이메일을 확인해주세요.");
    }
});


// 인증번호 확인 클릭시에 대한 동작
const cNumber = document.getElementById("cNumber");
const cBtn = document.getElementById("cBtn");

cBtn.addEventListener("click", function(){

    // 1. 인증번호 받기 버튼이 클릭되어 이메일이 발송되었는지 확인
    if(checkObj.sendEmail){ // 이메일이 발송되었을 때

        // 2. 입력된 인증번호가 6자리가 맞는지 확인
        if(cNumber.value.length == 6){ // 6자리 O
            
            $.ajax({
                url : "checkNumber",
                data : {"cNumber"    : cNumber.value,
                        "inputEmail" : memberEmail.value},
                type : "GET",
                success : function(result){
                    console.log(result);
                    // 1 : 인증번호 일치 O, 시간 만족 O
                    // 2 : 인증번호 일치 O, 시간 만족 X
                    // 3 : 인증번호 일치 X

                    if(result == 1){
                        // cMessage에 출력 중인 시간 멈춤
                        clearInterval(checkInterval); // 타이머 멈춤

                        cMessage.innerText = "인증되었습니다.";
                        cMessage.classList.add("confirm");
                        cMessage.classList.remove("error");

                        
                    }else if(result == 2){
                        alert("만료된 인증번호 입니다.");

                    }else{ // (result == 3)
                        alert("인증번호가 일치하지 않습니다.");
                    }
                },
                error : function(){
                    console.log("이메일 인증 실패");
                }

            });
            
        }else{ // 6자리 X
            alert("인증번호를 정확하게 입력해주세요.");
            cNumber.focus();
        }

    }else{ // 이메일 발송 X (인증번호를 안받은 경우)
        alert("인증번호 받기 버튼을 먼저 클릭해주세요.");
    }
});

