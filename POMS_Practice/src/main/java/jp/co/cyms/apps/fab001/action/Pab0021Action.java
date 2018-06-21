package jp.co.cyms.apps.fab001.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.cyms.apps.fab001.bean.Pab0021Bean;
import jp.co.cyms.apps.fab001.bean.UploadBean;
import jp.co.cyms.apps.fab001.bl.Pab0021BL;
import jp.co.cyms.apps.fab001.form.Pab0021Form;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.ConfigBean;

/**
 * Item Master 2 Pab0021Action
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0021Action extends Pab0021Form {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** List Category Code */
	private List<String> categoryCdList;

	/** List Item Code */
	private List<String> itemCdList;

	/** Pab0021Bean */
	private Pab0021Bean pab0021Bean;

	/** UploadBean */
	private UploadBean uploadBean;

	/** File catalog pdf **/
	private InputStream inputStream;

	/** Content Type */
	private String contentType;

	/** catalogPdfName */
	private String catalogPdfName;

	/** image base64 */
	private String imageBase64;

	/** check itemMst exist */
	private boolean checkInsert;

	/** Check file exist */
	private boolean fileExist;

	/**
	 * Method execute when init fab0021 screen
	 * 
	 * @return SUCCESS
	 */
	public String execute() throws Exception {
		LOG.info("*************execute StartSAB0021*************", "");
		// BL
		Pab0021BL logic = new Pab0021BL();
		// Get user info
		userInfo = this.getUserInfo();
		// Get list categoryCd
		categoryCdList = logic.getListCategoryCd();
		LOG.info("*************execute EndSAB0021*************", "");
		return SUCCESS;
	}

	/**
	 * Action get categoryName, itemCd
	 * 
	 * @return SUCCESS
	 */
	public String doEvent01() throws Exception{
		LOG.info("*************doEvent01 Start*************", "");
		// Business Logic
		Pab0021BL logic = new Pab0021BL();
		pab0021Bean = new Pab0021Bean();
		pab0021Bean.setCategoryCd(categoryCd);
		pab0021Bean.setCategoryName(logic.getCategoryName(pab0021Bean));
		// Get list itemCd
		itemCdList = logic.getListItemCd(pab0021Bean);
		LOG.info("*************doEvent01 End*************", "");
		return SUCCESS;
	}

	/**
	 * Action get info item
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	public String doEvent02() throws Exception {
		// Business Logic
		Pab0021BL logic = new Pab0021BL();
		// Get config bean
		ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		LOG.info("*************doEvent02 Start*************", "");
		// ParamBean
		Pab0021Bean paramBean = new Pab0021Bean();
		paramBean.setCategoryCd(categoryCd);
		paramBean.setItemCd(itemCd);

		// get item_dtl_mst
		pab0021Bean = logic.getItemDtlMst(paramBean);

		// set itemName
		if (pab0021Bean == null) {
			pab0021Bean = new Pab0021Bean();
			checkInsert = true;
		}
		pab0021Bean.setItemName(logic.getItemName(paramBean));
		// Convert image to Base64
		imageBase64 = logic.encoder(config.getImageDir(), pab0021Bean.getImageName());
		LOG.info("*************doEvent02 End*************", "");
		return SUCCESS;
	}

	/**
	 * action insert or update item_dtl_mst
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String doEvent03() throws Exception {
		LOG.info("*************doEvent03 Start*************", "");
		// Check categoryCd, itemCd is empty
		if (StringUtil.isEmpty(this.categoryCd) || StringUtil.isEmpty(this.itemCd)) {
			LOG.info("*************doEvent03 End*************", "");
			return ERROR;
		}
		// Business logic
		Pab0021BL logic = new Pab0021BL();
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

		// upload file image, pdf
		// Get config bean
		ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		pab0021Bean = new Pab0021Bean();
		pab0021Bean.setCategoryCd(this.categoryCd);
		pab0021Bean.setCategoryName(this.categoryName);
		pab0021Bean.setItemCd(this.itemCd);
		pab0021Bean.setItemName(this.itemName);
		pab0021Bean.setRemark(this.remark);

		// upload file iamge
		if (this.fileImage != null) {
			// upload file
			uploadBean = logic.uploadFile(this.fileImage, this.imageName, config.getImageDir());
			// Check upload success
			if (uploadBean != null) {
				pab0021Bean.setLinkToImage(uploadBean.getLinkToFile());
				pab0021Bean.setImageName(uploadBean.getFileName());
				pab0021Bean.setImageSize(uploadBean.getFileSize());
			} else {
				LOG.info("*************doEvent04 End*************", "");
				return ERROR;
			}
		} else {
			pab0021Bean.setLinkToImage(this.linkToImage);
			pab0021Bean.setImageName(this.imageName);
			pab0021Bean.setImageSize(this.imageSize);
		}

		uploadBean = null;

		// upload file pdf
		if (this.filePdf != null) {
			// upload file
			uploadBean = logic.uploadFile(this.filePdf, this.pdfName, config.getPdfDir());
			// Check upload success
			if (uploadBean != null) {
				pab0021Bean.setLinkToPdf(uploadBean.getLinkToFile());
				pab0021Bean.setPdfName(uploadBean.getFileName());
				pab0021Bean.setPdfSize(uploadBean.getFileSize());
			} else {
				LOG.info("*************doEvent04 End*************", "");
				return ERROR;
			}
		} else {
			pab0021Bean.setLinkToPdf(this.linkToPdf);
			pab0021Bean.setPdfName(this.pdfName);
			pab0021Bean.setPdfSize(this.pdfSize);
		}

		// BaseDBBean
		pab0021Bean.setUpdatedUser(super.getUpdatedUser());
		pab0021Bean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		pab0021Bean.setTimeDifference(super.getTimeDifference());
		pab0021Bean.setExclusiveFg(Constant.EXCLUSIVE_FG);

		boolean checkInsert = false;
		// Check itemDtlMst is exist
		if (logic.getItemDtlMst(pab0021Bean) == null) {
			checkInsert = true;
		}
		try {
			// ItemDtlMst is not exist
			if (checkInsert) {
				// insert item_dtl_mst
				logic.insertItemDtlMst(pab0021Bean);
			} else {
				// update item_dtl_mst
				logic.updateItemDtlMst(pab0021Bean);
			}
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback Event03 *************", "");
		}
		LOG.info("*************doEvent03 End*************", "");
		return SUCCESS;
	}

	/**
	 * action upload file
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String doEvent04() throws Exception {
		LOG.info("*************doEvent04 Start*************", "");
		Pab0021BL logic = new Pab0021BL();
		ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		// upload file image
		if (this.fileImage != null) {
			uploadBean = logic.uploadFile(this.fileImage, this.imageName, config.getImageDir());
		}

		// upload file pdf
		if (this.filePdf != null) {
			uploadBean = logic.uploadFile(this.filePdf, this.pdfName, config.getPdfDir());
		}

		if (uploadBean == null) {
			LOG.info("*************doEvent04 End*************", "");
			return ERROR;
		}
		LOG.info("*************doEvent04 End*************", "");
		return SUCCESS;
	}

	/**
	 * action delete item_mst
	 * 
	 * @return result
	 * @throws Exception
	 */
	public String doEvent05() throws Exception {
		LOG.info("*************doEvent05 Start*************", "");

		if (StringUtil.isEmpty(this.itemCd)) {
			LOG.info("*************doEvent05 End*************", "");
			return ERROR;
		}
		// Business logic
		Pab0021BL logic = new Pab0021BL();
		// Get transaction
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		// Set value to pab0021Bean
		pab0021Bean = new Pab0021Bean();
		pab0021Bean.setItemCd(this.itemCd);
		// BaseDBBean
		pab0021Bean.setUpdatedUser(super.getUpdatedUser());
		pab0021Bean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		pab0021Bean.setTimeDifference(super.getTimeDifference());
		pab0021Bean.setExclusiveFg(Constant.EXCLUSIVE_FG);

		try {
			// Delete itemMst set delete_fg = 1
			logic.deleteItemMst(pab0021Bean);
			transactionManager.commit(transactionStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback Event05 *************", "");
		}

		LOG.info("*************doEvent05 End*************", "");
		return SUCCESS;
	}

	/**
	 * action download catalog pdf
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doEvent06() throws Exception {
		LOG.info("*************doEvent06 Start*************", "");
		// Get config bean
		ConfigBean configBean = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		// Check pdfName is empty
		if (StringUtil.isEmpty(this.pdfName)) {
			LOG.info("*************doEvent06 End*************", "");
			return ERROR;
		}
		try {
			// Get input file
			inputStream = new FileInputStream(new File(configBean.getPdfDir() + "\\" + this.pdfName));
			// Set contentType
			contentType = "application/pdf";
			// Set name for file download
			catalogPdfName = this.pdfName;
			LOG.info("*************doEvent06 Export file pdf*************", "");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("*************doEvent06 file not found*************", "");
			throw e;
		}
		LOG.info("*************doEvent06 End*************", "");
		return SUCCESS;
	}

	/**
	 * Action Check file catalog exist in folderDir
	 * 
	 * @param folderDir
	 * @param fileName
	 * @throws Exception
	 * @return
	 */
	public String checkFileExist() throws Exception {
		LOG.info("*************checkFileExist Start*************", "");
		// Check pdfName is empty
		if (StringUtil.isEmpty(this.pdfName)) {
			fileExist = false;
			LOG.info("*************checkFileExist End*************", "");
			return ERROR;
		}
		// Get config bean
		ConfigBean configBean = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		File fileCheck = new File(configBean.getPdfDir() + "\\" + this.pdfName);
		// If file exist
		if (fileCheck.exists()) {
			fileExist = true;
		} else {
			fileExist = false;
		}
		LOG.info("*************checkFileExist End*************", "");
		return SUCCESS;
	}

	/**
	 * @return the categoryCdList
	 */
	public List<String> getCategoryCdList() {
		return categoryCdList;
	}

	/**
	 * @param categoryCdList
	 *            the categoryCdList to set
	 */
	public void setCategoryCdList(List<String> categoryCdList) {
		this.categoryCdList = categoryCdList;
	}

	/**
	 * @return the itemCdList
	 */
	public List<String> getItemCdList() {
		return itemCdList;
	}

	/**
	 * @param itemCdList
	 *            the itemCdList to set
	 */
	public void setItemCdList(List<String> itemCdList) {
		this.itemCdList = itemCdList;
	}

	/**
	 * @return the pab0021Bean
	 */
	public Pab0021Bean getPab0021Bean() {
		return pab0021Bean;
	}

	/**
	 * @param pab0021Bean
	 *            the pab0021Bean to set
	 */
	public void setPab0021Bean(Pab0021Bean pab0021Bean) {
		this.pab0021Bean = pab0021Bean;
	}

	/**
	 * @return the uploadBean
	 */
	public UploadBean getUploadBean() {
		return uploadBean;
	}

	/**
	 * @param uploadBean
	 *            the uploadBean to set
	 */
	public void setUploadBean(UploadBean uploadBean) {
		this.uploadBean = uploadBean;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream
	 *            the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the catalogPdfName
	 */
	public String getCatalogPdfName() {
		return catalogPdfName;
	}

	/**
	 * @param catalogPdfName
	 *            the catalogPdfName to set
	 */
	public void setCatalogPdfName(String catalogPdfName) {
		this.catalogPdfName = catalogPdfName;
	}

	/**
	 * @return the imageBase64
	 */
	public String getImageBase64() {
		return imageBase64;
	}

	/**
	 * @param imageBase64
	 *            the imageBase64 to set
	 */
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	/**
	 * @return the checkInsert
	 */
	public boolean isCheckInsert() {
		return checkInsert;
	}

	/**
	 * @param checkInsert
	 *            the checkInsert to set
	 */
	public void setCheckInsert(boolean checkInsert) {
		this.checkInsert = checkInsert;
	}

	/**
	 * @return the fileExist
	 */
	public boolean isFileExist() {
		return fileExist;
	}

	/**
	 * @param fileExist
	 *            the fileExist to set
	 */
	public void setFileExist(boolean fileExist) {
		this.fileExist = fileExist;
	}

}
