define('task',['jquery', 'jsoncookie','util'],function($, jsoncookie, Util){
	$(function(){
		fetchNewMailCount()
		scrollTask()
	})
	function isLogin(){
		return Util.isValid([$.JSONCookie('ACCOUNT_INFO').email]) && JSON.stringify($.JSONCookie('ACCOUNT_INFO')) != '{}'
	}
	var taskRun = false;

	function stopScrollTask(){
		taskRun = false;
	}

	function startScrollTask(){
		taskRun = true
	}

	function scrollTask(){
		if(taskRun) {
			setInterval(Util.scrollTitle(), 500);
		}
	}
	
	function fetchNewMailCount() {
		if(!isLogin() || taskRun){
			setTimeout(fetchNewMailCount, 3000)
			return
		}
		$.ajax({'url':'mail/new'}).done(function(data){
			var newCount = data.data.newMailCount
			if(newCount != 0){
				document.title = '您有新邮件!'
				taskRun = true
				recordLastMessageNumber(data.data.lastMessageNum)
			}
		}).always(function() { setTimeout(fetchNewMailCount, 3000); });
	}
	return {
		stopScrollTask:stopScrollTask,
		startScrollTask:startScrollTask		
	}
})
