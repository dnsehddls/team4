// (function(){
//     const goToListBtn = document.getElementById("goToListBtn");

//         goToListBtn.addEventListener("click", function(){

//             // location 객체(BOM)

//             const pathname = location.pathname; // 주소상에서 요청 경로 반환
//             //  /team4/board/detail

//             // 문자열.substring(시작,끝) : 시작 이상 끝 미만 인덱스 까지 문자열 자르기

//             // 문자열.indexOf("검색 문자열", 시작 인덱스)
//             // : 문자열에서 "검색 문자열"의 위치(인덱스)를 찾아서 반환
//             //  단, 시작 인덱스 이후 부터 검색

//             // 이동할 주소 저장
//             let url = pathname.substring(0,  pathname.indexOf("/", 1)); // 
//             //   /team4

//             url += "/board/list?";  //   /team4/board/list?


//             // URL 내장 객체 : 주소 관련 정보를 나타내는 객체
//             // location.href : 현재 페이지 주소 + 쿼리스트링
//             // URL.searchParams : 쿼리 스트링만 별도 객체로 반환

//             // http://localhost:8080/team4/board/detail?no=249&cp=6&type=1&key=c&query=9
//             const params = new URL(location.href).searchParams;

//             const type =  "type=" + params.get("type"); //    type=1
           
//             let cp;

//             if(params.get("cp") != null){ // 쿼리스트링에 cp가 있을 경우
//                 cp =  "cp=" + params.get("cp");     
//             }else{
//                 cp = "cp=1";
//             }


//             // 조립
//             //   /team4/board/list?type=1&cp=1
//             url += type + "&" + cp;


//             // 검색 key, query가 존재하는 경우 url에 추가
//             if(params.get("key") != null){
//                 const key = "&key=" + params.get("key");
//                 const query = "&query=" + params.get("query");

//                 url += key + query; // url 뒤에 붙이기
//             }

//             location.href = url; 

//         });
// })();


function goToListBtn(){
    
}