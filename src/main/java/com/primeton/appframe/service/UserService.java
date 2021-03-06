package com.primeton.appframe.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.service.IService;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.model.AuthUser;

/**
 * 用户管理
 * ClassName: UserService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午1:40:08 <br/>
 *
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */

public interface UserService extends IService<AuthUser> {

	public PageAjax<AuthUser> queryPage(PageAjax<AuthUser> page, AuthUser user);

	public AjaxResult saveUser(AuthUser user);

	public AjaxResult updateUser(AuthUser user);

	public AjaxResult updatePwd(HttpServletResponse response, HttpServletRequest request, int id, String oldPwd, String newPwd);

	public AjaxResult deleteByID(int id);

	public List<AuthUser> queryRoleUsers(int roleid);

	public AjaxResult bindUser(int roleid, Integer[] ids);
}
