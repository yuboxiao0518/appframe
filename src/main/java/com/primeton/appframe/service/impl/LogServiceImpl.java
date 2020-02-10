package com.primeton.appframe.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.page.PageMethod;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.mapper.ILogMapper;
import com.primeton.appframe.model.ILog;
import com.primeton.appframe.service.LogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl extends ServiceImpl<ILogMapper, ILog> implements LogService {
    @Override
    public PageAjax<ILog> queryPage(PageAjax<ILog> page, ILog log) {
        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<ILog> list = baseMapper.selectList(Wrappers.query(log));
        return AppUtil.returnPage(list);
    }
}
