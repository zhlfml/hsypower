package com.hsypower.epct.service;

import java.util.Iterator;

import com.hsypower.epct.entity.ProductCategory;
import com.hsypower.epct.entity.User;

public interface IProductCategoryService {
	
	ProductCategory createNewProductCategory(ProductCategory productCategory, User user);
	
	ProductCategory updateProductCategory(ProductCategory productCategory, User user);
	
	ProductCategory getProductCategoryById(long productCategoryId);

	Iterator<ProductCategory> findAll();
}
