package jp.co.cyms.apps.fab001.form;

import java.io.File;

import jp.co.cyms.base.BaseAction;

/**
 * Item Master 2 Pab0021Form
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class Pab0021Form extends BaseAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected String categoryCd;
    protected String categoryName;
    protected String itemCd;
    protected String itemName;
    protected String linkToImage;
    protected String imageName;
    protected String imageSize;
    protected String linkToPdf;
    protected String pdfName;
    protected String pdfSize;
    protected String remark;

    /** upload file image */
    protected File fileImage;
    protected String fileImageFileName;

    /** upload file pdf */
    protected File filePdf;
    protected String fileImageFilePdf;

    /** location upload */
    protected String dir;
    
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

    public File getFileImage() {
        return fileImage;
    }

    public void setFileImage(File fileImage) {
        this.fileImage = fileImage;
    }

    public String getFileImageFileName() {
        return fileImageFileName;
    }

    public void setFileImageFileName(String fileImageFileName) {
        this.fileImageFileName = fileImageFileName;
    }

    public File getFilePdf() {
        return filePdf;
    }

    public void setFilePdf(File filePdf) {
        this.filePdf = filePdf;
    }

    public String getFileImageFilePdf() {
        return fileImageFilePdf;
    }

    public void setFileImageFilePdf(String fileImageFilePdf) {
        this.fileImageFilePdf = fileImageFilePdf;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

}
