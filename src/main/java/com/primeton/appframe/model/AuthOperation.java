package com.primeton.appframe.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName(value  = "auth_operation")
@Data
public class AuthOperation {
    /**
     * 主键
     */
    private Integer opid;

    /**
     * 权限值
     */
    private String opcode;

    /**
     * 权限名称
     */
    private String opname;

    /**
     * 权限操作链接
     */
    private String ophref;

    /**
     * 显示顺序
     */
    private Integer opseq;

   
}