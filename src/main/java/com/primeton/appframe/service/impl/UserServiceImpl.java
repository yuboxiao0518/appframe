package com.primeton.appframe.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.page.PageMethod;
import com.primeton.appframe.common.Constant;
import com.primeton.appframe.common.annotation.ServiceLog;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.common.support.DataCache;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.common.utils.CookieUtil;
import com.primeton.appframe.common.utils.DateUtil;
import com.primeton.appframe.common.utils.IPUtil;
import com.primeton.appframe.mapper.AuthRoleMapper;
import com.primeton.appframe.mapper.AuthUserMapper;
import com.primeton.appframe.model.AuthRole;
import com.primeton.appframe.model.AuthUser;
import com.primeton.appframe.service.UserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<AuthUserMapper,AuthUser> implements UserService {

    private final AuthUserMapper userMapper;
    private final AuthRoleMapper roleMapper;
    private final DataCache dataCache;

    @Override
    @ServiceLog("查询用户列表")
    public PageAjax<AuthUser> queryPage(PageAjax<AuthUser> page, AuthUser user) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<AuthUser> list = userMapper.queryList(user);
        return AppUtil.returnPage(list);
    }

    @ServiceLog("添加用户")
    public AjaxResult saveUser(AuthUser user) {
        String result = null;
        AuthUser $user = userMapper.queryByUsername(user.getUsername());
        if (null == $user) {
            user.setAddtime(DateUtil.getCurDateTime());
            save(user);
        } else {
            result = "用户名已存在";
        }
        return AppUtil.returnObj(result);
    }

    @ServiceLog("修改用户")
    public AjaxResult updateUser(AuthUser user) {
        String result = null;
        AuthUser $user = userMapper.queryByUsername(user.getUsername());
        if (null != $user && $user.getId() != user.getId()) {
            result = "用户名已存在";
        } else {
            userMapper.update(user,Wrappers.query());
        }
        return AppUtil.returnObj(result);
    }

    @ServiceLog("修改个人密码")
    public AjaxResult updatePwd(HttpServletResponse response, HttpServletRequest request, int id, String oldPwd, String newPwd) {
        AuthUser user = null;
        if(StringUtils.isNotEmpty(oldPwd)){
            user = userMapper.selectById(id);
            if(!oldPwd.equals(user.getPassword())){
                return new AjaxResult(0, "旧密码不正确");
            }
        }
        user = new AuthUser();
        user.setId(id);
        user.setPassword(newPwd);
        int ret = userMapper.updateById(user);
        if(ret > 0){
            String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);
            if (StringUtils.isNotEmpty(sessionId)) {
                dataCache.remove(sessionId);
                String userName = (String) dataCache.getValue(sessionId);
                if (StringUtils.isNotEmpty(userName)) {
                    dataCache.remove(userName + IPUtil.getIpAdd(request));
                }
                CookieUtil.delete(Constant.SESSION_IDENTITY_KEY, request, response);
            }
        }
        return new AjaxResult(1, "修改成功");
    }

    @Override
    public AjaxResult deleteByID(int id) {
        baseMapper.deleteById(id);
        return AppUtil.returnObj(null);
    }

    @ServiceLog("查询用户列表")
    public List<AuthUser> queryRoleUsers(int roleid) {
        AuthUser user = new AuthUser();
        user.setRoleid(roleid);
        List<AuthUser> authUsers = userMapper.selectList(Wrappers.query(user));
        return authUsers;
    }

    @ServiceLog("角色绑定用户")
    public AjaxResult bindUser(int roleid, Integer[] ids) {
        AuthRole role = roleMapper.queryByRolename(Constant.ROLE_ANONYMOUS);
        //剔除的用户
        List<AuthUser> userList = new ArrayList<AuthUser>();
        AuthUser user = null;
        //该角色原拥有的用户
        List<Integer> userids = userMapper.queryRoleUids(roleid);
        for(int id: userids){
            if(!Arrays.asList(ids).contains(id)){
                user = new AuthUser();
                user.setId(id);
                user.setRoleid(role.getRoleid());
                userList.add(user);
            }
        }
        updateBatchById(userList,10);
        //新增的用户
        userList = new ArrayList<AuthUser>();
        for(int id: ids){
            user = new AuthUser();
            user.setId(id);
            user.setRoleid(role.getRoleid());
            userList.add(user);
        }
        updateBatchById(userList,10);
        return AppUtil.returnObj(null);
    }

}
