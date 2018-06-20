package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * [SAC0024] Download Delivery Order Entry/Update Bean
 * 
 * @author AnhNT2
 * @since 24/01/2018
 */
public class Pac0024ParamBean extends BaseDBBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Order ID */
	private String orderId;

	/** Quote MRC */
	private String quoteMrc;

	/** doNo */
	private String doNo;
	
	/** bill flag */
	private String billFg;
	
	/** bill mth start */
	private String billMthStart;
	
	/** bill mth end */
	private String billMthEnd;

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
	 * @return the quoteMrc
	 */
	public String getQuoteMrc() {
		return quoteMrc;
	}

	/**
	 * @param quoteMrc the quoteMrc to set
	 */
	public void setQuoteMrc(String quoteMrc) {
		this.quoteMrc = quoteMrc;
	}

	/**
	 * @return the doNo
	 */
	public String getDoNo() {
		return doNo;
	}

	/**
	 * @param doNo the doNo to set
	 */
	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	/**
	 * @return the billFg
	 */
	public String getBillFg() {
		return billFg;
	}

	/**
	 * @param billFg the billFg to set
	 */
	public void setBillFg(String billFg) {
		this.billFg = billFg;
	}

	/**
	 * @return the billMthStart
	 */
	public String getBillMthStart() {
		return billMthStart;
	}

	/**
	 * @param billMthStart the billMthStart to set
	 */
	public void setBillMthStart(String billMthStart) {
		this.billMthStart = billMthStart;
	}

	/**
	 * @return the billMthEnd
	 */
	public String getBillMthEnd() {
		return billMthEnd;
	}

	/**
	 * @param billMthEnd the billMthEnd to set
	 */
	public void setBillMthEnd(String billMthEnd) {
		this.billMthEnd = billMthEnd;
	}

	
}
