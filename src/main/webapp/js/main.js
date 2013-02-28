requirejs.config({
	baseUrl: 'js',
	paths: {
		'jquery':'lib/jquery-1.9.0.min',
		'mustache':'lib/mustache',
		'jquery.cookie':'lib/jquery.cookie',
		'json':'lib/json2',
		'jquery.jsoncookie':'lib/jquery.jsoncookie',
		'mail':'modules/mail',
		'account':'modules/account',
		'util':'modules/util',
		'task':'modules/task',
		'talk':'modules/talk'
	}
})

require(['mail', 'account', 'util', 'task', 'talk']);
