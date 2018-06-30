package jp.co.cyms.apps.fac099.dao;

import java.util.List;

import jp.co.cyms.apps.fac099.bean.OrderProgressBean;
import jp.co.cyms.apps.fac099.bean.Pac9921Bean;
import jp.co.cyms.base.BaseDao;

@SuppressWarnings("unchecked")
public class Pac9921DAO extends BaseDao {
	public Pac9921DAO() {
		super();
	}
	
	public List<String> getCompanyCode() {
		List<String> list = null;
		list = (List<String>)this.queryList("FAC9911.getCompanyCode");
		return list;
	}
	
	public List<OrderProgressBean> getOrderProgressList (Pac9921Bean condition) {
		List<OrderProgressBean> list = null;
		list = (List<OrderProgressBean>)this.queryList("FAC9911.getOrderProcess", condition);
		return list;
	}
}
