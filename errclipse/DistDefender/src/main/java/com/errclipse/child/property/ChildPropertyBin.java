package com.errclipse.child.property;

public class ChildPropertyBin {
	int child_id;
	String db_driver;
	String db_url;
	String db_username;
	String db_passwd;
	String child_key;
	
	public ChildPropertyBin(){}
	
	public ChildPropertyBin(String driver, String url, String user,String passwd){
		this.db_driver = driver;
		this.db_url = url;
		this.db_username = user;
		this.db_passwd = passwd;	
		this.child_key = calculateChildKey();
	}	
	public String calculateChildKey(){
		return this.db_driver + this.db_url + this.db_username + this.db_passwd;
	}
	public String getDb_username() {
		return db_username;
	}
	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}
	public String getChild_key() {
		return child_key;
	}
	public void setChild_key(String child_key) {
		this.child_key = child_key;
	}
	public String getDb_driver() {
		return db_driver;
	}
	public void setDb_driver(String db_driver) {
		this.db_driver = db_driver;
	}
	public String getDb_url() {
		return db_url;
	}
	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}
	public String getDb_passwd() {
		return db_passwd;
	}
	public void setDb_passwd(String db_passwd) {
		this.db_passwd = db_passwd;
	}
	public int getChild_id() {
		return child_id;
	}
	public void setChild_id(int child_id) {
		this.child_id = child_id;
	}
	
}
