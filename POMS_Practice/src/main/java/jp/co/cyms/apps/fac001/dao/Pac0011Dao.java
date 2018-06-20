package jp.co.cyms.apps.fac001.dao;

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
import jp.co.cyms.base.BaseDao;

public class Pac0011Dao extends BaseDao {

	public Pac0011Dao() {
		super();
	}

	/**
	 * get all companies from COMPANY_MST
	 * 
	 * @return List<CompanyBean>
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyBean> getAllCompanies() {
		return (List<CompanyBean>) this.queryList("FAC0011.getAllCompanies");
	}

	/**
	 * get all department from DEPT_MST
	 * 
	 * @return List<DepartmentBean>
	 */
	@SuppressWarnings("unchecked")
	public List<DepartmentBean> getAllDepartment() {
		return (List<DepartmentBean>) this.queryList("FAC0011.getAllDepartments");
	}

	/**
	 * get all Items from ITEM_MST, ACTIVE_MST, ADDON_MST
	 * 
	 * @param bean
	 * 
	 * @return List<ItemBean>
	 */
	@SuppressWarnings("unchecked")
	public List<ItemBean> getAllItems() {
		return (List<ItemBean>) this.queryList("FAC0011.getAllItems");
	}

	/**
	 * get hidden Items from ITEM_MST, ADDON_MST
	 * 
	 * @param bean
	 * 
	 * @return List<ItemBean>
	 */
	@SuppressWarnings("unchecked")
	public List<ItemBean> getHiddenItems() {
		return (List<ItemBean>) this.queryList("FAC0011.getHiddenItems");
	}

	/**
	 * get addon config
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AddonBean> getAddon() {
		return (List<AddonBean>) this.queryList("FAC0011.getAddon");
	}

	/**
	 * insert new Order
	 * 
	 * @param bean
	 */
	public void insertOrder(OrderBean bean) {
		this.update("FAC0011.insertOrder", bean);
	}

	/**
	 * insert new ORDER_DTL
	 * 
	 * @param bean
	 */
	public void insertOrderDtl(OrderDtlBean bean) {
		this.update("FAC0011.insertOrderDtl", bean);
	}

	/**
	 * insert new ORDER_CFG
	 * 
	 * @param bean
	 */
	public void insertOrderCfg(OrderCfgBean bean) {
		this.update("FAC0011.insertOrderCfg", bean);
	}

	/**
	 * insert new ORDER_LEASE
	 * 
	 * @param bean
	 */
	public void insertOrderLease(OrderLeaseBean bean) {
		this.update("FAC0011.insertOrderLease", bean);
	}

	/**
	 * insert new ORDER_STATUS
	 * 
	 * @param bean
	 */
	public void insertOrderStatus(OrderStatusBean bean) {
		this.update("FAC0011.insertOrderStatus", bean);
	}

	/**
	 * get list orderId
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderBean> getNewestOrder() {
		return (List<OrderBean>) this.queryList("FAC0011.getNewestOrder");
	}

	/**
	 * get order by orderId
	 * 
	 * @return
	 */
	public OrderBean getOrder(String orderId) {
		return (OrderBean) this.queryObject("FAC0011.getOrder", orderId);
	}

	/**
	 * get list orderDtl
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderDtlBean> getListOrderDtl(OrderDtlBean bean) {
		return (List<OrderDtlBean>) this.queryList("FAC0011.getOrderDtl", bean);
	}

	/**
	 * get list orderCfg
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderCfgBean> getListOrderCfg(OrderCfgBean bean) {
		return (List<OrderCfgBean>) this.queryList("FAC0011.getOrderCfg", bean);
	}

	/**
	 * release resource Stock
	 */
	public void updateStock(OrderDtlBean bean) {
		this.update("FAC0011.doUpdateStock", bean);
	}

	/**
	 * print order
	 */
	public void printOrder(OrderBean bean) {
		this.update("FAC0011.printOrder", bean);
	}

	/**
	 * send order
	 */
	public void sendOrder(OrderBean bean) {
		this.update("FAC0011.sendOrder", bean);
	}

	/**
	 * send order dtl
	 */
	public void sendOrderDtl(OrderDtlBean bean) {
		this.update("FAC0011.sendOrderDtl", bean);
	}

	/**
	 * delete order
	 */
	public void deleteOrder(OrderBean bean) {
		this.update("FAC0011.deleteOrder", bean);
	}

	/**
	 * delete forever ORDERS, ORDER_DTL, ORDER_CFG, ORDER_LEASE, ORDER_STATUS
	 */
	public void deleteForeverOrder(OrderBean bean) {
		this.delete("FAC0011.deleteOrderForever", bean);
		this.delete("FAC0011.deleteOrderDtlForever", bean);
		this.delete("FAC0011.deleteOrderCfgForever", bean);
		this.delete("FAC0011.deleteOrderLeaseForever", bean);
		//this.delete("FAC0011.deleteOrderStatusForever", bean);
	}

	/**
	 * update config
	 */
	public void updateConfig() {
		this.update("FAC0011.updateConfig");
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserBean> selectSankyuAdmin(UserBean bean) {
		return (List<UserBean>) this.queryList("FAC0011.selectSankyuAdmin", bean);
	}
	
	/**
	 * Get max value of Running serial no
	 * 
	 * @param yearCreateOrder
	 * @return
	 * @throws Exception
	 */
	public int getMaxRunningSerialNo(String yearCreateOrder) throws Exception {
		return this.queryCount("FAC0011.getMaxRunningSerialNo", yearCreateOrder);
	}
}
