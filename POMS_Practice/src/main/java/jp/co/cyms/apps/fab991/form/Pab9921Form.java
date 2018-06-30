package jp.co.cyms.apps.fab991.form;

import java.io.File;

import jp.co.cyms.base.BaseAction;

public class Pab9921Form extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2218273767392042469L;
	protected String category_cd;
	protected String category_name;
	protected String item_cd;
	protected String item_name;
	protected String link_to_image;
	protected String image_name;
	protected String image_size;
	protected String link_to_pdf;
	protected String pdf_name;
	protected String pdf_size;
	protected String remark;
	
	protected File fileImage;
	
	protected File filepdf;
	
	
	



	public Pab9921Form() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Pab9921Form(String category_cd, String category_name, String item_cd, String item_name, String link_to_image,
			String image_name, String image_size, String link_to_pdf, String pdf_name, String pdf_size, String remark) {
		
		this.category_cd = category_cd;
		this.category_name = category_name;
		this.item_cd = item_cd;
		this.item_name = item_name;
		this.link_to_image = link_to_image;
		this.image_name = image_name;
		this.image_size = image_size;
		this.link_to_pdf = link_to_pdf;
		this.pdf_name = pdf_name;
		this.pdf_size = pdf_size;
		this.remark = remark;
	}



	public String getCategory_cd() {
		return category_cd;
	}
	public void setCategory_cd(String category_cd) {
		this.category_cd = category_cd;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(String item_cd) {
		this.item_cd = item_cd;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getLink_to_image() {
		return link_to_image;
	}
	public void setLink_to_image(String link_to_image) {
		this.link_to_image = link_to_image;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_size() {
		return image_size;
	}
	public void setImage_size(String image_size) {
		this.image_size = image_size;
	}
	public String getLink_to_pdf() {
		return link_to_pdf;
	}
	public void setLink_to_pdf(String link_to_pdf) {
		this.link_to_pdf = link_to_pdf;
	}
	public String getPdf_name() {
		return pdf_name;
	}
	public void setPdf_name(String pdf_name) {
		this.pdf_name = pdf_name;
	}
	public String getPdf_size() {
		return pdf_size;
	}
	public void setPdf_size(String pdf_size) {
		this.pdf_size = pdf_size;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public File getFileImage() {
		return fileImage;
	}



	public void setFileImage(File fileImage) {
		this.fileImage = fileImage;
	}



	public File getFilepdf() {
		return filepdf;
	}



	public void setFilepdf(File filepdf) {
		this.filepdf = filepdf;
	}
	

}
