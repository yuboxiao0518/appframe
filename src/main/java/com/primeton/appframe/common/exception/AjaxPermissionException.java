package com.primeton.appframe.common.exception;


/**
 * 
 * ClassName: AjaxPermissionException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午12:22:37 <br/>
 * Ajax请求异常
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@SuppressWarnings("serial")
public class AjaxPermissionException extends RuntimeException {
	private int key;
	private String message;

	public AjaxPermissionException() {
	}

	public AjaxPermissionException(String message) {
		this.message = message;
	}

	public AjaxPermissionException(int key, String message) {
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
