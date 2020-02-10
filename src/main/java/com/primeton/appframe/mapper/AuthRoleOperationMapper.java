package com.primeton.appframe.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.primeton.appframe.model.AuthRoleOperation;

public interface AuthRoleOperationMapper extends BaseMapper<AuthRoleOperation> {

	void batchInsert(List<AuthRoleOperation> list);

	void delRoleOpers(List<AuthRoleOperation> list);
}