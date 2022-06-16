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


