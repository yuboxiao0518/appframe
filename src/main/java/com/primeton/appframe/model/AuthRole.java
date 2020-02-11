/**
 * Project Name:appframe
 * File Name:AuthRole.java
 * Package Name:com.primeton.appframe.model
 * Date:2017年10月17日下午3:30:58
 * Copyright (c) 2017, Jin.He (mailto:hejin@primeton.com) All Rights Reserved.
 *
*/

package com.primeton.appframe.model;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
/**
 * ClassName:AuthRole <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年10月17日 下午3:30:58 <br/>
 * @author   Jin.He (mailto:hejin@primeton.com)
 * @version  
 * @see 	 
 */

@TableName(value  = "auth_role")
@Data
public class AuthRole {
	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Integer roleid;

	/**
	 * 角色名称
	 */
	private String rolename;

	/**
	 * 中文名
	 */
	private String cname;
	
	/**
	 * 角色权限
	 */
	@TableField(exist = false)
	private List<AuthOperation> operations = new ArrayList<AuthOperation>();

	
}

