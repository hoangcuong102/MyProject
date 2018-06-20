package jp.co.cyms.apps.fac001.dao;

import java.util.List;

import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.apps.fac001.bean.EmailBean;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderStatusBean;
import jp.co.cyms.apps.fac001.bean.Pac0021Bean;
import jp.co.cyms.apps.fac001.bean.Pac0021ParamBean;
import jp.co.cyms.base.BaseDao;

/**
 * PC Order Progress Update / Inquiry Pac0021Dao
 * 
 * @author binhvh
 * @since 2018/01/12
 */
public class Pac0021Dao extends BaseDao {

	public Pac0021Dao() {
		super();
	}

	/**
	 * get all company code from COMPANY_MST.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllCompanyCd() throws Exception {
		List<String> list = null;
		list = (List<String>) this.queryList("FAC0021.getAllCompanyCd");
		return list;
	}

	/**
	 * get company code when [USER_MST].USER_AUTHORITY = "1"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCompanyCd(UserBean userBean) throws Exception {
		String companyCd = (String) this.queryObject("FAC0021.getCompanyCd", userBean);
		return companyCd;
	}

	/**
	 * search order in ORDER_DTL
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Pac0021Bean> searchOrderDtl(Pac0021ParamBean paramBean) throws Exception {
		List<Pac0021Bean> list = null;
		list = (List<Pac0021Bean>) this.queryList("FAC0021.searchOrderDtl", paramBean);
		return list;
	}

	/**
	 * get all orderId expiring.
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getOrderIdExpiring(Pac0021ParamBean paramBean) throws Exception {
		List<String> list = null;
		list = (List<String>) this.queryList("FAC0021.getOrderIdExpiring", paramBean);
		return list;
	}

	/**
	 * delete order in ORDERS
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void deleteOrder(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.deleteOrder", paramBean);
	}

	/**
	 * get orderDtl by orderId
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Pac0021Bean> getOrderDtlByOrderId(Pac0021ParamBean paramBean) throws Exception {
		List<Pac0021Bean> list = null;
		list = (List<Pac0021Bean>) this.queryList("FAC0021.getOrderDtlByOrderId", paramBean);
		return list;
	}

	/**
	 * get orderCfg by orderId
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Pac0021Bean> getOrderCfgByOrderId(Pac0021ParamBean paramBean) throws Exception {
		List<Pac0021Bean> list = null;
		list = (List<Pac0021Bean>) this.queryList("FAC0021.getOrderCfgByOrderId", paramBean);
		return list;
	}

	/**
	 * update SANKYU_ORDER_DT = NULL
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateSankyuOrderDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.udpateSankyuOrderDt", paramBean);
	}

	/**
	 * update SEA_REQUEST_DT = 'DD-MMM'
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void updateSeaRequestDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.updateSeaRequestDt", paramBean);
	}

	/**
	 * update ORDER_STATUS
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void updateOrderStatus(OrderStatusBean paramBean) throws Exception {
		this.delete("FAC0021.updateOrderStatus", paramBean);
	}

	/**
	 * update stock inventory
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void updateStockInventory(Pac0021Bean paramBean) throws Exception {
		this.delete("FAC0021.updateStockInventory", paramBean);
	}

	/**
	 * check exist order status
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public Integer checkExistOrderStatus(OrderStatusBean paramBean) throws Exception {
		Integer result = (Integer) this.queryObject("FAC0021.checkExistOrderStatus", paramBean);
		return result;
	}

	/**
	 * check exist stock
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public Integer checkExistStock(Pac0021Bean paramBean) throws Exception {
		Integer result = (Integer) this.queryObject("FAC0021.checkExistStock", paramBean);
		return result;
	}

	/**
	 * update KDDI_ACCEPT_DT = DD-MMM
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateKddiAcceptDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.udpateKddiAcceptDt", paramBean);
	}

	/**
	 * update KDDI_ORDER_DT = DD-MMM
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateKddiOrderDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.udpateKddiOrderDt", paramBean);
	}

	/**
	 * update KDDI_RECEIVE_DT = DD-MMM
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateKddiReceiveDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.udpateKddiReceiveDt", paramBean);
	}

	/**
	 * update KDDI_LEASE_DT = DD-MMM
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateKddiLeaseDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.udpateKddiLeaseDt", paramBean);
	}
	
	/**
	 * update CONFIG
	 * REDIRECT = 1
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void updateRedirect(Pac0021ParamBean paramBean) throws Exception {
		this.update("FAC0021.updateRedirect", paramBean);
	}
	
	/**
	 * Get getDataSendEmail
	 * 
	 * @param paramBean
	 * @return EmailBean
	 * @throws Exception
	 */
	public EmailBean getDataSendEmail(Pac0021ParamBean paramBean) throws Exception {
		EmailBean emailBean = (EmailBean)this.queryObject("FAC0021.getDataSendEmail", paramBean);
		return emailBean;
	}
	
	/**
	 * Get list email address
	 * 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getToEmailAddress() throws Exception {
		List<String> list = (List<String>)this.queryList("FAC0021.getToEmailAddress");
		return list;
	}
	
	/**
	 * KDDI Reject
	 * Set [ORDER_DTL].KDDI_ACCEPT_DT = NULL
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void kddiRejectAcceptDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.kddiRejectAcceptDt", paramBean);
	}
	
	/**
	 * KDDI Reject
	 * Set [ORDER_DTL].KDDI_ORDER_DT = NULL
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void kddiRejectOrderDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.kddiRejectOrderDt", paramBean);
	}
	
	/**
	 * KDDI Reject
	 * Set [ORDER_DTL].KDDI_RECEIVE_DT = NULL
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void kddiRejectReceiveDt(Pac0021ParamBean paramBean) throws Exception {
		this.delete("FAC0021.kddiRejectReceiveDt", paramBean);
	}
	
	/**
	 * Update [ORDER_DTL] when order cancel
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void cancelOrder(Pac0021ParamBean paramBean) throws Exception {
		this.update("FAC0021.updateOrderDtlWhenCancel", paramBean);
	}
	
	/**
	 * Get order by orderId
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderBean getOrders(String orderId){
		OrderBean orderBean = (OrderBean)this.queryObject("FAC0021.getOrders", orderId);
		return orderBean;
	}
	
	/**
	 * Count DO in ORDER_DO table has DO_SIGNBACK_FG = 1 for ORDER
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public int countDoSignback(Pac0021ParamBean paramBean) throws Exception {
		int result = this.queryCount("FAC0021.countDoSignback", paramBean);
		return result;
	}
}
