package com.primeton.appframe.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.model.AuthUser;
import org.springframework.stereotype.Service;

import com.primeton.appframe.model.ILog;

public interface LogService extends IService<ILog> {

    public PageAjax<ILog> queryPage(PageAjax<ILog> page, ILog log);

}
