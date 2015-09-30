package com.hsypower.epct.service;

import java.util.Iterator;

import org.springframework.data.domain.Page;

import com.hsypower.epct.entity.Product;
import com.hsypower.epct.entity.User;

public interface IProductService {
	
	Product createNewProduct(Product product, User user);
	
	Product updateProduct(Product product, User user);

	Product getProductById(long id);
	
	Product getPrevProductById(long categoryId, long id);
	
	Product getNextProductById(long categoryId, long id);
	
	Iterator<Product> findProductsByCategoryId(long categoryId);
	
	Page<Product> findProductsByCategoryId(long categoryId, int page, int size);
	
	Iterator<Product> findAll();
}
