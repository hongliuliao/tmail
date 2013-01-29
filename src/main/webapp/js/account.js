$(function(){
	showOtherAccounts()
	
	$('#accountHistorySel').bind('change',function(){
		var selectValue = $("#accountHistorySel option:selected").val();
		var accountInfo = JSON.parse(selectValue)
		$('#accountEmail').val(accountInfo.email)
		$('#accountPassword').val(accountInfo.password)
	})
	
	fetchNewMailCount()
	scrollTask()
	
})

function isLogin(){
	return isValid([$.JSONCookie('ACCOUNT_INFO').email]) && JSON.stringify($.JSONCookie('ACCOUNT_INFO')) != '{}'
}

function addOtherAccount(accountInfo){
	var otherAccounts = $.JSONCookie('OTHER_ACCOUNTS')
	if(JSON.stringify(otherAccounts) == '{}'){
		otherAccounts = [accountInfo]
		$.JSONCookie('OTHER_ACCOUNTS', otherAccounts)
		return;
	}
	
	for(var i=0;i<otherAccounts.length;i++){
		if(otherAccounts[i].email == accountInfo.email){
			otherAccounts.splice(i, 1)
		}
	}
	
	otherAccounts.push(accountInfo)
	$.JSONCookie('OTHER_ACCOUNTS', otherAccounts)
}

function showOtherAccounts() {
	var otherAccounts = $.JSONCookie('OTHER_ACCOUNTS')
	if(otherAccounts == {}){
		return
	}
	for(var i=otherAccounts.length -1 ;i>=0;i--){
		var accountInfo = JSON.stringify(otherAccounts[i])
		$("#accountHistorySel").append("<option value='"+accountInfo+"'>"+otherAccounts[i].email+"</option>")
	}
}

var taskRun = false;

function scrollTitle(){
	if(taskRun) {
		var title = document.title;
		var firstch = title.charAt(0);
		var leftstr = title.substring(1, title.length);
		document.title = leftstr + firstch;
	}
}

function stopScrollTask(){
	taskRun = false;
}

function startScrollTask(){
	taskRun = true
}

function scrollTask(){
	setInterval("scrollTitle()", 500);
}

function fetchNewMailCount() {
	if(!isLogin() || taskRun){
		setTimeout("fetchNewMailCount()", 3000)
		return
	}
	$.ajax({'url':'mail/new'}).done(function(data){
		var newCount = data.data.newMailCount
		if(newCount != 0){
			document.title = '您有新邮件!'
			taskRun = true
			recordLastMessageNumber(data.data.lastMessageNum)
		}
	}).always(function() { setTimeout("fetchNewMailCount()", 3000); });
}

function recordLastMessageNumber(lastMessageNum){
	var accountInfo = $.JSONCookie('ACCOUNT_INFO')
	accountInfo.lastMessageNum = lastMessageNum
	$.JSONCookie('ACCOUNT_INFO', accountInfo)
}
