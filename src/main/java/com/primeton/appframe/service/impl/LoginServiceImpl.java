package com.primeton.appframe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primeton.appframe.common.Constant;
import com.primeton.appframe.common.annotation.ServiceLog;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.pojo.Identity;
import com.primeton.appframe.common.pojo.ParamData;
import com.primeton.appframe.common.support.DataCache;
import com.primeton.appframe.common.utils.*;
import com.primeton.appframe.mapper.AuthRoleMapper;
import com.primeton.appframe.mapper.AuthUserMapper;
import com.primeton.appframe.mapper.ILogMapper;
import com.primeton.appframe.model.AuthRole;
import com.primeton.appframe.model.AuthUser;
import com.primeton.appframe.model.ILog;
import com.primeton.appframe.service.LoginService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthUserMapper userMapper;
    private final AuthRoleMapper roleMapper;
    private final DataCache dataCache;

    @ServiceLog("登录")
    public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
        String verifyCode = (String) request.getSession().getAttribute(Constant.VERIFY_CODE);
        String result = null;
        ParamData params = new ParamData();
        String vcode = params.getString("vcode");
        if (params.containsKey("vcode") && (StringUtils.isEmpty(verifyCode) || !verifyCode.equalsIgnoreCase(vcode))) {
            result = "验证码错误";
        }else{
            String username = params.getString("username");
            String loginIp = params.getString("loginIp");
            String key = username + loginIp + Constant.LOGIN_ERROR_TIMES;
            AuthUser user = userMapper.queryByUsername(username);

            if (user == null || !user.getPassword().equals(params.getString("password"))) {
                int errTimes = dataCache.getInt(key);
                //记录密码错误次数,达到3次则需要输出验证码
                dataCache.setValue(key, errTimes += 1);
                result = "用户名或密码错误|" + errTimes;
            }else if(Constant.ROLE_ANONYMOUS.equals(user.getRole().getRolename())){
                result = "用户未分组,无法登录";
            }else{
                // 更新登录IP和登录时间
                user.setLoginip(loginIp);
                user.setLogintime(DateUtil.getCurDateTime());
                userMapper.updateById(user);
//                userMapper.updateByPrimaryKeySelective(user);

                Identity identity = new Identity();
                AuthRole role = roleMapper.queryRoleById(user.getRoleid());

                identity.setOperationList(role.getOperations());
                identity.setLoginUser(user);
                identity.setLoginIp(loginIp);
                String sessionId = getSessionId(username, identity.getLoginIp());
                identity.setSessionId(sessionId);
                dataCache.setValue(username + loginIp, identity);
                dataCache.setValue(sessionId, username);
                dataCache.remove(key);
                CookieUtil.set(Constant.SESSION_IDENTITY_KEY, sessionId, response);
            }
        }
        return AppUtil.returnObj(result);
    }

    @ServiceLog("退出")
    public AjaxResult logout(HttpServletResponse response, HttpServletRequest request) {
        String sessionId = CookieUtil.get(Constant.SESSION_IDENTITY_KEY, request);

        if (StringUtils.isNotEmpty(sessionId)) {
            dataCache.remove(sessionId);
            String userName = (String) dataCache.getValue(sessionId);
            if (StringUtils.isNotEmpty(userName)) {
                dataCache.remove(userName + IPUtil.getIpAdd(request));
            }
            CookieUtil.delete(Constant.SESSION_IDENTITY_KEY, request, response);
        }
        return AppUtil.returnObj(null);
    }

    private String getSessionId(String userName, String ip) {
        String str = userName + "_" + System.currentTimeMillis() + "_" + ip;
        try {
            return MD5Util.encrypt(str);
        } catch (Exception e) {
            return "生成token错误";
        }
    }
}
