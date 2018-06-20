package jp.co.cyms.apps.fad001.action;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import jp.co.cyms.apps.fad001.bean.CompanyBean;
import jp.co.cyms.apps.fad001.bean.ExcelPCLeaseInvoiceDataKYearlyBean;
import jp.co.cyms.apps.fad001.bean.ExcelPCLeaseInvoiceDataSBean;
import jp.co.cyms.apps.fad001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fad001.bean.Pad0011Bean;
import jp.co.cyms.apps.fad001.bl.Pad0011BL;
import jp.co.cyms.apps.fad001.bl.Pad0011ReportBL;
import jp.co.cyms.apps.fad001.form.Pad0011Form;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.DateFormat;
import jp.co.cyms.common.DateUtil;
import jp.co.cyms.common.DownloadInputStream;
import jp.co.cyms.common.StringUtil;
/**
 * Data Downloading Action
 * 
 * @author anhnt2
 * @since 2018/01/11
 */
public class Pad0011Action extends Pad0011Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Message Id */
	private String messageId;

	/** Business logic object. */
	Pad0011BL logic = new Pad0011BL();
	Pad0011ReportBL reportBL = new Pad0011ReportBL();
	
	/** List company table */
	private List<CompanyBean> companyList;

	/** File report **/
	private InputStream inputStream;

	/** contentType **/
	private String contentType;
    
    /** Report Name*/
    private String reportName;
    
    /** Report Name*/
    private String userAuthorityCd;
    
	/**
	 * Call when enter the screen
	 * 
	 * @return SUCCESS
	 */
	public String execute(){
		return SUCCESS;
	}
	
	
	/**
	 * Load data for json
	 * 
	 * @return SUCCESS
	 */
	public String loadDataSad0011() throws Exception {
		LOG.info("*************loadData Start*************", "");
		companyList = logic.getListCompany();
		userInfo = super.getUserInfo();
		userAuthorityCd = userInfo.getUserAuthorityCd();
		LOG.info("*************loadData End***************", "");
		return SUCCESS;
	}

	/**
	 * Action data download 
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String doDataDownload() throws Exception {
		LOG.info("*************doDataDownload Start*************", "");
		checkValidate();
		if (this.messageId == null || "".equals(this.messageId)) {
			// Create map param
			Map<String, Object> reqParams = new HashMap<String, Object>();
			userInfo = super.getUserInfo();
			
			// PC Lease Invoice Data Download
			/*if (this.outputLeaseInvoiceData) {
				//userInfo = super.getUserInfo();
				// if [USER_MST].USER_AUTHORITY = "2" (KDDI Admin)
				// Go to "Excel PC Lease Invoice Data (K)" sheet
				if (Integer.parseInt(userInfo.getUserAuthorityCd()) == Constant.KDDI_CD) {
					// Excel PC Lease Invoice Data (K)
					if (!this.outputLeaseInvoiceDataKYearly){
						List<Pad0011Bean> getOrdersByMonthlyCost = logic.getOrdersByMonthlyCost(this);
						List<Pad0011Bean> getOrdersByOneTimeCost = logic.getOrdersByOneTimeCost(this);
						reqParams.put("getOrdersByMonthlyCost", getOrdersByMonthlyCost);
						reqParams.put("getOrdersByOneTimeCost", getOrdersByOneTimeCost);
						reqParams.put("outputLeaseInvoiceDataK", this.outputLeaseInvoiceData);
						//reqParams.put("outputLeaseInvoiceDataKYearly", this.outputLeaseInvoiceDataKYearly);
					} else {
						// Excel PC Lease Invoice Data (K) Yearly
						List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummaryMRC = logic
								.getLeaseInvoiceDataKYearlyTotalSummaryMRC(this);
						List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummaryOTC = logic
								.getLeaseInvoiceDataKYearlyTotalSummaryOTC(this);
						List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyCompanyCode = logic
								.getLeaseInvoiceDataKYearlyCompanyCode(this);
						reqParams.put("getLeaseInvoiceDataKYearlyTotalSummaryMRC", getLeaseInvoiceDataKYearlyTotalSummaryMRC);
						reqParams.put("getLeaseInvoiceDataKYearlyTotalSummaryOTC", getLeaseInvoiceDataKYearlyTotalSummaryOTC);
						//reqParams.put("getLeaseInvoiceDataKYearlyCompanyCode", getLeaseInvoiceDataKYearlyCompanyCode);
						reqParams.put("outputLeaseInvoiceDataK", this.outputLeaseInvoiceData);
						reqParams.put("outputLeaseInvoiceDataKYearly", this.outputLeaseInvoiceDataKYearly);
					}
				}
				// if [USER_MST].USER_AUTHORITY = "3" (SANKYU Admin) 
				// Go to "Excel PC Lease Invoice Data (S)" sheet
				if (Integer.parseInt(userInfo.getUserAuthorityCd()) == Constant.SANKYU_ADMIN_CD) {
					// Excel PC Lease Invoice Data (S)
					this.month = String.format("%02d", Integer.parseInt(this.month));
					List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummary = logic.getLeaseInvoiceDataSTotalSummary(this);
					List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSCompanyCode = logic.getLeaseInvoiceDataSCompanyCode(this);
					reqParams.put("getLeaseInvoiceDataSTotalSummary", getLeaseInvoiceDataSTotalSummary);
					reqParams.put("getLeaseInvoiceDataSCompanyCode", getLeaseInvoiceDataSCompanyCode);
					reqParams.put("outputLeaseInvoiceDataS", this.outputLeaseInvoiceData);
				}
			}*/
			
			// PC Lease Invoice Data Download (PC Lease Monthly Summary)
			if (this.outputLeaseInvoiceData) {
				// if [USER_MST].USER_AUTHORITY = "2" (KDDI Admin)
				// Go to "Excel PC Lease Invoice Data (K)" sheet
				if (Integer.parseInt(userInfo.getUserAuthorityCd()) == Constant.KDDI_CD) {
					List<Pad0011Bean> getOrdersByMonthlyCost = logic.getOrdersByMonthlyCost(this);
					List<Pad0011Bean> getOrdersByOneTimeCost = logic.getOrdersByOneTimeCost(this);
					reqParams.put("getOrdersByMonthlyCost", getOrdersByMonthlyCost);
					reqParams.put("getOrdersByOneTimeCost", getOrdersByOneTimeCost);
					reqParams.put("outputLeaseInvoiceDataK", this.outputLeaseInvoiceData);
				}
			}
			
			// PC Lease Invoice Data K Download (Yearly)
			if (this.outputLeaseInvoiceDataKYearly) {
				// if [USER_MST].USER_AUTHORITY = "2" (KDDI Admin)
				// Go to "Excel PC Lease Invoice Data (Y)" sheet
				if (Integer.parseInt(userInfo.getUserAuthorityCd()) == Constant.KDDI_CD) {
					// Excel PC Lease Invoice Data (K) Yearly
					List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummary = logic
							.getLeaseInvoiceDataKYearlyTotalSummary(this);
					List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyCompanyCode = logic
							.getLeaseInvoiceDataKYearlyCompanyCode(this);
					reqParams.put("getLeaseInvoiceDataKYearlyTotalSummary", getLeaseInvoiceDataKYearlyTotalSummary);
					reqParams.put("getLeaseInvoiceDataKYearlyCompanyCode", getLeaseInvoiceDataKYearlyCompanyCode);
					reqParams.put("outputLeaseInvoiceDataK", this.outputLeaseInvoiceData);
					reqParams.put("outputLeaseInvoiceDataKYearly", this.outputLeaseInvoiceDataKYearly);
				}
				
				// if [USER_MST].USER_AUTHORITY = "3" (SANKYU Admin) 
				// Go to "Excel PC Lease Invoice Data (S)" sheet
				if (Integer.parseInt(userInfo.getUserAuthorityCd()) == Constant.SANKYU_ADMIN_CD) {
					// Excel PC Lease Invoice Data (S)
					/*List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummary = logic.getLeaseInvoiceDataSTotalSummary(this);
					List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSCompanyCode = logic.getLeaseInvoiceDataSCompanyCode(this);
					reqParams.put("getLeaseInvoiceDataSTotalSummary", getLeaseInvoiceDataSTotalSummary);
					reqParams.put("getLeaseInvoiceDataSCompanyCode", getLeaseInvoiceDataSCompanyCode);*/
					List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummaryMRC = logic.getLeaseInvoiceDataSTotalSummaryMRC(this);
					List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummaryOTC = logic.getLeaseInvoiceDataSTotalSummaryOTC(this);
					List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSCompanyCode = logic.getLeaseInvoiceDataSCompanyCode(this);
					reqParams.put("getLeaseInvoiceDataSTotalSummaryMRC", getLeaseInvoiceDataSTotalSummaryMRC);
					reqParams.put("getLeaseInvoiceDataSTotalSummaryOTC", getLeaseInvoiceDataSTotalSummaryOTC);
					reqParams.put("getLeaseInvoiceDataSCompanyCode", getLeaseInvoiceDataSCompanyCode);
					reqParams.put("outputLeaseInvoiceDataS", this.outputLeaseInvoiceDataKYearly);
				}
			}
			
			// PC Lease Data Download
			if (this.outputLeaseData) {
				// Get Data For PC Lease Data Download
				List<OrderLeaseBean> getPCLeaseData = logic.getPCLeaseData(this);
				reqParams.put("outputLeaseData", this.outputLeaseData);
				reqParams.put("getPCLeaseData", getPCLeaseData);
			}
			// put userID
			reqParams.put(Constant.UID, getUserInfo().getUserId());
			String monthYearSelected = DateUtil.formatDate(year+month, DateFormat.YYYYMM, DateFormat.MMM_YY);
			reqParams.put("monthYearSelected", monthYearSelected);
			reqParams.put("yearSelected", year);
			try {
				// Run export excel
				Map<String, Object> resultMap = reportBL.exportExecute(reqParams, ServletActionContext.getResponse());
				// Check output file isExist.
				String outputFile = (String) resultMap.get(Constant.OUTPUT_FILE);
				// get input file
				inputStream = new DownloadInputStream(new File(outputFile));
				// set contentType
				contentType = Constant.EXCEL_TYPE;
	            // set report name
	            reportName = (String) resultMap.get(Constant.OUTPUT_NAME) + Constant.XLSX_EXTENSION;
			} catch (Exception e) {
				// TODO
				e.printStackTrace();
				LOG.info("***********doDataDownload End***********");
	            super.addActionError("PCOD-0006");
				return ERROR;
			}
			LOG.info("*************doDataDownload End***************", "");
			return SUCCESS;
		}
		LOG.info("*************doDataDownload End***************", "");
		return ERROR;
	}
	
	/**
	 * Check validate
	 * 
	 * @return true/false
	 */
	private void checkValidate() throws Exception {
		// PC Lease Invoice Data Download
		if (this.outputLeaseInvoiceData) {
			// Check empty year
			if (StringUtil.isNullOrEmpty(year)) {
				messageId = "CM-0006";
			}
			// Check empty month
			if (StringUtil.isNullOrEmpty(month)) {
				messageId = "CM-0007";
			}
		}
	}
	
	/**
	 * Get list company 
	 * 
	 * @return list company
	 */
	public List<CompanyBean> getCompanyList() {
		return companyList;
	}
	
	/**
	 * Set list company 
	 * 
	 */
	public void setCompanyList(List<CompanyBean> companyList) {
		this.companyList = companyList;
	}


	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}


	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}


	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}


	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}


	/**
	 * @return the userAuthorityCd
	 */
	public String getUserAuthorityCd() {
		return userAuthorityCd;
	}


	/**
	 * @param userAuthorityCd the userAuthorityCd to set
	 */
	public void setUserAuthorityCd(String userAuthorityCd) {
		this.userAuthorityCd = userAuthorityCd;
	}
	
}
