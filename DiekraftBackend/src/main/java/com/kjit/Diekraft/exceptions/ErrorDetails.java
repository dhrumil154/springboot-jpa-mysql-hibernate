package com.kjit.Diekraft.exceptions;

public class ErrorDetails extends RuntimeException {
	private String message;
	private Integer httpStatusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public ErrorDetails(String message, Integer httpStatusCode) {
		super();
		this.message = message;
		this.httpStatusCode = httpStatusCode;
	}

    public ErrorDetails() {
    }
}
