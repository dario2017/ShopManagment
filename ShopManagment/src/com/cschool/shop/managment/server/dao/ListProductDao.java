package com.cschool.shop.managment.server.dao;

import java.util.ArrayList;
import java.util.List;

import com.cschool.shop.managment.shared.model.Product;

public class ListProductDao implements ProductDao {
	
	public List<Product> productList = new ArrayList<>();
	
	@Override
	public void add(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("You cannot add null product");
		}
		productList.add(product);
	}
	
	@Override
	public void removeProd(Product product) {
		productList.remove(product);
	
	}

	@Override
	public List<Product> getProductList() {
		return productList;
	}

}
