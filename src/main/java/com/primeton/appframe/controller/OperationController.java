package com.primeton.appframe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.primeton.appframe.common.annotation.Authority;
import com.primeton.appframe.common.annotation.ControllerLog;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.model.AuthOperation;
import com.primeton.appframe.service.OperationService;

/**
 * 
 * ClassName: OperationController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午1:27:23 <br/>
 *
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@Controller
@RequestMapping("/admin/oper/")
public class OperationController extends BaseController {

	@Autowired
	private OperationService opService;

	@Authority(opCode = "0103", opName = "权限管理界面")
	@RequestMapping("mainPage")
	public String mainPage(){
		return "auth/oper/main";
	}

	@ControllerLog("查询权限列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "0103", opName = "查询权限列表")
    public PageAjax<AuthOperation> queryPage(PageAjax<AuthOperation> page, AuthOperation oper) {
        return opService.queryPage(page, oper);
    }
}
