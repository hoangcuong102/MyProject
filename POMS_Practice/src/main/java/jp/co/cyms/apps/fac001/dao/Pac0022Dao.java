package jp.co.cyms.apps.fac001.dao;

import java.util.List;

import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.Pac0022Bean;
import jp.co.cyms.base.BaseDao;

public class Pac0022Dao extends BaseDao {

	public Pac0022Dao() {
		super();
	}
	
	/**
	 * get all order lease in ORDER_LEASE by orderCd
	 * @param orderCd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pac0022Bean> getOrderLease(String orderId) {
		List<Pac0022Bean> list = null;
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(orderId);
		list = (List<Pac0022Bean>)this.queryList("FAC0022.getOrderLease", orderBean);
		return list;
	}

	public int doUpdateOrderLease(Pac0022Bean pac0022Bean) {
		return this.update("FAC0022.updateOrderLease", pac0022Bean);
	}

	public int doUpdateOrderDtl(Pac0022Bean pac0022Bean) {
		return this.update("FAC0022.updateOrderDtl", pac0022Bean);
	}
}
