package com.cschool.shop.managment.server.service;

import java.util.List;
import java.util.Set;

import com.cschool.shop.managment.server.dao.CategoryDao;
import com.cschool.shop.managment.shared.model.Category;

public class CategoryServiceImpl implements CategoryService {

	private final CategoryDao categoryDao;
	
	public CategoryServiceImpl(CategoryDao categoryDao) {
		if (categoryDao == null) {
			throw new IllegalArgumentException("Cannot add null categoryDao");
		}
		this.categoryDao = categoryDao;
	}
	
	@Override
	public void addCategory(Category category) {
		if (category == null) {
			throw new IllegalArgumentException("Cannot add null category");
		}
		this.categoryDao.addCategory(category);
		
	}

	@Override
	public void removeCategory(Category category) {
		this.categoryDao.removeCategory(category);
	}
	@Override
	public Set<Category> getCategorySet() {
		return this.categoryDao.getCategorySet();
	}

}
