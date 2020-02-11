package com.primeton.appframe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.mapper.IArticleMapper;
import com.primeton.appframe.model.IArticle;
import com.primeton.appframe.service.ArticleService;

@Service
public class ArticleServiceImpl extends ServiceImpl<IArticleMapper, IArticle> implements ArticleService {
    @Override
    public PageAjax<IArticle> queryArticlePage(PageAjax<IArticle> page, IArticle article) {
//        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<IArticle> list = baseMapper.selectList(Wrappers.query(article));
        return AppUtil.returnPage(list);
    }
}
