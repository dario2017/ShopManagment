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
	public void updateProd(Product product) {
		if (product == null) {
			throw new IllegalStateException("Product is null. You can't update null product");
		}		
//	find this product on product list. remove old and put new one on the same position in list
		Product found = findById(product.getProductId());
		if (found == null) {
			throw new IllegalArgumentException("There is no product with such Id");
		} else {
			int prodListIndex = productList.indexOf(found);
			productList.remove(found);
			productList.add(prodListIndex, product);
		}
	}
	
	@Override
	public void removeProd(Product product) {
		productList.remove(product);
	
	}
	
	@Override
	public Product findById(int productId) {
		Product product = null;
		for (Product eachProduct: productList) {
			if (eachProduct.getProductId() == productId) {
				product = eachProduct;
			}
		}
		return product;
	}

	@Override
	public List<Product> getProductList() {
		return productList;
	}

}
