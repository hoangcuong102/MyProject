/*
 * CLASS   : BaseDBBean
 * PACKAGE : jp.co.cyms.base
 * VERSION : 1.0
 * HISTORY : 2014-08-01　LSC　LU CREATE
 */
package jp.co.cyms.base;

import java.util.Date;


/**
 * 
 * @author longnd updated local time = doGetStringCurrentDateWithFormat(format)
 *
 */
public abstract class BaseDBBean extends BaseBean {
	protected String updatedUser;
	protected Date updatedDtLocal;
	protected float timeDifference;
	protected String exclusiveFg;

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public Date getUpdatedDtLocal() {
		return updatedDtLocal;
	}

	public void setUpdatedDtLocal(Date updatedDtLocal) {
		this.updatedDtLocal = updatedDtLocal;
	}

	public float getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(float timeDifference) {
		this.timeDifference = timeDifference;
	}

	public String getExclusiveFg() {
		return exclusiveFg;
	}

	public void setExclusiveFg(String exclusiveFg) {
		this.exclusiveFg = exclusiveFg;
	}

}
