package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * [SAC0024] Download Delivery Order Entry/Update Bean
 * 
 * @author AnhNT2
 * @since 24/01/2018
 */
public class DownloadDeliveryOrderBean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String item;
	private String doNo;
	private String companyName;
	private String companyCd;
	private String countryCd;
	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	private String attnToName;
	private String sectionName;
	private String categoryCd;
	private Integer categoryDisplayOrder;
	private String itemCd;
	private String itemName;
	private String remark;
	private String itemQty;
	private String serialNo;
	private String quoteMrc;
	private String deliverDt;

	public String getDoNo() {
		return doNo;
	}

	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAttnToName() {
		return attnToName;
	}

	public void setAttnToName(String attnToName) {
		this.attnToName = attnToName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItemQty() {
		return itemQty;
	}

	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getQuoteMrc() {
		return quoteMrc;
	}

	public void setQuoteMrc(String quoteMrc) {
		this.quoteMrc = quoteMrc;
	}

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

	/**
	 * @return the categoryCd
	 */
	public String getCategoryCd() {
		return categoryCd;
	}

	/**
	 * @param categoryCd
	 *            the categoryCd to set
	 */
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	/**
	 * @return the categoryDisplayOrder
	 */
	public Integer getCategoryDisplayOrder() {
		return categoryDisplayOrder;
	}

	/**
	 * @param categoryDisplayOrder
	 *            the categoryDisplayOrder to set
	 */
	public void setCategoryDisplayOrder(Integer categoryDisplayOrder) {
		this.categoryDisplayOrder = categoryDisplayOrder;
	}



}
