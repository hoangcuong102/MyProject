package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;
import java.util.Date;

import jp.co.cyms.base.BaseDBBean;

public class OrderLeaseBean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderId;
	private String quoteMrc;
	private String quoteOtc;
	private String categoryCd;
	private String itemCd;
	private Integer displayOrder;
	private String leaseType;
	private String leaseStatus;
	private String leaseNo;
	private String serialNo;
	private String assetNo;
	private String doNo;
	private Date deliverDt;
	private Date endDt;
	private Date deliverMth;
	private Date warrantyDt;
	private String officeLicNo;
	private String accessLicNo;
	private String deliverFg;
	private boolean updateGenerateNew;
	private boolean updateSave;
	private boolean updateGenerateLink;
	private boolean updateGenerateDelete;
	private boolean updateGenerateLinkDoNoNull;

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

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getItemCd() {
		return itemCd;
	}

	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	public String getLeaseStatus() {
		return leaseStatus;
	}

	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
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

	public String getDoNo() {
		return doNo;
	}

	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	public Date getDeliverDt() {
		return deliverDt;
	}

	public void setDeliverDt(Date deliverDt) {
		this.deliverDt = deliverDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public Date getDeliverMth() {
		return deliverMth;
	}

	public void setDeliverMth(Date deliverMth) {
		this.deliverMth = deliverMth;
	}

	public Date getWarrantyDt() {
		return warrantyDt;
	}

	public void setWarrantyDt(Date warrantyDt) {
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

	public String getDeliverFg() {
		return deliverFg;
	}

	public void setDeliverFg(String deliverFg) {
		this.deliverFg = deliverFg;
	}

	/**
	 * @return the updateGenerateNew
	 */
	public boolean isUpdateGenerateNew() {
		return updateGenerateNew;
	}

	/**
	 * @param updateGenerateNew
	 *            the updateGenerateNew to set
	 */
	public void setUpdateGenerateNew(boolean updateGenerateNew) {
		this.updateGenerateNew = updateGenerateNew;
	}

	/**
	 * @return the updateSave
	 */
	public boolean isUpdateSave() {
		return updateSave;
	}

	/**
	 * @param updateSave
	 *            the updateSave to set
	 */
	public void setUpdateSave(boolean updateSave) {
		this.updateSave = updateSave;
	}

	/**
	 * @return the updateGenerateLink
	 */
	public boolean isUpdateGenerateLink() {
		return updateGenerateLink;
	}

	/**
	 * @param updateGenerateLink
	 *            the updateGenerateLink to set
	 */
	public void setUpdateGenerateLink(boolean updateGenerateLink) {
		this.updateGenerateLink = updateGenerateLink;
	}

	/**
	 * @return the updateGenerateDelete
	 */
	public boolean isUpdateGenerateDelete() {
		return updateGenerateDelete;
	}

	/**
	 * @param updateGenerateDelete
	 *            the updateGenerateDelete to set
	 */
	public void setUpdateGenerateDelete(boolean updateGenerateDelete) {
		this.updateGenerateDelete = updateGenerateDelete;
	}

	/**
	 * @return the updateGenerateLinkDoNoNull
	 */
	public boolean isUpdateGenerateLinkDoNoNull() {
		return updateGenerateLinkDoNoNull;
	}

	/**
	 * @param updateGenerateLinkDoNoNull
	 *            the updateGenerateLinkDoNoNull to set
	 */
	public void setUpdateGenerateLinkDoNoNull(boolean updateGenerateLinkDoNoNull) {
		this.updateGenerateLinkDoNoNull = updateGenerateLinkDoNoNull;
	}

}
