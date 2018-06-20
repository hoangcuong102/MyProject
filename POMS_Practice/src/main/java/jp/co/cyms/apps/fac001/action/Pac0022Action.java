package jp.co.cyms.apps.fac001.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jp.co.cyms.apps.fac001.bean.Pac0022Bean;
import jp.co.cyms.apps.fac001.bean.Pac0024Bean;
import jp.co.cyms.apps.fac001.bean.Pac0024ParamBean;
import jp.co.cyms.apps.fac001.bl.Pac0022BL;
import jp.co.cyms.apps.fac001.bl.Pac0024BL;
import jp.co.cyms.apps.fac001.form.Pac0022Form;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.DateFormat;
import jp.co.cyms.common.DateUtil;
import jp.co.cyms.common.StringUtil;

public class Pac0022Action extends Pac0022Form {
	
	private List<Pac0022Bean> lstOrderLease = new ArrayList<Pac0022Bean>();
	
	private String listEditedItemObject;

	/** user authority */
	private String userAuthority;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Execute
	 * 
	 * @return result
	 */
	public String execute() {
		String orderId = request.getParameter("orderId");
		Pac0022BL logic = new Pac0022BL();
		this.listOrderLease = logic.getOrderLease(orderId);
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		return SUCCESS;
	}
	
	/**
	 * Get all order lease
	 * 
	 * @return result
	 */
	public String loadOrderLease() {
		String orderId = request.getParameter("orderId");
		Pac0022BL logic = new Pac0022BL();
		lstOrderLease = logic.getOrderLease(orderId);
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		return SUCCESS;
	}

	/**
	 * Action update order lease
	 * 
	 * @return result
	 * @throws ParseException 
	 */
	public String doSave() throws ParseException {
		LOG.info("*************doSave Start*************", "");
		// Get user info
		this.userInfo = getUserInfo();
		// Get user authority
		userAuthority = userInfo.getUserAuthorityCd();
		// SANKYU Admin can access to this screen as read-only. Only enable [D/O] and [Close] buttons.
		if (!userAuthority.equals(Constant.SANKYU_ADMIN_AUTHORITY)) {
			TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
			TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
			try {
				Pac0022BL logic = new Pac0022BL();
				// Get paramList from Ajax
				String param = this.listEditedItemObject;
				// Convert Param to JsonElement
				JsonElement jelement = new JsonParser().parse(param);
				// JsonObject created
				JsonObject jobject = jelement.getAsJsonObject();
	
				// Get object arr from jobject
				JsonArray jarray = jobject.getAsJsonArray("arr");
				
				List<Pac0022Bean> pac0022BeanList = stringToArray(jarray, Pac0022Bean[].class);
				
				if (pac0022BeanList != null && pac0022BeanList.size() > 0){
					// Update [ORDER_LEASE]
					for (Pac0022Bean pac0022Bean : pac0022BeanList) {
						// BaseDBBean
						pac0022Bean.setUpdatedUser(super.getUpdatedUser());
						pac0022Bean.setUpdatedDtLocal(super.getUpdatedDtLocal());
						pac0022Bean.setTimeDifference(super.getTimeDifference());
						
						logic.doUpdateOrderLease(pac0022Bean);
					}
				}
				// Get orderId
				String orderId = request.getParameter("orderId");
				
				// If orderId is not empty
				if (!StringUtil.isEmpty(orderId)){
					// Update [ORDER_DTL]
					Pac0022Bean paramUpdateOrderDtl = new Pac0022Bean();
					paramUpdateOrderDtl.setOrderId(orderId);
					paramUpdateOrderDtl.setUpdatedUser(super.getUpdatedUser());
					paramUpdateOrderDtl.setUpdatedDtLocal(super.getUpdatedDtLocal());
					paramUpdateOrderDtl.setTimeDifference(super.getTimeDifference());
					logic.doUpdateOrderDtl(paramUpdateOrderDtl);
					
					// Update [ORDERS].BILL_FG
//					Date date = new Date();
					///Business logic Pac0024BL
					Pac0024BL pac0024bl = new Pac0024BL();
					// Check is delivery all by order id
					Pac0024ParamBean pac0024ParamBean = new Pac0024ParamBean();
					pac0024ParamBean.setOrderId(orderId);
					int isAllDelivery = pac0024bl.checkIsDeliveryByOrderId(pac0024ParamBean);
					// Get Status of bill order
					Pac0024Bean pac0024Bean = pac0024bl.getBillFgByOrderId(pac0024ParamBean);
					if (isAllDelivery > 0) {
						pac0024ParamBean.setBillFg("");
						pac0024ParamBean.setBillMthStart("");
						pac0024ParamBean.setBillMthEnd("");
					} else {
//						SimpleDateFormat df = new SimpleDateFormat(DateFormat.YYYYMMDD2);
//						String mthStart = df.format(date);
//						String mthMthStart = df.format(DateUtil.addMonth(date, 47));
						String mthStart = pac0024bl.getLastestDelivery(orderId);
						pac0024ParamBean.setBillFg("1");
						pac0024ParamBean.setBillMthStart(mthStart);
//						pac0024ParamBean.setBillMthEnd(mthStart);
					}
					// Set value for UPDATED_USER, UPDATED_DT_UTC, UPDATED_DT_SERVER, UPDATED_DT_LOCAL
					pac0024ParamBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
					pac0024ParamBean.setUpdatedUser(super.getUpdatedUser());
					pac0024ParamBean.setTimeDifference(super.getTimeDifference());
					
					// If  BILL_FG = ""
					if (pac0024Bean != null && StringUtil.isNullOrEmpty(pac0024Bean.getBillFg())) {
						// Update [ORDERS]
						pac0024bl.updateBillFgByOrderId(pac0024ParamBean);
					}
				}
				
				transactionManager.commit(transactionStatus);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				transactionManager.rollback(transactionStatus);
				e.printStackTrace();
				LOG.info("*************Do rollback doSave*************", "");
			}
		}

		LOG.info("*************doSave End***************", "");
		return SUCCESS;
	}
	
	public List<Pac0022Bean> getLstOrderLease() {
		return lstOrderLease;
	}

	public void setLstOrderLease(List<Pac0022Bean> lstOrderLease) {
		this.lstOrderLease = lstOrderLease;
	}

	public String getListEditedItemObject() {
		return listEditedItemObject;
	}

	public void setListEditedItemObject(String listEditedItemObject) {
		this.listEditedItemObject = listEditedItemObject;
	}
	
	public static <T> List<T> stringToArray(JsonArray s, Class<T[]> clazz) {
	    T[] arr = new Gson().fromJson(s, clazz);
	    return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
	}

	/**
	 * @return the userAuthority
	 */
	public String getUserAuthority() {
		return userAuthority;
	}

	/**
	 * @param userAuthority the userAuthority to set
	 */
	public void setUserAuthority(String userAuthority) {
		this.userAuthority = userAuthority;
	}
	
	
}
