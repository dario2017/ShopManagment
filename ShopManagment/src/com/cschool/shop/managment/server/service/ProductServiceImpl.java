package com.cschool.shop.managment.server.service;

import java.util.List;

import com.cschool.shop.managment.server.dao.ProductDao;
import com.cschool.shop.managment.shared.model.Product;

public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;

	public ProductServiceImpl(ProductDao productDao) {
		if (productDao == null) {
			throw new IllegalArgumentException("ProductDao cannot be null");
		}
		this.productDao = productDao;
		
	}
	
	@Override
	public void add(Product product) {
		this.productDao.add(product);
	}
	
	@Override
	public void removeProd(Product product) {
		this.productDao.removeProd(product);
	}

	@Override
	public List<Product> getProductList() {
		return this.productDao.getProductList();
	}
	

}
