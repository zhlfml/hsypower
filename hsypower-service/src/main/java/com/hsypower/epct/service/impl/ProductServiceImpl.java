package com.hsypower.epct.service.impl;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsypower.epct.dao.IProductCategoryDAO;
import com.hsypower.epct.dao.IProductDAO;
import com.hsypower.epct.entity.Product;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDAO productDAO;

	@Autowired
	private IProductCategoryDAO productCategoryDAO;

	@Override
	@Transactional
	public Product createNewProduct(Product product, User user) {
		product.setCreatedBy(user.getName());
		product.setCreateOn(new Date());

		return productDAO.save(product);
	}

	@Override
	@Transactional
	public Product updateProduct(Product product, User user) {
		product.setModifiedBy(user.getName());
		product.setModifyOn(new Date());

		return productDAO.save(product);
	}

	@Override
	public Product getProductById(long id) {
		return productDAO.findOne(id);
	}

	@Override
	public Product getPrevProductById(long categoryId, long id) {
		Product prev = null;
		Iterator<Product> products = this.findProductsByCategoryId(categoryId);
		while (products.hasNext()) {
			Product product = products.next();

			if (product.getId() == id) {
				break;
			}
			prev = product;
		}

		return prev;
	}

	@Override
	public Product getNextProductById(long categoryId, long id) {
		Product next = null;
		Iterator<Product> products = this.findProductsByCategoryId(categoryId);
		while (products.hasNext()) {
			Product product = products.next();

			if (product.getId() == id) {
				if (products.hasNext()) {
					next = products.next();
				}
			}
		}

		return next;
	}

	@Override
	public Iterator<Product> findProductsByCategoryId(long categoryId) {
		return this.findProductsByCategoryId(categoryId, 0, Integer.MAX_VALUE)
				.iterator();
	}

	@Override
	public Page<Product> findProductsByCategoryId(long categoryId, int page,
			int size) {
		return productDAO.findProductsByCategoryId(categoryId, new PageRequest(
				page, size, new Sort("id")));
	}

	@Override
	public Iterator<Product> findAll() {
		return productDAO.findAll().iterator();
	}

}
