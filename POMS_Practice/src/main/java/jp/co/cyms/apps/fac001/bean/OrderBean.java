package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;
import java.util.Date;

import jp.co.cyms.base.BaseDBBean;

public class OrderBean extends BaseDBBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String quoteMrc;
	private String quoteOtc;
	private String countryCd;
	private String companyCd;
	private String deptCd;
	private Float ttlMrc;
	private Float ttlOtc;
	private Float ttlAvg;
	private Float ttlMon;
	private String remark;
	private String saveFg;
	private String printFg;
	private String sendOrderFg;
	private String deleteFg;
	private String billFg;
	private Date billMthStart;
	private Date billMthEnd;

	private String itemName;
	private String companyName;
	private String attnToName;
	private String ccName;
	private String deptName;

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

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public Float getTtlMrc() {
		return ttlMrc;
	}

	public void setTtlMrc(Float ttlMrc) {
		this.ttlMrc = ttlMrc;
	}

	public Float getTtlOtc() {
		return ttlOtc;
	}

	public void setTtlOtc(Float ttlOtc) {
		this.ttlOtc = ttlOtc;
	}

	public Float getTtlAvg() {
		return ttlAvg;
	}

	public void setTtlAvg(Float ttlAvg) {
		this.ttlAvg = ttlAvg;
	}

	public Float getTtlMon() {
		return ttlMon;
	}

	public void setTtlMon(Float ttlMon) {
		this.ttlMon = ttlMon;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSaveFg() {
		return saveFg;
	}

	public void setSaveFg(String saveFg) {
		this.saveFg = saveFg;
	}

	public String getPrintFg() {
		return printFg;
	}

	public void setPrintFg(String printFg) {
		this.printFg = printFg;
	}

	public String getSendOrderFg() {
		return sendOrderFg;
	}

	public void setSendOrderFg(String sendOrderFg) {
		this.sendOrderFg = sendOrderFg;
	}

	public String getDeleteFg() {
		return deleteFg;
	}

	public void setDeleteFg(String deleteFg) {
		this.deleteFg = deleteFg;
	}

	public String getBillFg() {
		return billFg;
	}

	public void setBillFg(String billFg) {
		this.billFg = billFg;
	}

	public Date getBillMthStart() {
		return billMthStart;
	}

	public void setBillMthStart(Date billMthStart) {
		this.billMthStart = billMthStart;
	}

	public Date getBillMthEnd() {
		return billMthEnd;
	}

	public void setBillMthEnd(Date billMthEnd) {
		this.billMthEnd = billMthEnd;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
