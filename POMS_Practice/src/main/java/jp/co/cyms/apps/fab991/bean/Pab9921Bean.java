package jp.co.cyms.apps.fab991.bean;

import jp.co.cyms.base.BaseDBBean;

public class Pab9921Bean extends BaseDBBean {
	private String category_cd;
	private String category_name;
	private String item_cd;
	private String item_name;
	private String link_to_image;
	private String image_name;
	private String image_size;
	private String link_to_pdf;
	private String pdf_name;
	private String pdf_size;
	private String remark;
	
	public Pab9921Bean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pab9921Bean(String category_cd, String category_name, String item_cd, String item_name, String link_to_image,
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

	
	
}
