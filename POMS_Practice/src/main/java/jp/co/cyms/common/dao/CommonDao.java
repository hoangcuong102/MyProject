package jp.co.cyms.common.dao;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyms.base.BaseDao;
import jp.co.cyms.common.bean.CategoryBean;
import jp.co.cyms.common.bean.ConfigBean;
import jp.co.cyms.common.bean.CountryBean;
public class CommonDao extends BaseDao {
	
	public CommonDao() {
		super();
	}
	/**
	 * get configuration form CONFIG table
	 * 
	 * @return
	 */
	public ConfigBean getConfig() {
		return (ConfigBean) this.queryObject("COMMON.getConfig");
	}
	
	@SuppressWarnings("unchecked")
	public List<CountryBean> getCountries(){
		List<CountryBean> data = new ArrayList<CountryBean>();
		data = (List<CountryBean>) this.queryList("COMMON.getCountries");
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryBean> getListCategory(){
		List<CategoryBean> data = new ArrayList<CategoryBean>();
		data = (List<CategoryBean>) this.queryList("COMMON.getListCategory");
		return data;
	}
}
