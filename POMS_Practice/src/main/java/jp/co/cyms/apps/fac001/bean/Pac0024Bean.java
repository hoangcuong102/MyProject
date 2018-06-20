package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;
import jp.co.cyms.base.BaseDBBean;

/**
 * [SAC0024] Delivery Order Entry/Update Bean
 * 
 * @author AnhNT2
 * @since 23/01/2018
 */
public class Pac0024Bean extends BaseDBBean implements Serializable {

	/**
	 * @return the deliverDt
	 */
	public String getDeliverDt() {
		return deliverDt;
	}

	/**
	 * @param deliverDt
	 *            the deliverDt to set
	 */
	public void setDeliverDt(String deliverDt) {
		this.deliverDt = deliverDt;
	}

	/** serial version default */
	private static final long serialVersionUID = 8195214115618983000L;

	/** The order ID */
	private String orderId;

	/** The timestamp */
	private String timestamp;

	/** The do number */
	private String doNo;

	/** The seq number */
	private String seqNo;

	/** The customer company code */
	private String customerCompanyCd;

	/** The do sign back flag */
	private String doSignbackFg;

	/** The do generated flag */
	private String doGeneratedFg;

	/** The deliver date */
	private String deliverDt;

	/** The delete flag */
	private String deleteFg;

	/** The bill flag */
	private String billFg;

	public Pac0024Bean() {
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
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the doNo
	 */
	public String getDoNo() {
		return doNo;
	}

	/**
	 * @param doNo
	 *            the doNo to set
	 */
	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	/**
	 * @return the seqNo
	 */
	public String getSeqNo() {
		return seqNo;
	}

	/**
	 * @param seqNo
	 *            the seqNo to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	/**
	 * @return the customerCompanyCd
	 */
	public String getCustomerCompanyCd() {
		return customerCompanyCd;
	}

	/**
	 * @param customerCompanyCd
	 *            the customerCompanyCd to set
	 */
	public void setCustomerCompanyCd(String customerCompanyCd) {
		this.customerCompanyCd = customerCompanyCd;
	}

	/**
	 * @return the doSignbackFg
	 */
	public String getDoSignbackFg() {
		return doSignbackFg;
	}

	/**
	 * @param doSignbackFg
	 *            the doSignbackFg to set
	 */
	public void setDoSignbackFg(String doSignbackFg) {
		this.doSignbackFg = doSignbackFg;
	}

	/**
	 * @return the doGeneratedFg
	 */
	public String getDoGeneratedFg() {
		return doGeneratedFg;
	}

	/**
	 * @param doGeneratedFg
	 *            the doGeneratedFg to set
	 */
	public void setDoGeneratedFg(String doGeneratedFg) {
		this.doGeneratedFg = doGeneratedFg;
	}

	/**
	 * @return the deleteFg
	 */
	public String getDeleteFg() {
		return deleteFg;
	}

	/**
	 * @param deleteFg
	 *            the deleteFg to set
	 */
	public void setDeleteFg(String deleteFg) {
		this.deleteFg = deleteFg;
	}

	/**
	 * @return the billFg
	 */
	public String getBillFg() {
		return billFg;
	}

	/**
	 * @param billFg
	 *            the billFg to set
	 */
	public void setBillFg(String billFg) {
		this.billFg = billFg;
	}

}
