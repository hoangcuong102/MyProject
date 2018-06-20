package jp.co.cyms.apps.fac001.dao;

import java.util.List;

import jp.co.cyms.apps.fac001.bean.DownloadDeliveryOrderBean;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fac001.bean.Pac0022UpdateOrderDtlBean;
import jp.co.cyms.apps.fac001.bean.Pac0024Bean;
import jp.co.cyms.apps.fac001.bean.Pac0024ParamBean;
import jp.co.cyms.base.BaseDao;

/**
 * [SAC0024] Delivery Order Entry/Update DAO
 * 
 * @author AnhNT2
 * @since 23/01/2018
 */
public class Pac0024Dao extends BaseDao {
    /**
     * Install
     */
    public Pac0024Dao() {
        super();
    }
    
    /**
     * Insert database
     * @return number row insert
     */
	public int doInsert(Pac0024Bean pac0024Bean) throws Exception{
		return this.update("FAC0024.doInsert", pac0024Bean);
	}
    
    /**
     * Update database
     * @return number row insert
     */
	public int doUpdate(Pac0024Bean pac0024Bean) throws Exception{
		return this.update("FAC0024.doUpdate", pac0024Bean);
	}
    
    /**
     * Check exist
     * @return number row insert
     */
	public boolean checkExist(Pac0024Bean pac0024Bean) throws Exception{
		int count = this.queryCount("FAC0024.checkExist", pac0024Bean);
		if(count > 0){
			return true;
		}
		return false;
	}
	
    /**
     * Get seqNo by order id
     * @return number row insert
     */
	public int getSeqNoByOrderId(Pac0024Bean pac0024Bean) throws Exception{
		int seqNo = this.queryCount("FAC0024.getSeqNoByOrderId", pac0024Bean);
		return seqNo;
	}
	
    /**
     * Get company by order id
     * @return CompanyBean
     */
	public OrderBean getCompanyCdByOrderId(Pac0024Bean pac0024Bean) throws Exception{
		OrderBean orderBean = new OrderBean();
		orderBean = (OrderBean) this.queryObject("FAC0024.getCompanyCdByOrderId", pac0024Bean);
        return orderBean;
	}
	
    /**
     * Init load data
     * @return list orderDo
     */
	@SuppressWarnings("unchecked")
	public List<Pac0024Bean> getListOrderDoByOrderId(OrderBean orderBean) throws Exception{
		List<Pac0024Bean> list = null;
		list = (List<Pac0024Bean>)this.queryList("FAC0024.getListOrderDoByOrderId", orderBean);
		return list;
	}
    
    /**
     * Sign Upload/Delete.
     * @return number row insert
     */
	public int doSignedUploadDelete(Pac0024Bean pac0024Bean) throws Exception{
		return this.update("FAC0024.doSignedUploadDelete", pac0024Bean);
	}
    
    /**
     * Generated Upload/Delete.
     * @return number row insert
     */
	public int doGeneratedUploadDelete(Pac0024Bean pac0024Bean) throws Exception{
		return this.update("FAC0024.doGeneratedUploadDelete", pac0024Bean);
	}
    
    /**
     * Delete
     * @return number update
     */
	public int doDelete(Pac0024Bean pac0024Bean) throws Exception{
		return this.update("FAC0024.doDelete", pac0024Bean);
	}
    
    /**
     * Save
     * @return number update
     */
	public int doSave(Pac0024Bean pac0024Bean) throws Exception{
		return this.update("FAC0024.doSave", pac0024Bean);
	}
	
    /**
     * Get data delivery order.
     * @return list orderDo
     */
	@SuppressWarnings("unchecked")
	public List<DownloadDeliveryOrderBean> searchDeliveryOrder(Pac0024ParamBean pac0024ParamBean) throws Exception{
		List<DownloadDeliveryOrderBean> list = null;
		list = (List<DownloadDeliveryOrderBean>)this.queryList("FAC0024.searchDeliveryOrder", pac0024ParamBean);
		return list;
	}
	
    /**
     * Get data header delivery order.
     * @return list orderDo
     */
	public DownloadDeliveryOrderBean getDataHeaderForDownloadDelivery(Pac0024ParamBean pac0024ParamBean) throws Exception{
		DownloadDeliveryOrderBean downloadDeliveryOrderBean = null;
		downloadDeliveryOrderBean = (DownloadDeliveryOrderBean)this.queryObject("FAC0024.getDataHeaderForDownloadDelivery", pac0024ParamBean);
		return downloadDeliveryOrderBean;
	}
	
	/**
	 * Get Quotation No
	 * 
	 * @param orderBean
	 * @return Quotation No
	 * @throws Exception
	 */
	public String getQuotationNo(OrderBean orderBean) throws Exception{
		String quotation = (String)this.queryObject("FAC0024.getQuotationNo", orderBean);
		return quotation;
	}
	
	/**
	 * Get list order_lease by orderId
	 * [ORDER_LEASE].SERIAL_NO <> NULL
	 * 
	 * @param orderLeaseBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderLeaseBean> getListOrderLease(OrderLeaseBean orderLeaseBean){
		List<OrderLeaseBean> list = null;
		list = (List<OrderLeaseBean>)this.queryList("FAC0024.getDataOrderLease", orderLeaseBean);
		return list;
	}
	
	/**
	 * Get list order_dtl by orderId
	 * 
	 * @param pac0024ParamBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pac0022UpdateOrderDtlBean> getListDataOrderDtlByOrderId(Pac0024ParamBean pac0024ParamBean){
		List<Pac0022UpdateOrderDtlBean> list = null;
		list = (List<Pac0022UpdateOrderDtlBean>)this.queryList("FAC0024.getListDataOrderDtlByOrderId", pac0024ParamBean);
		return list;
	}
	
	/**
	 * update record in ORDER_LEASE
	 * 
	 * @param orderLeaseBean
	 */
	public void updateOrderLease(OrderLeaseBean orderLeaseBean){
		this.delete("FAC0024.updateOrderLease", orderLeaseBean);
	}
	
	/**
	 * update record in ORDER_DTL
	 * 
	 * @param pac0022UpdateOrderDtlBean
	 */
	public void updateKddiDeliverQty(Pac0022UpdateOrderDtlBean pac0022UpdateOrderDtlBean){
		this.update("FAC0024.updateKddiDeliverQty", pac0022UpdateOrderDtlBean);
	}
	
	/**
     * Check exist ORDER_LEASE
     * @return number row insert
     */
	public boolean checkExistOrderLease(OrderLeaseBean orderLeaseBean) throws Exception{
		int count = this.queryCount("FAC0024.checkExistOrderLease", orderLeaseBean);
		if(count > 0){
			return true;
		}
		return false;
	}
    
    /**
     * Check is delivery all by order id
     * @return number update
     */
	public int checkIsDeliveryByOrderId(Pac0024ParamBean pac0024ParamBean) throws Exception{
		return this.queryCount("FAC0024.checkIsDeliveryByOrderId", pac0024ParamBean);
	}
	
    /**
     * Get status of bill order
     * @return BillFg
     */
	public Pac0024Bean getBillFgByOrderId(Pac0024ParamBean pac0024ParamBean) throws Exception{
		Pac0024Bean pac0024Bean = null;
		pac0024Bean = (Pac0024Bean)this.queryObject("FAC0024.getBillFgByOrderId", pac0024ParamBean);
		return pac0024Bean;
	}
	
	/**
	 * Update status, mth start, end of bill order
	 * 
	 * @param pac0022UpdateOrderDtlBean
	 */
	public void updateBillFgByOrderId(Pac0024ParamBean pac0024ParamBean){
		this.update("FAC0024.updateBillFgByOrderId", pac0024ParamBean);
	}
	
	/**
	 * insert record in ORDER_STATUS
	 * 
	 * @param orderStatusBean
	 */
	public void insertOrderStatusDeliver(Pac0022UpdateOrderDtlBean orderStatusBean){
		this.update("FAC0024.insertOrderStatusDeliver", orderStatusBean);
	}
	
	/**
	 * Count item delivered
	 * 
	 * @param pac0024Bean
	 * @return
	 */
	public int countItemDelivered(Pac0024Bean pac0024Bean){
		return this.queryCount("FAC0024.countItemDelivered", pac0024Bean);
	}
	
	/**
	 * Update ORDER_DTL.KDDI_DELIVER_DT = null, ORDER_DTL.KDDI_DELIVER_QTY = null
	 * 
	 * @param pac0024Bean
	 */
	public void updateOrderDtlWhenDeleteDo(Pac0024Bean pac0024Bean){
		this.update("FAC0024.updateOrderDtlWhenDeleteDo", pac0024Bean);
	}
	
	/**
	 * Get orderDo in ORDER_DO by doNo
	 * 
	 * @param pac0024Bean
	 * @return
	 */
	public Pac0024Bean getOrderDoByDoNo(Pac0024Bean pac0024Bean){
		Pac0024Bean orderDo = null;
		orderDo = (Pac0024Bean)this.queryObject("FAC0024.getOrderDoByDoNo", pac0024Bean);
		return orderDo;
	}
	
	/**
	 * Count record DO_GENERATED_FG = NULL
	 * 
	 * @param orderBean
	 * @return
	 */
	public int countRecordGenerateDelete(OrderBean orderBean){
		return this.queryCount("FAC0024.countRecordGenerateDelete", orderBean);
	}
	
	/**
	 * Get list order_lease by orderId when click generate link
	 * [ORDER_LEASE].SERIAL_NO <> NULL AND DO_NO = Do_No or DO_NO is NULL
	 * 
	 * @param orderLeaseBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderLeaseBean> getDataOrderLeaseGenerateLink(OrderLeaseBean orderLeaseBean){
		List<OrderLeaseBean> list = null;
		list = (List<OrderLeaseBean>)this.queryList("FAC0024.getDataOrderLeaseGenerateLink", orderLeaseBean);
		return list;
	}
	
	/**
	 * insert mutil record in ORDER_STATUS
	 * 
	 * @param orderStatusBean
	 */
	public void insertOrderStatusByDoNoGroupByCategoryAbbrev(Pac0022UpdateOrderDtlBean orderStatusBean){
		this.update("FAC0024.insertOrderStatusByDoNoGroupByCategoryAbbrev", orderStatusBean);
	}
	
	/**
	 * update record in ORDER_DTL
	 * set value to KDDI_LEASE_DT
	 * 
	 * @param pac0024ParamBean
	 */
	public void updateKddiLeaseDt(Pac0024ParamBean pac0024ParamBean){
		this.update("FAC0024.updateKddiLeaseDt", pac0024ParamBean);
	}
	
	/**
	 * get lastest DELIVER_MTH of the order
	 * 
	 * @param orderId
	 */
	public String getLastestDelivery(String orderId){
		return (String) this.queryObject("FAC0024.getLastestDelivery", orderId);
	}
	
	/**
	 * Update status, mth start, end of bill order is null
	 * 
	 * @param pac0024Bean
	 */
	public void updateBillFgWhenDelete(Pac0024Bean pac0024Bean){
		this.update("FAC0024.updateBillFgWhenDelete", pac0024Bean);
	}
}
