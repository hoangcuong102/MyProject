package jp.co.cyms.apps.fab991.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sun.star.io.IOException;

import jp.co.cyms.apps.fab991.bean.Pab9921Bean;
import jp.co.cyms.apps.fab991.bl.Pab9921BL;
import jp.co.cyms.apps.fab991.form.Pab9921Form;
import jp.co.cyms.common.Constant;
import jp.co.cyms.common.StringUtil;
import jp.co.cyms.common.bean.ConfigBean;

public class Pab9921Action extends Pab9921Form {

	/**
	 * 
	 */
	private List<String> listCategory_cd;
	private List<String> listItemCd;
	private Pab9921Bean pab9921Bean;
	private String imageBase64;
	private boolean checkExisted;
	private FileInputStream inputStream;
	private String contentType;
	private String catalogPdfName;
	
	
	
	private static final long serialVersionUID = -544124277598148864L;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		LOG.info("*************execute Start SAB9921*************", "");
		Pab9921BL logic =  new Pab9921BL();
		setListCategory_cd(logic.getListCategory_CD());
		LOG.info("*************execute End SAB9921*************", "");
		return SUCCESS;
	}
	
	/*
	 * Get item_cd allowing category_cd*/
	public String doEvent01() throws Exception{
		LOG.info("*********** execute Start doEvent01 *****************");
		pab9921Bean = new Pab9921Bean();
		Pab9921BL logic =  new Pab9921BL();
		
		pab9921Bean.setCategory_cd(category_cd);
		pab9921Bean.setCategory_name(logic.getCategoryName(pab9921Bean));
		listItemCd = logic.getListItem_CD(pab9921Bean);
		LOG.info("*********** execute End doEvent01 *****************");
		return SUCCESS ;
	}
	
	/*
	 * Get ItemName allowing itemCode
	 * */
	public String doEvent02() throws Exception{
		LOG.info("*********** execute Start doEvent02 *****************");
		Pab9921Bean temp = new Pab9921Bean();
		Pab9921BL logic =  new Pab9921BL();
		ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		temp.setItem_cd(item_cd);
		temp.setCategory_cd(category_cd);
		//pab9921Bean.setItem_name(logic.getItemNameByItemCode(pab9921Bean));
		pab9921Bean = logic.getItemByItemCD_CategoryCD(temp);
		if(pab9921Bean == null) {
			pab9921Bean = new Pab9921Bean();
			
		}
		pab9921Bean.setItem_name(logic.getItemNameByItemCDCateCD(temp));	
		imageBase64 = logic.encodeImageToBase64(config.getImageDir(), pab9921Bean.getImage_name());
			
		
		LOG.info("*********** execute End doEvent02 *****************");
		return SUCCESS;
	}
	
	/*
	 * Delete Item_MST
	 * */
	public String deleteItemMST() throws Exception{
		LOG.info("*********** execute Start deleteItemMST *****************");
		if(this.item_cd == null || this.item_cd.length() == 0) {
			LOG.info("*********** execute End deleteItemMST *****************");
			return ERROR;
		}
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		pab9921Bean = new Pab9921Bean();
		Pab9921BL logic =  new Pab9921BL();
		
		pab9921Bean.setItem_cd(this.item_cd);
		pab9921Bean.setUpdatedUser(this.getUpdatedUser());
		pab9921Bean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		pab9921Bean.setTimeDifference(super.getTimeDifference());
		pab9921Bean.setExclusiveFg(Constant.EXCLUSIVE_FG);
		
		try {
			logic.deleteItem_MST(pab9921Bean);
			transactionManager.commit(transactionStatus);
		}catch(Exception e) {
			transactionManager.rollback(transactionStatus);
			e.printStackTrace();
			LOG.info("*************Do rollback Event05 *************", "");
		}
		
		
		LOG.info("*********** execute End deleteItemMST *****************");
		return SUCCESS;
	}
	
//		check pdf file is existed or not
	public String isExistedPdf() throws IOException {
		LOG.info("*********** execute Start check exist file *****************");
		if(this.pdf_name == null || this.pdf_name.trim().length() == 0) {
			checkExisted = false;
			LOG.info("*********** execute End check exist file *****************");
			return ERROR;
		}
		ConfigBean config = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		String path = config.getPdfDir()+"\\" + this.pdf_name;
		File f = new File(path);
		if(f.exists()) {
			checkExisted = true;
			
		}else {
			checkExisted = false;
		}
		LOG.info("*********** execute End check exist file *****************");
		return SUCCESS;
	}
	
//	Download File
	public String downloadpdf() throws Exception{
		LOG.info("*************Download Start*************", "");
		// Get config bean
		ConfigBean configBean = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		// Check pdfName is empty
		if (StringUtil.isEmpty(this.pdf_name)) {
			LOG.info("*************Download End*************", "");
			return ERROR;
		}
		try {
			// Get input file
			setInputStream(new FileInputStream(new File(configBean.getPdfDir() + "\\" + this.pdf_name)));
			// Set contentType
			setContentType("application/pdf");
			// Set name for file download
			setCatalogPdfName(this.pdf_name);
			LOG.info("*************Download Export file pdf*************", "");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("*************Download file not found*************", "");
			throw e;
		}
		LOG.info("*************Download End*************", "");
		return SUCCESS;
	}

//	Update
	public String update() throws Exception{
		LOG.info("*************Update Start*************", "");
		if(this.category_cd == null || this.category_cd.trim().length() == 0 || this.item_cd == null || this.item_cd.trim().length() == 0) {
			LOG.info("*************Update End*************", "");
			return ERROR;
		}
		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		ConfigBean configBean = (ConfigBean) session.get(Constant.SESSION_KEY_CONFIG_INFO);
		pab9921Bean = new Pab9921Bean();
		Pab9921BL logic = new Pab9921BL();
		if(fileImage != null) {
			if(logic.uploadFile(this.fileImage, image_name, configBean.getImageDir())) {
				pab9921Bean.setLink_to_image(this.link_to_image);
				pab9921Bean.setImage_name(catalogPdfName);
				pab9921Bean.setImage_size(this.image_size);
			}
		}
		if(filepdf != null) {
			if(logic.uploadFile(this.filepdf, pdf_name, configBean.getPdfDir())) {
				pab9921Bean.setLink_to_pdf(this.link_to_pdf);
				pab9921Bean.setPdf_name(this.pdf_name);
				pab9921Bean.setPdf_size(this.pdf_size);
			}
		}
		pab9921Bean.setCategory_cd(this.category_cd);
		pab9921Bean.setItem_cd(this.item_cd);
		pab9921Bean.setRemark(this.remark);
		pab9921Bean.setUpdatedUser(super.getUpdatedUser());
		pab9921Bean.setUpdatedDtLocal(super.getUpdatedDtLocal());
		pab9921Bean.setTimeDifference(super.getTimeDifference());
		pab9921Bean.setExclusiveFg(super.exclusiveFg);
		
		boolean checkInsert = false;
		if(logic.getItemByItemCD_CategoryCD(pab9921Bean) == null) {
			checkInsert = true;
		}
		
		if(checkInsert) {
			LOG.info("*************Insert new record Start*************", "");
			if(logic.insertItem_Dtl_Mst(pab9921Bean)<=0) {
				LOG.info("*************Insert new record End*************", "");
				transactionManager.rollback(transactionStatus);
				return ERROR;
			}
			transactionManager.commit(transactionStatus);
			LOG.info("*************Insert new record End*************", "");
		}else {
			if(logic.updateItem_Dtl_Mst(pab9921Bean) <= 0) {
				LOG.info("*************Update End*************", "");
				transactionManager.rollback(transactionStatus);
				return ERROR;
			}
			transactionManager.commit(transactionStatus);
		}
		
		LOG.info("*************Update End*************", "");
		return SUCCESS;
	}
	
	public Pab9921Bean getPab9921Bean() {
		return pab9921Bean;
	}

	public void setPab9921Bean(Pab9921Bean pab9921Bean) {
		this.pab9921Bean = pab9921Bean;
	}

	public List<String> getListCategory_cd() {
		return listCategory_cd;
	}

	public void setListCategory_cd(List<String> listCategory_cd) {
		this.listCategory_cd = listCategory_cd;
	}

	public List<String> getListItemCd() {
		return listItemCd;
	}

	public void setListItemCd(List<String> listItemCd) {
		this.listItemCd = listItemCd;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public boolean isCheckExisted() {
		return checkExisted;
	}

	public void setCheckExisted(boolean checkExisted) {
		this.checkExisted = checkExisted;
	}

	public FileInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(FileInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getCatalogPdfName() {
		return catalogPdfName;
	}

	public void setCatalogPdfName(String catalogPdfName) {
		this.catalogPdfName = catalogPdfName;
	}

}
