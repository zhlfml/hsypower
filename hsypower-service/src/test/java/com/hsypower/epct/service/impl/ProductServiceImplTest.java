package com.hsypower.epct.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.hsypower.epct.AbstractDBUnitSpringContextTests;
import com.hsypower.epct.entity.Product;
import com.hsypower.epct.entity.User;
import com.hsypower.epct.service.IProductService;
import com.hsypower.epct.utils.DateTimeUtils;

@DatabaseSetup("ProductServiceImplTest.xml")
public class ProductServiceImplTest extends AbstractDBUnitSpringContextTests {

	private User user;

	@Autowired
	private IProductService productService;

	@Before
	public void setUp() {
		user = new User();
		user.setName("admin");
	}

	@Test
	public void testCreateNewProduct() {
		Product product = new Product();
		product.setName("GCK、GCL型低压抽出式成套开关设备");
		product.setIcon("upload/product/icon/xyz.jpg");
		product.setIntroduce("<img src=\"http://localhost:8080/hsypower/upload/product/introduce/abc.jpg\" />");

		productService.createNewProduct(product, user);
		assertNotNull(product.getId());
		assertEquals(user.getName(), product.getCreatedBy());
		assertNotNull(product.getCreateOn());
	}

	@Test
	public void testUpdateProduct() {
		Product product = productService.getProductById(1);
		product.setName("GCK、GCL型低压抽出式成套开关设备2");

		product = productService.updateProduct(product, user);
		assertEquals("GCK、GCL型低压抽出式成套开关设备2", product.getName());
		assertEquals(user.getName(), product.getModifiedBy());
		assertNotNull(product.getModifyOn());
	}

	@Test
	public void testGetProductById() {
		Product product = productService.getProductById(1);

		assertEquals("GCK、GCL型低压抽出式成套开关设备", product.getName());
		assertEquals("upload/product/icon/xyz.jpg", product.getIcon());
		assertEquals("<img src='http://localhost:8080/hsypower/upload/product/introduce/abc.jpg' />", product.getIntroduce());
		assertEquals("admin", product.getCreatedBy());
		assertEquals("2013-06-29 13:58:38",
				DateTimeUtils.format(product.getCreateOn()));
		assertNull(product.getModifiedBy());
		assertNull(product.getModifyOn());
	}

	@Test
	public void testGetPrevProductById() {
		Product product = productService.getPrevProductById(1, 1);
		assertNull(product);

		Product product2 = productService.getPrevProductById(1, 8);
		assertEquals(7, product2.getId());
	}

	@Test
	public void testGetNextProductById() {
		Product product = productService.getNextProductById(1, 8);
		assertNull(product);

		Product product2 = productService.getNextProductById(1, 1);
		assertEquals(2, product2.getId());
	}

	@Test
	public void testFindProductsByCategoryId() {
		Iterator<Product> products = productService.findProductsByCategoryId(1);
		int total = 0;
		while (products.hasNext()) {
			total++;
			products.next();
		}

		assertEquals(8, total);
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testFindProductsByCategoryIdPage() {
		Page<Product> products = productService.findProductsByCategoryId(
				1, 1, 3);

		Iterator<Product> it = products.iterator();
		Product product = it.next();
		assertEquals(4, product.getId());
		Product product2 = it.next();
		assertEquals(5, product2.getId());
		Product product3 = it.next();
		assertEquals(6, product3.getId());
		Product product4 = it.next();
		assertNull(product4);
	}
	
	@Test
	public void testFindAll() {
		Iterator<Product> products = productService.findAll();
		int total = 0;
		while (products.hasNext()) {
			total++;
			products.next();
		}

		assertEquals(9, total);
	}

}
