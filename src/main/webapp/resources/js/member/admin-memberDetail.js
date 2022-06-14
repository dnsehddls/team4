function secessionFl(){
    
    const secessionFlag = document.getElementById("secessionFlag").value;

    if(secessionFlag.trim().length == 0){
        alert("탈퇴 여부를 입력해주세요");
        secessionFlag.focus();
        
        return false;
    }

    true;

}






