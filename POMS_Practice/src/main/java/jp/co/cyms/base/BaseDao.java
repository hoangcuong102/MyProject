package jp.co.cyms.base;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

/*
 * CLASS   : BaseDao
 * PACKAGE : jp.co.cyms.base
 * VERSION : 1.0
 * HISTORY : 2014-08-01縲�SC縲�U CREATE
 */

/**
 * BaseDao
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
public abstract class BaseDao {

	/**
	 * sqlMapClientTemplate
	 */
	private static SqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * BaseDao
	 * 
	 */
	protected BaseDao() {
		if (sqlMapClientTemplate == null) {
			ApplicationContext ac = WebApplicationContextUtils
								.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
			sqlMapClientTemplate = ac.getBean("sqlMapClientTemplate", SqlMapClientTemplate.class);
		}
	}

	/**
	 * BaseDao
	 * 
	 * @param dbClient SqlMapClientTemplate
	 */
	protected BaseDao(SqlMapClientTemplate dbClient) {
		sqlMapClientTemplate = dbClient;
	}

	/**
	 * queryCount
	 * 
	 * @param statementName String
	 * @return int
	 */
	protected int queryCount(String statementName) {
		Integer count = (Integer) sqlMapClientTemplate.queryForObject(statementName);
		return count.intValue();
	}

	/**
	 * queryCount
	 * 
	 * @param statementName String
	 * @param parameter Object
	 * @return int
	 */
	protected int queryCount(String statementName, Object parameter) {
		Integer count = (Integer) sqlMapClientTemplate.queryForObject(statementName, parameter);
		return count.intValue();
	}

	/**
	 * queryList
	 * 
	 * @param statementName String
	 * @return List<?>
	 */
	protected List< ? > queryList(String statementName) {
		return sqlMapClientTemplate.queryForList(statementName);
	}

	/**
	 * queryList
	 * 
	 * @param statementName String
	 * @param parameter Object
	 * @return List<?>
	 */
	protected List< ? > queryList(String statementName, Object parameter) {
		return sqlMapClientTemplate.queryForList(statementName, parameter);
	}

	/**
	 * queryObject
	 * 
	 * @param statementName String
	 * @return Object
	 */
	protected Object queryObject(String statementName) {
		return sqlMapClientTemplate.queryForObject(statementName);
	}

	/**
	 * queryObject
	 * 
	 * @param statementName String
	 * @param parameter Object
	 * @return Object
	 */
	protected Object queryObject(String statementName, Object parameter) {
		return sqlMapClientTemplate.queryForObject(statementName, parameter);
	}

	/**
	 * delete
	 * 
	 * @param statementName String
	 * @return int
	 */
	protected int delete(String statementName) {
		return sqlMapClientTemplate.delete(statementName);
	}

	/**
	 * 
	 * 
	 * @param statementName String
	 * @param parameter Object
	 * @return int
	 */
	protected int delete(String statementName, Object parameter) {
		return sqlMapClientTemplate.delete(statementName, parameter);
	}

	/**
	 * update
	 * 
	 * @param statementName String
	 * @return int
	 */
	protected int update(String statementName) {
		return sqlMapClientTemplate.update(statementName);
	}

	/**
	 * update
	 * 
	 * @param statementName String
	 * @param parameter Object
	 * @return int
	 */
	protected int update(String statementName, Object parameter) {
		return sqlMapClientTemplate.update(statementName, parameter);
	}
	@SuppressWarnings("rawtypes")
	protected Map queryForMap(String statementName, Object parameterObject, String keyProperty){
		return sqlMapClientTemplate.queryForMap(statementName, parameterObject, keyProperty);
	}
	@SuppressWarnings("rawtypes")
	protected Map queryForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty){
		return sqlMapClientTemplate.queryForMap(statementName, parameterObject, keyProperty, valueProperty);
	}
}
