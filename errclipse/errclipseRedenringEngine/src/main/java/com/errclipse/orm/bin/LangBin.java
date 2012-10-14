package com.errclipse.orm.bin;

public class LangBin {
	int language_id;
	String language_desc;
	long solution_count;
	
	public LangBin(String desc,long count){
		this.language_desc = desc;
		this.solution_count = count;
	}
	
	public int getLang_id() {
		return language_id;
	}
	public void setLang_id(int lang_id) {
		this.language_id = lang_id;
	}
	public String getLanguage_desc() {
		return language_desc;
	}
	public void setLanguage_desc(String language_desc) {
		this.language_desc = language_desc;
	}
	public long getsolution_count() {
		return solution_count;
	}
	public void setsolution_count(long solution_count) {
		this.solution_count = solution_count;
	}
}
