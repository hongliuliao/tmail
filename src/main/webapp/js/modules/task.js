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

	function scrollTitle(){
		if(taskRun) {
			var title = document.title;
			var firstch = title.charAt(0);
			var leftstr = title.substring(1, title.length);
			document.title = leftstr + firstch;
		}
	}
	
	function scrollTask(){
		setInterval(scrollTitle, 500);
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
			}
		}).always(function() { setTimeout(fetchNewMailCount, 3000); });
	}
	return {
		stopScrollTask:stopScrollTask,
		startScrollTask:startScrollTask		
	}
})
