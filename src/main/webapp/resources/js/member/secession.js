function secessionValidate(){

    const inputPw = document.getElementById("inputPw");

    // 비밀번호 미작성
    if(inputPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        inputPw.focus();
        return false;
    }

    if(!confirm("정말 탈퇴 하시겠습니까?")){ // 취소를 누른 경우
        return false;
    }

    return true;
}