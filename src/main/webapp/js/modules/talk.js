define('talk',['jquery', 'mustache'], function($, Mustache){
	function showTalkDialog(talkto) {
		var data = {'talkto':talkto}
		var html = Mustache.render($('#talkdialogTemplate').html(), data)
		$('body').append(html)
	}
	return {
		showTalkDialog:showTalkDialog
	}
})
