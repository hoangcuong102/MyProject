package jp.co.cyms.apps.fac001.bl;

import java.util.List;

import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.Pac0023OrderStatusBean;
import jp.co.cyms.apps.fac001.dao.Pac0023Dao;

public class Pac0023BL {
	/**
	 * get order by orderId
	 * 
	 * @return
	 */
	public OrderBean getOrder(String orderId) {
		return new Pac0023Dao().getOrder(orderId);
	}
	
	/**
	 * get list orderSatus
	 * 
	 * @return
	 */
	public List<Pac0023OrderStatusBean> getListOrderStatus(String orderId) {
		return new Pac0023Dao().getListOrderStatus(orderId);
	}
}
