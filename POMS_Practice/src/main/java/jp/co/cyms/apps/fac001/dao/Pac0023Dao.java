package jp.co.cyms.apps.fac001.dao;

import java.util.List;

import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.Pac0023OrderStatusBean;
import jp.co.cyms.base.BaseDao;

public class Pac0023Dao extends BaseDao {

	public Pac0023Dao() {
		super();
	}

	/**
	 * get order by orderId
	 * 
	 * @return
	 */
	public OrderBean getOrder(String orderId) {
		return (OrderBean) this.queryObject("FAC0023.getOrder", orderId);
	}

	/**
	 * get List order_status
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pac0023OrderStatusBean> getListOrderStatus(String orderId) {
		return (List<Pac0023OrderStatusBean>) this.queryList("FAC0023.getListOrderStatus", orderId);
	}
}
