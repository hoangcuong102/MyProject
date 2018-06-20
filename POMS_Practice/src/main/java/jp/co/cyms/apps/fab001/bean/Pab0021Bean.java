package jp.co.cyms.apps.fab001.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * Item Master 2 Pab0021Bean
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0021Bean extends BaseDBBean implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String categoryCd;
    private String categoryName;
    private String itemCd;
    private String itemName;
    private String linkToImage;
    private String imageName;
    private String imageSize;
    private String linkToPdf;
    private String pdfName;
    private String pdfSize;
    private String remark;
    
    public String getCategoryCd() {
        return categoryCd;
    }
    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getItemCd() {
        return itemCd;
    }
    public void setItemCd(String itemCd) {
        this.itemCd = itemCd;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getLinkToImage() {
        return linkToImage;
    }
    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public String getImageSize() {
        return imageSize;
    }
    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }
    public String getLinkToPdf() {
        return linkToPdf;
    }
    public void setLinkToPdf(String linkToPdf) {
        this.linkToPdf = linkToPdf;
    }
    public String getPdfName() {
        return pdfName;
    }
    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }
    public String getPdfSize() {
        return pdfSize;
    }
    public void setPdfSize(String pdfSize) {
        this.pdfSize = pdfSize;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
