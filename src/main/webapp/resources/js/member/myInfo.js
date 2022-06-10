function changeInfoValidate(){
    
    const newPw = document.getElementById("newPw");
    const newPw2 = document.getElementById("newPw2");
    const memberNickname = document.getElementById("memberNick");
    const memberTel = document.getElementById("memberTel");
    const memberEmail = document.getElementById("memberEmail");


    const regExp1 = /^[a-zA-Z0-9가-힣]{2,10}$/; // 닉네임 정규식
    const regExp2 = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/; // 전화번호 정규식
    const regExp3 = /^[\w!@#_-]{6,30}$/; // 비밀번호 정규식
    const regExp4 = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/; // 이메일 정규식

    
   
    // 닉네임 유효성 검사
    if(memberNickname.value.length == 0){ // 미작성시 : 닉네임을 입력해주세요.
        alert("닉네임을 입력해주세요.");
        memberNickname.focus();
        return false;
    }

    if(!regExp1.test(memberNickname.value)){ // 유효하지 않은 경우
        alert("닉네임은 영어/숫자/한글 2~10글자 사이로 작성해주세요.");
        memberNickname.focus();
        return false;
    }

    
    // 전화번호 유효성 검사
    if(memberTel.value.length == 0){ // 미작성시
        alert("전화번호를 입력해주세요.(- 제외");
        memberTel.focus();
        return false;
    }

    if(!regExp2.test(memberTel.value)){ // 유효하지 않은 경우
        alert("전화번호 형식이 올바르지 않습니다.");
        memberTel.focus();
        return false;
    }


    //  유효성 검사
    if(memberEmail.value.length == 0){ // 미작성시
        alert("이메일을 입력해주세요.");
        memberEmail.focus();
        return false;
    }

    if(!regExp4.test(memberEmail.value)){ // 유효하지 않은 경우
        alert("이메일 형식이 올바르지 않습니다.");
        memberEmail.focus();
        return false;
    }

    
    // 비밀번호 변경

    // 비밀번호 정규표현식
    const regEx = /^[\w!@#_-]{6,30}$/;

    // if(newPw.value.trim().length == 0){
    //     alert("새 비밀번호를 입력해주세요.");
    //     newPw.focus();
    //     return false;
    // }

    // 유효하지 않은 경우
    if(!regEx.test(newPw.value)){
        alert("영어, 숫자, 특수문자(!,@,#,-,_) 6~30 글자 사이로 작성해주세요.");
        newPw.focus();
        return false;
    }

    if(newPwConfirm.value.trim().length == 0){
        return printAlert(newPwConfirm, "새 비밀번호 확인을 입력해주세요.");
    }

    // 새 비밀번호 != 새 비밀번호 확인
    if(newPw.value != newPwConfirm.value){
        return printAlert(newPwConfirm, "새 비밀번호가 일치하지 않습니다.");
    }



    return true;
}