define('util',['jquery'], function($){
	var Util = function(){}
	Util.prototype = {
		constructor: Util,
		isValid : function(objs){
			for(var i=0;i<objs.length;i++){
				if(objs[i] == null || objs[i] == undefined || objs[i] == ''){
					return false;
				}
			}
			return true
		}
	}
	return new Util()
})