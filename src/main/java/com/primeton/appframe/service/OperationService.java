package com.primeton.appframe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;
import com.primeton.appframe.common.pojo.PageAjax;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeton.appframe.common.Constant;
import com.primeton.appframe.common.MapperKey;
import com.primeton.appframe.common.annotation.Authority;
import com.primeton.appframe.common.utils.AnnotationUtil;
import com.primeton.appframe.common.utils.JsonConvertUtil;
import com.primeton.appframe.common.utils.MD5Util;
import com.primeton.appframe.model.AuthOperation;
import com.primeton.appframe.model.AuthRole;
import com.primeton.appframe.model.AuthUser;

/**
 * 权限管理业务层
 * ClassName: OperationService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午1:35:57 <br/>
 *
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
public interface OperationService extends IService<AuthOperation> {

    public void initAuthority();

    public PageAjax<AuthOperation> queryPage(PageAjax<AuthOperation> page, AuthOperation operation);
}
