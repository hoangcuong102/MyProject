package jp.co.cyms.apps.fac001.bl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.apps.fac001.bean.EmailBean;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderStatusBean;
import jp.co.cyms.apps.fac001.bean.Pac0021Bean;
import jp.co.cyms.apps.fac001.bean.Pac0021ParamBean;
import jp.co.cyms.apps.fac001.dao.Pac0021Dao;
import jp.co.cyms.apps.fad001.bean.Pad0021Bean;
import jp.co.cyms.apps.fad001.bl.Pad0021BL;
import jp.co.cyms.common.StringUtil;

/**
 * PC Order Progress Update / Inquiry Pac0021BL
 * 
 * @author binhvh
 * @since 2018/01/12
 */
public class Pac0021BL {

	/**
	 * get all company code from COMPANY_MST
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<String> getAllCompanyCd() throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		List<String> list = dao.getAllCompanyCd();
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * get company code when [USER_MST].USER_AUTHORITY = "1"
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getCompanyCd(UserBean userBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		return dao.getCompanyCd(userBean);
	}

	/**
	 * search order in ORDER_DTL
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public List<Pac0021Bean> searchOrderDtl(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		List<Pac0021Bean> list = dao.searchOrderDtl(paramBean);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * get all orderId expiring.
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public List<String> getOrderIdExpiring(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		List<String> list = dao.getOrderIdExpiring(paramBean);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * delete order in ORDERS
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void deleteOrder(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.deleteOrder(paramBean);
	}

	/**
	 * get orderDtl by orderId
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public List<Pac0021Bean> getOrderDtlByOrderId(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		List<Pac0021Bean> list = dao.getOrderDtlByOrderId(paramBean);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * get orderCfg by orderId
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public List<Pac0021Bean> getOrderCfgByOrderId(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		List<Pac0021Bean> list = dao.getOrderCfgByOrderId(paramBean);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * update SANKYU_ORDER_DT = NULL
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateSankyuOrderDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.udpateSankyuOrderDt(paramBean);
	}

	/**
	 * update SEA_REQUEST_DT = 'DD-MMM'
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void updateSeaRequestDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.updateSeaRequestDt(paramBean);
	}

	/**
	 * update ORDER_STATUS
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void updateOrderStatus(OrderStatusBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.updateOrderStatus(paramBean);
	}

	/**
	 * update stock inventory
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void updateStockInventory(Pac0021Bean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.updateStockInventory(paramBean);
	}
	
	/**
	 * check exist order status
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public boolean checkExistOrderStatus(OrderStatusBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		Integer result = dao.checkExistOrderStatus(paramBean);
		return result == null ? false : true;
	}
	
	/**
	 * check exist stock
	 * 
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public boolean checkExistStock(Pac0021Bean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		Integer result = dao.checkExistStock(paramBean);
		return result == null ? false : true;
	}
	
	/**
	 * update KDDI_ACCEPT_DT = DD-MMM
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateKddiAcceptDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.udpateKddiAcceptDt(paramBean);
	}

	/**
	 * update KDDI_ORDER_DT = DD-MMM
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateKddiOrderDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.udpateKddiOrderDt(paramBean);
	}

	/**
	 * update KDDI_RECEIVE_DT = DD-MMM
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateKddiReceiveDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.udpateKddiReceiveDt(paramBean);
	}

	/**
	 * update KDDI_LEASE_DT = DD-MMM
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void udpateKddiLeaseDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.udpateKddiLeaseDt(paramBean);
	}
	
	/**
	 * update CONFIG
	 * REDIRECT = 1
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void updateRedirect(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.updateRedirect(paramBean);
	}
	
	/**
	 * Get getDataSendEmail
	 * @param paramBean
	 * @return EmailBean
	 * @throws Exception
	 */
	public EmailBean getDataSendEmail(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		return dao.getDataSendEmail(paramBean);
	}
	
	/**
	 * Get list email address
	 * 
	 * @return List
	 * @throws Exception
	 */
	public List<String> getToEmailAddress() throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		return dao.getToEmailAddress();
	}
	
	/**
	 * KDDI Reject
	 * Set [ORDER_DTL].KDDI_ACCEPT_DT = NULL
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void kddiRejectAcceptDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.kddiRejectAcceptDt(paramBean);
	}
	
	/**
	 * KDDI Reject
	 * Set [ORDER_DTL].KDDI_ORDER_DT = NULL
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void kddiRejectOrderDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.kddiRejectOrderDt(paramBean);
	}
	
	/**
	 * KDDI Reject
	 * Set [ORDER_DTL].KDDI_RECEIVE_DT = NULL
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void kddiRejectReceiveDt(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.kddiRejectReceiveDt(paramBean);
	}
	
	/**
	 * Update [ORDER_DTL] when order cancel
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void cancelOrder(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		dao.cancelOrder(paramBean);
	}
	
	/**
	 * Get order by orderId
	 * 
	 * @param orderId
	 * @return
	 */
	public OrderBean getOrders(String orderId){
		Pac0021Dao dao = new Pac0021Dao();
		OrderBean orderBean = dao.getOrders(orderId);
		return orderBean;
	}
	
	/**
	 * validate when reject or approve
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean validate(String userAuthority, Pac0021Bean bean) throws Exception {
		boolean result;
		if (StringUtil.isEmpty(userAuthority) || bean == null) {
			return false;
		}
		// If USER_AUTHORITY = 3
		if (userAuthority.equals("3")) {
			if (!StringUtil.isEmpty(bean.getSankyuOrderDt()) && StringUtil.isEmpty(bean.getSeaRequestDt())) {
				result = true;
			} else {
				result = false;
			}
		} // If USER_AUTHORITY = 2
		else if (userAuthority.equals("2")) {
			if (!StringUtil.isEmpty(bean.getSeaRequestDt()) && StringUtil.isEmpty(bean.getKddiAcceptDt())) {
				result = true;
			} else if (!StringUtil.isEmpty(bean.getKddiAcceptDt()) && StringUtil.isEmpty(bean.getKddiOrderDt())) {
				result = true;
			} else if (!StringUtil.isEmpty(bean.getKddiOrderDt()) && StringUtil.isEmpty(bean.getKddiReceiveDt())) {
				result = true;
			} else if(!StringUtil.isEmpty(bean.getKddiReceiveDt()) && StringUtil.isEmpty(bean.getKddiDeliverDt())){
				result = true;
			} else if (!StringUtil.isEmpty(bean.getKddiReceiveDt()) && !StringUtil.isEmpty(bean.getKddiDeliverDt())
					&& StringUtil.isEmpty(bean.getKddiLeaseDt())) {
				if (bean.getQuantity() == bean.getKddiDeliverQty()) {
					result = true;
				} else {
					result = true;
				}
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		return result;
	}
	
	/**
	 * validate when click Approve
	 * If  ([ORDER_DTL].ITEM_QTY = [ORDER_DTL].KDDI_DELIVER_QTY) return true
	 * 
	 * @param listOrderDtl
	 * @return
	 * @throws Exception
	 */
	public boolean validateQuantity(List<Pac0021Bean> listOrderDtl) throws Exception {
		for (Pac0021Bean orderDtl : listOrderDtl) {
			if (!orderDtl.getQuantity().equals(orderDtl.getKddiDeliverQty())){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * generateListGroupOrderDtl from listOrderDtl
	 * 
	 * @param listBean
	 * @return
	 * @throws Exception
	 */
	public List<List<Pac0021Bean>> generateListGroupOrderDtl(List<Pac0021Bean> listOrderDtl, boolean descendingOrder) throws Exception {
		// Grouping List orderDtl by orderId
		Map<String, List<Pac0021Bean>> mapList = listOrderDtl.stream()
				.sorted(Comparator.comparing(Pac0021Bean::getDisplayOrder, Comparator.nullsLast(Comparator.naturalOrder()))
						.thenComparing(Pac0021Bean::getKddiDeliverDtDoNoTypeDate, Comparator.nullsLast(Comparator.naturalOrder()))
						.thenComparing(Pac0021Bean::getSeqNo, Comparator.nullsLast(Comparator.naturalOrder())))
				.collect(Collectors.groupingBy(Pac0021Bean::getOrderId));
		// Sort by orderId
		Map<String, List<Pac0021Bean>> mapListSort = null;
		// If descendingOrder = true
		if (descendingOrder){
			// descending Order
			mapListSort = new TreeMap<String, List<Pac0021Bean>>(Collections.reverseOrder());
			mapListSort.putAll(mapList);
		} else {
			// ascending order
			mapListSort = new TreeMap<String, List<Pac0021Bean>>(mapList);
		}
		// Convert Map to list List<List<Pac0021Bean>>
		List<List<Pac0021Bean>> listGroupOrderDtl = mapListSort.values().stream().collect(Collectors.toList());
		
		return listGroupOrderDtl;
	}
	
	/**
	 * If currently being edited by another user then set value ExclusiveFg is 1 else null.
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	public void checkCurrentlyEdited(Pac0021Bean paramBean) throws Exception {
		Pad0021BL logic = new Pad0021BL();
		Pad0021Bean pad0021Bean = new Pad0021Bean();
		pad0021Bean.setExclusiveFg(null);
		boolean checkExclusive = logic.checkUserEnter(pad0021Bean);
		if (checkExclusive) {
			paramBean.setExclusiveFg(null);
		} else {
			paramBean.setExclusiveFg("1");
			paramBean.setUpdatedUser(null);
		}
	}
	
	/**
	 * Count DO in ORDER_DO table has DO_SIGNBACK_FG = 1 for ORDER
	 * @param paramBean
	 * @return
	 * @throws Exception
	 */
	public int countDoSignback(Pac0021ParamBean paramBean) throws Exception {
		Pac0021Dao dao = new Pac0021Dao();
		return dao.countDoSignback(paramBean);
	}
	
	/**
	 * Check the conditions of order cancellation
	 * @param paramBean
	 * @return true if countDoSignback = 0, false if countDoSignback # 0
	 * @throws Exception
	 */
	public boolean checkCancelOrder(Pac0021ParamBean paramBean) throws Exception {
		int countDoSignback = countDoSignback(paramBean);
		return countDoSignback == 0 ? true : false;
	}
}
