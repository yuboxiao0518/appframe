/**
 * Project Name:appframe
 * File Name:AuthUser.java
 * Package Name:com.primeton.appframe.model
 * Date:2017年10月17日下午3:29:53
 * Copyright (c) 2017, Jin.He (mailto:hejin@primeton.com) All Rights Reserved.
 *
*/

package com.primeton.appframe.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * ClassName:AuthUser <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年10月17日 下午3:29:53 <br/>
 * @author   Jin.He (mailto:hejin@primeton.com)
 * @version  
 * @see 	 
 */
@TableName(value = "auth_user")
@Data
public class AuthUser {
    private Integer id;

    private String username;

    private String password;

    private String email;
    @TableField(exist = false)
    private AuthRole role;
    @TableField(exist = false)
    private int status;

    /**
     * 是否可用(0禁用,1可用)
     */
    private Integer useable;

    /**
     * 所属角色
     */
    private Integer roleid;

    /**
     * 创建时间
     */
    private String addtime;

    /**
     * 登陆时间
     */
    private String logintime;

    /**
     * 登陆IP
     */
    private String loginip;

}

