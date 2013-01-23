function refreshInfo(){
	$.get('mail/1/2',function(data){
		$('#responseArea').html(data.data.mailIntroduction.personal + ':' + data.data.mailIntroduction.subject);
	});
}

function getRecentMailIntroductions(){
	$.get('mail/1',function(data){
		var html = '';
		for(var i in data.data) {
			html = html + data.data[i].subject + '<br />'
		}
		$('#responseArea').html(html)
	});
}