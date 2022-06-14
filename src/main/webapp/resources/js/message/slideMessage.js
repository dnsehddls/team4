const dept02 = document.getElementsByClassName("dept02");
const topMenu = document.getElementsByClassName("topMenu");
const messageSpan = document.getElementsByClassName("messageSpan");
const box = document.getElementById("box");


$(document).on('click', '.messageSpan', function () {
    $('.dept02').slideDown(200);
});
$(document).on('mouseleave', '#box', function () {
    if (!$(this).hasClass('topMenu')) {
        $('.dept02').slideUp(200);
    }
});