function remove() {
    var el = document.getElementById("remove");
    var el2 = el.parentNode.parentNode;
    el.parentNode.parentNode.remove();
    //var el3 = document.getElementById("lastTR");
    var el3 = document.getElementById("lastTR");
    el3.before(el2);
}
    //this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
$(document).ready(function(){
    //jQuery调用
    $('#remove').on('click',function () {
        $(this.parentNode.parentNode).remove();
    });

    $("tr").css("background-color", "red");
});




//var elRemove = document.getElementById('remove');
//elRemove.addEventListener('click', remove, false);

/*
$(function () {
    $('li').on('click',function () {
        $(this).animate({
            opacity: 0.0,
            paddingLeft: '+=80'
        }, 500, function () {
            $(this).remove();
        });
    });
});
*/