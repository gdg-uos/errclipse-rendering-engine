package com.errclipse.orm.bin;

public class MethodBin {
	int method_id;
	String level_key;
	String method_desc;
	int error_count;
	
	public int getMethod_id() {
		return method_id;
	}
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}
	public String getLevel_key() {
		return level_key;
	}
	public void setLevel_key(String level_key) {
		this.level_key = level_key;
	}
	public String getMethod_desc() {
		return method_desc;
	}
	public void setMethod_desc(String method_desc) {
		this.method_desc = method_desc;
	}
	public int getError_count() {
		return error_count;
	}
	public void setError_count(int error_count) {
		this.error_count = error_count;
	}
	
}
