package com.primeton.appframe.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName(value = "i_log")
@Data
public class ILog {

	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	/**请求用户*/
	private String username;
	/**请求描述*/
	private String description;
	/**请求地址*/
	private String url;
	/**执行方法*/
	private String method;
	/**请求参数*/
	private String params;
	/**日志类型(0操作日志;1异常日志)*/
	private Integer type;
	/**请求IP*/
	private String requestip;
	/**异常描述*/
	private String detail;
	/**请求日期*/
	private String operDate;
	
}
