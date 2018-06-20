package jp.co.cyms.apps.fac001.bean;

import java.util.List;

public class OrderInfoBean {
	private OrderBean order;
	private List<OrderDtlBean> listOrderDtl;
	private List<OrderCfgBean> listOrderCfg;

	public OrderBean getOrder() {
		return order;
	}

	public void setOrder(OrderBean order) {
		this.order = order;
	}

	public List<OrderDtlBean> getListOrderDtl() {
		return listOrderDtl;
	}

	public void setListOrderDtl(List<OrderDtlBean> listOrderDtl) {
		this.listOrderDtl = listOrderDtl;
	}

	public List<OrderCfgBean> getListOrderCfg() {
		return listOrderCfg;
	}

	public void setListOrderCfg(List<OrderCfgBean> listOrderCfg) {
		this.listOrderCfg = listOrderCfg;
	}

}
