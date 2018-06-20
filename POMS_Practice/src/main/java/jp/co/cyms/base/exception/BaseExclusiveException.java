/*
 * CLASS   : BaseExclusiveException
 * PACKAGE : jp.co.cyms.base.exception
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.base.exception;

/**
 * BaseExclusiveException
 * 
 * @author LSC LU
 * @since 2014/08/01
 */
@SuppressWarnings("serial")
public class BaseExclusiveException extends Exception {

	/**
	 * Program ID
	 */
	private String programId;

	/**
	 * BaseExclusiveException
	 * 
	 * @param programId String
	 * @author KCCS XXXXX
	 * @since 2014/12/25
	 */
	public BaseExclusiveException(String programId) {
		super();
		this.programId = programId;
	}

	/**
	 * @return programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @param programId programId
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
}
