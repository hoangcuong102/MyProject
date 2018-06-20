package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * PC Order Progress Update / Inquiry Pac0021ParamBean
 * 
 * @author binhvh
 * @since 2018/01/12
 */
public class Pac0021ParamBean extends BaseDBBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** company code */
	private String companyCd;
	
	/** entry date from */
	private String entryDateFrom;
	
	/** entry date to */
	private String entryDateTo;
	
	/** only Non-Delivery */
	private boolean nonDelivery;
	
	/** expiring orders */
	private boolean expiringOrders;
	
	/** order id */
	private String orderId;
	
	/** item code */
	private String itemCd;
	
	/** country code */
	private String countryCd;
	
	/** redirect */
	private String redirect;

	/**
	 * @return the companyCd
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * @param companyCd the companyCd to set
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
	 * @param entryDateFrom the entryDateFrom to set
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
	 * @param entryDateTo the entryDateTo to set
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
	 * @param nonDelivery the nonDelivery to set
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
	 * @param expiringOrders the expiringOrders to set
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
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * @param itemCd the itemCd to set
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * @return the countryCd
	 */
	public String getCountryCd() {
		return countryCd;
	}

	/**
	 * @param countryCd the countryCd to set
	 */
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	/**
	 * @return the redirect
	 */
	public String getRedirect() {
		return redirect;
	}

	/**
	 * @param redirect the redirect to set
	 */
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	
}
