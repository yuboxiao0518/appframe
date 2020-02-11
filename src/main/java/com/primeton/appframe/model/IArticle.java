package com.primeton.appframe.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName(value = "i_article")
@Data
public class IArticle {

	private Integer id;
	/**用户id*/
	private Integer uid;
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
