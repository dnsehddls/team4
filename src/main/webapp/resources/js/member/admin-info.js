// 관리자 정보 수정 유효성 검사
function infoValidate(){

    const memberNickname = document.getElementById("memberNickname");
    const memberTel = document.getElementById("memberTel");

    // 닉네임 정규식
    const regExp1 = /^[a-zA-Z0-9가-힣]{2,10}$/;

    // 전화번호 정규식
    const regExp2 = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}|\d{4}$/;


    // 닉네임 유효성 검사
    if(memberNickname.value.length == 0){
        alert("이메일을 입력해주세요");
        memberNickname.focus();
        return false;
    }

    if(!regExp1.test(memberNickname.value)){
        alert("닉네임은 영어/숫자/한글 2~10 글자 사이로 작성해주세요")
        memberNickname.focus();
        return false;
    }


    // 전화번호 유효성 검사
    if(memberTel.value.length == 0){
        alert("전화번호를 입력해주세요.(- 제외)")
        memberTel.focus();
        return false;
    }

    if(!regExp2.test(memberTel)){
        alert("전화번호 형식이 올바르지 않습니다");
        memberTel.focus();
        return false;
    }

    return true;

}

// 비밀번호 변경 유효성 검사
function changePwValidate(){

    const currentPw = document.getElementsByName("currentPw")[0];
    const newPw = document.getElementsByName("newPw")[0];
    const newPwConfirm = document.getElementsByName("newPwConfirm")[0];

    const regEx = /^[\w!@#_-]{6,30}$/;

    // 현재 비밀번호 미작성
    if(currentPw.value.trim().length == 0){

        alert("현재 비밀번호를 입력해주세요");
        currentPw.focus();
        return false;
    }

    // 새 비밀번호 미작성
    if(newPw.value.trim().length == 0){

        alert("새로운 비밀번호를 입력해주세요");
        newPw.focus();
        return false;
    }

    // 새 비밀번호 유효하지 않은 경우
    if(!regEx.test(newPw.value)){

        alert("영어, 숫자, 특수문자(!, @, #, -, _) 6~30 글자 사이로 작성해주세요");
        newPw.focus();
        newPw.value = "";
        return false;
    }

    // 새 비밀번호 확인 미작성
    if(newPwConfirm.value.trim().length == 0){

        alert("새로운 비밀번호 확인을 입력해주세요");
        newPw.focus();
        return false;
    }

    if(newPw.value != newPwConfirm){
        alert("새로운 비밀번호가 일치하지 않습니다");
        return false;
    }

    return true;


}