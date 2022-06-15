// 관리자 정보 수정 유효성 검사
function changeInfoValidate(){

    if(!buttonCheck){
        alert("닉네임 중복확인을 먼저 진행해주세요.")
        return false;
    }
    

    if(memberTel.value.trim().length == 0){
        alert("전화번호를 입력해주세요.(-제외)");
        memberTel.focus();
        return false;
    }
    if(!regExp2.test(memberTel.value)){
        alert("전화번호 형식이 올바르지 않습니다.");
        memberTel.focus();
        return false;
    }  


    return true;

}

function changePwValidate(){
    
    const currentPw = document.getElementsByName("currentPw")[0];
    const newPw = document.getElementsByName("newPw")[0];
    const newPw2 = document.getElementsByName("newPw2")[0];

    const regEx = /^[\w!@#_-]{6,30}$/;

    if(currentPw.value.trim().length == 0){
        alert("현재 비밀번호를 입력해주세요.");
        currentPw.focus();
        return false;
    }

    if(newPw.value.trim().length == 0){
        alert("새 비밀번호를 입력해주세요.");
        newPw.focus();
        return false;
    }


    if(newPw2.value.trim().length == 0){
        alert("새 비밀번호 확인을 입력해주세요.");
        newPw2.focus();
        return false;
    }
    if(newPw.value != newPw2.value){
        alert("새 비밀번호가 일치하지 않습니다.");
        newPw2.focus();
        return false;
    }

    return true;
}