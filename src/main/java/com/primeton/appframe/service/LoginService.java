package com.primeton.appframe.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.service.IService;
import com.primeton.appframe.model.ILog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.appframe.common.Constant;
import com.primeton.appframe.common.annotation.ServiceLog;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.pojo.Identity;
import com.primeton.appframe.common.pojo.ParamData;
import com.primeton.appframe.common.support.DataCache;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.common.utils.CookieUtil;
import com.primeton.appframe.common.utils.DateUtil;
import com.primeton.appframe.common.utils.IPUtil;
import com.primeton.appframe.common.utils.MD5Util;
import com.primeton.appframe.mapper.AuthRoleMapper;
import com.primeton.appframe.mapper.AuthUserMapper;
import com.primeton.appframe.model.AuthRole;
import com.primeton.appframe.model.AuthUser;

/**
 * 登录管理业务层
 * ClassName: LoginService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午1:35:31 <br/>
 *
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@Service
public interface LoginService {

    public AjaxResult login(HttpServletRequest request, HttpServletResponse response);

    public AjaxResult logout(HttpServletResponse response, HttpServletRequest request);

}
