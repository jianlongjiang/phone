var isdrag=false; 
var tx,x;
$.ui.ready(function(){
//$(function(){
  //jtlg
    var divW = $(".news-nav").width();
    var winW = $(window).width();
    var maxDefelction = winW*(divW - 100)/100 * -1;
    document.getElementById("jtlg-pub-pic").addEventListener('touchend',function(){
        isdrag = false;
        try {
			if (parseInt($(this).css("left")) >= 0) {
				$(this).animate({
					left : '0'
				}, 500);
			}
			if (parseInt($(this).css("left")) <= maxDefelction) {
				$(this).animate({
					left : maxDefelction + 'px'
				}, 500);
			}
		} catch (e) {
		}
    });
    document.getElementById("jtlg-pub-pic").addEventListener('touchstart',function(e){ 
       isdrag = true; 
       tx = parseInt(document.getElementById("jtlg-pub-pic").style.left+0); 
       x = e.touches[0].pageX;  
       return false; 
    });
    document.getElementById("jtlg-pub-pic").addEventListener('touchmove',movemouse);
});
function movemouse(e){ 
  if (isdrag){ 
    var n = tx+ e.touches[0].pageX - x;

    $(this).css("left",n);

   return false; 
   } 
} 