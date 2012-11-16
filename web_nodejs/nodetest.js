var mysql = require('./mysql_lib');
var util = require('util');

var ret;

function selectCallback(value){
	console.log(util.inspect(value));
}

mysql.connect('root', '', 'localhost', '3306', 'errclipse');

mysql.select("select * from user", selectCallback);
