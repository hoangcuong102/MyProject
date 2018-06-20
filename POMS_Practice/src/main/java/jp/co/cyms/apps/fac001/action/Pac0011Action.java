package jp.co.cyms.apps.fac001.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.cyms.apps.faa001.bean.UserBean;
import jp.co.cyms.apps.fac001.bean.AddonBean;
import jp.co.cyms.apps.fac001.bean.CompanyBean;
import jp.co.cyms.apps.fac001.bean.DepartmentBean;
import jp.co.cyms.apps.fac001.bean.ItemBean;
import jp.co.cyms.apps.fac001.bean.ItemOrder;
import jp.co.cyms.apps.fac001.bean.OrderBean;
import jp.co.cyms.apps.fac001.bean.OrderCfgBean;
import jp.co.cyms.apps.fac001.bean.OrderDtlBean;
import jp.co.cyms.apps.fac001.bean.OrderInfoBean;
import jp.co.cyms.apps.fac001.bean.OrderLeaseBean;
import jp.co.cyms.apps.fac001.bean.OrderStatusBean;
import jp.co.cyms.apps.fac001.bl.Pac0011BL;
import jp.co.cyms.apps.fac001.bl.Pac0011FileBL;
import jp.co.cyms.apps.fac001.form.Pac0011Form;
import jp.co.cyms.base.email.EmailUtil;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.ConfigBean;
import jp.co.cyms.common.bean.ErrorBean;
import jp.co.cyms.common.bean.ExcelBean;
import jp.co.cyms.common.bl.CommonBL;
import jp.co.cyms.common.properties.ErrorProperties;

/**
 * create / update order
 * 
 * @author longnd
 *
 */

public class Pac0011Action extends Pac0011Form {

	private static final long serialVersionUID = 1L;
	private List<CompanyBean> listCompanies;
	private List<ItemBean> listAllItems;
	private List<ItemBean> listHiddenItems;
	private List<AddonBean> listAddon;
	private String quoteMrc;
	private String quoteOtc;
	private List<ItemOrder> listOjectItemOrder;
	/** File report **/
	private InputStream inputStream;
	private String runningNoString;
	private ErrorBean error;

	/** contentType **/
	private String contentType;

	/** reportName **/
	private String reportName;;

	private boolean flagEdit;

	// info order need update;
	private OrderInfoBean orderInfo;

	private String redirect;

	public String execute() {
		userInfo = super.getUserInfo();
		flagEdit = false;
		return SUCCESS;
	}

	public String allDataSac0011() {
		Pac0011BL bl = new Pac0011BL();
		userInfo = super.getUserInfo();
		listCompanies = bl.getAllCompanies(userInfo);
		listAddon = bl.getAddon();
		listAllItems = bl.getAllItems();
		listHiddenItems = bl.getHiddenItems();
		redirect = new CommonBL().getConfig().getRedirect();

		if (super.orderId != null && flagEdit == true
				&& (String.valueOf(Constant.SANKYU_ADMIN_CD).equals(userInfo.getUserAuthorityCd())
						|| String.valueOf(Constant.GENERAL_USER_CD).equals(userInfo.getUserAuthorityCd()))) {

			OrderDtlBean beanDtl = new OrderDtlBean();
			beanDtl.setOrderId(super.getOrderId());
			List<OrderDtlBean> listOrderDtl = bl.getListOrderDtl(beanDtl);

			boolean checkOrderReceived = false;
			for (OrderDtlBean orderDtl : listOrderDtl) {
				if ("".equals(orderDtl.getKddiReceiveDt()) || orderDtl.getKddiReceiveDt() == null) {
					checkOrderReceived = true;
					break;
				}
			}

			if (checkOrderReceived) {
				OrderCfgBean beanCfg = new OrderCfgBean();
				beanCfg.setOrderId(super.getOrderId());
				orderInfo = new OrderInfoBean();
				orderInfo.setOrder(bl.getOrder(super.getOrderId()));
				orderInfo.setListOrderDtl(listOrderDtl);
				orderInfo.setListOrderCfg(bl.getListOrderCfg(beanCfg));
				quoteMrc = orderInfo.getOrder().getQuoteMrc();
				quoteOtc = orderInfo.getOrder().getQuoteOtc();
			} else {
				return ERROR;
			}

		}
		return SUCCESS;
	}

	public String insertOrder() throws Exception {

		String result = SUCCESS;

		LOG.info("*********** insertOrder start***********");
		try {
			Pac0011BL bl = new Pac0011BL();
			error = validateOrder(super.companyCd, super.deptCd);
			if (error != null) {
				result = INPUT;
			} else {
				if (!StringUtil.isEmpty(this.orderId) && this.quoteMrc != null && this.quoteOtc != null) {
					TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
					TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
					try {
						OrderBean bean = new OrderBean();
						bean.setOrderId(this.orderId);
						// release resource for stock
						OrderDtlBean beanDtl = new OrderDtlBean();
						beanDtl.setOrderId(this.orderId);
						List<OrderDtlBean> listOrderDtl = bl.getListOrderDtl(beanDtl);
						for (OrderDtlBean beanDtlUpdate : listOrderDtl) {
							bl.updateStock(beanDtlUpdate);
						}
						bl.deleteForeverOrder(bean);

						transactionManager.commit(transactionStatus);
					} catch (Exception e) {
						transactionManager.rollback(transactionStatus);
						session.put(Constant.EXCEPTION_AJAX, e);
						throw new Exception();
					}
				} else {
					DateFormat df = new SimpleDateFormat("yy");
					String yy = df.format(Calendar.getInstance().getTime());
					serialNo(yy);
					this.orderId = yy + runningNoString + " " + super.countryCd;
					this.quoteMrc = yy + runningNoString + "-01-" + super.companyCd + "M";
					this.quoteOtc = yy + runningNoString + "-02-" + super.companyCd + "O";

				}
				result = createOrder();
				sendEmail(Constant.SAVE_ORDER_TITLE);

			}

		} catch (Exception e) {
			session.put(Constant.EXCEPTION_AJAX, e);
//			throw new Exception();
		}
		LOG.info("*********** insertOrder End***********");

		return result;

	}

	public String createOrder() throws Exception {
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		Pac0011BL bl = new Pac0011BL();
		ObjectMapper mapper = new ObjectMapper();
		try {
			listOjectItemOrder = mapper.readValue(listItemOrder, new TypeReference<List<ItemOrder>>() {
			});
			if (listOjectItemOrder.size() == 0) {
				error = new ErrorBean();
				error.setKey(ErrorProperties.ExceptionKey.PCOD_0005.toString().replace("_", "-"));
				error.setValue(ErrorProperties.get(ErrorProperties.ExceptionKey.PCOD_0005));
				return INPUT;
			}

			LOG.info("*********** insert ORDER_MST***********");
			OrderBean beanOrder = new OrderBean();
			beanOrder.setOrderId(this.orderId);
			beanOrder.setQuoteMrc(this.quoteMrc);
			beanOrder.setQuoteOtc(this.quoteOtc);
			beanOrder.setCountryCd(super.countryCd);
			beanOrder.setCompanyCd(super.companyCd);
			beanOrder.setDeptCd(super.deptCd);
			beanOrder.setTtlMrc(super.totalMonthlyRecurring);
			beanOrder.setTtlOtc(super.totalOneTimeCharge);
			beanOrder.setTtlAvg(super.fireEyeEdgeXSoftware);
			beanOrder.setTtlMon(super.pcMonitoringSoftware);
			beanOrder.setRemark(super.remark);
			beanOrder.setSaveFg(Constant.SAVE_FG);
			beanOrder.setUpdatedUser(super.getUpdatedUser());
			beanOrder.setUpdatedDtLocal(super.getUpdatedDtLocal());
			beanOrder.setTimeDifference(super.getTimeDifference());
			beanOrder.setExclusiveFg(Constant.EXCLUSIVE_FG);
			bl.insertOrder(beanOrder);

			LOG.info("*********** insert ORDER_STATUS***********");
			OrderStatusBean beanOderstatus = new OrderStatusBean();
			beanOderstatus.setOrderId(this.orderId);
			beanOderstatus.setPhase(Constant.SANKYU_REGIST);
			beanOderstatus.setUpdatedUser(super.getUpdatedUser());
			beanOderstatus.setUpdatedDtLocal(super.getUpdatedDtLocal());
			beanOderstatus.setTimeDifference(super.getTimeDifference());
			beanOderstatus.setExclusiveFg(Constant.EXCLUSIVE_FG);
			bl.insertOrderStatus(beanOderstatus);

			DecimalFormat numberFomat = new DecimalFormat();
			numberFomat.setMaximumFractionDigits(Constant.DECIMAL_PLACES_DIGIST);

			// List large category
			Map<String, Integer> mapListLargeOrder = new HashMap<String, Integer>();
			for (ItemOrder itemOrder : listOjectItemOrder) {
				if (itemOrder.getCategoryLarge().equals(itemOrder.getCategoryCd())) {
					// m.getKey = categoryLarge, m.getValue = Quantity of item
					// model (this
					// categoryLarge)
					mapListLargeOrder.put(itemOrder.getCategoryLarge(), itemOrder.getQty());

				}
			}
			for (@SuppressWarnings("rawtypes")
			Map.Entry m : mapListLargeOrder.entrySet()) {
				for (ItemOrder itemOrder : listOjectItemOrder) {
					if (m.getKey().equals(itemOrder.getCategoryCd())) {
						/* update model */
						LOG.info("*********** insert ORDER_DTL ***********");
						OrderDtlBean orderDtlBean = new OrderDtlBean();
						orderDtlBean.setOrderId(this.orderId);
						orderDtlBean.setCategoryCd(itemOrder.getCategoryCd());
						orderDtlBean.setItemCd(itemOrder.getItemCd());
						orderDtlBean.setItemQty(itemOrder.getQty());
						orderDtlBean.setUnitPrice(itemOrder.getUnitPrice());
						Float amountValue = itemOrder.getQty() * itemOrder.getUnitPrice();
						orderDtlBean.setAmount(Float.parseFloat(numberFomat.format(amountValue).replaceAll(",", "")));
						orderDtlBean.setLeaseType(Constant.LEASE_TYPE_MRC);
						orderDtlBean.setUpdatedUser(super.getUpdatedUser());
						orderDtlBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
						orderDtlBean.setTimeDifference(super.getTimeDifference());
						orderDtlBean.setExclusiveFg(Constant.EXCLUSIVE_FG);
						bl.insertOrderDtl(orderDtlBean);

						int displayOrderNumber = 1;
						for (int i = 1; i <= itemOrder.getQty(); i++) {
							LOG.info("*********** insert ORDER_LEASE ***********");
							OrderLeaseBean orderLeaseBean = new OrderLeaseBean();
							orderLeaseBean.setOrderId(this.orderId);
							orderLeaseBean.setQuoteMrc(this.quoteMrc);
							orderLeaseBean.setQuoteOtc(this.quoteOtc);
							orderLeaseBean.setCategoryCd(itemOrder.getCategoryCd());
							orderLeaseBean.setItemCd(itemOrder.getItemCd());
							orderLeaseBean.setDisplayOrder(displayOrderNumber);
							orderLeaseBean.setLeaseType(Constant.LEASE_TYPE_MRC);
							orderLeaseBean.setUpdatedUser(super.getUpdatedUser());
							orderLeaseBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
							orderLeaseBean.setTimeDifference(super.getTimeDifference());
							orderLeaseBean.setExclusiveFg(Constant.EXCLUSIVE_FG);
							bl.insertOrderLease(orderLeaseBean);
							displayOrderNumber++;
						}

					} else if (m.getKey().equals(itemOrder.getCategoryLarge())) {
						LOG.info("*********** insert ORDER_CFG ***********");
						OrderCfgBean orderCfgBean = new OrderCfgBean();
						orderCfgBean.setOrderId(this.orderId);
						orderCfgBean.setCategoryCd(itemOrder.getCategoryLarge());
						orderCfgBean.setAddonCd(itemOrder.getAddonCd());
						orderCfgBean.setAddonConfig(itemOrder.getAddonConfig());
						orderCfgBean.setItemCd(itemOrder.getItemCd());
						orderCfgBean.setItemQty(itemOrder.getQty());
						orderCfgBean.setUnitPrice(itemOrder.getUnitPrice());
						Float amountValue = itemOrder.getQty() * itemOrder.getUnitPrice();
						orderCfgBean.setAmount(Float.parseFloat(numberFomat.format(amountValue).replaceAll(",", "")));
						orderCfgBean.setLeaseType(itemOrder.getLeaseType());
						orderCfgBean.setWarningFg(itemOrder.getWarningFg());
						orderCfgBean.setUpdatedUser(super.getUpdatedUser());
						orderCfgBean.setUpdatedDtLocal(super.getUpdatedDtLocal());
						orderCfgBean.setTimeDifference(super.getTimeDifference());
						orderCfgBean.setExclusiveFg(Constant.EXCLUSIVE_FG);
						bl.insertOrderCfg(orderCfgBean);
					}

				}
			}

			LOG.info("*********** update STOCK***********");
			OrderDtlBean beanDtl = new OrderDtlBean();
			beanDtl.setOrderId(this.orderId);
			List<OrderDtlBean> listOrderDtl = bl.getListOrderDtl(beanDtl);
			for (OrderDtlBean beanDtlUpdate : listOrderDtl) {
				beanDtlUpdate.setItemQty(beanDtlUpdate.getItemQty() * -1);
				bl.updateStock(beanDtlUpdate);
			}

			LOG.info("*********** create file Quotation mrc***********");
			List<String> listOutputFile = new ArrayList<String>();
			Pac0011FileBL blPdf = new Pac0011FileBL();
			List<ExcelBean> listDataMrc = new ArrayList<ExcelBean>();
			String inputFileMrc = blPdf.getFileName(Constant.QUOTE_MRC_XLSX, session, true);
			String outputFileMrc = blPdf.getFileName(quoteMrc + Constant.XLSX, session, false);
			listDataMrc = blPdf.getDataToExport(this.orderId, Constant.LEASE_TYPE_MRC);
			listOutputFile.add(blPdf.createPdfFile(inputFileMrc, outputFileMrc, listDataMrc, session, this.quoteMrc, Constant.LEASE_TYPE_MRC));

			LOG.info("*********** create file Quotation OTC***********");

			List<ExcelBean> listDataOTc = new ArrayList<ExcelBean>();
			String inputFileOtc = blPdf.getFileName(Constant.QUOTE_OTC_XLSX, session, true);
			String outputFileOtc = blPdf.getFileName(quoteOtc + Constant.XLSX, session, false);
			listDataOTc = blPdf.getDataToExport(this.orderId, Constant.LEASE_TYPE_OTC);
			listOutputFile.add(blPdf.createPdfFile(inputFileOtc, outputFileOtc, listDataOTc, session, this.quoteOtc, Constant.LEASE_TYPE_OTC));
			
			configInfo = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
			blPdf.converPdf(listOutputFile, configInfo);
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			error = new ErrorBean();
			error.setKey(ErrorProperties.ExceptionKey.PCOD_0005.toString().replace("_", "-"));
			error.setValue(ErrorProperties.get(ErrorProperties.ExceptionKey.PCOD_0005));
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	private ErrorBean validateOrder(String companyCd, String deptCd) throws IOException {
		userInfo = super.getUserInfo();
		ErrorBean errorInfo = new ErrorBean();
		boolean validValue = true;
		boolean existCompany = false;
		boolean exitsDept = false;
		listCompanies = new Pac0011BL().getAllCompanies(userInfo);
		for (CompanyBean c : listCompanies) {
			if (companyCd.equals(c.getCompanyCd())) {
				existCompany = true;
				for (DepartmentBean d : c.getListDepartment()) {
					if (deptCd.equals(d.getDeptCd())) {
						exitsDept = true;
						break;
					}
				}
				break;
			}
		}

		if (existCompany == false || exitsDept == false) {
			validValue = false;
		} else if (String.valueOf(Constant.GENERAL_USER_CD).equals(userInfo.getUserAuthorityCd())) {
			if (!companyCd.equals(userInfo.getUserCompanyCd())) {
				validValue = false;
			}

		}
		ConfigBean configBean = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);

		File fileMRCTemp = new File(configBean.getExcelTemp() + "\\" + Constant.QUOTE_MRC_XLSX);
		File fileOTCTemp = new File(configBean.getExcelTemp() + "\\" + Constant.QUOTE_OTC_XLSX);

		if (fileMRCTemp.exists() == false || fileOTCTemp.exists() == false) {
			errorInfo.setKey(ErrorProperties.ExceptionKey.PCOD_0008.toString().replace("_", "-"));
			errorInfo.setValue(ErrorProperties.get(ErrorProperties.ExceptionKey.PCOD_0008));
			return errorInfo;
		}
		if (validValue == false) {
			errorInfo.setKey(ErrorProperties.ExceptionKey.PCOD_0007.toString().replace("_", "-"));
			errorInfo.setValue(ErrorProperties.get(ErrorProperties.ExceptionKey.PCOD_0007));
			return errorInfo;
		}

		File directory = new File(configBean.getLibreOffice());
		if (directory.exists() == false) {
			errorInfo.setKey(ErrorProperties.ExceptionKey.PCOD_0009.toString().replace("_", "-"));
			errorInfo.setValue(ErrorProperties.get(ErrorProperties.ExceptionKey.PCOD_0009));
			return errorInfo;
		}

		errorInfo = null;
		return errorInfo;
	}

	public String updateConfig() {
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {
			new Pac0011BL().updateConfig();
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	private void sendEmail(String title) throws Exception {
		try {
			List<UserBean> listSankyuAdmin = new ArrayList<UserBean>();
			Pac0011BL bl = new Pac0011BL();
			OrderBean order = bl.getOrder(this.orderId);
			UserBean userBean = new UserBean();
			userBean.setUserAuthorityCd(String.valueOf(Constant.SANKYU_ADMIN_CD));
			userBean.setUserCompanyCd(order.getCompanyCd());
			listSankyuAdmin = bl.selectSankyuAdmin(userBean);

			Properties props = new Properties();
			CommonBL logic = new CommonBL();
			ConfigBean configBean = logic.getConfig();
			Session session = EmailUtil.createSession(props, configBean);
			String subject = title;
			StringBuffer body = new StringBuffer();
			body.append("<table>");
			body.append("<tr><td style='text-align: right;width: 100px;'>Country :&nbsp;</td><td>" + order.getCountryCd() + "</td></tr>");
			body.append("<tr><td style='text-align: right;width: 100px;'>Company :&nbsp;</td><td>" + order.getCompanyCd() + "</td></tr>");
			body.append("<tr><td style='text-align: right;width: 100px;'>Dept :&nbsp;</td><td>" + order.getDeptCd() + "</td></tr>");
			body.append("<tr><td style='text-align: right;width: 100px;'>User :&nbsp;</td><td>" + order.getUpdatedUser() + "</td></tr>");
			body.append("<tr><td style='text-align: right;width: 100px;'>Quotation No :&nbsp;</td><td>" + order.getQuoteMrc() + " / "
					+ order.getQuoteOtc() + "</td></tr>");
			body.append("</table>");

			ArrayList<String> listFile = new ArrayList<String>();
			listFile.add(order.getQuoteMrc() + Constant.PDF);
			listFile.add(order.getQuoteOtc() + Constant.PDF);
			List<String> listToUser = new ArrayList<String>();
			for (UserBean user : listSankyuAdmin) {
				listToUser.add(user.getUserEmail());
			}
			// Check format email
			String listToUserString = EmailUtil.checkValidation(session, String.join(",", listToUser));
			EmailUtil.sendAttachmentEmail(session, listToUserString, subject, body.toString(), listFile);
		} catch (Exception ex) {
			error = new ErrorBean();
			error.setKey(ErrorProperties.ExceptionKey.PCOD_0017.toString().replace("_", "-"));
			error.setValue(ErrorProperties.get(ErrorProperties.ExceptionKey.PCOD_0017));
			throw ex;
		}
	}

	public String printOrder() throws Exception {
		LOG.info("*********** print Order start***********");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {
			OrderBean bean = new OrderBean();
			bean.setOrderId(super.orderId);
			bean.setPrintFg(Constant.PRINT_ORDER_FG);
			Pac0011BL bl = new Pac0011BL();
			bl.printOrder(bean);

			inputStream = new FileInputStream(new File(super.filePath + super.fileNameDownload));
			// set contentType
			contentType = Constant.PDF_TYPE;
			// set name for report
			reportName = super.fileNameDownload;
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			session.put(Constant.EXCEPTION_AJAX, e);
			throw new Exception();
		}

		LOG.info("*********** print Order end***********");
		return SUCCESS;

	}

	public String sendOrder() throws Exception {
		LOG.info("*********** send Order start***********");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {
			OrderBean bean = new OrderBean();
			bean.setOrderId(super.orderId);
			bean.setSendOrderFg(Constant.SEND_ORDER_FG);
			Pac0011BL bl = new Pac0011BL();
			bl.sendOrder(bean);

			OrderDtlBean beanDtl = new OrderDtlBean();
			beanDtl.setOrderId(super.orderId);
			bl.sendOrderDtl(beanDtl);

			// ORDER_STATUS
			OrderStatusBean beanOderstatus = new OrderStatusBean();
			beanOderstatus.setOrderId(this.orderId);
			beanOderstatus.setPhase(Constant.SANKYU_ORDER);
			beanOderstatus.setUpdatedUser(super.getUpdatedUser());
			beanOderstatus.setUpdatedDtLocal(super.getUpdatedDtLocal());
			beanOderstatus.setTimeDifference(super.getTimeDifference());
			beanOderstatus.setExclusiveFg(Constant.EXCLUSIVE_FG);
			bl.insertOrderStatus(beanOderstatus);

			sendEmail(Constant.SEND_ORDER_TITLE);
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			session.put(Constant.EXCEPTION_AJAX, e);
			throw new Exception();
		}
		LOG.info("*********** send Order End***********");
		return SUCCESS;
	}

	public String deleteOrder() throws Exception {
		LOG.info("*********** delete Order start***********");
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		try {
			OrderBean bean = new OrderBean();
			bean.setOrderId(super.orderId);
			bean.setDeleteFg(Constant.DELETE_ORDER_FG);
			Pac0011BL bl = new Pac0011BL();
			bl.deleteOrder(bean);
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			session.put(Constant.EXCEPTION_AJAX, e);
			throw new Exception();
		}
		LOG.info("*********** delete Order End***********");
		return SUCCESS;
	}

	public String editOrder() {
		flagEdit = true;
		return SUCCESS;
	}

	private void serialNo(String yearCreateOrder) {
		int runningNoLong;
		try {
			runningNoLong = new Pac0011BL().getMaxRunningSerialNo(yearCreateOrder);
		} catch (Exception e) {
			runningNoLong = Constant.DEFAULT_RUNNING_NO;
		}
		// increase running serial no to 1
		runningNoLong = runningNoLong + 1;
		
		try {
			// Convert running no to format ###
			runningNoString = String.format("%03d", runningNoLong);
		} catch (Exception e) {
			runningNoString = "001";
		}
	}

	public String downloadItemPdf() throws IOException {
		configInfo = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		try {
			inputStream = new FileInputStream(new File(configInfo.getPdfDir() + "\\" + super.fileNameDownload));
			// set contentType
			contentType = Constant.PDF_TYPE;
			// set name for report
			reportName = super.fileNameDownload;
		} catch (IOException e) {
			LOG.info("*********** file pdf not exist ***********" + configInfo.getPdfDir() + "\\"
					+ super.fileNameDownload);
			error = new ErrorBean();
			error.setKey(ErrorProperties.ExceptionKey.PCOD_0006.toString().replace("_", "-"));
			error.setValue(ErrorProperties.get(ErrorProperties.ExceptionKey.PCOD_0006));
			return ERROR;
		}
		if (checkPdfExist != null && checkPdfExist == true) {
			return NONE;
		}
		return SUCCESS;

	}

	public List<CompanyBean> getListCompanies() {
		return listCompanies;
	}

	public void setListCompanies(List<CompanyBean> listCompanies) {
		this.listCompanies = listCompanies;
	}

	public UserBean getUserInfo() {
		return super.getUserInfo();
	}

	public List<ItemBean> getListAllItems() {
		return listAllItems;
	}

	public void setListAllItems(List<ItemBean> listAllItems) {
		this.listAllItems = listAllItems;
	}

	public List<ItemBean> getListHiddenItems() {
		return listHiddenItems;
	}

	public void setListHiddenItems(List<ItemBean> listHiddenItems) {
		this.listHiddenItems = listHiddenItems;
	}

	public List<AddonBean> getListAddon() {
		return listAddon;
	}

	public void setListAddon(List<AddonBean> listAddon) {
		this.listAddon = listAddon;
	}

	public String getQuoteMrc() {
		return quoteMrc;
	}

	public void setQuoteMrc(String quoteMrc) {
		this.quoteMrc = quoteMrc;
	}

	public String getQuoteOtc() {
		return quoteOtc;
	}

	public void setQuoteOtc(String quoteOtc) {
		this.quoteOtc = quoteOtc;
	}

	public String getOrderId() {
		return super.getOrderId();
	}

	public OrderInfoBean getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfoBean orderInfo) {
		this.orderInfo = orderInfo;
	}

	public InputStream getInputStream() {
		return inputStream;
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

	public boolean isFlagEdit() {
		return flagEdit;
	}

	public void setFlagEdit(boolean flagEdit) {
		this.flagEdit = flagEdit;
	}

	public ErrorBean getError() {
		return error;
	}

	public void setError(ErrorBean error) {
		this.error = error;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

}
