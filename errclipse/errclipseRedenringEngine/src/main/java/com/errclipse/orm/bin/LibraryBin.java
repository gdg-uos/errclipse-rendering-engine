package com.errclipse.orm.bin;

public class LibraryBin {
	int lib_id;
	String level_key;
	String lib_desc;
	long error_count;
	
	public int getLib_id() {
		return lib_id;
	}
	public void setLib_id(int lib_id) {
		this.lib_id = lib_id;
	}
	public String getLevel_key() {
		return level_key;
	}
	public void setLevel_key(String level_key) {
		this.level_key = level_key;
	}
	public String getLib_desc() {
		return lib_desc;
	}
	public void setLib_desc(String lib_desc) {
		this.lib_desc = lib_desc;
	}
	public long getError_count() {
		return error_count;
	}
	public void setError_count(long error_count) {
		this.error_count = error_count;
	}
	
}
