package com.errclipse.orm.bin;

public class LibraryBin {
	int library_id;
	String level_key;
	String library_desc;
	long solution_count;

	public LibraryBin(String level_key,String desc,long count){
		this.level_key = level_key;
		this.library_desc = desc;
		this.solution_count = count;
	}

	public int getLib_id() {
		return library_id;
	}
	public void setLib_id(int lib_id) {
		this.library_id = lib_id;
	}
	public String getLevel_key() {
		return level_key;
	}
	public void setLevel_key(String level_key) {
		this.level_key = level_key;
	}
	public String getLib_desc() {
		return library_desc;
	}
	public void setLib_desc(String lib_desc) {
		this.library_desc = lib_desc;
	}
	public long getsolution_count() {
		return solution_count;
	}
	public void setsolution_count(long solution_count) {
		this.solution_count = solution_count;
	}

}
