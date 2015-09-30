package com.hsypower.epct.service.impl;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsypower.epct.dao.IProductCategoryDAO;
import com.hsypower.epct.entity.ProductCategory;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

	@Autowired
	private IProductCategoryDAO productCategoryDAO;

	@Override
	@Transactional
	public ProductCategory createNewProductCategory(
			ProductCategory productCategory, User user) {
		productCategory.setCreatedBy(user.getName());
		productCategory.setCreateOn(new Date());

		return productCategoryDAO.save(productCategory);
	}

	@Override
	@Transactional
	public ProductCategory updateProductCategory(
			ProductCategory productCategory, User user) {
		productCategory.setModifiedBy(user.getName());
		productCategory.setModifyOn(new Date());

		return productCategoryDAO.save(productCategory);
	}
	
	@Override
	public ProductCategory getProductCategoryById(long productCategoryId) {
		return productCategoryDAO.findOne(productCategoryId);
	}

	@Override
	public Iterator<ProductCategory> findAll() {
		return productCategoryDAO.findAll().iterator();
	}

}
