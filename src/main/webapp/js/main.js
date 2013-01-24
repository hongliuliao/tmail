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
});

function refreshInfo(){
	$.get('mail/1/2',function(data){
		$('#responseArea').html(data.data.mailIntroduction.personal + ':' + data.data.mailIntroduction.subject);
	});
};

function getMailIntroductionsList(){
	$.get('mail/list',function(data){
		var html = '';
		for(var i in data.data) {
			html = html + data.data[i].subject + '<br />'
		}
		$('#responseArea').html(html)
	});
};