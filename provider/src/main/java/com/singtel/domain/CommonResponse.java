package com.singtel.domain;

import java.io.Serializable;

public class CommonResponse implements Serializable {
	private static final long serialVersionUID = -7537567896573027764L;
	private String code;
	private String message;
	private Object result;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
