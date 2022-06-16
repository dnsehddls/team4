
// 좋아요 수 변경하기 
const changeNo = document.getElementById("changeNo");

if(changeNo != null){

    changeNo.addEventListener("click", function(){
    
        let url = "changeNo";

 
        const params = new URL(location.href).searchParams;


        const changeNo = "?changeNo=" + params.get("changeNo");


        url += changeNo;


        if(confirm("변경하시겠습니까?")){

            location.href = url;    

        } 
    
    })

    return;

}


