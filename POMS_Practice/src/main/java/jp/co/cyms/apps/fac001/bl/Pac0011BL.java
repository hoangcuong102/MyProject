package jp.co.cyms.apps.fac001.bl;

import java.util.ArrayList;
import java.util.List;

import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.apps.fac001.bean.AddonBean;
import jp.co.cyms.apps.fac001.bean.CompanyBean;
import jp.co.cyms.apps.fac001.bean.DepartmentBean;
import jp.co.cyms.apps.fac001.bean.ItemBean;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderCfgBean;
import jp.co.cyms.apps.fac001.bean.OrderDtlBean;
import jp.co.cyms.apps.fac001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fac001.bean.OrderStatusBean;
import jp.co.cyms.apps.fac001.dao.Pac0011Dao;
import jp.co.cyms.common.Constant;

public class Pac0011BL {

	/**
	 * get All company + department inside this
	 * 
	 * @return
	 */
	public List<CompanyBean> getAllCompanies(UserBean userInfo) {
		Pac0011Dao dao = new Pac0011Dao();
		List<DepartmentBean> listDept = dao.getAllDepartment();
		List<CompanyBean> listCompanies = dao.getAllCompanies();
		for (CompanyBean company : listCompanies) {
			List<DepartmentBean> listDepartment = new ArrayList<>();
			for (DepartmentBean dept : listDept) {
				if (company.getCompanyCd().equals(dept.getCompanyCd())) {
					listDepartment.add(dept);
					continue;
				}
			}
			company.setListDepartment(listDepartment);
		}

		if (String.valueOf(Constant.GENERAL_USER_CD).equals(userInfo.getUserAuthorityCd())) {
			for (int i = listCompanies.size() - 1; i >= 0; i--) {
				if (!userInfo.getUserCompanyCd().equals(listCompanies.get(i).getCompanyCd())) {
					listCompanies.remove(i);
				}
			}
		}

		return listCompanies;
	}

	/**
	 * get all Items from ITEM_MST, ACTIVE_MST, ADDON_MST
	 * 
	 * @return List<ItemBean>
	 */
	public List<ItemBean> getAllItems() {
		return new Pac0011Dao().getAllItems();
	}

	/**
	 * get hidden Items from ITEM_MST, ADDON_MST
	 * 
	 * @return List<ItemBean>
	 */
	public List<ItemBean> getHiddenItems() {
		return new Pac0011Dao().getHiddenItems();
	}

	/**
	 * get addon
	 * 
	 * @return
	 */
	public List<AddonBean> getAddon() {
		return new Pac0011Dao().getAddon();
	}

	/**
	 * insert new Order
	 * 
	 * @param bean
	 */
	public void insertOrder(OrderBean bean) {
		new Pac0011Dao().insertOrder(bean);
	}

	/**
	 * insert new Order_Dtl
	 * 
	 * @param bean
	 */
	public void insertOrderDtl(OrderDtlBean bean) {
		new Pac0011Dao().insertOrderDtl(bean);
	}

	/**
	 * insert new Order_Cfg
	 * 
	 * @param bean
	 */
	public void insertOrderCfg(OrderCfgBean bean) {
		new Pac0011Dao().insertOrderCfg(bean);
	}

	/**
	 * insert new Order_Lease
	 * 
	 * @param bean
	 */
	public void insertOrderLease(OrderLeaseBean bean) {
		new Pac0011Dao().insertOrderLease(bean);
	}

	/**
	 * insert new Order_Status
	 * 
	 * @param bean
	 */
	public void insertOrderStatus(OrderStatusBean bean) {
		new Pac0011Dao().insertOrderStatus(bean);
	}

	/**
	 * get newest order id
	 * 
	 * @return
	 */
	public long getNewestOrder() {
		List<OrderBean> data = new Pac0011Dao().getNewestOrder();
		int max = 0;
		for (OrderBean r : data) {
			int curren = Integer.parseInt(r.getOrderId().split("-")[1].split(" ")[0]);
			if (curren > max) {
				max = curren;
			}
		}
		return max;

	}

	/**
	 * release resource Stock
	 */
	public void updateStock(OrderDtlBean bean) {
		new Pac0011Dao().updateStock(bean);
	}

	/**
	 * print Order
	 * 
	 * @param bean
	 */
	public void printOrder(OrderBean bean) {
		new Pac0011Dao().printOrder(bean);
	}

	/**
	 * send Order
	 * 
	 * @param bean
	 */
	public void sendOrder(OrderBean bean) {
		new Pac0011Dao().sendOrder(bean);
	}

	/**
	 * send Order dtl
	 * 
	 * @param bean
	 */
	public void sendOrderDtl(OrderDtlBean bean) {
		new Pac0011Dao().sendOrderDtl(bean);
	}

	/**
	 * delete Order (set DELETE_FG = 1)
	 * 
	 * @param bean
	 */
	public void deleteOrder(OrderBean bean) {
		new Pac0011Dao().deleteOrder(bean);
	}

	/**
	 * clear all data with orderId
	 */
	public void deleteForeverOrder(OrderBean bean) {
		new Pac0011Dao().deleteForeverOrder(bean);
	}

	/**
	 * get order by orderId
	 * 
	 * @return
	 */
	public OrderBean getOrder(String orderId) {
		return new Pac0011Dao().getOrder(orderId);
	}

	/**
	 * get list orderDtl
	 * 
	 * @return
	 */
	public List<OrderDtlBean> getListOrderDtl(OrderDtlBean bean) {
		return new Pac0011Dao().getListOrderDtl(bean);
	}

	/**
	 * get list orderCfg
	 * 
	 * @return
	 */
	public List<OrderCfgBean> getListOrderCfg(OrderCfgBean bean) {
		return new Pac0011Dao().getListOrderCfg(bean);
	}

	/**
	 * update config
	 */
	public void updateConfig() {
		new Pac0011Dao().updateConfig();
	}

	/**
	 * 
	 * @param bean
	 * @return
	 */
	public List<UserBean> selectSankyuAdmin(UserBean bean) {
		return new Pac0011Dao().selectSankyuAdmin(bean);
	}

	/**
	 * Get max value of Running serial no
	 * 
	 * @param yearCreateOrder
	 * @return
	 * @throws Exception
	 */
	public int getMaxRunningSerialNo(String yearCreateOrder) throws Exception {
		return new Pac0011Dao().getMaxRunningSerialNo(yearCreateOrder);
	}
}
