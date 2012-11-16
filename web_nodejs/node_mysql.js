var util = require('util');
var mysql = require('mysql');

var client = mysql.createClient({
	user: 'root',
	password: ''
});

client.query('USE errclipse', function(error, results){
	if(error){
		console.log('database connection error' + error);
		return ;
	}
	console.log('connection success');
});

client.query("select * from user", function(error,results,fields){
	if(error){
		console.log("select failed: "+error);
		client.end();
		return ;
	}
	console.log('Results:');
	console.log(results);
	console.log('Filed metadata:');
	console.log(fields);
	console.log(util.inspect(results));

	if(results.length > 0){
		var firstResult = results[0];
	}
});

client.end();
console.log("connection closed");
