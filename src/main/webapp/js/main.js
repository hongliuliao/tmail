requirejs.config({
	baseUrl: 'js',
	paths: {
		'jquery':'common/jquery-1.9.0.min',
		'mustache':'common/mustache',
		'jquery.cookie':'common/jquery.cookie',
		'json':'common/json2',
		'jquery.jsoncookie':'common/jquery.jsoncookie'
	}
})

define("jsoncookie", ['jquery', 'jquery.cookie', 'json', 'jquery.jsoncookie'])

require(['mail', 'account', 'task', 'talk']);
