package com.hsypower.epct.dao;

import org.springframework.data.repository.CrudRepository;

import com.hsypower.epct.entity.ProductCategory;

public interface IProductCategoryDAO extends CrudRepository<ProductCategory, Long> {

}
