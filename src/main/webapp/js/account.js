$(function(){
	showOtherAccounts()
	
	$('#accountHistorySel').bind('change',function(){
		var selectValue = $("#accountHistorySel option:selected").val();
		var accountInfo = JSON.parse(selectValue)
		$('#accountEmail').val(accountInfo.email)
		$('#accountPassword').val(accountInfo.password)
	})
})
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
