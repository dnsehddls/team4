console.log("main-login loaded");

function login(){
    const id = document.getElementById("inputID");
    const pw = document.getElementById("inputPW");
    if(id.value.trim().length==0){
        alert("아이디를 입력해주세요.");
        return false;
    }
    if(pw.value.trim().length==0){
        alert("비밀번호를 입력해주세요.");
        return false;
    }
}

