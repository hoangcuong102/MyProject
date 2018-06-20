package jp.co.cyms.base.export;

import java.io.Serializable;

/**
 * Java bean for 'SysParam' entity (Change)
 * 
 * @author anhnt2
 * @since 2017/01/18
 *
 */
public class SysParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ouputDir;

	private String tempDir;

	private String nameFile0;

	private String nameFile1;

	private String nameFile2;

	private String nameFile3;

	private String nameFileTempSac0021;

	private String nameFileTempSac0024;

	public String getOuputDir() {
		return ouputDir;
	}

	public void setOuputDir(String ouputDir) {
		this.ouputDir = ouputDir;
	}

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	public String getNameFile0() {
		return nameFile0;
	}

	public void setNameFile0(String nameFile0) {
		this.nameFile0 = nameFile0;
	}

	public String getNameFile1() {
		return nameFile1;
	}

	public void setNameFile1(String nameFile1) {
		this.nameFile1 = nameFile1;
	}

	public String getNameFileTempSac0021() {
		return nameFileTempSac0021;
	}

	public void setNameFileTempSac0021(String nameFileTempSac0021) {
		this.nameFileTempSac0021 = nameFileTempSac0021;
	}

	public String getNameFileTempSac0024() {
		return nameFileTempSac0024;
	}

	public void setNameFileTempSac0024(String nameFileTempSac0024) {
		this.nameFileTempSac0024 = nameFileTempSac0024;
	}

	/**
	 * @return the nameFile2
	 */
	public String getNameFile2() {
		return nameFile2;
	}

	/**
	 * @param nameFile2
	 *            the nameFile2 to set
	 */
	public void setNameFile2(String nameFile2) {
		this.nameFile2 = nameFile2;
	}

	/**
	 * @return the nameFile3
	 */
	public String getNameFile3() {
		return nameFile3;
	}

	/**
	 * @param nameFile3
	 *            the nameFile3 to set
	 */
	public void setNameFile3(String nameFile3) {
		this.nameFile3 = nameFile3;
	}

}