
(function(){
    const goToListBtn = document.getElementById("goToListBtn");

    if(goToListBtn != null) {

        goToListBtn.addEventListener("click", function(){

            const pathname = location.pathname;

            let url = pathname.substring(0, pathname.indexOf("/", 1));

            url += "/board/list?";

            const params = new URL(location.href).searchParams;

            const type = "type=" + params.get("type");

            let cp;

            if(params.get("cp") != null){
                cp = "cp=" + params.get("cp");
            }else{
                cp = "cp=1";
            }

            url += type + "&" + cp;

            if(params.get("key") != null){
                const key = "&key=" + params.get("key");
                const query = "&query=" + params.get("query");

                url += key + query; 
            }

            location.href = url; 

        });
    }
})();




// 즉시 실행 함수 : 성능up, 변수명 중복 X
(function(){
    const deleteBtn = document.getElementById("deleteBtn"); // 존재하지 않으면 null

    if(deleteBtn != null){ // 버튼이 화면에 존재할 때
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

})();


// 검색창에 이전 검색기록 반영하기
(function(){
    const select = document.getElementById("search-key");

    // const option = select.children;
    const option = document.querySelectorAll("#search-key > option");

    const input = document.getElementById("search-query");

    if(select != null){ // 검색창이 화면이 존재할 때에만 코드 적용

        // 현재 주소에서 쿼리스트링(파라미터) 얻어오기
        const params = new URL(location.href).searchParams;

        // 얻어온 파라미터 중 key, query만 변수에 저장
        const key = params.get("key");
        const query = params.get("query");

        // input에 query 값 대입
        input.value = query;

        // option을 반복 접근해서 value와 key가 같으면 selected 속성 추가
        for(let op of option){
            if(op.value == key ){
                op.selected = true;
            }
        }
    }

})();



// 검색 유효성 검사(검색어를 입력 했는지 확인)
function searchValidate(){

    const query = document.getElementById("search-query");

    if(query.value.trim().length == 0){ // 미작성
        query.value = ""; // 빈칸
        query.focus();

        return false;
    }

    return true;
}


