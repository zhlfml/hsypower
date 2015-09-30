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
public class Company extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 5373363910430036403L;
	
	private String address;
	private String telephone;
	private String tax;
	private String email;
	private String zipcode;
	private String website;
	private String saleTel;
	private String techServiceTel;
	private String keywords;
	private String description;
	private String introduce;
	private String customerService;
	private String salesNetwork;
	private String perfermance;
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * @return the tax
	 */
	public String getTax() {
		return tax;
	}
	
	/**
	 * @param tax the tax to set
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	/**
	 * @return the saleTel
	 */
	public String getSaleTel() {
		return saleTel;
	}
	
	/**
	 * @param saleTel the saleTel to set
	 */
	public void setSaleTel(String saleTel) {
		this.saleTel = saleTel;
	}
	
	/**
	 * @return the techServiceTel
	 */
	public String getTechServiceTel() {
		return techServiceTel;
	}
	
	/**
	 * @param techServiceTel the techServiceTel to set
	 */
	public void setTechServiceTel(String techServiceTel) {
		this.techServiceTel = techServiceTel;
	}
	
	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the introduce
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public String getIntroduce() {
		return introduce;
	}
	
	/**
	 * @param introduce the introduce to set
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	/**
	 * @return the customerService
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public String getCustomerService() {
		return customerService;
	}

	/**
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(String customerService) {
		this.customerService = customerService;
	}

	/**
	 * @return the salesNetwork
	 */
	public String getSalesNetwork() {
		return salesNetwork;
	}

	/**
	 * @param salesNetwork the salesNetwork to set
	 */
	public void setSalesNetwork(String salesNetwork) {
		this.salesNetwork = salesNetwork;
	}

	/**
	 * @return the perfermance
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public String getPerfermance() {
		return perfermance;
	}

	/**
	 * @param perfermance the perfermance to set
	 */
	public void setPerfermance(String perfermance) {
		this.perfermance = perfermance;
	}
	
}
