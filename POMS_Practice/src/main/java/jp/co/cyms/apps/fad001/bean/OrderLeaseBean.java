package jp.co.cyms.apps.fad001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * Order Lease Class
 *
 * @author anhnt2
 * @since 2018/01/19
 */
public class OrderLeaseBean extends BaseDBBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** Seq */
	private String seq;
	
	/** Company code */
	private String companyCd;

	/** Dept cod */
	private String deptCd;
	
	/** Quotation No. */
	private String quotationNo;
	
	/** Lease No. */
	private String leaseNo;
	
	/** Lease Fee */
	private String leaseFee;
	
	/** Start Date */
	private String deliverDt;
	
	/** End Date */
	private String endDt;
	
	/** Warranty End Date */
	private String warrantyDt;
	
	/** Category */
	private String categoryCd;
	
	/** Model */
	private String itemCd;
	
	/** Serial No. */
	private String serialNo;
	
	/** Lease Status */
	private String activeStatus;

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public String getLeaseNo() {
		return leaseNo;
	}

	public void setLeaseNo(String leaseNo) {
		this.leaseNo = leaseNo;
	}

	public String getLeaseFee() {
		return leaseFee;
	}

	public void setLeaseFee(String leaseFee) {
		this.leaseFee = leaseFee;
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

	public String getWarrantyDt() {
		return warrantyDt;
	}

	public void setWarrantyDt(String warrantyDt) {
		this.warrantyDt = warrantyDt;
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

}
