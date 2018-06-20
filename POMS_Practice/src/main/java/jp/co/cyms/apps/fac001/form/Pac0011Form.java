package jp.co.cyms.apps.fac001.form;

import jp.co.cyms.base.BaseAction;

public class Pac0011Form extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String orderId;
	protected String quoteMrc;
	protected String quoteOtc;
	protected String countryCd;
	protected String companyCd;
	protected String deptCd;
	protected String remark;
	protected Float totalMonthlyRecurring;
	protected Float totalOneTimeCharge;
	protected Float fireEyeEdgeXSoftware;
	protected Float pcMonitoringSoftware;
	protected String listItemOrder;
	protected String fileNameDownload;
	protected String filePath;
	protected Boolean checkPdfExist;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Float getTotalMonthlyRecurring() {
		return totalMonthlyRecurring;
	}

	public void setTotalMonthlyRecurring(Float totalMonthlyRecurring) {
		this.totalMonthlyRecurring = totalMonthlyRecurring;
	}

	public Float getTotalOneTimeCharge() {
		return totalOneTimeCharge;
	}

	public void setTotalOneTimeCharge(Float totalOneTimeCharge) {
		this.totalOneTimeCharge = totalOneTimeCharge;
	}

	//Hantt shift the quantity check to Save
	/*public Float getFireEyeEdgeXSoftware() {
		return fireEyeEdgeXSoftware;
	}

	public void setFireEyeEdgeXSoftware(Float fireEyeEdgeXSoftware) {
		this.fireEyeEdgeXSoftware = fireEyeEdgeXSoftware;
	}

	public Float getPcMonitoringSoftware() {
		return pcMonitoringSoftware;
	}

	public void setPcMonitoringSoftware(Float pcMonitoringSoftware) {
		this.pcMonitoringSoftware = pcMonitoringSoftware;
	}*/

	public String getListItemOrder() {
		return listItemOrder;
	}

	public void setListItemOrder(String listItemOrder) {
		this.listItemOrder = listItemOrder;
	}

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

	public String getFileNameDownload() {
		return fileNameDownload;
	}

	public void setFileNameDownload(String fileNameDownload) {
		this.fileNameDownload = fileNameDownload;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Boolean getCheckPdfExist() {
		return checkPdfExist;
	}

	public void setCheckPdfExist(Boolean checkPdfExist) {
		this.checkPdfExist = checkPdfExist;
	}

}
