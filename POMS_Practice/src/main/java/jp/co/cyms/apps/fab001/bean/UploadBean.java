package jp.co.cyms.apps.fab001.bean;

import java.io.Serializable;

/**
 * Item Master 2 UploadBean
 * 
 * @author binhvh
 * @since 2017/12/25
 */
public class UploadBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * type : image or pdf
     */
    private String type;
    private String linkToFile;
    private String fileName;
    private String fileSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLinkToFile() {
        return linkToFile;
    }

    public void setLinkToFile(String linkToFile) {
        this.linkToFile = linkToFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

}
