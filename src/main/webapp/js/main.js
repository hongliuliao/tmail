jQuery(function ($) {
	$('#addAccountBtn').click(function(){
		var emailTextField = $('#accountEmail').val();
		var password = $('#accountPassword').val();
		if(emailTextField == '' || password == ''){
			alert('用户名或密码不能为空!')
			return;
		}
		var storeData = {'email':emailTextField,'password':password};
		$.JSONCookie('ACCOUNT_INFO', storeData, {'path': '/'});
		$('#accountInfo').html('当前用户:' + emailTextField)
	});
	
	$('#sendCommandBtn').click(function(){
		var cmd = $('#command').val()
		if(cmd == ''){
			alert("cmd can not be null!");
			return;
		}
		if(cmd == 'list'){
			getMailIntroductionsList();
		}
		if(cmd.match(/^view [0-9]+$/)) {
			var msgnum = cmd.match(/[0-9]+$/)
			getMail(msgnum)
		}
		if(cmd.match(/^vc [0-9]+$/)) {
			var msgnum = cmd.match(/[0-9]+$/)
			getHtmlMail(msgnum)
		}
	})
	
	$('#command').bind('keyup', function(){
		if(event.keyCode==13){
			$('#sendCommandBtn').click();
		}
	})
});

function getMail(msgnum){
	$.get('mail/' + msgnum,function(data){
		var html = data.data.mailIntroduction.personal + ':' + data.data.mailIntroduction.subject + '<br />'
		html = html + data.data.context
		html = html.replace(/\n/g, '<br />')
		$('#responseArea').html(html);
	});
};

function getHtmlMail(msgnum){
	$.get('mail/' + msgnum,function(data){
		var html = data.data.mailIntroduction.personal + ':' + data.data.mailIntroduction.subject + '<br />'
		html = html + data.data.htmlContext
		$('#responseArea').html(html);
	});
};

function getMailIntroductionsList(){
	$.get('mail/list',function(data){
		var html = '';
		for(var i in data.data) {
			html = html + '[' + data.data[i].messageNumber + ']' + data.data[i].subject + '<br />'
		}
		$('#responseArea').html(html)
	});
};