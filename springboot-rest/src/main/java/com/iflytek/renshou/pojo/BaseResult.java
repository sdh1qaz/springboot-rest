package com.iflytek.renshou.pojo;

import java.io.Serializable;

public class BaseResult implements Serializable{

	private static final long serialVersionUID = -6008049463520866732L;
	private int result;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
}
