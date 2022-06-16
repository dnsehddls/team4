
// 회원 탈퇴
const flagY = document.getElementById("flagY");

if(flagY != null){
 
    flagY.addEventListener("click", function(){

    
        let url = "flagY";

 
        const params = new URL(location.href).searchParams;


        const memberEmail = "?memberEmail=" + params.get("memberEmail");


        url += memberEmail;


        if(confirm("회원을 정말로 탈퇴시키겠습니까?")){

            location.href = url;    

         }

    })

    return;

}



// 탈퇴 회원 복구
const flagN = document.getElementById("flagN");

if(flagN != null){
 
    flagN.addEventListener("click", function(){

    
        let url = "flagN";

 
        const params = new URL(location.href).searchParams;


        const memberEmail = "?memberEmail=" + params.get("memberEmail");


        url += memberEmail;


        if(confirm("회원을 복구 하시겠습니까?")){

            location.href = url;    

         }

    })

    return;

}



