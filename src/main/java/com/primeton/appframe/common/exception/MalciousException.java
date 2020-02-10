package com.primeton.appframe.common.exception;

/**
 * 
 * ClassName: MalciousException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午12:23:17 <br/>
 * 请求频繁异常
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@SuppressWarnings("serial")
public class MalciousException extends RuntimeException {
	private int key;
	private String message;

	public MalciousException() {
	}

	public MalciousException(String message) {
		this.message = message;
	}

	public MalciousException(int key, String message) {
		this.key = key;
		this.message = message;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
