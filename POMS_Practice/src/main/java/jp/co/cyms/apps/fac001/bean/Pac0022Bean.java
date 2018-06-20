package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

public class Pac0022Bean extends BaseDBBean implements Serializable {

	private String isDisabled;
	private String orderId;
	private String quoteMrc;
	private String quoteOtc;
	private String itemCd;
	private String itemName;
	private String categoryCd;
	private String categoryAbbrev;
	private String leaseNo;
	private String serialNo;
	private String assetNo;
	private String deliverDt;
	private String endDt;
	private String leaseFee;
	private String warrantyDt;
	private String officeLicNo;
	private String accessLicNo;
	private String displayOrder;
	private String leaseStatus;
	private String deliverMth;
	private String categoryName;

	public Pac0022Bean() {
	}

	public Pac0022Bean(String isDisabled, String orderId, String quoteMrc, String quoteOtc, String itemCd, String itemName,
			String categoryCd, String categoryAbbrev, String leaseNo, String serialNo, String assetNo, String deliverDt,
			String endDt, String leaseFee, String warrantyDt, String officeLicNo, String accessLicNo,
			String displayOrder, String leaseStatus, String deliverMth) {
		super();
		this.isDisabled = isDisabled;
		this.orderId = orderId;
		this.quoteMrc = quoteMrc;
		this.quoteOtc = quoteOtc;
		this.itemCd = itemCd;
		this.itemName = itemName;
		this.categoryCd = categoryCd;
		this.categoryAbbrev = categoryAbbrev;
		this.leaseNo = leaseNo;
		this.serialNo = serialNo;
		this.assetNo = assetNo;
		this.deliverDt = deliverDt;
		this.endDt = endDt;
		this.leaseFee = leaseFee;
		this.warrantyDt = warrantyDt;
		this.officeLicNo = officeLicNo;
		this.accessLicNo = accessLicNo;
		this.displayOrder = displayOrder;
		this.leaseStatus = leaseStatus;
		this.deliverMth = deliverMth;
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

	public String getQuoteMrc() {
		return quoteMrc;
	}

	public void setQuoteMrc(String quoteMrc) {
		this.quoteMrc = quoteMrc;
	}

	public String getQuoteOtc() {
		return quoteOtc;
	}

	public void setQuoteOtc(String quoteOtc) {
		this.quoteOtc = quoteOtc;
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

	public String getCategoryAbbrev() {
		return categoryAbbrev;
	}

	public void setCategoryAbbrev(String categoryAbbrev) {
		this.categoryAbbrev = categoryAbbrev;
	}

	public String getLeaseNo() {
		return leaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		this.leaseNo = leaseNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public String getDeliverDt() {
		return deliverDt;
	}

	public void setDeliverDt(String deliverDt) {
		this.deliverDt = deliverDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getLeaseFee() {
		return leaseFee;
	}

	public void setLeaseFee(String leaseFee) {
		this.leaseFee = leaseFee;
	}

	public String getWarrantyDt() {
		return warrantyDt;
	}

	public void setWarrantyDt(String warrantyDt) {
		this.warrantyDt = warrantyDt;
	}

	public String getOfficeLicNo() {
		return officeLicNo;
	}

	public void setOfficeLicNo(String officeLicNo) {
		this.officeLicNo = officeLicNo;
	}

	public String getAccessLicNo() {
		return accessLicNo;
	}

	public void setAccessLicNo(String accessLicNo) {
		this.accessLicNo = accessLicNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getLeaseStatus() {
		return leaseStatus;
	}

	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}

	public String getDeliverMth() {
		return deliverMth;
	}

	public void setDeliverMth(String deliverMth) {
		this.deliverMth = deliverMth;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the isDisabled
	 */
	public String getIsDisabled() {
		return isDisabled;
	}

	/**
	 * @param isDisabled the isDisabled to set
	 */
	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}

}
