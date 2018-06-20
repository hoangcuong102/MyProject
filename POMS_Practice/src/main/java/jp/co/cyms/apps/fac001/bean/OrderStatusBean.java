package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class OrderStatusBean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderId;
	private String phase;
	private String action;
	private String deliverCategory;
	private String deliverQty;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDeliverCategory() {
		return deliverCategory;
	}

	public void setDeliverCategory(String deliverCategory) {
		this.deliverCategory = deliverCategory;
	}

	public String getDeliverQty() {
		return deliverQty;
	}

	public void setDeliverQty(String deliverQty) {
		this.deliverQty = deliverQty;
	}

}
