package com.primeton.appframe.service;

import java.io.File;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.appframe.common.annotation.ServiceLog;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.common.utils.DateUtil;
import com.primeton.appframe.mapper.IFileMapper;
import com.primeton.appframe.model.IArticle;
import com.primeton.appframe.model.IFile;

/**
 * 文件管理
 * ClassName: TechService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午1:39:37 <br/>
 *
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@Service
public interface TechService extends IService<IFile> {

    public PageAjax<IFile> queryFilePage(PageAjax<IFile> page, IFile file);

    public AjaxResult addFile(IFile file);

    public AjaxResult updateFile(IFile file);

    public AjaxResult delFile(int id);




}
