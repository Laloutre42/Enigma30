package com.zed.enigme.enumeration;

public enum EnigmaExecutionResult {

	NOT_FOUND(1),
	FOUND(2),
	FINISHED(3);

	private int num;

	private EnigmaExecutionResult(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
