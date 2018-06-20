package jp.co.cyms.apps.fac001.bl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import jp.co.cyms.apps.fac001.bean.DownloadDeliveryOrderBean;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fac001.bean.Pac0022UpdateOrderDtlBean;
import jp.co.cyms.apps.fac001.bean.Pac0024Bean;
import jp.co.cyms.apps.fac001.bean.Pac0024ParamBean;
import jp.co.cyms.apps.fac001.dao.Pac0024Dao;
import jp.co.cyms.base.BaseLogic;


/**
 * [SAC0024] Delivery Order Entry/Update BL
 * 
 * @author AnhNT2
 * @since 23/01/2018
 */
@Service
public class Pac0024BL extends BaseLogic {

    Pac0024Dao dao = new Pac0024Dao();
    
	/**
	 * Insert order do.
	 *
	 * @param pac0024Bean
	 * @return the int
	 * @throws Exception 
	 */
	public int doInsert(Pac0024Bean pac0024Bean) throws Exception {
		return dao.doInsert(pac0024Bean);
	}
    
	/**
	 * Update order do.
	 *
	 * @param pac0024Bean
	 * @return the int
	 * @throws Exception 
	 */
	public int doUpdate(Pac0024Bean pac0024Bean) throws Exception {
		return dao.doUpdate(pac0024Bean);
	}
    
	/**
	 * Check exist.
	 *
	 * @param pac0024Bean 
	 * @return true, if successful
	 * @throws Exception 
	 */
	public boolean checkExist(Pac0024Bean pac0024Bean) throws Exception {
		return dao.checkExist(pac0024Bean);
	}
	
	/**
	 * Get seqNo by order id.
	 *
	 * @param pac0024Bean 
	 * @return the int
	 * @throws Exception 
	 */
	public int getSeqNoByOrderId(Pac0024Bean pac0024Bean) throws Exception {
		return dao.getSeqNoByOrderId(pac0024Bean);
	}
	
	/**
	 * Get company by order id.
	 *
	 * @param pac0024Bean 
	 * @return the int
	 * @throws Exception 
	 */
	public OrderBean getCompanyCdByOrderId(Pac0024Bean pac0024Bean) throws Exception {
		return dao.getCompanyCdByOrderId(pac0024Bean);
	}
		
	/**
	 * Init load data.
	 *
	 * @param pac0024Bean 
	 * @return the int
	 * @throws Exception 
	 */
	public List<Pac0024Bean> getListOrderDoByOrderId(OrderBean orderBean) throws Exception {
		return dao.getListOrderDoByOrderId(orderBean);
	}
	
	/**
	 * Signed Upload/Delete.
	 *
	 * @param pac0024Bean 
	 * @return the int
	 * @throws Exception 
	 */
	public int doSignedUploadDelete(Pac0024Bean pac0024Bean) throws Exception {
		return dao.doSignedUploadDelete(pac0024Bean);
	}
	
	/**
	 * Generated Upload/Delete.
	 *
	 * @param pac0024Bean 
	 * @return the int
	 * @throws Exception 
	 */
	public int doGeneratedUploadDelete(Pac0024Bean pac0024Bean) throws Exception {
		return dao.doGeneratedUploadDelete(pac0024Bean);
	}
	
	/**
	 * Delete.
	 *
	 * @param pac0024Bean 
	 * @return the int
	 * @throws Exception 
	 */
	public int doDelete(Pac0024Bean pac0024Bean) throws Exception {
		return dao.doDelete(pac0024Bean);
	}
	
	/**
	 * Save.
	 *
	 * @param pac0024Bean 
	 * @return the int
	 * @throws Exception 
	 */
	public int doSave(Pac0024Bean pac0024Bean) throws Exception {
		return dao.doSave(pac0024Bean);
	}
	
	/**
	 * Get data delivery order.
	 *
	 * @param pac0024ParamBean 
	 * @return the detail delivery order
	 * @throws Exception 
	 */
	public List<DownloadDeliveryOrderBean> searchDeliveryOrder(Pac0024ParamBean pac0024ParamBean) throws Exception {
		return dao.searchDeliveryOrder(pac0024ParamBean);
	}

	
	/**
	 * Get data for header for download delivery order.
	 *
	 * @param pac0024ParamBean 
	 * @return Data for header for download delivery order
	 * @throws Exception 
	 */
	public DownloadDeliveryOrderBean getDataHeaderForDownloadDelivery(Pac0024ParamBean pac0024ParamBean) throws Exception {
		return dao.getDataHeaderForDownloadDelivery(pac0024ParamBean);
	}
	
	/**
	 * Get Quotation No
	 * 
	 * @param orderBean
	 * @return Quotation No
	 * @throws Exception
	 */
	public String getQuotationNo(OrderBean orderBean) throws Exception{
		return dao.getQuotationNo(orderBean);
	}
	
	/**
	 * Get list order_lease by orderId
	 * [ORDER_LEASE].SERIAL_NO <> NULL
	 * 
	 * @param orderLeaseBean
	 * @return
	 */
	public List<OrderLeaseBean> getListOrderLease(OrderLeaseBean orderLeaseBean){
		return dao.getListOrderLease(orderLeaseBean);
	}
	
	/**
	 * Get list order_dtl by orderId
	 * 
	 * @param pac0024ParamBean
	 * @return
	 */
	public List<Pac0022UpdateOrderDtlBean> getListDataOrderDtlByOrderId(Pac0024ParamBean pac0024ParamBean){
		return dao.getListDataOrderDtlByOrderId(pac0024ParamBean);
	}
	
	/**
	 * update record in ORDER_LEASE
	 * 
	 * @param orderLeaseBean
	 */
	public void updateOrderLease(OrderLeaseBean orderLeaseBean){
		dao.updateOrderLease(orderLeaseBean);
	}
	
	/**
	 * update record in ORDER_DTL
	 * 
	 * @param pac0022UpdateOrderDtlBean
	 */
	public void updateKddiDeliverQty(Pac0022UpdateOrderDtlBean pac0022UpdateOrderDtlBean){
		dao.updateKddiDeliverQty(pac0022UpdateOrderDtlBean);
	}
	
	/**
     * Upload file to location localPath
     */
    public void uploadFile(File file, String fileName, String localPath) throws Exception{
        if (file != null && fileName != null && localPath != null) {			
			// check localPath exists
			File checkFile = new File(localPath);
			if (!checkFile.exists()) {
			    checkFile.mkdir();
			}
			
			// upload file
			try {
			    File localFile = new File(localPath, fileName);
			    FileUtils.copyFile(file, localFile);
			    //return result;
			} catch (Exception e) {
			    e.printStackTrace();
			    throw e;
			    //return null;
			}
        }
    }
    
    /**
     * convert Json to list Pac0024Bean
     * 
     * @param jsonParams
     * @return list Pac0024Bean
     */
    public List<Pac0024Bean> convertJsonToObject(String jsonParams){
    	List<Pac0024Bean> list;
    	try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonParams, new TypeToken<List<Pac0024Bean>>(){}.getType());
			return list;
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * Check exist ORDER_LEASE
     * @return
     */
	public boolean checkExistOrderLease(OrderLeaseBean orderLeaseBean) throws Exception{
		return dao.checkExistOrderLease(orderLeaseBean);
	}
    
    /**
     * Check is delivery all by order id
     * @return
     */
	public int checkIsDeliveryByOrderId(Pac0024ParamBean pac0024ParamBean) throws Exception{
		return dao.checkIsDeliveryByOrderId(pac0024ParamBean);
	}
    
    /**
     * Get status of bill order
     * @return
     */
	public Pac0024Bean getBillFgByOrderId(Pac0024ParamBean pac0024ParamBean) throws Exception{
		return dao.getBillFgByOrderId(pac0024ParamBean);
	}
    
    /**
     * Update status, mth start, end of bill order
     * @return
     */
	public void updateBillFgByOrderId(Pac0024ParamBean pac0024ParamBean) throws Exception{
		dao.updateBillFgByOrderId(pac0024ParamBean);
	}
	
	/**
	 * insert record in ORDER_STATUS
	 * 
	 * @param orderStatusBean
	 */
	public void insertOrderStatusDeliver(Pac0022UpdateOrderDtlBean orderStatusBean){
		dao.insertOrderStatusDeliver(orderStatusBean);
	}
	
	/**
	 * Count item delivered
	 * 
	 * @param pac0024Bean
	 * @return
	 */
	public int countItemDelivered(Pac0024Bean pac0024Bean){
		return dao.countItemDelivered(pac0024Bean);
	}
	
	/**
	 * Update ORDER_DTL.KDDI_DELIVER_DT = null, ORDER_DTL.KDDI_DELIVER_QTY = null
	 * 
	 * @param pac0024Bean
	 */
	public void updateOrderDtlWhenDeleteDo(Pac0024Bean pac0024Bean){
		dao.updateOrderDtlWhenDeleteDo(pac0024Bean);
	}
	
	/**
	 * Get orderDo in ORDER_DO by doNo
	 * 
	 * @param pac0024Bean
	 * @return
	 */
	public Pac0024Bean getOrderDoByDoNo(Pac0024Bean pac0024Bean){
		return dao.getOrderDoByDoNo(pac0024Bean);
	}
	
	/**
	 * check record DO_GENERATED_FG = NULL
	 * 
	 * @param orderBean
	 * @return true if any [ORDER_DO].DO_GENERATED_FG = NULL
	 * 		   else false
	 */
	public boolean checkDoGenerateDelete(OrderBean orderBean){
		int countRecordGenerateDelete = dao.countRecordGenerateDelete(orderBean);
		if (countRecordGenerateDelete > 0){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get list order_lease by orderId when click generate link
	 * [ORDER_LEASE].SERIAL_NO <> NULL AND DO_NO = Do_No or DO_NO is NULL
	 * 
	 * @param orderLeaseBean
	 * @return
	 */
	public List<OrderLeaseBean> getDataOrderLeaseGenerateLink(OrderLeaseBean orderLeaseBean){
		return dao.getDataOrderLeaseGenerateLink(orderLeaseBean);
	}
	
	/**
	 * insert mutil record in ORDER_STATUS
	 * 
	 * @param orderStatusBean
	 */
	public void insertOrderStatusByDoNoGroupByCategoryAbbrev(Pac0022UpdateOrderDtlBean orderStatusBean){
		dao.insertOrderStatusByDoNoGroupByCategoryAbbrev(orderStatusBean);
	}
	
	/**
	 * update record in ORDER_DTL
	 * set value to KDDI_LEASE_DT
	 * 
	 * @param pac0024ParamBean
	 */
	public void updateKddiLeaseDt(Pac0024ParamBean pac0024ParamBean){
		dao.updateKddiLeaseDt(pac0024ParamBean);
	}
	
	/**
	 * get lastest DELIVER_MTH of the order
	 * 
	 * @param pac0024ParamBean
	 */
	public String getLastestDelivery(String orderId){
		return dao.getLastestDelivery(orderId);
	}
	
	/**
	 * Update status, mth start, end of bill order is null
	 * 
	 * @param pac0024Bean
	 */
	public void updateBillFgWhenDelete(Pac0024Bean pac0024Bean){
		dao.updateBillFgWhenDelete(pac0024Bean);
	}
}
