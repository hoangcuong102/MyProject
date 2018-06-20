package jp.co.cyms.common.bean;

import java.io.Serializable;

import jp.co.cyms.base.BaseDBBean;

/**
 * ※ *_DIR stores the directory location of each field ※ *_LIMIT stores the file
 * size limit of each field type. screen is accessed from main menu or from
 * [Progress] screen.
 * 
 * @author longnd
 *
 */

public class ConfigBean extends BaseDBBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imageDir;
	private String imageLimit;
	private String pdfDir;
	private String pdfLimit;
	private String quoteDir;
	private String doDir;
	private String resultDir;
	private String redirect;
	private String serverLocation;
	private String libreOffice;
	protected String excelTemp;
	protected String excelOutFiles;
	protected String excelNamePad00110;
	protected String excelNamePad00111;
	protected String excelNamePad00112;
	protected String excelNamePad00113;
	protected String excelNameSac0021;
	protected String excelNameSac0024;
	protected String address;
	protected String passwordAddress;
	protected String hostAddress;
	protected String portAddress;

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public String getImageLimit() {
		return imageLimit;
	}

	public void setImageLimit(String imageLimit) {
		this.imageLimit = imageLimit;
	}

	public String getPdfDir() {
		return pdfDir;
	}

	public void setPdfDir(String pdfDir) {
		this.pdfDir = pdfDir;
	}

	public String getPdfLimit() {
		return pdfLimit;
	}

	public void setPdfLimit(String pdfLimit) {
		this.pdfLimit = pdfLimit;
	}

	public String getQuoteDir() {
		return quoteDir;
	}

	public void setQuoteDir(String quoteDir) {
		this.quoteDir = quoteDir;
	}

	public String getDoDir() {
		return doDir;
	}

	public void setDoDir(String doDir) {
		this.doDir = doDir;
	}

	public String getResultDir() {
		return resultDir;
	}

	public void setResultDir(String resultDir) {
		this.resultDir = resultDir;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getServerLocation() {
		return serverLocation;
	}

	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
	}

	public String getLibreOffice() {
		return libreOffice;
	}

	public void setLibreOffice(String libreOffice) {
		this.libreOffice = libreOffice;
	}

	public String getExcelTemp() {
		return excelTemp;
	}

	public void setExcelTemp(String excelTemp) {
		this.excelTemp = excelTemp;
	}

	public String getExcelOutFiles() {
		return excelOutFiles;
	}

	public void setExcelOutFiles(String excelOutFiles) {
		this.excelOutFiles = excelOutFiles;
	}

	public String getExcelNamePad00110() {
		return excelNamePad00110;
	}

	public void setExcelNamePad00110(String excelNamePad00110) {
		this.excelNamePad00110 = excelNamePad00110;
	}

	public String getExcelNamePad00111() {
		return excelNamePad00111;
	}

	public void setExcelNamePad00111(String excelNamePad00111) {
		this.excelNamePad00111 = excelNamePad00111;
	}

	public String getExcelNameSac0021() {
		return excelNameSac0021;
	}

	public void setExcelNameSac0021(String excelNameSac0021) {
		this.excelNameSac0021 = excelNameSac0021;
	}

	public String getExcelNameSac0024() {
		return excelNameSac0024;
	}

	public void setExcelNameSac0024(String excelNameSac0024) {
		this.excelNameSac0024 = excelNameSac0024;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPasswordAddress() {
		return passwordAddress;
	}

	public void setPasswordAddress(String passwordAddress) {
		this.passwordAddress = passwordAddress;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getPortAddress() {
		return portAddress;
	}

	public void setPortAddress(String portAddress) {
		this.portAddress = portAddress;
	}

	/**
	 * @return the excelNamePad00112
	 */
	public String getExcelNamePad00112() {
		return excelNamePad00112;
	}

	/**
	 * @param excelNamePad00112
	 *            the excelNamePad00112 to set
	 */
	public void setExcelNamePad00112(String excelNamePad00112) {
		this.excelNamePad00112 = excelNamePad00112;
	}

	/**
	 * @return the excelNamePad00113
	 */
	public String getExcelNamePad00113() {
		return excelNamePad00113;
	}

	/**
	 * @param excelNamePad00113
	 *            the excelNamePad00113 to set
	 */
	public void setExcelNamePad00113(String excelNamePad00113) {
		this.excelNamePad00113 = excelNamePad00113;
	}

}
