package com.cschool.shop.managment.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cschool.shop.managment.shared.model.Category;

public class ListCategoryDao implements CategoryDao {
	
	private List<Category> categoryList;
	
	
	public ListCategoryDao() {
		categoryList = new ArrayList<>();
	}
	@Override
	public List<Category> getCategoryList() {
		return categoryList;
	}

	@Override
	public void addCategory(Category category) {
		if (category == null) {
			throw new IllegalArgumentException("Cannot add null category");
		}
		categoryList.add(category);
	}

}
