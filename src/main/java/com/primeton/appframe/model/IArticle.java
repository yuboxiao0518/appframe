package com.primeton.appframe.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName(value = "i_article")
@Data
public class IArticle {

	@TableId
	private String id;
	/**用户id*/
	private String uid;
	/**标题*/
	private String title;
	/**标题颜色*/
	private String color;
	/**封面*/
	private String cover;
	/**内容*/
	private String content;
	/**发表时间*/
	private String posttime;
	
	
}
