package com.primeton.appframe.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.page.PageMethod;
import com.primeton.appframe.common.Constant;
import com.primeton.appframe.common.MapperKey;
import com.primeton.appframe.common.annotation.Authority;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.common.utils.AnnotationUtil;
import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.common.utils.JsonConvertUtil;
import com.primeton.appframe.common.utils.MD5Util;
import com.primeton.appframe.mapper.AuthOperationMapper;
import com.primeton.appframe.mapper.AuthRoleMapper;
import com.primeton.appframe.mapper.AuthUserMapper;
import com.primeton.appframe.model.AuthOperation;
import com.primeton.appframe.model.AuthRole;
import com.primeton.appframe.model.AuthUser;
import com.primeton.appframe.model.IArticle;
import com.primeton.appframe.service.OperationService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Service
@AllArgsConstructor
public class OperationServiceImpl extends ServiceImpl<AuthOperationMapper,AuthOperation> implements OperationService {

    // 扫描包
    private static final String PERMISS_PACKAGE = "com.primeton.appframe.controller";
    private static final String ADMIN = "admin";

    private final AuthRoleMapper roleMapper;
    private final AuthUserMapper userMapper;
    private final AuthOperationMapper operationMapper;
    /**
     * 初始化权限
     */
    @Transactional // 开启事务
    @SuppressWarnings("unchecked")
    public void initAuthority() {
        List<AuthOperation> operationList = baseMapper.selectList(Wrappers.query());
        List<String> opIdList = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(operationList)) {
            for (AuthOperation operation : operationList) {
                opIdList.add(operation.getOpid() + "");
            }
        }

        // 要保存的列表
        List<AuthOperation> saveList = new ArrayList<AuthOperation>();
        // 要修改的列表
        List<AuthOperation> updateList = new ArrayList<AuthOperation>();

        // 同步json文件(左侧菜单栏权限)里面的权限
        List<AuthOperation> operationJsonList = JsonConvertUtil.json2List("operations.json", AuthOperation.class);
        if (operationJsonList != null) {
            for (AuthOperation operation : operationJsonList) {
                AuthOperation $operation = baseMapper.selectOne(Wrappers.query(operation));
                if ($operation != null) {
                    $operation.setOpname(operation.getOpname());
                    $operation.setOphref(operation.getOphref());
                    $operation.setOpseq(operation.getOpseq());
                    updateList.add(operation);
                    // 剔除已经更新的数据
                    opIdList.remove($operation.getOpid() + "");
                } else {
                    $operation = new AuthOperation();
                    $operation.setOpcode(operation.getOpcode());
                    $operation.setOpname(operation.getOpname());
                    $operation.setOphref(operation.getOphref());
                    $operation.setOpseq(operation.getOpseq());
                    saveList.add(operation);
                }
            }
        }

        // 同步注解里面的权限
        Map<String, Authority> permissionMap = AnnotationUtil.getMethodAnnotaionByPackage(PERMISS_PACKAGE, Authority.class);
        Set<Map.Entry<String, Authority>> entrySet = permissionMap.entrySet();
        for (Map.Entry<String, Authority> entry : entrySet) {
            Authority permission = entry.getValue();
            String href = entry.getKey();
            String code = permission.opCode();
            if (StringUtils.isEmpty(code)) {
                continue;
            }
            AuthOperation $operation = new AuthOperation();
            $operation.setOpcode(code);
            $operation.setOphref(href);
            AuthOperation operation = baseMapper.selectOne(Wrappers.query($operation));
            if (operation != null) {
                operation.setOpname(permission.opName());
                operation.setOphref(href);
                operation.setOpseq(permission.opSeq());
                updateList.add(operation);
                // 剔除已经更新的数据
                opIdList.remove(operation.getOpid() + "");
            } else {
                operation = new AuthOperation();
                operation.setOpname(permission.opName());
                operation.setOphref(href);
                operation.setOpseq(permission.opSeq());
                operation.setOpcode(code);
                saveList.add(operation);
            }
        }

        // 批量保存新增的权限
        if (CollectionUtils.isNotEmpty(saveList)) {
            saveBatch(saveList);
        }
        // 批量更新修改的权限
        if (CollectionUtils.isNotEmpty(updateList)) {
            updateBatchById(updateList);
        }
        // 删除的权限值
        if (CollectionUtils.isNotEmpty(opIdList)) {
            baseMapper.deleteBatchIds(opIdList);
        }

        AuthRole authRole = new AuthRole();
        authRole.setRolename(Constant.ROLE_ANONYMOUS);
        AuthRole role = roleMapper.selectOne(Wrappers.query(authRole));
        // 创建匿名用户组

        if (null == role) {
            role = new AuthRole();
            role.setRolename(Constant.ROLE_ANONYMOUS);
            role.setCname("匿名用户组");
            roleMapper.insert(role);
        }
        AuthRole authRole1 = new AuthRole();
        authRole1.setRolename(ADMIN);
        role = roleMapper.selectOne(Wrappers.query(authRole1));

        // 创建超级管理员
        if (null == role) {
            role = new AuthRole();
            role.setRolename(ADMIN);
            role.setCname("超级管理员");
            roleMapper.insert(role);
        }
        int roleid = role.getRoleid();
        AuthUser user = userMapper.queryByUsername(ADMIN);
        // 创建超级用户
        if (null == user) {
            user = new AuthUser();
            user.setUsername(ADMIN);
            try {
                String password = MD5Util.encrypt(ADMIN);
                user.setPassword(password);
            } catch (Exception e) {
            }
            user.setRoleid(roleid);
            userMapper.insert(user);
        }
        // 更新超级管理员权限
        operationMapper.bindOpers(roleid);


//		operationList = (List<AuthOperation>) dao.findForList(MapperKey.OPERATION_queryAllOpers, null);
//		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
//		Map<String, Integer> map = null;
//		for (AuthOperation operation : operationList) {
//			map = new HashMap<String, Integer>();
//			map.put("roleid", roleid);
//			map.put("opid", operation.getOpid());
//			int count = dao.findForObject(MapperKey.OPERATION_queryOperCount, map);
//			if (count <= 0) {
//				list.add(map);
//			}
//		}
//		if (CollectionUtils.isNotEmpty(list)) {
//			dao.batchSave(MapperKey.OPERATION_batchSaveGroupOper, list);
//		}
    }

    @Override
    public PageAjax<AuthOperation> queryPage(PageAjax<AuthOperation> page, AuthOperation operation) {
    	PageMethod.startPage(page.getPageNo(), page.getPageSize());
        List<AuthOperation> list = baseMapper.selectList(Wrappers.query(operation));
        return AppUtil.returnPage(list);
    }
}
