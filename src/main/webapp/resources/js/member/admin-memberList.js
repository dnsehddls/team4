// 회원 목록 조회 (ajax)

document.getElementById("select1").addEventListener("click", function(){

    const input = document.getElementById("in1");
    const div = document.getElementById("memberList");

    $.ajax({

        url : "member/selectOne",
        data : {"memberEmail" : input.value},
        type : "POST",
        dataType : "JSON",

        success : function(member){

            div.innerHTML = "";

            if(member != null){ // 회원 정보 존재

                const tr = document.createElement("tr");

                const td1 = document.createElement("td");
                td1.innerText = item.memberNo; // 회원 번호 

                const td2 = document.createElement("td");
                td2.innerText = item.memberEmail;  // 회원 이메일

                const td3 = document.createElement("td");
                td3.innerText = item.memberNickname;  // 회원 닉네임

                const td4 = document.createElement("td");
                td4.innerText = item.memberRegistDate;  // 회원 가입일
                

                tr.append(td1, td2, td3, td4);

                memberList.append(tr);

            }else{

                const h4 = document.createElement("h4");

                h4.innerText = "일치하는 회원이 없습니다";

                h4.style.color = "red";

                memberList.append(h4);

            }

        },
        error : function(request, status, error){}

    })

});

// 일정 시간마다 회원 목록 조회
function selectAll(){

    $.ajax({

        url : "member/selectAll",
        dataType : "json",
        success : function(list){
            const memberList = document.getElementById("memberList");

            memberList.innerHTML = "";

            for(let item of list){

                const tr = document.createElement("tr");

                const td1 = document.createElement("td");
                td1.innerText = item.memberNo; // 회원 번호 

                const td2 = document.createElement("td");
                td2.innerText = item.memberEmail;  // 회원 이메일

                const td3 = document.createElement("td");
                td3.innerText = item.memberNickname;  // 회원 닉네임

                const td4 = document.createElement("td");
                td4.innerText = item.memberRegistDate;  // 회원 가입일
                

                tr.append(td1, td2, td3, td4);

                memberList.append(tr);

            }

        },
        error : function(){
            console.log("에러 발생");
        }

    });

}


(function(){

    selectAll();

    window.setInterval(selectAll, 10000);

})();

