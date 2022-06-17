const changeNo = document.getElementById("chageNo");
const input = document.getElementByIdI("input");

changeNo.addEventListener("click", function(){

    if(input.value.trim().length == 0){
        alert("변경할 값을 입력하세요");
        input.focus();
        return;
    }

    $.ajax({

        url : contextPath + "/admin/manageStandard/insert",
        data : {"input" : input.value},
        type : "post",
        success : function(result){

            if(result>0){
                alert("변경 성공")
                input.value = "";

            }

        },
        error : function(req, status, error){
            console.log("댓글 등록 실패");
        }


    })

});