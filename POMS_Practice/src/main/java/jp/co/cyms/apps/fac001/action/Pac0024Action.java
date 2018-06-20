package jp.co.cyms.apps.fac001.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.cyms.apps.fac001.bean.DownloadDeliveryOrderBean;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fac001.bean.Pac0022UpdateOrderDtlBean;
import jp.co.cyms.apps.fac001.bean.Pac0024Bean;
import jp.co.cyms.apps.fac001.bean.Pac0024ParamBean;
import jp.co.cyms.apps.fac001.bl.Pac0024BL;
import jp.co.cyms.apps.fac001.bl.Pac0024ReportBL;
import jp.co.cyms.apps.fac001.form.Pac0024Form;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.DateFormat;
import jp.co.cyms.common.DateUtil;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.ConfigBean;

/**
 * [SAC0024] Delivery Order Entry/Update Action
 * 
 * @author AnhNT2
 * @since 23/01/2018
 */
public class Pac0024Action extends Pac0024Form {
	
	/** Country code. */
	private static final String SG = "SG";
	private static final String MY = "MY";
	private static final String SA = "SA";
	private static final String OM = "OM";
	private static final String SE = "SE";
	
	private static final String signedUpload = "1";
	private static final String signedDelete = "";
	private static final String delete = "1";
	private static final String generatedNew = "1";
	private static final String generatedDelete = "";
	
	/** Business logic object. */
	Pac0024BL logic = new Pac0024BL();

	/** List Delivery Order */
	private List<DownloadDeliveryOrderBean> listDownloadDeliveryOrder;
	
	/** Message Id */
	private String messageId;
	
	/** List data of table orderDo */
	private List<Pac0024Bean> listPac0024Bean;
	
	private static final long serialVersionUID = 1L;

	/** File report **/
    private InputStream inputStream;

    /** contentType **/
    private String contentType;
    
    /** Report Name*/
    private String reportName;

	/** user authority */
	private String userAuthority;

	/**
	 * check in ORDER_DO set disabled for [Generate New] button 
	 * if any [ORDER_DO].DO_GENERATED_FG = NULL set value false
	 * else set value true
	 */
	private boolean generateNewFlag;
	
	/**
	 * Init load data.
	 * @throws Exception 
	 * 
	 */
	public String execute() throws Exception {
		LOG.info("*************execute Start*************", "");
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(this.orderId);
		// Get quotation no
		this.quotationNo = logic.getQuotationNo(orderBean);
		LOG.info("*************execute End*************", "");
		return SUCCESS;
	}
	
	/**
	 * Load data from order_do
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadDataOrderDo() throws Exception{
		LOG.info("*************initScreen Start*************", "");
		OrderBean orderBean = new OrderBean();
		orderBean.setOrderId(this.orderId);
		// Get list order_no
		listPac0024Bean = logic.getListOrderDoByOrderId(orderBean);
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// Get generateNewFlag
		generateNewFlag = !logic.checkDoGenerateDelete(orderBean);
		LOG.info("*************initScreen End*************", "");
		return SUCCESS;
	}
	
	/**
	 * Generate New.
	 * @throws Exception 
	 * 
	 */
	public String doGenerateNew() throws Exception {
		LOG.info("*************doGenerateNew Start*************", "");
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// SANKYU Admin can access to this screen as read-only. Only enable "Generated->View" and "Signed->View" link labels and [Close] button.
		if (!userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY)) {

			// Check orderId, doNo isEmpty
	        if(StringUtil.isEmpty(this.orderId)) {
	            LOG.info("*************doGenerateNew End*************", "");
	            return ERROR;
	        }
	        // Get transaction
	        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
	        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
	        
			Pac0024Bean pac0024Bean = new Pac0024Bean();
			pac0024Bean.setOrderId(orderId);
			// check before save
			checkValidate(pac0024Bean);
			if (this.messageId == null || "".equals(this.messageId)) {
				
				try {
					// If data has not exist on DB: call Inserted
					OrderBean orderBean = new OrderBean();
					orderBean = logic.getCompanyCdByOrderId(pac0024Bean);
					int seqNo = logic.getSeqNoByOrderId(pac0024Bean) + 1;
					String countryCd = orderBean.getCountryCd();
					String companyCd = orderBean.getCompanyCd();
					// If  [ORDER].COUNTRY_CD = "SG" or "MY" or "SA" or "OM",  CUSTOMER_COMPANY_CD = "SE"
					if (countryCd.equals(SG) || countryCd.equals(MY) || countryCd.equals(SA) || countryCd.equals(OM)) {
						companyCd = SE;
					}
					// Set value for do no
					pac0024Bean.setDoNo(createDoNo(seqNo));
					
					// Set value for seq no
					pac0024Bean.setSeqNo(String.valueOf(seqNo));
					
					// Set value for customer company code
					pac0024Bean.setCustomerCompanyCd(companyCd);
					
					// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
					loadDataInfoUpdate(pac0024Bean);
					
					// Get current date
					SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
					String deliveryDtCurrent = df.format(pac0024Bean.getUpdatedDtLocal());
					
					// Set Delivery date = current date
					pac0024Bean.setDeliverDt(deliveryDtCurrent);
					
					// Set DO_GENERATED_FG = "1"
					pac0024Bean.setDoGeneratedFg(generatedNew);
					
					// Insert record to ORDER_DO
					logic.doInsert(pac0024Bean);
					
					// Update DO_NO in [ORDER_LEASE]
					OrderLeaseBean orderLeaseParam = new OrderLeaseBean();
					orderLeaseParam.setOrderId(pac0024Bean.getOrderId());
					orderLeaseParam.setDoNo(null);
					orderLeaseParam.setSerialNo("1");
					
					// Get list order_lease with [ORDER_LEASE].SERIAL_NO <> null
					List<OrderLeaseBean> listOrderLease = logic.getListOrderLease(orderLeaseParam);
					
					if (listOrderLease != null && listOrderLease.size() > 0){
						for (OrderLeaseBean orderLease : listOrderLease){
							if ( !StringUtil.isEmpty(orderLease.getSerialNo()) ){
								// Set value DO_NO
								orderLease.setDoNo(pac0024Bean.getDoNo());
								// Set deliverFg = null
								orderLease.setDeliverFg(Constant.DELIVER_FG);
								// Set deliverDt = current date
								orderLease.setDeliverDt(df.parse(deliveryDtCurrent));
								// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
								orderLease.setUpdatedUser(super.getUpdatedUser());
								orderLease.setUpdatedDtLocal(super.getUpdatedDtLocal());
								orderLease.setTimeDifference(super.getTimeDifference());
								// Set updateGenerateNew = true
								orderLease.setUpdateGenerateNew(true);
								// Update DO_NO
								logic.updateOrderLease(orderLease);
							}
						}
					}
					// Create DO file. Save to [CONFIG].DO_DIR
					createDoFile(pac0024Bean.getDoNo());
					
					// Insert to ORDER_STATUS
					insertOrderStatusByDoNoGroupByCategoryAbbrev(pac0024Bean);
					
					transactionManager.commit(transactionStatus);
				} catch (Exception e) {
					// TODO Auto-generated catch block
		            transactionManager.rollback(transactionStatus);
		            e.printStackTrace();
		            LOG.info("*************Do rollback doGenerateNew *************", "");
				}
			}
			// Reload data json
			loadDataOrderDo();
		}
		LOG.info("*************doGenerateNew End*************", "");
		return SUCCESS;
	}
	
	/**
	 * Signed Upload.
	 * @throws Exception 
	 * 
	 */
	public String doSignedUpload() throws Exception {
		LOG.info("*************doSignedUpload Start*************", "");
		// Check orderId, doNo isEmpty
        if(StringUtil.isEmpty(this.orderId) || StringUtil.isEmpty(this.doNo)) {
            LOG.info("*************doSignedUpload End*************", "");
            return ERROR;
        }
        // Get transaction
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        // Get configBean
	    ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
        
		try {
			Pac0024Bean pac0024Bean = new Pac0024Bean();
			pac0024Bean.setOrderId(orderId);
			pac0024Bean.setDoNo(doNo);
			// Set DO_SIGNBACK_FG = 1
			pac0024Bean.setDoSignbackFg(signedUpload);
			// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
			loadDataInfoUpdate(pac0024Bean);
			
			String fileName = this.doNo + "_Signback.pdf";
			String localPath = config.getDoDir() + "\\";
			// Upload file Signback.pdf
			logic.uploadFile(this.fileUpload, fileName, localPath);
			
			
			// Update [ORDER_NO].DO_SIGNBACK_FG = 1
			logic.doSignedUploadDelete(pac0024Bean);
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
            LOG.info("*************Do rollback doSignedUpload *************", "");
		}
		// Reload data json
		loadDataOrderDo();
		LOG.info("*************doSignedUpload End*************", "");
		return SUCCESS;
	}
	
	/**
	 * Signed Delete.
	 * @throws Exception 
	 * 
	 */
	public String doSignedDelete() throws Exception {
		LOG.info("*************doSignedDelete Start*************", "");
		// Check orderId, doNo isEmpty
        if(StringUtil.isEmpty(this.orderId) || StringUtil.isEmpty(this.doNo)) {
            LOG.info("*************doSignedDelete End*************", "");
            return ERROR;
        }
        // Get transaction
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        
		try {
			Pac0024Bean pac0024Bean = new Pac0024Bean();
			pac0024Bean.setOrderId(orderId);
			pac0024Bean.setDoNo(doNo);
			// Set DO_SIGNBACK_FG = ""
			pac0024Bean.setDoSignbackFg(signedDelete);
			// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
			loadDataInfoUpdate(pac0024Bean);
			// Delete DO_Signback file
			deleteDoFile(pac0024Bean.getDoNo(), Constant.SIGNED);
			
			// Update [ORDER_NO].DO_SIGNBACK_FG = ""
			logic.doSignedUploadDelete(pac0024Bean);
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
            LOG.info("*************Do rollback doSignedDelete *************", "");
		}
		// Reload data json
		loadDataOrderDo();
		LOG.info("*************doSignedDelete End*************", "");
		return SUCCESS;
	}

	
	/**
	 * Generated Generate.
	 * @throws Exception 
	 * 
	 */
	public String doGeneratedGenerate() throws Exception {
		LOG.info("*************doGeneratedGenerate Start*************", "");
		// Check orderId, doNo isEmpty
        if(StringUtil.isEmpty(this.orderId) || StringUtil.isEmpty(this.doNo)) {
            LOG.info("*************doGeneratedGenerate End*************", "");
            return ERROR;
        }
        // Get transaction
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        
        // Param Bean
		Pac0024Bean pac0024Bean = new Pac0024Bean();
		pac0024Bean.setOrderId(orderId);
		pac0024Bean.setDoNo(doNo);
		// Set DO_GENERATED_FG = "1"
		pac0024Bean.setDoGeneratedFg(generatedNew);
		// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
		loadDataInfoUpdate(pac0024Bean);
		
		try {
			// Update [ORDER_NO].DO_GENERATED_FG = ""
			logic.doGeneratedUploadDelete(pac0024Bean);
			
			// Update DO_NO in [ORDER_LEASE]
			OrderLeaseBean orderLeaseParam = new OrderLeaseBean();
			orderLeaseParam.setOrderId(pac0024Bean.getOrderId());
			orderLeaseParam.setDoNo(doNo);
			
			// Update [ORDER_LEASE]
			// Get list order_lease with [ORDER_LEASE].SERIAL_NO <> null
			orderLeaseParam.setSerialNo("1");
			List<OrderLeaseBean> listOrderLease = logic.getDataOrderLeaseGenerateLink(orderLeaseParam);
			Date deliveryDtCurrent = new Date();
			if (listOrderLease != null && listOrderLease.size() > 0){
				for (OrderLeaseBean orderLease : listOrderLease){
					if ( !StringUtil.isEmpty(orderLease.getSerialNo()) ){
						// Set value DO_NO
						orderLease.setDoNo(pac0024Bean.getDoNo());
						// Set deliverFg = null
						orderLease.setDeliverFg(Constant.DELIVER_FG);
						// Set deliverDt = current date
						orderLease.setDeliverDt(deliveryDtCurrent);
						// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
						orderLease.setUpdatedUser(super.getUpdatedUser());
						orderLease.setUpdatedDtLocal(super.getUpdatedDtLocal());
						orderLease.setTimeDifference(super.getTimeDifference());
						// Set updateGenerateDoNoNull = true
						orderLease.setUpdateGenerateLinkDoNoNull(true);
						// Update DO_NO
						logic.updateOrderLease(orderLease);
					}
				}
			}
			
			// Get list order_lease with [ORDER_LEASE].SERIAL_NO = null
			orderLeaseParam.setSerialNo(null);
			listOrderLease = logic.getDataOrderLeaseGenerateLink(orderLeaseParam);
			if (listOrderLease != null && listOrderLease.size() > 0){
				for (OrderLeaseBean orderLease : listOrderLease){
					if ( StringUtil.isEmpty(orderLease.getSerialNo()) ){
						// Set value DO_NO
						orderLease.setDoNo(pac0024Bean.getDoNo());
						// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
						orderLease.setUpdatedUser(super.getUpdatedUser());
						orderLease.setUpdatedDtLocal(super.getUpdatedDtLocal());
						orderLease.setTimeDifference(super.getTimeDifference());
						// Set updateGenerateLink = true
						orderLease.setUpdateGenerateLink(true);
						// Update DO_NO
						logic.updateOrderLease(orderLease);
					}
				}
			}
			
			// Create DO file
			createDoFile(pac0024Bean.getDoNo());

			// Insert to ORDER_STATUS
			insertOrderStatusByDoNoGroupByCategoryAbbrev(pac0024Bean);
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
            LOG.info("*************Do rollback doGeneratedGenerate *************", "");
		}
		// Reload data json
		loadDataOrderDo();
		LOG.info("*************doGeneratedGenerate End*************", "");
		return SUCCESS;
	}
	
	/**
	 * Generated Delete.
	 * @throws Exception 
	 * 
	 */
	public String doGeneratedDelete() throws Exception {
		LOG.info("*************doGeneratedDelete Start*************", "");
		// Check orderId, doNo isEmpty
        if(StringUtil.isEmpty(this.orderId) || StringUtil.isEmpty(this.doNo)) {
            LOG.info("*************doGeneratedDelete End*************", "");
            return ERROR;
        }
        // Get transaction
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        
		Pac0024Bean pac0024Bean = new Pac0024Bean();
		pac0024Bean.setOrderId(orderId);
		pac0024Bean.setDoNo(doNo);
		// Set DO_GENERATED_FG = "1"
		pac0024Bean.setDoGeneratedFg(generatedDelete);
		// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
		loadDataInfoUpdate(pac0024Bean);
		
		try {
			// Update [ORDER_NO].DO_GENERATED_FG = "1"
			logic.doGeneratedUploadDelete(pac0024Bean);
			
			// Update DO_NO in [ORDER_LEASE]
			OrderLeaseBean orderLeaseParam = new OrderLeaseBean();
			orderLeaseParam.setOrderId(pac0024Bean.getOrderId());
			orderLeaseParam.setDoNo(doNo);
			orderLeaseParam.setSerialNo(null);
			
			// Get list order_lease with [ORDER_LEASE].DO_NO = doNo
			List<OrderLeaseBean> listOrderLease = logic.getListOrderLease(orderLeaseParam);

			if (listOrderLease != null && listOrderLease.size() > 0){
				for (OrderLeaseBean orderLease : listOrderLease){
					if ( !StringUtil.isEmpty(orderLease.getSerialNo()) ){
						// Set value DO_NO
						orderLease.setDoNo(pac0024Bean.getDoNo());
						// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
						orderLease.setUpdatedUser(super.getUpdatedUser());
						orderLease.setUpdatedDtLocal(super.getUpdatedDtLocal());
						orderLease.setTimeDifference(super.getTimeDifference());
						// Set updateGenerateDelete = true
						orderLease.setUpdateGenerateDelete(true);
						// Update DO_NO
						logic.updateOrderLease(orderLease);
					}
				}
			}
			
			// Delete DO file
			deleteDoFile(pac0024Bean.getDoNo(), Constant.GENERATED);

			// Insert to ORDER_STATUS
			insertOrderStatusDeliver(pac0024Bean.getDoNo(), Constant.DELETE);
			
			// Update [ORDERS].BILL_FG, BILL_MTH_START, BILL_MTH_START = NULL
			logic.updateBillFgWhenDelete(pac0024Bean);
			
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
            LOG.info("*************Do rollback doGeneratedDelete *************", "");
		}
		// Reload data json
		loadDataOrderDo();
		LOG.info("*************doGeneratedDelete End*************", "");
		return SUCCESS;
	}
	
	/**
	 * Delete.
	 * @throws Exception 
	 * 
	 */
	public String doDelete() throws Exception {
		LOG.info("*************doDelete Start*************", "");
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// SANKYU Admin can access to this screen as read-only. Only enable "Generated->View" and "Signed->View" link labels and [Close] button.
		if (!userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY)) {

			// Check orderId, doNo isEmpty
	        if(StringUtil.isEmpty(this.orderId) || StringUtil.isEmpty(this.doNo)) {
	            LOG.info("*************doDelete End*************", "");
	            return ERROR;
	        }
	        // Get transaction
	        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
	        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
	        
	        // Param Bean
			Pac0024Bean pac0024Bean = new Pac0024Bean();
			pac0024Bean.setOrderId(orderId);
			pac0024Bean.setDoNo(doNo);
			// Set DELETE_FG = "1"
			pac0024Bean.setDeleteFg(delete);
			// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
			loadDataInfoUpdate(pac0024Bean);
			
			try {
				// Update [ORDER_NO].DELETE_FG = "1"
				logic.doDelete(pac0024Bean);
				
				// Update DO_NO in [ORDER_LEASE]
				OrderLeaseBean orderLeaseParam = new OrderLeaseBean();
				orderLeaseParam.setOrderId(orderId);
				orderLeaseParam.setDoNo(doNo);
				
				// Update [ORDER_LEASE]
				// Get list order_lease with [ORDER_LEASE].SERIAL_NO = null
				orderLeaseParam.setSerialNo(null);
				List<OrderLeaseBean> listOrderLease = logic.getListOrderLease(orderLeaseParam);
				if (listOrderLease != null && listOrderLease.size() > 0){
					for (OrderLeaseBean orderLease : listOrderLease){
						if ( !StringUtil.isEmpty(orderLease.getDoNo()) ){
							// Set value DO_NO
							orderLease.setDoNo(pac0024Bean.getDoNo());
							// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
							orderLease.setUpdatedUser(super.getUpdatedUser());
							orderLease.setUpdatedDtLocal(super.getUpdatedDtLocal());
							orderLease.setTimeDifference(super.getTimeDifference());
							// Set updateGenerateLink = true
							orderLease.setUpdateGenerateLink(true);
							// Update DO_NO
							logic.updateOrderLease(orderLease);
						}
					}
				}
				
				// Update OrderDtl set KDDI_DELIVER_DT, KDDI_DELIVER_QTY
				logic.updateOrderDtlWhenDeleteDo(pac0024Bean);
				
				// Update [ORDERS].BILL_FG, BILL_MTH_START, BILL_MTH_START = NULL
				logic.updateBillFgWhenDelete(pac0024Bean);
				
				transactionManager.commit(transactionStatus);
			} catch (Exception e) {
				// TODO Auto-generated catch block
	            transactionManager.rollback(transactionStatus);
	            e.printStackTrace();
	            LOG.info("*************Do rollback doDelete *************", "");
			}
			// Reload data json
			loadDataOrderDo();
		}
		LOG.info("*************doDelete End*************", "");
		return SUCCESS;
	}
	
	/**
	 * Save.
	 * When click button [Save]
	 * 
	 * @throws Exception 
	 * 
	 */
	public String doSave() throws Exception {
		LOG.info("*************doSave Start*************", "");
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// SANKYU Admin can access to this screen as read-only. Only enable "Generated->View" and "Signed->View" link labels and [Close] button.
		if (!userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY)) {

			// Check orderId, doNo isEmpty
	        if(StringUtil.isEmpty(this.orderId)) {
	            LOG.info("*************doSave End*************", "");
	            return ERROR;
	        }
	        // Get transaction
	        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
	        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			
			// Get list Pac0024Bean from parameter when call ajax
			List<Pac0024Bean> listOrderDo = logic.convertJsonToObject(this.jsonParams);
			// Check listOrderDo is not null
			if (listOrderDo != null && listOrderDo.size() > 0) {
				try {
					// OrderLease param bean
					OrderLeaseBean orderLeaseParam = new OrderLeaseBean();
					// Get date format HH:mm:ss
					Date date = new Date();
					SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy HH:mm:ss");
					SimpleDateFormat dateFormatHour = new SimpleDateFormat(DateFormat.HHMMSS);
					String hour = dateFormatHour.format(date);
					for (Pac0024Bean orderDo: listOrderDo){
						// Get orderDo in table ORDER_DO
						Pac0024Bean orderDoCheckExist = logic.getOrderDoByDoNo(orderDo);
						// If orderNo exist and deliverDt <> null
						if (orderDoCheckExist != null && !StringUtil.isEmpty(orderDo.getDeliverDt())){
							// Value deliverDate input on screen
							String deliverDateOnScreen = orderDo.getDeliverDt();
							// Update [ORDER_DO]
							// Set deliverDt (format date DD-Mon-YY HH:MM:SS)
							orderDo.setDeliverDt(orderDo.getDeliverDt() + " " + hour);
							// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
							loadDataInfoUpdate(orderDo);
							// Save orderDo
							logic.doSave(orderDo);
							
							// Check delivery date on screen is change
							if (!StringUtil.isEmpty(orderDoCheckExist.getDeliverDt())){
								// If delivery date change
								if (!orderDoCheckExist.getDeliverDt().equals(deliverDateOnScreen)
										&& !StringUtil.isNullOrEmpty(orderDoCheckExist.getDoGeneratedFg())
										&& "1".equals(orderDoCheckExist.getDoGeneratedFg())) {
									// Delete file DO old
									deleteDoFile(orderDo.getDoNo(), Constant.GENERATED);
									// Create file DO with Delivery date new
									// Save to [CONFIG].DO_DIR
									createDoFile(orderDo.getDoNo());
								}
							}
							
							// Update [ORDER_LEASE]
							// parambean
							orderLeaseParam.setOrderId(orderId);
							orderLeaseParam.setDoNo(orderDo.getDoNo());
							orderLeaseParam.setSerialNo("1");
							// List order_lease
							List<OrderLeaseBean> listOrderLease = logic.getListOrderLease(orderLeaseParam);
							
							// Check listOrderLease is not null
							if (listOrderLease != null && listOrderLease.size() > 0) {
								Date deliverDate = df.parse(orderDo.getDeliverDt() + " " + hour);
								for (OrderLeaseBean orderLeaseBean : listOrderLease){
									// If orderLeaseBean exist
									if (logic.checkExistOrderLease(orderLeaseBean)){
										// Set value deliverDt
										orderLeaseBean.setDeliverDt(deliverDate);
										// Set value deliverFg = 1
										orderLeaseBean.setDeliverFg(Constant.DELIVER_FG);
										// Set updateSave = true
										orderLeaseBean.setUpdateSave(true);
										// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
										orderLeaseBean.setUpdatedUser(super.getUpdatedUser());
										orderLeaseBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
										orderLeaseBean.setTimeDifference(super.getTimeDifference());
										// update record in [ORDER_LEASE]
										logic.updateOrderLease(orderLeaseBean);
									}
								}
							}
							
							// Update [ORDER_DTL]
							Pac0024ParamBean pac0024ParamBean = new Pac0024ParamBean();
							pac0024ParamBean.setOrderId(orderId);
							List<Pac0022UpdateOrderDtlBean> listPac0022UpdateOrderDtlBean = logic.getListDataOrderDtlByOrderId(pac0024ParamBean);
							// Check listOrderLease is not null
							if (listPac0022UpdateOrderDtlBean != null && listPac0022UpdateOrderDtlBean.size() > 0) {
								for (Pac0022UpdateOrderDtlBean pac0022UpdateOrderDtlBean : listPac0022UpdateOrderDtlBean){
									pac0022UpdateOrderDtlBean.setKddiDeliverDt(DateUtil.formatDate(orderDo.getDeliverDt(), DateFormat.ddMMMyyHHmmss, DateFormat.ddMMMyy));
									// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
									pac0022UpdateOrderDtlBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
									pac0022UpdateOrderDtlBean.setUpdatedUser(super.getUpdatedUser());
									pac0022UpdateOrderDtlBean.setTimeDifference(super.getTimeDifference());
									// Update KDDI_DELIVER_QTY
									logic.updateKddiDeliverQty(pac0022UpdateOrderDtlBean);
								}
							}
						}

						// Insert to ORDER_STATUS
						insertOrderStatusDeliver(orderDo.getDoNo(), Constant.UPDATE);
					}
					
					// Check is delivery all by order id
					Pac0024ParamBean pac0024ParamBean = new Pac0024ParamBean();
					pac0024ParamBean.setOrderId(orderId);
					int isAllDelivery = logic.checkIsDeliveryByOrderId(pac0024ParamBean);
					// Get Status of bill order
					Pac0024Bean pac0024Bean = logic.getBillFgByOrderId(pac0024ParamBean);
					if (isAllDelivery > 0) {
						pac0024ParamBean.setBillFg("");
						pac0024ParamBean.setBillMthStart("");
						pac0024ParamBean.setBillMthEnd("");
					} else {
						String mthStart = logic.getLastestDelivery(orderId);
						pac0024ParamBean.setBillFg("1");
						pac0024ParamBean.setBillMthStart(mthStart);
					}
					// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
					pac0024ParamBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
					pac0024ParamBean.setUpdatedUser(super.getUpdatedUser());
					pac0024ParamBean.setTimeDifference(super.getTimeDifference());
					
					// If  BILL_FG = ""
					if (StringUtil.isNullOrEmpty(pac0024Bean.getBillFg())) {
						// Update [ORDERS]
						logic.updateBillFgByOrderId(pac0024ParamBean);
					}
					
					// Update KDDI_LEASE_DT
					// If total delivered quantity is equals to order quantity,
					// automatically show KDDI_LEASE_DT as the latest delivered date
					// Else KDDI_LEASE_DT = null
					logic.updateKddiLeaseDt(pac0024ParamBean);
					
					transactionManager.commit(transactionStatus);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					transactionManager.rollback(transactionStatus);
					e.printStackTrace();
					LOG.info("*************Do rollback doDelete *************", "");
				} 
			}
			// Reload data json
			loadDataOrderDo();
		}
		LOG.info("*************doSave End*************", "");
		return SUCCESS;
	}
	


	/**
	 * Action download file
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDownload() throws Exception{	 
	    LOG.info("*************doDownload Start*************", "");
	    // Get configBean
	    ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
	    // Check type download, doNo is empty
	    if(StringUtil.isEmpty(this.type) || StringUtil.isEmpty(this.doNo)) {
            LOG.info("*************doDownload End*************", "");
            return ERROR;
        }
        
        try {
        	// Get file name
        	String fileName = doNo;
        	if (type.equals(Constant.SIGNED)){
        		fileName += "_Signback.pdf";
        	} else {
        		fileName += ".pdf";
        	}
            // get input file
            inputStream = new FileInputStream(new File(config.getDoDir() + "\\" + fileName));
            // set contentType
            contentType = Constant.PDF_TYPE;
            // set report name
            reportName = fileName;
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
            LOG.info("***********printAction End***********");
            throw e;
        }   
	    LOG.info("*************doDownload End*************", "");
	    return SUCCESS;
	}
	
	/**
	 * Create DO file
	 * Save to [CONFIG].DO_DIR folder
	 * 
	 * @return
	 * @throws Exception
	 */
	public void createDoFile(String doNo) throws Exception{
	    LOG.info("*************createDoFile Start*************", "");
	    // Get configBean
	    ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
	    // BL report
	    Pac0024ReportBL reportBL = new Pac0024ReportBL();
	    // Create map param
        Map<String, Object> reqParams = new HashMap<String, Object>();
        // put userID
        reqParams.put(Constant.UID, getUserInfo().getUserId());
        // put libre office dir
        reqParams.put("libreOfficeDir", config.getLibreOffice());
        
        // Get data
		// ParamBean
		Pac0024ParamBean paramBean = new Pac0024ParamBean();
		paramBean.setOrderId(this.orderId);
		paramBean.setDoNo(doNo);
        listDownloadDeliveryOrder = logic.searchDeliveryOrder(paramBean);
        reqParams.put("listDownloadDeliveryOrder", listDownloadDeliveryOrder);
        
        DownloadDeliveryOrderBean downloadDeliveryOrderBean = logic.getDataHeaderForDownloadDelivery(paramBean);
        reqParams.put("doNo", doNo);
        reqParams.put("getDataHeaderForDownloadDelivery", downloadDeliveryOrderBean);
        try {
            // Run export excel
            Map<String, Object> resultMap = reportBL.exportExecute(reqParams, ServletActionContext.getResponse());
            // Get output file.
            String outputFile = (String) resultMap.get(Constant.OUTPUT_FILE);
            File outputFileTemp = new File(outputFile);
            // Check output file isExist.
            if (outputFileTemp.exists() && outputFileTemp.isFile()){
            	// Get folder [CONFIG].DO_DIR
            	String doDir = config.getDoDir();
            	// Check folder [CONFIG].DO_DIR exist
            	File checkDoDir = new File(doDir);
            	if (!checkDoDir.exists()){
            		// If not exist, create folder [CONFIG].DO_DIR
            		checkDoDir.mkdirs();
            	}
            	// Info do file
            	String doFileName = "\\" + doNo + ".pdf";
            	File doFile = new File(doDir + doFileName);
            	// Copy file to [CONFIG].DO_DIR folder
            	FileUtils.copyFile(outputFileTemp, doFile);
            	// Delete outputFileTemp
            	outputFileTemp.delete();
            }
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
            LOG.info("***********printAction End***********");
            throw e;
        }   
	    LOG.info("*************createDoFile End*************", "");
	    //return SUCCESS;
	}
	
	/**
	 * Delete DO file from [CONFIG].DO_DIR folder
	 * 
	 * @return
	 * @throws Exception
	 */
	public void deleteDoFile(String doNo, String typeDelete) throws Exception{
	    LOG.info("*************deleteDoFile Start*************", "");
	    // Get configBean
	    ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
        
        try {
        	// Get name DO file
            String doFileName = config.getDoDir() + "\\" + doNo;
            if (typeDelete.equals(Constant.SIGNED)){
            	doFileName += "_Signback.pdf";
        	} else {
        		doFileName += ".pdf";
        	}
            File file = new File(doFileName);
            // Check DO file isExist.
            if (file.exists() && file.isFile()){
            	// Delete DO file
            	file.delete();
            }
        } catch (Exception e) {
            // TODO
            e.printStackTrace();
            LOG.info("***********deleteDoFile End***********");
            throw e;
        }   
	    LOG.info("*************deleteDoFile End*************", "");
	    //return SUCCESS;
	}
	
	/**
	 * Check validate
	 * 
	 * @param pad0021Bean
	 * @return true/false
	 */
	private void checkValidate(Pac0024Bean pac0024Bean) throws Exception {
		
	}
	
	/**
	 * Create doNo
	 * 
	 * @return doNo
	 */
	private String createDoNo(int seqNo) {
		Date date = new Date();
		String doNo = DateUtil.formatDate(date, DateFormat.yy);
		doNo = "DO-" + doNo + String.format("%03d", seqNo);
		return doNo;
	}
	
	/**
	 * Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
	 * 
	 * @return doNo
	 */
	private void loadDataInfoUpdate(Pac0024Bean pac0024Bean) {
		pac0024Bean.setUpdatedUser(super.getUpdatedUser());
		pac0024Bean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		pac0024Bean.setTimeDifference(super.getTimeDifference());
	}
	
	/**
	 * Insert multiple rows into ORDER_STATUS where Do_No group by Category_Cd
	 * 
	 */
	private void insertOrderStatusByDoNoGroupByCategoryAbbrev(Pac0024Bean pac0024Bean) {
		// Insert to ORDER_STATUS
		Pac0022UpdateOrderDtlBean orderStatus = new Pac0022UpdateOrderDtlBean();
		// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
		orderStatus.setUpdatedDtLocal(super.getUpdatedDtLocal());
		orderStatus.setUpdatedUser(super.getUpdatedUser());
		orderStatus.setTimeDifference(super.getTimeDifference());
		// Set order id
		orderStatus.setOrderId(orderId);
		// Set phase
		orderStatus.setPhase(pac0024Bean.getDoNo());
		// Set action
		orderStatus.setActionOrderStatus(Constant.DELIVER);
		// Insert ORDER_STATUS
		logic.insertOrderStatusByDoNoGroupByCategoryAbbrev(orderStatus);
	}
	
	/**
	 * Insert rows into ORDER_STATUS
	 * 
	 */
	private void insertOrderStatusDeliver(String phase, String actionOrderStatus) {
		// Insert to ORDER_STATUS
		Pac0022UpdateOrderDtlBean orderStatus = new Pac0022UpdateOrderDtlBean();
		// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
		orderStatus.setUpdatedDtLocal(super.getUpdatedDtLocal());
		orderStatus.setUpdatedUser(super.getUpdatedUser());
		orderStatus.setTimeDifference(super.getTimeDifference());
		// Set order id
		orderStatus.setOrderId(orderId);
		// Set phase
		orderStatus.setPhase(phase);
		// Set action
		orderStatus.setActionOrderStatus(actionOrderStatus);
		// Insert ORDER_STATUS
		logic.insertOrderStatusDeliver(orderStatus);
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the listPac0024Bean
	 */
	public List<Pac0024Bean> getListPac0024Bean() {
		return listPac0024Bean;
	}

	/**
	 * @param listPac0024Bean the listPac0024Bean to set
	 */
	public void setListPac0024Bean(List<Pac0024Bean> listPac0024Bean) {
		this.listPac0024Bean = listPac0024Bean;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	/**
	 * @return the userAuthority
	 */
	public String getUserAuthority() {
		return userAuthority;
	}

	/**
	 * @param userAuthority
	 *            the userAuthority to set
	 */
	public void setUserAuthority(String userAuthority) {
		this.userAuthority = userAuthority;
	}

	/**
	 * @return the generateNewFlag
	 */
	public boolean isGenerateNewFlag() {
		return generateNewFlag;
	}

	/**
	 * @param generateNewFlag
	 *            the generateNewFlag to set
	 */
	public void setGenerateNewFlag(boolean generateNewFlag) {
		this.generateNewFlag = generateNewFlag;
	}
	
}