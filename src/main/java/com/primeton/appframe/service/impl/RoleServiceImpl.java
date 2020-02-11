package com.primeton.appframe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primeton.appframe.common.annotation.ServiceLog;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.mapper.AuthRoleMapper;
import com.primeton.appframe.mapper.AuthRoleOperationMapper;
import com.primeton.appframe.model.AuthRole;
import com.primeton.appframe.model.AuthRoleOperation;
import com.primeton.appframe.service.RoleService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements RoleService {

    private final AuthRoleMapper roleMapper;

    private final AuthRoleOperationMapper roleOperMapper;

    public AuthRole queryRoleById(int roleid){
        return roleMapper.queryRoleById(roleid);
    }

    @ServiceLog("新增角色")
    public AjaxResult saveRole(AuthRole role) {
        String result = null;
        AuthRole $role = roleMapper.queryByRolename(role.getRolename());
        if (null == $role) {
            save(role);
        } else {
            result = "角色名已存在";
        }
        return AppUtil.returnObj(result);
    }

    @ServiceLog("更新角色")
    public AjaxResult updateRole(AuthRole role) {
        String result = null;
        AuthRole $role = roleMapper.queryByRolename(role.getRolename());
        if (null != $role && $role.getRoleid() != role.getRoleid()) {
            result = "角色名已存在";
        } else {
            roleMapper.updateById(role);
        }
        return AppUtil.returnObj(result);
    }

    @Override
    public AjaxResult deleteByID(int id) {
        baseMapper.deleteById(id);
        return AppUtil.returnObj(null);
    }

    public List<AuthRole> queryNotAdmin() {
        AuthRole authRole = new AuthRole();
        authRole.setRolename("admin");
        QueryWrapper<AuthRole> query = Wrappers.query(authRole);
        List<AuthRole> authRoles = roleMapper.selectList(query);
        return authRoles;
    }

    @ServiceLog("绑定角色权限")
    public AjaxResult bindOpers(int roleid, int[] opids) {
        List<AuthRoleOperation> list = new ArrayList<AuthRoleOperation>();
        AuthRoleOperation roleOperation = null;
        for(int opid: opids){
            roleOperation = new AuthRoleOperation();
            roleOperation.setRoleid(roleid);
            roleOperation.setOpid(opid);
            list.add(roleOperation);
        }
        //通用mapper的批量插入竟然不行
//		roleOperMapper.insertList(list);
        roleOperMapper.batchInsert(list);
        return AppUtil.returnObj(null);
    }

    @ServiceLog("解除角色权限")
    public AjaxResult unbindOpers(int roleid, int[] opids){
        List<AuthRoleOperation> list = new ArrayList<AuthRoleOperation>();
        AuthRoleOperation roleOperation = null;
        for(int opid: opids){
            roleOperation = new AuthRoleOperation();
            roleOperation.setRoleid(roleid);
            roleOperation.setOpid(opid);
            list.add(roleOperation);
        }
        roleOperMapper.delRoleOpers(list);
        return AppUtil.returnObj(null);
    }


    @Override
    public PageAjax<AuthRole> queryPage(PageAjax<AuthRole> page, AuthRole role) {
//        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<AuthRole> list = baseMapper.selectList(Wrappers.query(role));
        return AppUtil.returnPage(list);
    }
}
