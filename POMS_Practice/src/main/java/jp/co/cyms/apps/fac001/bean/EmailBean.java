package jp.co.cyms.apps.fac001.bean;

import java.io.Serializable;
import java.util.List;

/**
 * EmailBean
 * 
 * @author binhvh
 * @since 2018/01/31
 */
public class EmailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> toEmailAddress;
	private String title;
	private String countryCd;
	private String companyCd;
	private String deptCd;
	private String userName;
	private String quoteMrc;
	private String quoteOtc;

	/**
	 * @return the toEmailAddress
	 */
	public List<String> getToEmailAddress() {
		return toEmailAddress;
	}

	/**
	 * @param toEmailAddress
	 *            the toEmailAddress to set
	 */
	public void setToEmailAddress(List<String> toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the countryCd
	 */
	public String getCountryCd() {
		return countryCd;
	}

	/**
	 * @param countryCd
	 *            the countryCd to set
	 */
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	/**
	 * @return the companyCd
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * @param companyCd
	 *            the companyCd to set
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * @return the deptCd
	 */
	public String getDeptCd() {
		return deptCd;
	}

	/**
	 * @param deptCd
	 *            the deptCd to set
	 */
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the quoteMrc
	 */
	public String getQuoteMrc() {
		return quoteMrc;
	}

	/**
	 * @param quoteMrc
	 *            the quoteMrc to set
	 */
	public void setQuoteMrc(String quoteMrc) {
		this.quoteMrc = quoteMrc;
	}

	/**
	 * @return the quoteOtc
	 */
	public String getQuoteOtc() {
		return quoteOtc;
	}

	/**
	 * @param quoteOtc
	 *            the quoteOtc to set
	 */
	public void setQuoteOtc(String quoteOtc) {
		this.quoteOtc = quoteOtc;
	}

}
