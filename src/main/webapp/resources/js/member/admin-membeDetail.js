function secessionFl(){
    
    const secessionFlag = document.getElementById("secessionFlag").value;

    if(secessionFlag ='Y'){ // 회원탈퇴 상태
        alert("회원 탈퇴"); 

        return true;
    }
    
    if(secessionFlag ='N'){ // 회원 안탈퇴
        alert("회원 복구 성공"); 

        return true;
    }

    if(secessionFlag.length == 0){
        alert("탈퇴 여부를 입력해주세요");
        secessionFlag.focus();
        
        return false;
    }


}

