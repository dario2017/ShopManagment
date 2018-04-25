package com.cschool.shop.managment.server.dao;

import java.util.ArrayList;
import java.util.List;

import com.cschool.shop.managment.shared.model.Product;

public interface ProductDao {
	void add(Product product);
	void removeProd(Product product);
	List<Product> getProductList();
	
}
