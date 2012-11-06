package com.errclipse.orm.bin;

public class SolutionBin {
	String level_key;
	int solution_id;
	String solution_desc;
	float global_score;
	int local_score;
	
	public SolutionBin(){};
	public SolutionBin(String level_key, String desc){
		this.level_key = level_key;
		this.solution_desc = desc;
		this.global_score = 0;
		this.local_score = 0;
	}
	public String getLevel_key() {
		return level_key;
	}
	public void setLevel_key(String level_key) {
		this.level_key = level_key;
	}
	public int getSolution_id() {
		return solution_id;
	}
	public void setSolution_id(int solution_id) {
		this.solution_id = solution_id;
	}
	public String getSolution_desc() {
		return solution_desc;
	}
	public void setSolution_desc(String solution_desc) {
		this.solution_desc = solution_desc;
	}
	public float getGlobal_score() {
		return global_score;
	}
	public void setGlobal_score(float global_score) {
		this.global_score = global_score;
	}
	public int getLocal_score() {
		return local_score;
	}
	public void setLocal_score(int local_score) {
		this.local_score = local_score;
	}
}
