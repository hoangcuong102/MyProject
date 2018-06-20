package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class Pac0022UpdateOrderDtlBean extends BaseDBBean implements Serializable {

	private String orderId;
	private String categoryCd;
	private String kddiDeliverDt;
	private Integer kddiDeliverQty;
	private String categoryAbbrev;
	private String actionOrderStatus;
	private String phase;

	public Pac0022UpdateOrderDtlBean() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getKddiDeliverDt() {
		return kddiDeliverDt;
	}

	public void setKddiDeliverDt(String kddiDeliverDt) {
		this.kddiDeliverDt = kddiDeliverDt;
	}

	/**
	 * @return the kddiDeliverQty
	 */
	public Integer getKddiDeliverQty() {
		return kddiDeliverQty;
	}

	/**
	 * @param kddiDeliverQty the kddiDeliverQty to set
	 */
	public void setKddiDeliverQty(Integer kddiDeliverQty) {
		this.kddiDeliverQty = kddiDeliverQty;
	}

	/**
	 * @return the categoryAbbrev
	 */
	public String getCategoryAbbrev() {
		return categoryAbbrev;
	}

	/**
	 * @param categoryAbbrev the categoryAbbrev to set
	 */
	public void setCategoryAbbrev(String categoryAbbrev) {
		this.categoryAbbrev = categoryAbbrev;
	}

	/**
	 * @return the actionOrderStatus
	 */
	public String getActionOrderStatus() {
		return actionOrderStatus;
	}

	/**
	 * @param actionOrderStatus the actionOrderStatus to set
	 */
	public void setActionOrderStatus(String actionOrderStatus) {
		this.actionOrderStatus = actionOrderStatus;
	}

	/**
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * @param phase the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	
}
