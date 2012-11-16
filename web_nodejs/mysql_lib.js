var util = require('util');
var mysqllib = require('mysql');
var connected = false;
var connection;
var client;

module.exports =  {
	connect: function(userId, userPassword, targetHost, targetHost, targetDB){
		var id= '', pw = '', host = '', port = '',db = '';
	
		id = userId;
		
		if(userPassword != undefined)
			pw = userPassword;
		if(host != undefined)
			host = targetHost;
		if(port != undefined)
			port = targetHost;
		if(targetDB != undefined)
			db = targetDB;

		client = mysqllib.createClient({
			user: id,
			password: pw
		});

		if(db){
			client.query('USE '+db, function(error, results){
				if(error)
						return error;
				else
					return true;
			});
		}			
	},

	select: function(query, callback){
		var ret;
		client.query(query, function(error, results, fields){
			if(error){
				callback(error);
			}else{
				callback(results);
			}
		});
	},

	insert: function(query,callback)	{
		var ret;
		client.query(query, function(error,results){
			if(error){
				callback(error);
			}else{
				callback(results);
			}
		});
	},

	update: function(query,callback)	{
		var ret;
		client.query(query, function(error,results){
			if(error){
				callback(error);
			}else{
				callback(results);
			}
		});
	},

	delete: function(query,callback)	{
		var ret;
		client.query(query, function(error,results){
			if(error){
				callback(error);
			}else{
				callback(results);
			}
		});
	},

	getClient: function(){
		return client;
	}
}
