package jp.co.cyms.apps.fad001.dao;

import java.util.List;

import jp.co.cyms.apps.fad001.bean.CompanyBean;
import jp.co.cyms.apps.fad001.bean.ExcelPCLeaseInvoiceDataKYearlyBean;
import jp.co.cyms.apps.fad001.bean.ExcelPCLeaseInvoiceDataSBean;
import jp.co.cyms.apps.fad001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fad001.bean.Pad0011Bean;
import jp.co.cyms.apps.fad001.form.Pad0011Form;
import jp.co.cyms.base.BaseDao;

/**
 * Data Download DAO
 *
 * @author anhnt2
 * @since 2018/01/11
 */
public class Pad0011Dao extends BaseDao {
    /**
     * Install
     */
    public Pad0011Dao() {
        super();
    }
    
    /**
     * Get list company
     * @return List stock
     */
    @SuppressWarnings("unchecked")
    public List<CompanyBean> getListCompany(){
        List<CompanyBean> list = null;
        list = (List<CompanyBean>) this.queryList("FAD0011.getListCompany");
        return list;
    }
    
    /**
     * Get list monthly cost
     * @return List stock
     */
    @SuppressWarnings("unchecked")
    public List<Pad0011Bean> getOrdersByMonthlyCost(Pad0011Form pad0011Form){
        List<Pad0011Bean> list = null;
        list = (List<Pad0011Bean>) this.queryList("FAD0011.getOrdersByMonthlyCost", pad0011Form);
        return list;
    }
    
    /**
     * Get list one time cost
     * @return List stock
     */
    @SuppressWarnings("unchecked")
    public List<Pad0011Bean> getOrdersByOneTimeCost(Pad0011Form pad0011Form){
        List<Pad0011Bean> list = null;
        list = (List<Pad0011Bean>) this.queryList("FAD0011.getOrdersByOneTimeCost", pad0011Form);
        return list;
    }
    
    /**
     * Excel PC Lease Invoice Data (S)
     * @return List stock
     *//*
    @SuppressWarnings("unchecked")
    public List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummary(Pad0011Form pad0011Form){
        List<ExcelPCLeaseInvoiceDataSBean> list = null;
        list = (List<ExcelPCLeaseInvoiceDataSBean>) this.queryList("FAD0011.getLeaseInvoiceDataSTotalSummary", pad0011Form);
        return list;
    }*/
    
    /**
     * Excel PC Lease Invoice Data (S)
     * @return List stock MRC
     */
    @SuppressWarnings("unchecked")
    public List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummaryMRC(Pad0011Form pad0011Form){
        List<ExcelPCLeaseInvoiceDataSBean> list = null;
        list = (List<ExcelPCLeaseInvoiceDataSBean>) this.queryList("FAD0011.getLeaseInvoiceDataSTotalSummaryMRC", pad0011Form);
        return list;
    }
    
    /**
     * Excel PC Lease Invoice Data (S)
     * @return List stock OTC
     */
    @SuppressWarnings("unchecked")
    public List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummaryOTC(Pad0011Form pad0011Form){
        List<ExcelPCLeaseInvoiceDataSBean> list = null;
        list = (List<ExcelPCLeaseInvoiceDataSBean>) this.queryList("FAD0011.getLeaseInvoiceDataSTotalSummaryOTC", pad0011Form);
        return list;
    }
    
    /**
     * Excel PC Lease Invoice Data (S)
     * @return List stock
     */
    @SuppressWarnings("unchecked")
    public List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSCompanyCode(Pad0011Form pad0011Form){
        List<ExcelPCLeaseInvoiceDataSBean> list = null;
        list = (List<ExcelPCLeaseInvoiceDataSBean>) this.queryList("FAD0011.getLeaseInvoiceDataSCompanyCode", pad0011Form);
        return list;
    }
    
    /**
     * Get list lease data
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<OrderLeaseBean> getPCLeaseData(Pad0011Form pad0011Form){
        List<OrderLeaseBean> list = null;
        list = (List<OrderLeaseBean>) this.queryList("FAD0011.getPCLeaseData", pad0011Form);
        return list;
    }
    
    /**
     * Excel PC Lease Invoice Data (K) Yearly
     * Data for sheet Company
     * 
     * @return List ExcelPCLeaseInvoiceDataKYearlyBean
     */
    @SuppressWarnings("unchecked")
	public List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyCompanyCode(Pad0011Form pad0011Form)
			throws Exception {
        List<ExcelPCLeaseInvoiceDataKYearlyBean> list = null;
        list = (List<ExcelPCLeaseInvoiceDataKYearlyBean>) this.queryList("FAD0011.getLeaseInvoiceDataKYearlyCompanyCode", pad0011Form);
        return list;
    }
    
    /**
     * Excel PC Lease Invoice Data (K) Yearly
     * Data for sheet Total Summary
     * 
     * @return List ExcelPCLeaseInvoiceDataKYearlyBean
     */
    @SuppressWarnings("unchecked")
	public List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummary(Pad0011Form pad0011Form)
			throws Exception {
        List<ExcelPCLeaseInvoiceDataKYearlyBean> list = null;
        list = (List<ExcelPCLeaseInvoiceDataKYearlyBean>) this.queryList("FAD0011.getLeaseInvoiceDataKYearlyTotalSummary", pad0011Form);
        return list;
    }
    
    /**
     * Excel PC Lease Invoice Data (MRC) Yearly
     * Data for sheet Total Summary
     * 
     * @return List ExcelPCLeaseInvoiceDataKYearlyBean
     */
    @SuppressWarnings("unchecked")
	public List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummaryMRC(Pad0011Form pad0011Form)
			throws Exception {
        List<ExcelPCLeaseInvoiceDataKYearlyBean> list = null;
        list = (List<ExcelPCLeaseInvoiceDataKYearlyBean>) this.queryList("FAD0011.getLeaseInvoiceDataKYearlyTotalSummaryDetailMRC", pad0011Form);
        return list;
    }
    
    /**
     * Excel PC Lease Invoice Data (OTC) Yearly
     * Data for sheet Total Summary
     * 
     * @return List ExcelPCLeaseInvoiceDataKYearlyBean
     */
    @SuppressWarnings("unchecked")
	public List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummaryOTC(Pad0011Form pad0011Form)
			throws Exception {
        List<ExcelPCLeaseInvoiceDataKYearlyBean> list = null;
        list = (List<ExcelPCLeaseInvoiceDataKYearlyBean>) this.queryList("FAD0011.getLeaseInvoiceDataKYearlyTotalSummaryDetailOTC", pad0011Form);
        return list;
    }
}
