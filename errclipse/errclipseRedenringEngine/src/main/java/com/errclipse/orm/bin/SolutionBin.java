package com.errclipse.orm.bin;

public class SolutionBin {
	String level_key;
	int solution_id;
	String solution_note;
	float global_score;
	int local_score;
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
	public String getSolution_note() {
		return solution_note;
	}
	public void setSolution_note(String solution_note) {
		this.solution_note = solution_note;
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
