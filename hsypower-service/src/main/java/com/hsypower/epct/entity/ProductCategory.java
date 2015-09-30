package com.hsypower.epct.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.hsypower.epct.BaseEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProductCategory extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 9062538247631953690L;
	
	private List<Product> products;

	/**
	 * @return the products
	 */
	@OrderBy
	@OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
