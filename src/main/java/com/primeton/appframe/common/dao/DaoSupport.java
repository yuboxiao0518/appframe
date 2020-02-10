package com.primeton.appframe.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * ClassName: DaoSupport <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年10月17日 下午12:12:00 <br/>
 * DAO 基础接口实现
 * @author Jin.He (mailto:hejin@primeton.com)
 * @version
 */
@Repository("daoSupport")
public class DaoSupport implements DAO {

	private final static String PACKAGE = "com.primeton.appframe.mapper.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public  int save(String str, Object obj) {
		return sqlSessionTemplate.insert(PACKAGE + str, obj);
	}

	/**
	 * 批量添加
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public  int batchSave(String str, List<?> objs) {
		return sqlSessionTemplate.insert(PACKAGE + str, objs);
	}

	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public int update(String str, Object obj) {
		return sqlSessionTemplate.update(PACKAGE + str, obj);
	}

	/**
	 * 批量更新
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int batchUpdate(String str, List<?> objs) {
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		// 批量执行器
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int succ = 0;
		try {
			if (objs != null) {
				for (int i = 0, size = objs.size(); i < size; i++) {
					sqlSession.update(PACKAGE + str, objs.get(i));
				}
				sqlSession.flushStatements();
				sqlSession.commit();
				sqlSession.clearCache();
			}
		} finally {
			sqlSession.close();
		}
		return succ;
	}

	/**
	 * 批量删除(集合)
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public  int batchDelete(String str, List<?> objs) {
		return sqlSessionTemplate.delete(PACKAGE + str, objs);
	}

	/**
	 * 批量删除(数组)
	 * @param str
	 * @param ids
	 * @return
	 */
	public int batchDelete(String str, int[] ids) {
		return sqlSessionTemplate.delete(PACKAGE + str, ids);
	}

	/**
	 * 删除对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public  int delete(String str, Object obj) {
		return sqlSessionTemplate.delete(PACKAGE + str, obj);
	}

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> T findForObject(String str, Object obj) {
		return sqlSessionTemplate.selectOne(PACKAGE + str, obj);
	}

	/**
	 * 查找对象集合
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<?> findForList(String str, Object obj) {
		return sqlSessionTemplate.selectList(PACKAGE + str, obj);
	}

	@Override
	public Map<?, ?> findForMap(String str, Object obj, String key) {
		return sqlSessionTemplate.selectMap(PACKAGE + str, obj, key);
	}

}
