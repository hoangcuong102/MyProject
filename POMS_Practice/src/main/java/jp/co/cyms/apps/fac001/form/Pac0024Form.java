package jp.co.cyms.apps.fac001.form;

import java.io.File;

import jp.co.cyms.base.BaseAction;

/**
 * [SAC0024] Delivery Order Entry/Update Form
 * 
 * @author AnhNT2
 * @since 24/01/2018
 */
public class Pac0024Form extends BaseAction {
	private static final long serialVersionUID = 7619695169390416423L;

	/** Order code. */
	protected String orderId;

	/** Do No. */
	protected String doNo;

	/** Quotation No */
	protected String quotationNo;

	/**
	 * Type Download or Delete 
	 * 	- Generated
	 *  - Signed
	 */
	protected String type;

	/** Upload File */
	protected File fileUpload;

	/** Json param call ajax */
	protected String jsonParams;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDoNo() {
		return doNo;
	}

	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	/**
	 * @return the quotationNo
	 */
	public String getQuotationNo() {
		return quotationNo;
	}

	/**
	 * @param quotationNo
	 *            the quotationNo to set
	 */
	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getJsonParams() {
		return jsonParams;
	}

	public void setJsonParams(String jsonParams) {
		this.jsonParams = jsonParams;
	}

}
