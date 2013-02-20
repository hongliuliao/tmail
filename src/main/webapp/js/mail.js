define('mail',['jquery','jsoncookie', 'util', 'task', 'talk'], function($, JSONCOOKIE, Util, task, talk){
	$(function () {
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
			addOtherAccount(storeData)
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
			if(cmd.match(/^list [0-9]+$/)) {
				var page = cmd.match(/[0-9]+$/)
				getMailIntroductionsList(page)
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
			if(!Util.isValid([toMail, subjectText, context])){
				alert("参数不合法!")
				return
			}
			$.post("mail/", { 'subject': subjectText, 'context': context, 'tomail': toMail } ,function(data){
				if(data.code == 0){
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
		
		$('#listBtn').bind('click', function(){
			$('#command').val('list')
			$('#sendCommandBtn').click();
		})
		
		$('#removeBtn').bind('click', function(){
			var msgnums = new Array()
			$('#mailListForm input[type="checkbox"]:checked').each(function(){
				msgnums.push($(this).val())
			})
			$.post("mail/remove", jQuery.param({ 'msgnums': msgnums }, true), function(data){
				if(data.code == 0){
					$('#listBtn').click()
				}else{
					alert(data.msg)
				}
			})
		})
		
	});
	
	var isValid = Util.isValid
	
	function showAccountInfo() {
		var accountInfo = $.JSONCookie('ACCOUNT_INFO');
		if(Util.isValid([accountInfo.email])){
			$('#accountInfo').html('当前用户:' + accountInfo.email)
		}
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

	function getMailIntroductionsList(page){
		if(page == undefined){
			page = 1
		}
		$.get('mail/list?page=' + page,function(data){
			$('#processArea').html('')
			if(data.code != 0){
				alert(data.msg)
				return;
			}
			var html = '<form id="mailListForm">'
			html = html + '<ul id="mailList">';
			for(var i in data.data) {
				html = html + '<li mail-id='+data.data[i].messageNumber + ' sender="' + data.data[i].from +'">'
				html = html + '<input name="mailCheckBox" type="checkbox" value=' + data.data[i].messageNumber +'>'
				html = html + '[' + data.data[i].messageNumber + ']<a name="mailTitle" href="javascript:void(0);">' + data.data[i].subject + '</a>'
				html = html + ' ' + new Date(data.data[i].sentDate).toLocaleString()
				html = html + ' <a name="talkLink" href="javascript:void(0);">与TA聊聊</a>'
				html = html + '</li>'
			}
			html = html + '</ul>'
			html = html + '</form>'
			showResponse(html)
			bindingMail()
			bindingTalk()
			document.title = 'Hello World!'
			task.stopScrollTask()
		});
	};

	function bindingMail() {
		$('#mailList > li').each(function(){
			var li = $(this)
			li.children('a[name="mailTitle"]').bind('click', function(){
				$('#command').val('view ' + li.attr("mail-id"))
				$('#sendCommandBtn').click()
			})
		})
	}

	function bindingTalk() {
		$('#mailList > li').each(function(){
			var li = $(this)
			li.children('a[name="talkLink"]').bind('click', function(){
				var sender = li.attr("sender")
				talk.showTalkDialog(sender)
			})
		})
	}
})


