console.log("load test");

const deleteBtn = document.getElementById("delete-btn"); 
const replyBtn = document.getElementById("reply-btn");
const checkAll = document.getElementById("checkAll");

// 체크박스 - 전체 체크 / 해제
function checkAll1(checkAll){ // 전체 체크 후 답장 클릭 시 ? -> 안되게? or 닉네임들 쪽지보내기 페이지로 전달??
const datas = document.getElementsByName("data");
    datas.forEach((checkbox) => {
        checkbox.checked = checkAll.checked;
    })
}




// 답장
/*replyBtn.addEventListener("click", function(){

    if(checkbox.checked && checkAll.checked ){ // 체크된 쪽지에 답장
                            // 다수 쪽지 체크된 경우 닉네임 다 전달? or 한명만?

        // 쪽지 보내기 페이지로 이동 (닉네임 가지고)
        
    }
});*/


// 답장 버튼 클릭시 쪽지보내기 페이지로 이동 (닉네임 가지고)
/*(function(){

        goToListBtn.addEventListener("click", function(){

            // location 객체(BOM)

            const pathname = location.pathname; // 주소상에서 요청 경로 반환
            //  /team4/Semi/detail

            // 문자열.substring(시작,끝) : 시작 이상 끝 미만 인덱스 까지 문자열 자르기

            // 문자열.indexOf("검색 문자열", 시작 인덱스)
            // : 문자열에서 "검색 문자열"의 위치(인덱스)를 찾아서 반환
            //  단, 시작 인덱스 이후 부터 검색

            // 이동할 주소 저장
            let url = pathname.substring(0,  pathname.indexOf("/", 1)); // 
            //   /community

            url += "/board/list?";  //   /community/board/list?


            // URL 내장 객체 : 주소 관련 정보를 나타내는 객체
            // location.href : 현재 페이지 주소 + 쿼리스트링
            // URL.searchParams : 쿼리 스트링만 별도 객체로 반환

            // 쪽지보내기 주소
            const params = new URL(location.href).searchParams;

            const type =  "type=" + params.get("type"); //    type=1
           
            let cp;

            if(params.get("cp") != null){ // 쿼리스트링에 cp가 있을 경우
                cp =  "cp=" + params.get("cp");     
            }else{
                cp = "cp=1";
            }


            // 조립
            //   /community/board/list?type=1&cp=1
            url += type + "&" + cp;


            // 검색 key, query가 존재하는 경우 url에 추가
            if(params.get("key") != null){
                const key = "&key=" + params.get("key");
                const query = "&query=" + params.get("query");

                url += key + query; // url 뒤에 붙이기
            }


            // location.href = "주소";  -> 해당 주소로 이동
            location.href = url; 

        });

})(); */



// 삭제 버튼 즉시 실행 함수
/*(function(){

    if(checkbox.value == checked){ // 체크되어 있을 때
        deleteBtn.addEventListener("click", function(){
            // 현재 : detail?no=1508&cp=1&type=1

            // 목표 : delete?no=1508&type=1

            let url = "delete"; // 상대경로 형식으로 작성

            // 주소에 작성된 쿼리스트링에서 필요한 파라미터만 얻어와서 사용

            // 1) 쿼리스트링에 존재하는 파라미터 모두 얻어오기
            const params = new URL(location.href).searchParams  ;

            // 2) 원하는 파라미터만 얻어와 변수에 저장
            const no = "?no=" + params.get("no");   // ?no=1508

            const type = "&type=" + params.get("type"); // &type=1

            // url에 쿼리스트링 추가
            url += no + type; // delete?no=1508&type=1

            if( confirm("정말로 삭제 하시겠습니까?") ){
                location.href = url; // get방식으로 url에 요청
            }

        });
    }

})();*/



// 삭제
/*deleteBtn.addEventListener("click", function(){

    if(checkbox.value == checked){ // 체크된 쪽지

        // 쪽지 삭제 제출
        deleteMessage.submit();
    }
});*/