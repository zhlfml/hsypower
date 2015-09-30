package com.hsypower.epct.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hsypower.epct.BaseEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 3080876021523293654L;

	private String icon;
	private CommonsMultipartFile file;
	private String introduce;
	private ProductCategory productCategory;

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/**
	 * @return the file
	 */
	@Transient
	public CommonsMultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the introduce
	 */
	public String getIntroduce() {
		return introduce;
	}

	/**
	 * @param introduce
	 *            the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * @return the productCategory
	 */
	@ManyToOne
	@JoinColumn(name = "productCategoryId")
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory
	 *            the productCategory to set
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
