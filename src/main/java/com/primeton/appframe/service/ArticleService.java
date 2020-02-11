package com.primeton.appframe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.model.IArticle;


public interface ArticleService extends IService<IArticle> {

    PageAjax<IArticle> queryArticlePage(PageAjax<IArticle> page, IArticle article);
}
