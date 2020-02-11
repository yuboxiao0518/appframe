package com.primeton.appframe.model;


import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName(value = "auth_role_operation")
@Data
public class AuthRoleOperation {
    /**
     * 角色ID
     */
    private Integer roleid;

    /**
     * 权限ID
     */
    private Integer opid;

   
}