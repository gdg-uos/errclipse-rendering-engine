package com.errclipse.orm.bin;

public class ErrorBin {
	int error_id;
	String level_key;
	String error_desc;
	int solution_count;
	
	public ErrorBin(String level_key, String desc, int count){
		this.level_key = level_key;
		this.error_desc = desc;
		this.solution_count = count;
	}
	
	public int getError_id() {
		return error_id;
	}
	public void setError_id(int error_id) {
		this.error_id = error_id;
	}
	public String getLevel_key() {
		return level_key;
	}
	public void setLevel_key(String level_key) {
		this.level_key = level_key;
	}
	public String getError_desc() {
		return error_desc;
	}
	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}
	public int getsolution_count() {
		return solution_count;
	}
	public void setsolution_count(int solution_count) {
		this.solution_count = solution_count;
	}
}
