package jp.co.cyms.apps.fad001.form;

import jp.co.cyms.base.BaseAction;

/**
 * Data Downloading Form
 * 
 * @author anhnt2
 * @since 2018/01/10
 */
public class Pad0011Form extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** year */
	protected String year;

	/** Month */
	protected String month;

	/** Country Cd */
	protected String country;

	/** Company Cd */
	protected String company;

	/** Output Lease Invoice Data */
	protected boolean outputLeaseInvoiceData;

	/** Output Lease Invoice DataK Yearly */
	protected boolean outputLeaseInvoiceDataKYearly;

	/** Output Lease Data */
	protected boolean outputLeaseData;

	/** Status Active */
	protected boolean statusActive;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public boolean isOutputLeaseInvoiceData() {
		return outputLeaseInvoiceData;
	}

	public void setOutputLeaseInvoiceData(boolean outputLeaseInvoiceData) {
		this.outputLeaseInvoiceData = outputLeaseInvoiceData;
	}

	public boolean isOutputLeaseData() {
		return outputLeaseData;
	}

	public void setOutputLeaseData(boolean outputLeaseData) {
		this.outputLeaseData = outputLeaseData;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	/**
	 * @return the outputLeaseInvoiceDataKYearly
	 */
	public boolean isOutputLeaseInvoiceDataKYearly() {
		return outputLeaseInvoiceDataKYearly;
	}

	/**
	 * @param outputLeaseInvoiceDataKYearly
	 *            the outputLeaseInvoiceDataKYearly to set
	 */
	public void setOutputLeaseInvoiceDataKYearly(boolean outputLeaseInvoiceDataKYearly) {
		this.outputLeaseInvoiceDataKYearly = outputLeaseInvoiceDataKYearly;
	}

}
