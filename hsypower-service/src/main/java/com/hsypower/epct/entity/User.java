package com.hsypower.epct.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.hsypower.epct.BaseEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 6222832557692442272L;

	private String password;
	private String oldPwd;
	private String confirmPwd;
	private boolean activity;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the oldPwd
	 */
	@Transient
	public String getOldPwd() {
		return oldPwd;
	}

	/**
	 * @param oldPwd
	 *            the oldPwd to set
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	/**
	 * @return the confirmPwd
	 */
	@Transient
	public String getConfirmPwd() {
		return confirmPwd;
	}

	/**
	 * @param confirmPwd
	 *            the confirmPwd to set
	 */
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	/**
	 * @return the activity
	 */
	public boolean isActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(boolean activity) {
		this.activity = activity;
	}

}
