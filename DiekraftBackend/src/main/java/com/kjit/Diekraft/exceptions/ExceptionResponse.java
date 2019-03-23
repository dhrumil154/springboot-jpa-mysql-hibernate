package com.kjit.Diekraft.exceptions;


public class ExceptionResponse {

	private int code;
	private String description;
	public int getCode()
	{
		return code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCode(int code) {
		this.code = code;
	}

    public ExceptionResponse(String description,int code) {
        this.code = code;
        this.description = description;
    }
}
