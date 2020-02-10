package com.primeton.appframe.controller;

import java.util.Map;

import com.primeton.appframe.common.utils.AppUtil;
import com.primeton.appframe.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.primeton.appframe.common.annotation.Authority;
import com.primeton.appframe.common.annotation.ControllerLog;
import com.primeton.appframe.common.pojo.AjaxResult;
import com.primeton.appframe.common.pojo.PageAjax;
import com.primeton.appframe.common.utils.DateUtil;
import com.primeton.appframe.model.IArticle;
import com.primeton.appframe.model.IFile;
import com.primeton.appframe.service.TechService;

/**
 * 
 * ClassName: TechController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午1:28:13 <br/>
 *
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@Controller
@RequestMapping("/admin/tech/")
@AllArgsConstructor
public class TechController extends BaseController{

	private final TechService techService;

	private final ArticleService articleService;

	@Authority(opCode = "0201", opName = "文件管理界面")
	@RequestMapping("filePage")
	public String filePage() {
		return "tech/file";
	}

	@ControllerLog("查询文件列表")
	@RequestMapping("queryFilePage")
	@ResponseBody
	@Authority(opCode = "0201", opName = "查询文件列表")
	public PageAjax<IFile> queryFilePage(PageAjax<IFile> page, IFile file) {
		return techService.queryFilePage(page, file);
	}

	@Authority(opCode = "020101", opName = "添加文件页面")
	@RequestMapping("addFilePage")
	public String addFilePage(Map<String, Object> map) {
		return "tech/file_add";
	}

	@ControllerLog("添加文件")
	@RequestMapping("addFile")
	@ResponseBody
	@Authority(opCode = "020101", opName = "添加文件")
	public AjaxResult addFile(IFile file) {
		return techService.addFile(file);
	}

	@Authority(opCode = "020102", opName = "更新文件页面")
	@RequestMapping("updateFilePage/{id}")
	public String updateFilePage(@PathVariable("id") int id, Map<String, Object> map) {
		IFile file = techService.getById(id);
		map.put("file", file);
		return "tech/file_update";
	}

	@ControllerLog("修改文件")
	@RequestMapping("updateFile")
	@ResponseBody
	@Authority(opCode = "020102", opName = "修改文件")
	public AjaxResult updateFile(IFile file) {
		return techService.updateFile(file);
	}

	@ControllerLog("删除文件")
	@RequestMapping("delFile/{id}")
	@ResponseBody
	@Authority(opCode = "020103", opName = "删除文件")
	public AjaxResult delFile(@PathVariable("id") int id) {
		return techService.delFile(id);
	}

	//==================================================文章管理==================================================
	@Authority(opCode = "0202", opName = "文章管理界面")
	@RequestMapping("articlePage")
	public String articlePage(Map<String, Object> map) {
		return "tech/article";
	}

	@ControllerLog("查询文章列表")
	@RequestMapping("queryArticlePage")
	@ResponseBody
	@Authority(opCode = "0202", opName = "查询文章列表")
	public PageAjax<IArticle> queryArticlePage(PageAjax<IArticle> page, IArticle article) {
		return articleService.queryArticlePage(page, article);
	}

	@Authority(opCode = "020201", opName = "添加文章页面")
	@RequestMapping("addArticlePage")
	public String addArticlePage(Map<String, Object> map) {
		return "tech/article_add";
	}

	@ControllerLog("添加文章")
	@RequestMapping("addArticle")
	@ResponseBody
	@Authority(opCode = "020201", opName = "添加文章")
	public AjaxResult addArticle(IArticle article) {
		article.setPosttime(DateUtil.getCurDateTime());
		articleService.save(article);
		return AppUtil.returnObj(null);
	}

	@Authority(opCode = "020202", opName = "更新文章页面")
	@RequestMapping("updateArticlePage/{id}")
	public String updateArticlePage(@PathVariable("id") int id, Map<String, Object> map) {
		IArticle article = articleService.getById(id);
		map.put("article", article);
		return "tech/article_update";
	}

	@ControllerLog("修改文章")
	@RequestMapping("updateArticle")
	@ResponseBody
	@Authority(opCode = "020202", opName = "修改文章")
	public AjaxResult updateArticle(IArticle article) {
		articleService.updateById(article);
		return AppUtil.returnObj(null);
	}

	@ControllerLog("删除文章")
	@RequestMapping("delArticle/{id}")
	@ResponseBody
	@Authority(opCode = "020203", opName = "删除文章")
	public AjaxResult delArticle(@PathVariable("id") int id) {
		articleService.removeById(id);
		return AppUtil.returnObj(null);
	}

	//==================================================文章管理==================================================
	@Authority(opCode = "0203", opName = "测试界面")
	@RequestMapping("testPage")
	public String testPage(Map<String, Object> map) {
		return "tech/article";
	}
}
