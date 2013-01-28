jQuery(function ($) {
	showAccountInfo()
	
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
		if($.cookie('ACCOUNT_INFO') == null || $.cookie('ACCOUNT_INFO') == '') {
			alert('请登录后操作!')
			return;
		}
		$('#processArea').html('正在执行...')
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
	
	$('#sendMailBtn').bind('click', function(){
		var toMail = $('#toText').val()
		var subjectText = $('#subjectText').val()
		var context = $('#contextTextArea').val()
		if(!isValid([toMail, subjectText, context])){
			alert("参数不合法!")
			return
		}
		$.post("mail/", { 'subject': subjectText, 'context': context, 'tomail': toMail } ,function(data){
			if(data.code == 1){
				alert('success')
			}else{
				alert(data.msg)
			}
		});
	})
	
	$('#command').bind('keyup', function(){
		if(event.keyCode==13){
			$('#sendCommandBtn').click();
		}
	})
});

function showAccountInfo() {
	var accountInfo = $.JSONCookie('ACCOUNT_INFO');
	if(isValid([accountInfo.email])){
		$('#accountInfo').html('当前用户:' + accountInfo.email)
	}
}

function isValid(objs){
	for(var i=0;i<objs.length;i++){
		if(objs[i] == null || objs[i] == undefined || objs[i] == ''){
			return false;
		}
	}
	return true
}

function showResponse(html, tmail) {
	$('#responseArea').html(html);
	$('#processArea').html('OK!')
	if(isValid([tmail])){
		$('#toText').val(tmail.mailIntroduction.from) 
	}
}

function getMail(msgnum){
	$.get('mail/' + msgnum,function(data){
		var html = data.data.mailIntroduction.personal + ':' + data.data.mailIntroduction.subject + '<br />'
		html = html + data.data.context
		html = html.replace(/\n/g, '<br />')
		html = html + getAttachmentInfo(data.data)
		showResponse(html, data.data)
	});
};

function getHtmlMail(msgnum){
	$.get('mail/' + msgnum,function(data){
		var html = data.data.mailIntroduction.personal + ':' + data.data.mailIntroduction.subject + '<br />'
		html = html + data.data.htmlContext
		html = html + getAttachmentInfo(data.data)
		showResponse(html, data.data)
	});
};

function getAttachmentInfo(tmail) {
	var html = ''
	for(var i=0;i<tmail.attachmentNames.length;i++) {
		html = html + '[附件:]<a href="mail/' + tmail.mailIntroduction.messageNumber + '/attachment?filename=' + encodeURIComponent(tmail.attachmentNames[i]) + '">' + tmail.attachmentNames[i] + '</a><br />'
	}
	return html
}

function getMailIntroductionsList(){
	$.get('mail/list',function(data){
		var html = '';
		for(var i in data.data) {
			html = html + '[' + data.data[i].messageNumber + ']' + data.data[i].subject + '<br />'
		}
		showResponse(html)
	});
};
