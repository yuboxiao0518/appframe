package com.primeton.appframe.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName(value  = "i_file")
@Data
public class IFile {

	@TableId
	private String id;
	/**文件原名*/
	private String filename;
	/**文件名称*/
	private String nowname;
	/**存放位置*/
	private String savepath;
	/**访问地址*/
	private String url;
	/**文件类型*/
	private String filetype;
	/**文件大小*/
	private long filesize;
	/**添加时间*/
	private String addtime;
	
	
	
}
