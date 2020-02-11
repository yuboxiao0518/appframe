package com.primeton.appframe.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.primeton.appframe.common.annotation.ServiceLog;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.common.utils.DateUtil;
import com.primeton.appframe.mapper.IFileMapper;
import com.primeton.appframe.model.IFile;
import com.primeton.appframe.service.TechService;
@Service
public class TechServiceImpl extends ServiceImpl<IFileMapper, IFile> implements TechService {

	@Autowired
    private IFileMapper fileMapper;

    @ServiceLog("查询文件列表")
    public PageAjax<IFile> queryFilePage(PageAjax<IFile> page, IFile file) {
//        PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<IFile> iFiles = fileMapper.selectList(Wrappers.query());
        return AppUtil.returnPage(iFiles);
    }

    @ServiceLog("新增文件")
    public AjaxResult addFile(IFile file) {
        file.setAddtime(DateUtil.getCurDateTime());
        fileMapper.insert(file);
        return AppUtil.returnObj(null);
    }

    public IFile queryFileByID(int id) {
        IFile iFile = fileMapper.selectById(id);
        return iFile;
    }

    @ServiceLog("更新文件")
    public AjaxResult updateFile(IFile file) {
        fileMapper.update(file,Wrappers.update());
        return AppUtil.returnObj(null);
    }

    @ServiceLog("删除文件")
    public AjaxResult delFile(int id) {
        IFile file = queryFileByID(id);
        if(null != file){
            fileMapper.deleteById(id);
            File f = new File(file.getSavepath());
            if (f.exists()) {
                f.delete();
            }
        }
        return AppUtil.returnObj(null);
    }
}
