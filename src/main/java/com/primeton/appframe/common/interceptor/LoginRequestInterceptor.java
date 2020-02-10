package com.primeton.appframe.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.primeton.appframe.common.Constant;
import com.primeton.appframe.common.exception.AjaxLoginException;
import com.primeton.appframe.common.exception.AjaxPermissionException;
import com.primeton.appframe.common.exception.LoginException;
import com.primeton.appframe.common.exception.PermissionException;
import com.primeton.appframe.common.pojo.Identity;
import com.primeton.appframe.common.support.DataCache;
import com.primeton.appframe.common.utils.CookieUtil;
import com.primeton.appframe.common.utils.IPUtil;
import com.primeton.appframe.model.AuthOperation;


/**
 * 
 * ClassName: LoginRequestInterceptor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午12:25:53 <br/>
 * 权限拦截器
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
public class LoginRequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private DataCache dataCache;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//启动支持@Autowired注解
		WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		//权限验证结果
		boolean isOpera = true;
		//登录信息验证结果
		String loginResult = validateLogin(request, response);

		String requestType = request.getHeader("X-Requested-With");
		String accept = request.getHeader("Accept");
		//ajax请求
		if (requestType != null && "XMLHttpRequest".equals(requestType) && accept.contains("application/json")) {
			if(StringUtils.isNotEmpty(loginResult)){
				if (StringUtils.isNotEmpty(loginResult)) {
					throw new AjaxLoginException(401, loginResult);
				}
			}
			isOpera = validateOperation(request);
			// 校验权限 true有权限， false 没有权限
			if(!isOpera){
				throw new AjaxPermissionException(402, "您没有此操作权限");
			}
		}
		
		if (StringUtils.isNotEmpty(loginResult)) {
			throw new LoginException(401, loginResult);
		}
		isOpera = validateOperation(request);
		if(!isOpera){
			throw new PermissionException(402, "您没有此操作权限");
		}
		return super.preHandle(request, response, handler);
	}

	private String validateLogin(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);
		if (StringUtils.isEmpty(sessionId)) {
			return "您还没有登陆，请登陆";
		}
		String username = dataCache.getString(sessionId);
		if (StringUtils.isEmpty(username)) {
			return "登陆已失效，请重新登陆";
		}
		Identity identity = (Identity) dataCache.getValue(username + IPUtil.getIpAdd(request));
		if (identity == null) {
			return "登陆已失效，请重新登陆";
		}
		String identitySessionId = identity.getSessionId();
		if (!identitySessionId.equals(sessionId)) {
			CookieUtil.delete(Constant.SESSION_IDENTITY_KEY, request, response);
			return "您的账号已经在其他地方登陆，请重新登陆";
		}
		// 设置登录名和权限
		request.setAttribute("loginUser", identity.getLoginUser());
		request.setAttribute("operations", identity.getOperationList());
		return null;
	}

	// 校验权限 true有权限， false 没有权限
	private boolean validateOperation(HttpServletRequest request) {
		String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);
		String username = (String) dataCache.getValue(sessionId);
		Identity identity = (Identity) dataCache.getValue(username + IPUtil.getIpAdd(request));
		List<AuthOperation> list = identity.getOperationList();
		boolean isOper = false;
		String url = request.getServletPath();
		String href = null;
		//动态url过滤,如update/{id}
		String dyUrl = url.substring(url.lastIndexOf("/") + 1);
		if(StringUtils.isNumeric(dyUrl)){
			url = url.substring(0, url.lastIndexOf("/"));
		}
		for (AuthOperation oper : list) {
			href = oper.getOphref();
			if(StringUtils.isNumeric(dyUrl) && href.contains("{")){
				href = href.substring(0, href.lastIndexOf("/"));
			}
			if(url.equals(href)){
				isOper = true;
				break;
			}
		}
		return isOper;
	}

}
