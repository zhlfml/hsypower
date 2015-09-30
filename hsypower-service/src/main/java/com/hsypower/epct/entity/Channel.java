package com.hsypower.epct.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.hsypower.epct.BaseEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Channel extends BaseEntity implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 62230567405711336L;
	
	private String path;
	private int sort;
	private boolean display;
	private boolean navigate;
	private boolean leftSide;
	private boolean bottom;
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
	 * @return the display
	 */
	public boolean isDisplay() {
		return display;
	}

	/**
	 * @param display the display to set
	 */
	public void setDisplay(boolean display) {
		this.display = display;
	}

	/**
	 * @return the navigate
	 */
	public boolean isNavigate() {
		return navigate;
	}

	/**
	 * @param navigate the navigate to set
	 */
	public void setNavigate(boolean navigate) {
		this.navigate = navigate;
	}

	/**
	 * @return the leftSide
	 */
	public boolean isLeftSide() {
		return leftSide;
	}

	/**
	 * @param leftSide the leftSide to set
	 */
	public void setLeftSide(boolean leftSide) {
		this.leftSide = leftSide;
	}

	/**
	 * @return the bottom
	 */
	public boolean isBottom() {
		return bottom;
	}

	/**
	 * @param bottom the bottom to set
	 */
	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}
	
}
