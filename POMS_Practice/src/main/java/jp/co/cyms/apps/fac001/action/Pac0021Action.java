package jp.co.cyms.apps.fac001.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.cyms.apps.fac001.bean.EmailBean;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderStatusBean;
import jp.co.cyms.apps.fac001.bean.Pac0021Bean;
import jp.co.cyms.apps.fac001.bean.Pac0021ParamBean;
import jp.co.cyms.apps.fac001.bl.Pac0021BL;
import jp.co.cyms.apps.fac001.bl.Pac0021ReportBL;
import jp.co.cyms.apps.fac001.form.Pac0021Form;
import jp.co.cyms.base.email.EmailUtil;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.DownloadInputStream;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.ConfigBean;
import jp.co.cyms.common.bl.CommonBL;

/**
 * PC Order Progress Update / Inquiry Pac0021Action
 * 
 * @author binhvh
 * @since 2018/01/12
 */
public class Pac0021Action extends Pac0021Form {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** list OrderDtl */
	private List<Pac0021Bean> listOrderDtl;

	/** list orderDtl group by orderId */
	private List<List<Pac0021Bean>> listGroupOrderDtl;

	/** list orderId expiring */
	private List<String> listOrderIdExpiring;

	/** user authority */
	private String userAuthority;

	/** File report **/
    private InputStream inputStream;

    /** contentType **/
    private String contentType;
    
    /** Report Name*/
    private String reportName;
    
    private static final String subjectSendMailSankyuApprove = "POMS Order Request Notification";
    
    private static final String subjectSendMailSankyuCancel = "POMS Cancel Order Notification";
    
	/**
	 * Method execute when init SAC0021 screen
	 * 
	 * @return result
	 */
	public String execute() throws Exception{
		LOG.info("*************execute Start*************", "");
		Pac0021BL logic = new Pac0021BL();
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// Get list company code
		this.listCompanyCd = logic.getAllCompanyCd();
		// Get company code of user
		this.companyCd = logic.getCompanyCd(this.userInfo);
		LOG.info("*************execute End*************", "");
		return SUCCESS;
	}

	/**
	 * Search orderDtl action
	 *
	 * @return result data for json
	 * @throws Exception
	 */
	public String doSearch() throws Exception {
		LOG.info("*************doSearch Start*************", "");
		// Logic
		Pac0021BL logic = new Pac0021BL();
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// Get list company code
		this.listCompanyCd = logic.getAllCompanyCd();
		// Get company code of user
		// this.companyCd = logic.getCompanyCd(this.userInfo);

		// ParamBean
		Pac0021ParamBean paramBean = new Pac0021ParamBean();
		paramBean.setCompanyCd(this.companyCd);
		paramBean.setEntryDateFrom(this.entryDateFrom);
		paramBean.setEntryDateTo(this.entryDateTo);
		paramBean.setNonDelivery(this.nonDelivery);
		paramBean.setExpiringOrders(this.expiringOrders);

		// If USER_AUTHORITY = "1"
		if (userAuthority.equals(Constant.GENERAL_USER_AUTHORITY)) {
			paramBean.setCompanyCd(userInfo.getUserCompanyCd());
		}
		// Get list orderDtl
		listOrderDtl = logic.searchOrderDtl(paramBean);
		// Get list order expiring
		listOrderIdExpiring = logic.getOrderIdExpiring(paramBean);

		// Check list order is null
		if (listOrderDtl == null) {
			return SUCCESS;
		}
		this.descendingOrder = true;
		// Generate list orderDtl group by orderId
		listGroupOrderDtl = logic.generateListGroupOrderDtl(listOrderDtl, this.descendingOrder);
		listOrderDtl = null;
		LOG.info("*************doSearch End*************", "");
		return SUCCESS;
	}

	/**
	 * cancel order action
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doCancel() throws Exception {
		LOG.info("*************doDelete Start*************", "");
		// Logic
		Pac0021BL logic = new Pac0021BL();
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// If USER_AUTHORITY != 3 return
		if (!userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY) || StringUtil.isEmpty(this.orderId)) {
			// Reload data for json
			doSearch();
			return SUCCESS;
		}
		
		// ParamBean
		Pac0021ParamBean paramBean = new Pac0021ParamBean();
		// Set orderId
		paramBean.setOrderId(this.orderId);

		// BaseDBBean
		paramBean.setUpdatedUser(super.getUpdatedUser());
		paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		paramBean.setTimeDifference(super.getTimeDifference());
		
		// Check the conditions of order cancellation
		if (logic.checkCancelOrder(paramBean)) {
			// Get transaction
			TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			
			// List orderDtl get by orderId
			List<Pac0021Bean> listOrderDtlByOrderId = null;
			// Get listOrderDtl
			listOrderDtlByOrderId = logic.getOrderDtlByOrderId(paramBean);
	
			try {
				// Set [ORDERS].DELETE_FG = 1
				logic.deleteOrder(paramBean);
				// Update [ORDER_DTL]
				logic.cancelOrder(paramBean);
				// If listOrderDtl is not empty
				// Cancel by about #148 on Google Sheet QA
				/*if (listOrderDtlByOrderId != null && listOrderDtlByOrderId.size() > 0) {
					// Update and add the stock inventory
					// If If [ORDER_DTL].ITEM_CD = [STOCK].ITEM_CD
					for (Pac0021Bean orderDtl : listOrderDtlByOrderId) {
						// Set value to orderDtl
						orderDtl.setUpdatedUser(super.getUpdatedUser());
						orderDtl.setUpdatedDtLocal(super.getUpdatedDtLocal());
						orderDtl.setTimeDifference(super.getTimeDifference());
						// Check exist data in STOCK
						if (logic.checkExistStock(orderDtl)) {
							// If currently being edited by another user then set value ExclusiveFg is 1 else null.
							logic.checkCurrentlyEdited(orderDtl);
							// If exist update stock
							logic.updateStockInventory(orderDtl);
						}
					}
				}*/
				// Send email to KDDI
				// Get orders by orderId
				OrderBean orderBean = logic.getOrders(this.orderId);
				if (orderBean != null){
					paramBean.setCompanyCd(orderBean.getCompanyCd());
				}
				// Get value to emailbean
				EmailBean emailBean = logic.getDataSendEmail(paramBean);
				// Check emailBean is not null
				if (emailBean != null){
					// Get list to email address
					List<String> listEmail = logic.getToEmailAddress();
					System.out.println(listEmail);
					// Set toEmailAddress
					emailBean.setToEmailAddress(logic.getToEmailAddress());
					// Check toEmailAddress is not null
					if(emailBean.getToEmailAddress() != null && emailBean.getToEmailAddress().size() > 0){
						emailBean.setTitle(subjectSendMailSankyuCancel);
						// Send email
						sendEmail(emailBean);
					}
				}
				transactionManager.commit(transactionStatus);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				transactionManager.rollback(transactionStatus);
				e.printStackTrace();
				LOG.info("*************Do rollback doDelete*************", "");
			}
		} else {
			
		}
		// Reload data for json
		doSearch();
		LOG.info("*************doDelete End*************", "");
		return SUCCESS;
	}

	/**
	 * Action reject order with USER_AUTHORITY = 3
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doReject() throws Exception {
		LOG.info("*************doReject Start*************", "");

		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();

		// If USER_AUTHORITY is null or USER_AUTHORITY = 1
		if (StringUtil.isEmpty(userAuthority) || userAuthority.equals(Constant.GENERAL_USER_AUTHORITY)) {
			// Reload data for json
			doSearch();
			LOG.info("*************doReject End*************", "");
			return SUCCESS;
		}

		// Check USER_AUTHORITY = 3
		if (userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY)) {
			// SANKYU Admin reject order
			sankyuReject();
		}
		
		// Check USER_AUTHORITY = 2
		if (userAuthority.equals(Constant.KDDI_ADMIN_AUTHORITY)) {
			// KDDI Admin reject order
			kddiReject();
		}

		// Reload data for json
		doSearch();
		LOG.info("*************doReject End*************", "");
		return SUCCESS;
	}

	/**
	 * Action approve order
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doApprove() throws Exception {
		LOG.info("*************doApprove Start*************", "");

		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();

		// If USER_AUTHORITY is null or USER_AUTHORITY = 1
		if (userAuthority == null || userAuthority.equals(Constant.GENERAL_USER_AUTHORITY)) {
			// Reload data for json
			doSearch();
			LOG.info("*************doApprove End*************", "");
			return SUCCESS;
		}

		// Check USER_AUTHORITY = 3
		if (userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY)) {
			// Sankyu Admin approve order
			sankyuApprove();
		}
		
		// Check USER_AUTHORITY = 2
		if (userAuthority.equals(Constant.KDDI_ADMIN_AUTHORITY)) {
			// KDDI Admin approve order
			kddiApprove();
		}

		// Reload data for json
		doSearch();
		LOG.info("*************doApprove End*************", "");
		return SUCCESS;
	}

	/**
	 * Sankyu Admin reject order Set [ORDER_DTL].SANKYU_ORDER_DT = ""
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sankyuReject() throws Exception {
		LOG.info("*************sankyuReject Start*************", "");
		// Logic
		Pac0021BL logic = new Pac0021BL();

		// Check USER_AUTHORITY = 3
		if (userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY) && !StringUtil.isEmpty(this.orderId)) {
			// Get transaction
			TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			// ParamBean
			Pac0021ParamBean paramBean = new Pac0021ParamBean();
			// Order status bean
			OrderStatusBean statusBean = new OrderStatusBean();
			// List orderDtl get by orderId
			List<Pac0021Bean> listOrderDtlByOrderId = null;
			// List orderCfg get by orderId
			List<Pac0021Bean> listOrderCfgByOrderId = null;

			// Set value paramBean
			paramBean.setOrderId(this.orderId);
			paramBean.setUpdatedUser(super.getUpdatedUser());
			paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
			paramBean.setTimeDifference(super.getTimeDifference());
			// Set value orderStatus
			statusBean.setOrderId(this.orderId);
			statusBean.setPhase(Constant.SEA_REQUEST);
			statusBean.setAction(Constant.REJECT);
			statusBean.setUpdatedUser(super.getUpdatedUser());
			statusBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
			statusBean.setTimeDifference(super.getTimeDifference());

			// Get listOrderDtl
			listOrderDtlByOrderId = logic.getOrderDtlByOrderId(paramBean);
			// Get listOrderDtl
			listOrderCfgByOrderId = logic.getOrderCfgByOrderId(paramBean);

			try {
				// If listOrderDtl is not empty
				if (listOrderDtlByOrderId != null && listOrderDtlByOrderId.size() > 0) {
					// If [ORDER].SANKYU_ORDER_DT <> "" and
					// [ORDER].SEA_REQUEST_DT = ""
					if (logic.validate(userAuthority, listOrderDtlByOrderId.get(0))
							&& StringUtil.isEmpty(listOrderDtlByOrderId.get(0).getDeleteFg())) { // Check DELETE_FG <> '1'

						// Set [ORDER].SANKYU_ORDER_DT = ""
						logic.udpateSankyuOrderDt(paramBean);

						// Update Order status
						logic.updateOrderStatus(statusBean);

						// Update and add the stock inventory
						// If If [ORDER_DTL].ITEM_CD = [STOCK].ITEM_CD
						for (Pac0021Bean orderDtl : listOrderDtlByOrderId) {
							// Set value to orderDtl
							orderDtl.setUpdatedUser(super.getUpdatedUser());
							orderDtl.setUpdatedDtLocal(super.getUpdatedDtLocal());
							orderDtl.setTimeDifference(super.getTimeDifference());
							// Check exist data in STOCK
							if (logic.checkExistStock(orderDtl)) {
								// If currently being edited by another user then set value ExclusiveFg is 1 else null.
								logic.checkCurrentlyEdited(orderDtl);
								// If exist update stock
								logic.updateStockInventory(orderDtl);
							}
						}

						// If [ORDER_CFG].ITEM_CD = [STOCK].ITEM_CD
						if (listOrderCfgByOrderId != null && listOrderCfgByOrderId.size() > 0) {
							for (Pac0021Bean orderCfg : listOrderCfgByOrderId) {
								// Set value to orderCfg
								orderCfg.setUpdatedUser(super.getUpdatedUser());
								orderCfg.setUpdatedDtLocal(super.getUpdatedDtLocal());
								orderCfg.setTimeDifference(super.getTimeDifference());
								// Check exist data in STOCK
								if (logic.checkExistStock(orderCfg)) {
									// If currently being edited by another user then set value ExclusiveFg is 1 else null.
									logic.checkCurrentlyEdited(orderCfg);
									// If exist update stock
									logic.updateStockInventory(orderCfg);
								}
							}
						}
					}
				}
				transactionManager.commit(transactionStatus);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				transactionManager.rollback(transactionStatus);
				e.printStackTrace();
				LOG.info("*************Do rollback sankyuReject*************", "");
			}
		}
		LOG.info("*************sankyuReject End*************", "");
		return SUCCESS;
	}
	
	/**
	 * Sankyu Admin approve order Set [ORDER_DTL].SEA_REQUEST_DT = DD-MMM
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sankyuApprove() throws Exception {
		LOG.info("*************sankyuApprove Start*************", "");
		// Logic
		Pac0021BL logic = new Pac0021BL();

		// Check USER_AUTHORITY = 3
		if (userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY) && !StringUtil.isEmpty(this.orderId)) {
			// Get transaction
			TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			// ParamBean
			Pac0021ParamBean paramBean = new Pac0021ParamBean();
			// Order status bean
			OrderStatusBean statusBean = new OrderStatusBean();
			// Email bean
			EmailBean emailBean = new EmailBean();
			// List orderDtl get by orderId
			List<Pac0021Bean> listOrderDtlByOrderId = null;

			// Set value paramBean
			paramBean.setOrderId(this.orderId);
			paramBean.setUpdatedUser(super.getUpdatedUser());
			paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
			paramBean.setTimeDifference(super.getTimeDifference());
			// Set value orderStatus
			statusBean.setOrderId(this.orderId);
			statusBean.setPhase(Constant.SEA_REQUEST);
			statusBean.setAction(Constant.APPROVE);
			statusBean.setUpdatedUser(super.getUpdatedUser());
			statusBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
			statusBean.setTimeDifference(super.getTimeDifference());

			// Get listOrderDtl
			listOrderDtlByOrderId = logic.getOrderDtlByOrderId(paramBean);
			

			try {
				// If listOrderDtl is not empty
				if (listOrderDtlByOrderId != null && listOrderDtlByOrderId.size() > 0) {
					// If [ORDER].SANKYU_ORDER_DT <> "" and
					// [ORDER].SEA_REQUEST_DT = ""
					if (logic.validate(userAuthority, listOrderDtlByOrderId.get(0))
							&& StringUtil.isEmpty(listOrderDtlByOrderId.get(0).getDeleteFg())) { // Check [ORDERS].DELETE_FG <> '1'

						// Set [ORDER].SANKYU_ORDER_DT = ""
						logic.updateSeaRequestDt(paramBean);

						// Update Order status
						logic.updateOrderStatus(statusBean);
						
						// Send email to KDDI
						OrderBean orderBean = logic.getOrders(this.orderId);
						if (orderBean != null){
							paramBean.setCompanyCd(orderBean.getCompanyCd());
						}
						// Get value to emailbean
						emailBean = logic.getDataSendEmail(paramBean);
						// Check emailBean is not null
						if (emailBean != null){
							// Get list to email address
							List<String> listEmail = logic.getToEmailAddress();
							System.out.println(listEmail);
							// Set toEmailAddress
							emailBean.setToEmailAddress(logic.getToEmailAddress());
							// Check toEmailAddress is not null
							if(emailBean.getToEmailAddress() != null && emailBean.getToEmailAddress().size() > 0){
								emailBean.setTitle(subjectSendMailSankyuApprove);
								// Send email
								sendEmail(emailBean);
							}
						}
					}
				}
				transactionManager.commit(transactionStatus);
				
				// Send email to KDDI
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				transactionManager.rollback(transactionStatus);
				e.printStackTrace();
				LOG.info("*************Do rollback sankyuApprove*************", "");
			}
		}

		LOG.info("*************sankyuApprove End*************", "");
		return SUCCESS;
	}

	/**
	 * KDDI Admin reject order USER_AUTHORITY = 2
	 * 
	 * @return
	 * @throws Exception
	 */
	public String kddiReject() throws Exception {
		LOG.info("*************kddiReject Start*************", "");
		// Logic
		Pac0021BL logic = new Pac0021BL();

		// Check USER_AUTHORITY = 2
		if (userAuthority.equals(Constant.KDDI_ADMIN_AUTHORITY) && !StringUtil.isEmpty(this.orderId)) {
			// Get transaction
			TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			// ParamBean
			Pac0021ParamBean paramBean = new Pac0021ParamBean();
			// Order status bean
			OrderStatusBean statusBean = new OrderStatusBean();
			// List orderDtl get by orderId
			List<Pac0021Bean> listOrderDtlByOrderId = null;

			// Set value paramBean
			paramBean.setOrderId(this.orderId);
			paramBean.setUpdatedUser(super.getUpdatedUser());
			paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
			paramBean.setTimeDifference(super.getTimeDifference());
			// Set value orderStatus
			statusBean.setOrderId(this.orderId);
			// statusBean.setPhase(Constant.SEA_REQUEST);
			statusBean.setAction(Constant.REJECT);
			statusBean.setUpdatedUser(super.getUpdatedUser());
			statusBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
			statusBean.setTimeDifference(super.getTimeDifference());

			// Get listOrderDtl
			listOrderDtlByOrderId = logic.getOrderDtlByOrderId(paramBean);

			try {
				// If listOrderDtl is not empty
				if (listOrderDtlByOrderId != null && listOrderDtlByOrderId.size() > 0) {
					Pac0021Bean orderDtl = listOrderDtlByOrderId.get(0);
					// Validate when KDDI Approve
					if (logic.validate(userAuthority, orderDtl) 
							&& StringUtil.isEmpty(orderDtl.getDeleteFg())) { // Check [ORDERS].DELETE_FG  <> '1'

						// If [ORDER_DTL].KDDI_ACCEPT_DT <> ""  and  [ORDER_DTL].KDDI_ORDER_DT = "" 
						if (!StringUtil.isEmpty(orderDtl.getKddiAcceptDt())
								&& StringUtil.isEmpty(orderDtl.getKddiOrderDt())) {
							
							// Set  [ORDER_DTL].KDDI_ORDER_DT = ""
							logic.kddiRejectAcceptDt(paramBean);
							// Set PHASE
							statusBean.setPhase(Constant.KDDI_ORDER);
							// Update Order status
							logic.updateOrderStatus(statusBean);
							
						} // If  [ORDER_DTL].KDDI_ORDER_DT <> ""  and  [ORDER_DTL].KDDI_RECEIVE_DT = ""
						else if (!StringUtil.isEmpty(orderDtl.getKddiOrderDt())
								&& StringUtil.isEmpty(orderDtl.getKddiReceiveDt())) {
							
							// Set  [ORDER_DTL].KDDI_RECEIVE_DT = ""
							logic.kddiRejectOrderDt(paramBean);
							// Set PHASE
							statusBean.setPhase(Constant.KDDI_RECEIVE);
							// Update Order status
							logic.updateOrderStatus(statusBean);
							
						} // If  [ORDER_DTL].KDDI_RECEIVE_DT <> ""  and  [ORDER_DTL].KDDI_DELIVER_DT = ""
						else if (!StringUtil.isEmpty(orderDtl.getKddiReceiveDt())
								&& StringUtil.isEmpty(orderDtl.getKddiDeliverDt())) {
							
							// Set  [ORDER_DTL].KDDI_RECEIVE_DT = ""
							logic.kddiRejectReceiveDt(paramBean);
							// Set PHASE
							statusBean.setPhase(Constant.KDDI_DELIVER);
							// Update Order status
							logic.updateOrderStatus(statusBean);
							
						}
					}
				}
				transactionManager.commit(transactionStatus);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				transactionManager.rollback(transactionStatus);
				e.printStackTrace();
				LOG.info("*************Do rollback kddiReject*************", "");
			}
		}

		LOG.info("*************kddiReject End*************", "");
		return SUCCESS;
	}
	
	/**
	 * KDDI Admin approve order USER_AUTHORITY = 2
	 * 
	 * @return
	 * @throws Exception
	 */
	public String kddiApprove() throws Exception {
		LOG.info("*************kddiApprove Start*************", "");
		// Logic
		Pac0021BL logic = new Pac0021BL();

		// Check USER_AUTHORITY = 2
		if (userAuthority.equals(Constant.KDDI_ADMIN_AUTHORITY) && !StringUtil.isEmpty(this.orderId)) {
			// Get transaction
			TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			// ParamBean
			Pac0021ParamBean paramBean = new Pac0021ParamBean();
			// Order status bean
			OrderStatusBean statusBean = new OrderStatusBean();
			// List orderDtl get by orderId
			List<Pac0021Bean> listOrderDtlByOrderId = null;

			// Set value paramBean
			paramBean.setOrderId(this.orderId);
			paramBean.setUpdatedUser(super.getUpdatedUser());
			paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
			paramBean.setTimeDifference(super.getTimeDifference());
			// Set value orderStatus
			statusBean.setOrderId(this.orderId);
			// statusBean.setPhase(Constant.SEA_REQUEST);
			statusBean.setAction(Constant.APPROVE);
			statusBean.setUpdatedUser(super.getUpdatedUser());
			statusBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
			//statusBean.setTimeDifference(super.getTimeDifference());

			// Get listOrderDtl
			listOrderDtlByOrderId = logic.getOrderDtlByOrderId(paramBean);

			try {
				// If listOrderDtl is not empty
				if (listOrderDtlByOrderId != null && listOrderDtlByOrderId.size() > 0) {
					Pac0021Bean orderDtl = listOrderDtlByOrderId.get(0);
					// Validate when KDDI Approve
					if (logic.validate(userAuthority, orderDtl) 
							&& StringUtil.isEmpty(orderDtl.getDeleteFg())) { // Check [ORDERS].DELETE_FG  <> '1'

						// If [ORDER_DTL].SEA_REQUEST_DT <> "" and [ORDER_DTL].KDDI_ACCEPT_DT = ""
						if (!StringUtil.isEmpty(orderDtl.getSeaRequestDt())
								&& StringUtil.isEmpty(orderDtl.getKddiAcceptDt())) {
							
							// Set  [ORDER_DTL].KDDI_ACCEPT_DT = DD-MMM
							logic.udpateKddiAcceptDt(paramBean);
							// Set PHASE
							statusBean.setPhase(Constant.KDDI_ACCEPT);
							// Update Order status
							logic.updateOrderStatus(statusBean);
							
						} // If [ORDER_DTL].KDDI_ACCEPT_DT <> ""  and  [ORDER_DTL].KDDI_ORDER_DT = "" 
						else if (!StringUtil.isEmpty(orderDtl.getKddiAcceptDt())
								&& StringUtil.isEmpty(orderDtl.getKddiOrderDt())) {
							
							// Set  [ORDER_DTL].KDDI_ORDER_DT = DD-MMM
							logic.udpateKddiOrderDt(paramBean);
							// Set PHASE
							statusBean.setPhase(Constant.KDDI_ORDER);
							// Update Order status
							logic.updateOrderStatus(statusBean);
							
						} // If  [ORDER_DTL].KDDI_ORDER_DT <> ""  and  [ORDER_DTL].KDDI_RECEIVE_DT = ""
						else if (!StringUtil.isEmpty(orderDtl.getKddiOrderDt())
								&& StringUtil.isEmpty(orderDtl.getKddiReceiveDt())) {
							
							// Set  [ORDER_DTL].KDDI_RECEIVE_DT = DD-MMM
							logic.udpateKddiReceiveDt(paramBean);
							// Set PHASE
							statusBean.setPhase(Constant.KDDI_RECEIVE);
							// Update Order status
							logic.updateOrderStatus(statusBean);
							
						}
						// Cancel By issue #144 on GoogleSheet
						// If  [ORDER_DTL].KDDI_RECEIVE_DT <> ""  and  [ORDER_DTL].KDDI_LEASE_DT = ""
						/*else if (!StringUtil.isEmpty(orderDtl.getKddiReceiveDt())
								&& !StringUtil.isEmpty(orderDtl.getKddiDeliverDt())
								&& StringUtil.isEmpty(orderDtl.getKddiLeaseDt())) {
							
							// Check equal quantity
							//  If  ([ORDER_DTL].ITEM_QTY = [ORDER_DTL].KDDI_DELIVER_QTY)
							if (logic.validateQuantity(listOrderDtlByOrderId)) {
								// Set  [ORDER_DTL].KDDI_RECEIVE_DT = DD-MMM
								logic.udpateKddiLeaseDt(paramBean);
								// Set PHASE
								statusBean.setPhase(Constant.KDDI_LEASE);
								// If exist Update Order status
								logic.updateOrderStatus(statusBean);
							}
							
						}*/
					}
				}
				transactionManager.commit(transactionStatus);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				transactionManager.rollback(transactionStatus);
				e.printStackTrace();
				LOG.info("*************Do rollback kddiApprove*************", "");
			}
		}

		LOG.info("*************kddiApprove End*************", "");
		return SUCCESS;
	}

	/**
	 * Action download report
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDownload() throws Exception{
	    LOG.info("*************doDownload Start*************", "");
	    // BL report
	    Pac0021ReportBL reportBL = new Pac0021ReportBL();
	    // Create map param
        Map<String, Object> reqParams = new HashMap<String, Object>();
        // put userID
        reqParams.put(Constant.UID, getUserInfo().getUserId());
        
        // Get data
        doSearch();
        
        reqParams.put("listOrderDtl", listGroupOrderDtl);
        reqParams.put("companyCd", this.companyCd);
        reqParams.put("from", this.entryDateFrom);
        reqParams.put("to", this.entryDateTo);
      
        if (this.nonDelivery){
        	reqParams.put("nonDelivery", "Y");
        } else {
        	reqParams.put("nonDelivery", "N");
        }
        
        if (this.expiringOrders){
        	reqParams.put("expiringOrders", "Y");
        } else {
        	reqParams.put("expiringOrders", "N");
        }
        
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
            LOG.info("***********printAction End***********");
            super.addActionError("PCOD-0006");
			return ERROR;
        }   
	    LOG.info("*************doDownload End*************", "");
	    return SUCCESS;
	}
	
	/**
	 * Action do edit
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doEdit() throws Exception{
		LOG.info("*************doEdit Start*************", "");
		// BL
		Pac0021BL logic = new Pac0021BL();
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// If userAuthority = 1 or 3
		if (userAuthority.equals(Constant.GENERAL_USER_AUTHORITY) || userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY)){
			if (!StringUtil.isEmpty(this.orderId)){
				// ParamBean
				Pac0021ParamBean paramBean = new Pac0021ParamBean();
				// Set orderid
				paramBean.setOrderId(this.orderId);
				// List orderDtl get by orderId
				List<Pac0021Bean> listOrderDtlByOrderId = null;
				// Get listOrderDtl
				listOrderDtlByOrderId = logic.getOrderDtlByOrderId(paramBean);
				// If listOrderDtl is not empty and KDDI_RECEIVE_DT IS NULL
				if (listOrderDtlByOrderId != null && listOrderDtlByOrderId.size() > 0 
						&& StringUtil.isEmpty(listOrderDtlByOrderId.get(0).getKddiReceiveDt())) {
					// Get transaction
					TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
					TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
					
					// Set value
					paramBean.setRedirect(Constant.REDIRECT_1);
					paramBean.setUpdatedUser(super.getUpdatedUser());
					paramBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
					//paramBean.setTimeDifference(super.getTimeDifference());
			
					try {
						//set [CONFIG].REDIRECT = "1"
						logic.updateRedirect(paramBean);
						transactionManager.commit(transactionStatus);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						transactionManager.rollback(transactionStatus);
						e.printStackTrace();
						LOG.info("*************Do rollback doEdit*************", "");
					}
					LOG.info("*************doEdit End*************", "");
					return SUCCESS;
				} else {
					LOG.info("*************doEdit End*************", "");
					return ERROR;
				}
			} else {
				LOG.info("*************doEdit End*************", "");
				return ERROR;
			}
		} else {
			LOG.info("*************doEdit End*************", "");
			return ERROR;
		}
	}
	
	/**
	 * Send email to KDDI user when SANKYU ADMIN APPROVE
	 * 
	 * @param emailBean
	 */
	public void sendEmail(EmailBean emailBean) throws Exception{
		LOG.info("*************sendEmail Start*************", "");
		try {
			// Properties send mail
			Properties props = new Properties();
			// Common logic
			CommonBL logic = new CommonBL();
			// Get config bean
			ConfigBean configBean = logic.getConfig();
			// Create session send mail
			Session session = EmailUtil.createSession(props, configBean);
			// To Email_addres
			String toEmailAddress = String.join(",", emailBean.getToEmailAddress());
			// Subject mail
			String subject = emailBean.getTitle();
			// Body mail
			/*String body = "Country: " + emailBean.getCountryCd() + "<br>";
			body += "Company: " + emailBean.getCompanyCd() + "<br>";
			body += "Dept: " + emailBean.getDeptCd() + "<br>";
			body += "User: " + emailBean.getUserName() + "<br>";
			body += "Quotation No: " + emailBean.getQuoteMrc() + " / " + emailBean.getQuoteOtc() + "<br>";*/
			
			// Set key values
			Map<String, String> input = new HashMap<String, String>();
			input.put("CountryValue", emailBean.getCountryCd() == null ? "" : emailBean.getCountryCd());
			input.put("CompanyValue", emailBean.getCompanyCd() == null ? "" : emailBean.getCompanyCd());
			input.put("DeptValue", emailBean.getDeptCd() == null ? "" : emailBean.getDeptCd());
			input.put("UserValue", emailBean.getUserName() == null ? "" : emailBean.getUserName());
			input.put("QuotationNoValue", (emailBean.getQuoteMrc() == null ? "" : emailBean.getQuoteMrc()) 
					+ " / " + (emailBean.getQuoteOtc() == null ? "" : emailBean.getQuoteOtc()));
			// HTML mail content
			String htmlText = EmailUtil.readEmailFromHtml(getRealPathInWebInf("template_email.html"), input);
			  
			// List file attachment
			ArrayList<String> listFileAttachment = new ArrayList<String>();
			// Add QuoteMrc, QuoteOtc
			listFileAttachment.add(emailBean.getQuoteMrc() + Constant.PDF_EXTENSION);
			listFileAttachment.add(emailBean.getQuoteOtc() + Constant.PDF_EXTENSION);
			
			// Check format email
			toEmailAddress = EmailUtil.checkValidation(session, toEmailAddress);
			// Send email to KDDI Admin
			EmailUtil.sendAttachmentEmail(session, toEmailAddress, subject, htmlText, listFileAttachment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.info("*************sendEmail Unsuccess*************", "");
			throw e;
		}
		LOG.info("*************sendEmail End*************", "");
	}
	
	public List<Pac0021Bean> getListOrderDtl() {
		return listOrderDtl;
	}

	public void setListOrderDtl(List<Pac0021Bean> listOrderDtl) {
		this.listOrderDtl = listOrderDtl;
	}

	public List<List<Pac0021Bean>> getListGroupOrderDtl() {
		return listGroupOrderDtl;
	}

	public void setListGroupOrderDtl(List<List<Pac0021Bean>> listGroupOrderDtl) {
		this.listGroupOrderDtl = listGroupOrderDtl;
	}

	public String getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(String userAuthority) {
		this.userAuthority = userAuthority;
	}

	/**
	 * @return the listOrderIdExpiring
	 */
	public List<String> getListOrderIdExpiring() {
		return listOrderIdExpiring;
	}

	/**
	 * @param listOrderIdExpiring
	 *            the listOrderIdExpiring to set
	 */
	public void setListOrderIdExpiring(List<String> listOrderIdExpiring) {
		this.listOrderIdExpiring = listOrderIdExpiring;
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

}
