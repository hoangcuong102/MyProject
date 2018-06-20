package jp.co.cyms.apps.fad001.bl;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.cyms.apps.fad001.bean.CompanyBean;
import jp.co.cyms.apps.fad001.bean.ExcelPCLeaseInvoiceDataKYearlyBean;
import jp.co.cyms.apps.fad001.bean.ExcelPCLeaseInvoiceDataSBean;
import jp.co.cyms.apps.fad001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fad001.bean.Pad0011Bean;
import jp.co.cyms.apps.fad001.dao.Pad0011Dao;
import jp.co.cyms.apps.fad001.form.Pad0011Form;
import jp.co.cyms.base.BaseLogic;

/**
 * Data Download BL
 * 
 * @author anhnt2
 * @since 2018/01/11
 */
@Service
public class Pad0011BL extends BaseLogic {

    Pad0011Dao dao = new Pad0011Dao();
    
	/**
	 * Insert Stock.
	 *
	 * @return list company
	 * @throws Exception 
	 */
	public List<CompanyBean> getListCompany() throws Exception {
        List<CompanyBean> list = dao.getListCompany();
        if (list.size() > 0) {
            return list;
        }
        return null;
		
	}
	
	/**
	 * Get list monthly cost.
	 *
	 * @return list
	 * @throws Exception 
	 */
	public List<Pad0011Bean> getOrdersByMonthlyCost(Pad0011Form pad0011Form) throws Exception {
        List<Pad0011Bean> list = dao.getOrdersByMonthlyCost(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
		
	}
	
	/**
	 * Get list one time cost.
	 *
	 * @return list
	 * @throws Exception 
	 */
	public List<Pad0011Bean> getOrdersByOneTimeCost(Pad0011Form pad0011Form) throws Exception {
        List<Pad0011Bean> list = dao.getOrdersByOneTimeCost(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
		
	}
	
	/**
	 * Excel PC Lease Invoice Data (S).
	 *
	 * @return list
	 * @throws Exception
	 */
	public List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummaryMRC(Pad0011Form pad0011Form) throws Exception {
        List<ExcelPCLeaseInvoiceDataSBean> list = dao.getLeaseInvoiceDataSTotalSummaryMRC(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
		
	}
	
	/**
	 * Excel PC Lease Invoice Data (S).
	 *
	 * @return list
	 * @throws Exception
	 */
	public List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSTotalSummaryOTC(Pad0011Form pad0011Form) throws Exception {
        List<ExcelPCLeaseInvoiceDataSBean> list = dao.getLeaseInvoiceDataSTotalSummaryOTC(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
		
	}
	
	/**
	 * Excel PC Lease Invoice Data (S).
	 *
	 * @return list
	 * @throws Exception
	 */
	public List<ExcelPCLeaseInvoiceDataSBean> getLeaseInvoiceDataSCompanyCode(Pad0011Form pad0011Form) throws Exception {
        List<ExcelPCLeaseInvoiceDataSBean> list = dao.getLeaseInvoiceDataSCompanyCode(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
		
	}
	
	/**
     * Excel PC Lease Invoice Data (K) Yearly
     * Data for sheet Company
     * 
     * @return List ExcelPCLeaseInvoiceDataKYearlyBean
     */
	public List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyCompanyCode(Pad0011Form pad0011Form)
			throws Exception {
        List<ExcelPCLeaseInvoiceDataKYearlyBean> list = dao.getLeaseInvoiceDataKYearlyCompanyCode(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
	
	/**
     * Excel PC Lease Invoice Data (K) Yearly
     * Data for sheet Total Summary
     * 
     * @return List ExcelPCLeaseInvoiceDataKYearlyBean
     */
	public List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummary(Pad0011Form pad0011Form)
			throws Exception {
        List<ExcelPCLeaseInvoiceDataKYearlyBean> list = dao.getLeaseInvoiceDataKYearlyTotalSummary(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
    
    /**
     * Excel PC Lease Invoice Data (MRC) Yearly
     * Data for sheet Total Summary
     * 
     * @return List ExcelPCLeaseInvoiceDataKYearlyBean
     */
	public List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummaryMRC(Pad0011Form pad0011Form)
			throws Exception {
        List<ExcelPCLeaseInvoiceDataKYearlyBean> list = dao.getLeaseInvoiceDataKYearlyTotalSummaryMRC(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
	
	/**
     * Excel PC Lease Invoice Data (OTC) Yearly
     * Data for sheet Total Summary
     * 
     * @return List ExcelPCLeaseInvoiceDataKYearlyBean
     */
	public List<ExcelPCLeaseInvoiceDataKYearlyBean> getLeaseInvoiceDataKYearlyTotalSummaryOTC(Pad0011Form pad0011Form)
			throws Exception {
        List<ExcelPCLeaseInvoiceDataKYearlyBean> list = dao.getLeaseInvoiceDataKYearlyTotalSummaryOTC(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
    }
		
	/**
	 * Get list lease data.
	 *
	 * @return list order
	 * @throws Exception 
	 */
	public List<OrderLeaseBean> getPCLeaseData(Pad0011Form pad0011Form) throws Exception {
        List<OrderLeaseBean> list = dao.getPCLeaseData(pad0011Form);
        if (list.size() > 0) {
            return list;
        }
        return null;
	}
}
