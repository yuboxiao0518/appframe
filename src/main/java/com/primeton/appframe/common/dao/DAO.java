package com.primeton.appframe.common.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * ClassName: DAO <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午12:11:35 <br/>
 * DAO 基础接口
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
public interface DAO {

	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int save(String str, Object obj);

	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public  int update(String str, Object obj);

	/**
	 * 删除对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public  int delete(String str, Object obj);

	/**
	 * 查找对象
	 * @param <T>
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public <T> T findForObject(String str, Object obj);

	/**
	 * 查找对象集合
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List<?> findForList(String str, Object obj);

	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Map<?, ?> findForMap(String sql, Object obj, String key);

}
