$.ui.ready(function(){
	console.log("消息中心js");
	//消息页btn
	$("#sys-message-btn").click(function(){
		
		$('#user-message-btn').removeClass('active');
		$(this).addClass('active');
		$('#sys-message-div').css('display', 'block');
		$('#user-message-div').css('display', 'none');
	});
	$('#user-message-btn').click(function(){
		
		$('#sys-message-btn').removeClass('active');
		$(this).addClass('active');
		$('#user-message-div').css('display', 'block');
		$('#sys-message-div').css('display', 'none');
	});
	//默认active为系统消息sss
//	$('#sys-message-btn').trigger('click');

	$('#new1').click(function(){
		$.ajax({
			type: "POST",
			url: 'http://192.168.1.110:8080/admin/newsinfo/detail/1',
			context: $("#news-detail"),
			success: function(temp){
				console.log("res data:");
				console.log(temp);
			}
		});
	});
});



