package com.primeton.appframe.service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.appframe.common.annotation.ServiceLog;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.mapper.AuthRoleMapper;
import com.primeton.appframe.mapper.AuthRoleOperationMapper;
import com.primeton.appframe.model.AuthRole;
import com.primeton.appframe.model.AuthRoleOperation;


/**
 * 权限管理
 * ClassName: RoleService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午1:39:20 <br/>
 *
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
public interface RoleService extends IService<AuthRole> {

    public PageAjax<AuthRole> queryPage(PageAjax<AuthRole> page, AuthRole role);

    public AjaxResult saveRole(AuthRole role);

    public AjaxResult updateRole(AuthRole role);

    public AjaxResult deleteByID(int id);

    public AjaxResult unbindOpers(int roleid, int[] opids);

    public AuthRole queryRoleById(int roleid);

    public AjaxResult bindOpers(int roleid, int[] opids);

    public List<AuthRole> queryNotAdmin();
}
