package com.primeton.appframe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.primeton.appframe.model.AuthOperation;
import org.apache.ibatis.annotations.Param;

public interface AuthOperationMapper extends BaseMapper<AuthOperation> {

    void bindOpers(@Param("roleid")int roleid);
}