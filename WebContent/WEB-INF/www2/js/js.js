/**
 * Created by zxk on 2015/5/5.
 */
$(document).ready(function(){
    var len = $("#jtlg-pub-pic > a").length;
    var index;
    $("#jtlg-pub-pic > a").click(function(){
        index = $(this).index();
        change(index);
    });
})

function show(index){
    $('#alert'+index).show();
}
function hide(index){
    $('#alert'+index).hide();
}

function change(num){
    $('.items').hide();
    $('#items'+num).show();
    $('#jtlg-pub-pic > a').removeClass('active').eq(num).addClass('active');
}