package jp.co.cyms.apps.fac001.action;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.Pac0023Bean;
import jp.co.cyms.apps.fac001.bean.Pac0023OrderStatusBean;
import jp.co.cyms.apps.fac001.bl.Pac0023BL;
import jp.co.cyms.apps.fac001.form.Pac0023Form;

public class Pac0023Action extends Pac0023Form {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;
	private Pac0023Bean orderData;

	public String execute() {
		return SUCCESS;
	}

	public String loadAllOrderData() {
		orderData = new Pac0023Bean();
		OrderBean orderBean = new OrderBean();
		Pac0023BL bl = new Pac0023BL();
		orderBean = bl.getOrder(orderId);
		
		List<Pac0023OrderStatusBean> listOrderStatus = new ArrayList<Pac0023OrderStatusBean>();
		listOrderStatus = bl.getListOrderStatus(orderId);
		
		orderData.setOrder(orderBean);
		orderData.setListOrderStatus(listOrderStatus);
		return SUCCESS;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Pac0023Bean getOrderData() {
		return orderData;
	}

	public void setOrderData(Pac0023Bean orderData) {
		this.orderData = orderData;
	}

}
