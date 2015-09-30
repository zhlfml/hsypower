package com.hsypower.epct.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hsypower.epct.entity.Product;

public interface IProductDAO extends CrudRepository<Product, Long> {

	@Query("select o from Product o where o.productCategory.id = ?1")
	Page<Product> findProductsByCategoryId(long categoryId, Pageable pageable);
}
