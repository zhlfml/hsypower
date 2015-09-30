package com.hsypower.epct.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.hsypower.epct.BaseEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class News extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 6222832557692489272L;
	
	private String content;
	private boolean publish;
	
	/**
	 * @return the content
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public String getContent() {
		return content;
	}
	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @return the publish
	 */
	public boolean isPublish() {
		return publish;
	}
	
	/**
	 * @param publish the publish to set
	 */
	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	
}
