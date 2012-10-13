package com.errclipse.orm.bin;

public class MethodBin {
	int method_id;
	String level_key;
	String method_desc;
	int solution_count;
	
	public MethodBin(String level_key, String desc, int count){
		this.level_key = level_key;
		this.method_desc = desc;
		this.solution_count = count;
	}
	
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
	public int getsolution_count() {
		return solution_count;
	}
	public void setsolution_count(int solution_count) {
		this.solution_count = solution_count;
	}
	
}
