package jp.co.cyms.apps.fac001.form;

import java.util.List;

import jp.co.cyms.base.BaseAction;

/**
 * PC Order Progress Update / Inquiry Pac0021Form
 * 
 * @author binhvh
 * @since 2018/01/12
 */
public class Pac0021Form extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** list company code */
	protected List<String> listCompanyCd;

	/** company code */
	protected String companyCd;

	/** entry date from */
	protected String entryDateFrom;

	/** entry date to */
	protected String entryDateTo;

	/** only Non-Delivery */
	protected boolean nonDelivery;

	/** expiring orders */
	protected boolean expiringOrders;

	/** order ID */
	protected String orderId;

	/** descending Order */
	protected boolean descendingOrder;

	/**
	 * @return the listCompanyCd
	 */
	public List<String> getListCompanyCd() {
		return listCompanyCd;
	}

	/**
	 * @param listCompanyCd
	 *            the listCompanyCd to set
	 */
	public void setListCompanyCd(List<String> listCompanyCd) {
		this.listCompanyCd = listCompanyCd;
	}

	/**
	 * @return the companyCd
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * @param companyCd
	 *            the companyCd to set
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * @return the entryDateFrom
	 */
	public String getEntryDateFrom() {
		return entryDateFrom;
	}

	/**
	 * @param entryDateFrom
	 *            the entryDateFrom to set
	 */
	public void setEntryDateFrom(String entryDateFrom) {
		this.entryDateFrom = entryDateFrom;
	}

	/**
	 * @return the entryDateTo
	 */
	public String getEntryDateTo() {
		return entryDateTo;
	}

	/**
	 * @param entryDateTo
	 *            the entryDateTo to set
	 */
	public void setEntryDateTo(String entryDateTo) {
		this.entryDateTo = entryDateTo;
	}

	/**
	 * @return the nonDelivery
	 */
	public boolean isNonDelivery() {
		return nonDelivery;
	}

	/**
	 * @param nonDelivery
	 *            the nonDelivery to set
	 */
	public void setNonDelivery(boolean nonDelivery) {
		this.nonDelivery = nonDelivery;
	}

	/**
	 * @return the expiringOrders
	 */
	public boolean isExpiringOrders() {
		return expiringOrders;
	}

	/**
	 * @param expiringOrders
	 *            the expiringOrders to set
	 */
	public void setExpiringOrders(boolean expiringOrders) {
		this.expiringOrders = expiringOrders;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the descendingOrder
	 */
	public boolean isDescendingOrder() {
		return descendingOrder;
	}

	/**
	 * @param descendingOrder
	 *            the descendingOrder to set
	 */
	public void setDescendingOrder(boolean descendingOrder) {
		this.descendingOrder = descendingOrder;
	}

}
