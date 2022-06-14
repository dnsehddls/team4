// 회원 탈퇴
(function(){

    const flagY = document.getElementById("flagY");

    flagY.addEventListener("click", function(){

        let url = "flagY";

        const params = new URL(location.href).searchParams;

        const memberEmail = "?memberEmail=" + params.get("memberEmail");

        url += memberEmail;

        if(confirm("회원을 정말로 탈퇴시키겠습니까?")){

            location.href = url;

        }

    })

})();


// 탈퇴 회원 복구
(function(){

    const flagN = document.getElementById("flagN");

    flagN.addEventListener("click", function(){

        let url = "flagN";

        const params = new URL(location.href).searchParams;

        const memberEmail = "?memberEmail=" + params.get("memberEmail");

        url += memberEmail;

        if(confirm("탈퇴한 회원을 복구 하시겠습니까?")){

            location.href = url;

        }

    });

})();






