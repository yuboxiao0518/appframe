package com.primeton.appframe.common.pojo;

import java.util.List;

import com.primeton.appframe.model.AuthOperation;
import com.primeton.appframe.model.AuthUser;


/**
 * 
 * ClassName: Identity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午12:31:04 <br/>
 * 封装Session
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
public class Identity {
	private String sessionId;
	private String loginIp;
	private AuthUser loginUser;
	private List<AuthOperation> operationList;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public AuthUser getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(AuthUser loginUser) {
		this.loginUser = loginUser;
	}
	public List<AuthOperation> getOperationList() {
		return operationList;
	}
	public void setOperationList(List<AuthOperation> operationList) {
		this.operationList = operationList;
	}
	
}
