package jp.co.cyms.apps.fac001.bean;

import java.util.List;

public class Pac0023Bean {
	private OrderBean order;
	private List<Pac0023OrderStatusBean> listOrderStatus;
	public OrderBean getOrder() {
		return order;
	}
	public void setOrder(OrderBean order) {
		this.order = order;
	}
	public List<Pac0023OrderStatusBean> getListOrderStatus() {
		return listOrderStatus;
	}
	public void setListOrderStatus(List<Pac0023OrderStatusBean> listOrderStatus) {
		this.listOrderStatus = listOrderStatus;
	}

}
