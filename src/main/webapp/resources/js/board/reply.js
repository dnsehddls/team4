function selectReplyList(){
    $.ajax({
        url : contextPath+"/reply/replyList",
        data : {"boardNo" : boardNo},
        type : "GET",
        dataType : "JSON",
        success : function(rList){
            const replyList = document.getElementById("reply-main");
            replyList.innerHTML = "";
            for(let reply of rList){
                const replyRow = document.createElement("li");

                replyRow.classList.add("row");

                const replyContainer = document.createElement("div");
                replyContainer.classList.add("reply-container");

                //몰라
                const replyTitle = document.createElement("strong");
                replyTitle.classList.add("reply-title");

                const profileImage = document.createElement("img");
                // profileImage.setAttribute("src","#")
                // profileImage.setAttribute("alt","이")
                const replyNick = document.createElement("span");
                replyNick.classList.add("reply-nick");
                const replyDate = document.createElement("span");
                replyDate.classList.add("reply","date");
                const updateReplyDate = document.createElement("span");
                updateReplyDate.classList.add("reply-update","date");
                
                replyTitle.append(profileImage,replyNick,
                    replyDate,updateReplyDate);

                const goodBadBtnArea = document.createElement("div");
                goodBadBtnArea.classList.add("goodBadBtnArea");
                const good = document.createElement("span");
                good.classList.add("good");
                const bad = document.createElement("span");
                bad.classList.add("bad");
                
                goodBadBtnArea.append(good,bad);

                const comment = document.createElement("div");
                comment.classList.add("comment");
                const content = document.createElement("span");

                comment.append(content);

                const bottomArea = document.createElement("div");
                bottomArea.classList.add("bottom-area");
                const meno = document.createElement("span");
                meno.style.display = "none";

                bottomArea.append(meno);
                replyContainer.append(replyTitle,goodBadBtnArea,comment,bottomArea);
                replyRow.append(replyContainer);

                // if(reply.profileImage != null){
                //     profileImage.setAttribute("src",contextPath+reply.profileImage);
                // }else{
                //     profileImage.setAttribute("src",contextPath+"/resources/images/user.png");
                // }

                replyNick.innerText = reply.memberNickname;
                content.innerText = reply.replyContent
                replyDate.innerText = "("+reply.createDate+")";
                if(reply.updateDate != null){
                    updateReplyDate.innerText = "(최종 수정일) ("+reply.updateDate+")";
                }
                good.innerText = reply.likeCount;
                bad.innerText = reply.reportCount;
                meno.innerText = reply.memberNo;
                
                replyList.append(replyRow);
                
                if(reply.memberNo == loginMember){
                    const btnArea = document.createElement("div");
                    btnArea.classList.add("btn-area");

                    const updateBtn = document.createElement("button");
                    updateBtn.innerText = "수정";
                    updateBtn.setAttribute("onclick","showUpdateReply("+reply.replyNo+",this)")
                    
                    const deleteBtn = document.createElement("button");
                    deleteBtn.innerText = "삭제";
                    deleteBtn.setAttribute("onclick","deleteReply("+reply.replyNo+")")
                    
                    btnArea.append(updateBtn,deleteBtn);
                    bottomArea.append(btnArea);
                }
                
            }
        },
        error : function(req,status,error){
            console.log("error");
            console.log(req.reponseText);
        }
    });
};

const addReply = document.getElementById("addReply");
const replyContent = document.getElementById("replyContent");

addReply.addEventListener("click",function(){
    if(loginMember == ""){
        alert("로그인 후 이용해주세요");
        return;
    }
    if(replyContent.value.trim().length == 0 ){
        alert("댓글 내용을 입력해주세요.")
        replyContent.value = "";
        replyContent.focus();
        return;
    }

    $.ajax({
        url: contextPath+"/reply/insertReply",
        data: {"replyContent" : replyContent.value,
                "memberNo" : loginMember,
                "boardNo" : boardNo},
        type: "POST",
        success : function(result){
            if(result>0){
                alert("댓글이 등록되었습니다.");
                replyContent.value = "";
                selectReplyList();
                return;
            }else{
                alert("댓글 등록이 완료되지 않았습니다. 다시 시도해주세요.")
            }
        },
        error : function(req,status,error){
            console.log("댓글 등록 실패");
            console.log(req.responseText);
        }
    })
})

//수정완


function deleteReply(replyNo){
    console.log(replyNo);
    if(confirm("정말 삭제하시겠습니까?")){
        $.ajax({
            url: contextPath+"/reply/deleteReply",
            data: {"replyNo" : replyNo},
            type: "GET",
            success: function(result){
                if(result>0){
                    alert("삭제되었습니다.")
                    selectReplyList();
                }else{
                    alert("다시 시도해주세요.")
                }
            },
            error: function(req,status,error){
                console.log("댓글 등록 실패");
                console.log(req.reponseText);
            }
        })
    }
}




let beforeReplyRow;
function showUpdateReply(replyNo,eventTarget){
    const temp = document.getElementsByClassName("update-textarea");
    if(temp.length>0){
        // alert("수정을 완료하고 시도해주세요.");
        // return;
        if(confirm("다른 댓글을 수정 중입니다. \n다른 댓글 수정을 취소하고 현재 댓글을 수정하시겠습니까?")){
            temp[0].parentElement.innerHTML = beforeReplyRow;
        }else{
            return;
        }
    }
    // const replyRow = eventTarget.parentElement.parentElement.parentElement;
    const replyContainer = eventTarget.parentElement.parentElement.parentElement;
    beforeReplyRow = replyContainer.innerHTML;
    replyContainer.classList.toggle("ur");

    // 이것도 기억안난다 span.content 태그 안 text
    let preReplyContent = replyContainer.children[2].children[0].innerHTML;
    // 백업본 저장 후 밀어버림
    replyContainer.innerHTML = "";

    const textArea = document.createElement("textarea");
    textArea.classList.add("update-textarea");

    //필터링
    preReplyContent = preReplyContent.replaceAll("&amp;","&");
    preReplyContent = preReplyContent.replaceAll("&lt;","<");
    preReplyContent = preReplyContent.replaceAll("&gt;",">");
    preReplyContent = preReplyContent.replaceAll("&quot;","\"");
    preReplyContent = preReplyContent.replaceAll("<br>","\n");

    //백업본 삽입
    textArea.value = preReplyContent;
    replyContainer.append(textArea);

    const btnArea = document.createElement("div");
    btnArea.classList.add("btn-area");

    const updateBtn = document.createElement("button")
    const cancelBtn = document.createElement("button")
    updateBtn.innerText = "수정";
    cancelBtn.innerText = "취소";
    updateBtn.setAttribute("onclick","updateReply("+replyNo+",this)");
    cancelBtn.setAttribute("onclick","updatecancel(this)");

    btnArea.append(updateBtn,cancelBtn);
    replyContainer.append(btnArea);
};

function updatecancel(eventTarget){
    if(confirm("댓글 수정을 취소하시겠습니까?")){
        const replyContainer = eventTarget.parentElement.parentElement;
        eventTarget.parentElement.parentElement.innerHTML = beforeReplyRow;
        replyContainer.classList.toggle("ur");
    }
}

function updateReply(replyNo,eventTarget){
    const replyContent = eventTarget.parentElement.previousElementSibling.value;
    $.ajax({
        url: contextPath+"/reply/updateReply",
        data: { "replyNo":replyNo,
                "replyContent":replyContent},
        type: "POST",
        success : function(result){
            if(result>0){
                alert("댓글 수정이 완료되었습니다.")
                selectReplyList();
                const replyContainer = eventTarget.parentElement.parentElement;
                replyContainer.classList.toggle("ur");
            }else{
                alert("다시 시도해 주세요.")
            }
        },
        error : function(req,status,error){
            console.log("댓글 수정 실패");
            console.log(req.reponseText);
        }
    })
};