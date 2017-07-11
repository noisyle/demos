package com.noisyle.demo.annotation;


public class MyEntity {
	private long id;
	private String code;
	
	MyEntity(long id, String code){
		this.id = id;
		this.code = code;
	}
	
	public long getId(){
		return this.id;
	}

	public String getCode() {
		return code;
	}
}
