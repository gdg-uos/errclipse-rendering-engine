package com.errclipse.orm.bin;

public class LangBin {
	int lang_id;
	String language_desc;
	long error_count;
	
	public LangBin(String desc,long count){
		this.language_desc = desc;
		this.error_count = count;
	}
	
	public int getLang_id() {
		return lang_id;
	}
	public void setLang_id(int lang_id) {
		this.lang_id = lang_id;
	}
	public String getLanguage_desc() {
		return language_desc;
	}
	public void setLanguage_desc(String language_desc) {
		this.language_desc = language_desc;
	}
	public long getError_count() {
		return error_count;
	}
	public void setError_count(long error_count) {
		this.error_count = error_count;
	}
}
