package com.hsypower.epct.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Audit implements Serializable {

	private static final long serialVersionUID = -3353274186425143399L;

	private long id;
	private String localIp;
	private String remoteIp;
	private String referer;
	private String userAgent;
	private Date accessDate;
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the localIp
	 */
	public String getLocalIp() {
		return localIp;
	}

	/**
	 * @param localIp the localIp to set
	 */
	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	/**
	 * @return the remoteIp
	 */
	public String getRemoteIp() {
		return remoteIp;
	}

	/**
	 * @param remoteIp the remoteIp to set
	 */
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	/**
	 * @return the referer
	 */
	public String getReferer() {
		return referer;
	}
	
	/**
	 * @param referer the referer to set
	 */
	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}
	
	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	/**
	 * @return the accessDate
	 */
	public Date getAccessDate() {
		return accessDate;
	}
	
	/**
	 * @param accessDate the accessDate to set
	 */
	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
	
}
