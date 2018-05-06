package com.cschool.shop.managment.server.dao;

import java.util.HashSet;
import java.util.Set;

import com.cschool.shop.managment.shared.model.Category;

public class SetCategoryDao implements CategoryDao {	
	private Set<Category> categorySet;
	
	public SetCategoryDao() {
		categorySet = new HashSet<>();
	}
	
	@Override
	public Set<Category> getCategorySet() {
		return categorySet;
	}

	@Override
	public void addCategory(Category category) {
		if (category == null) {
			throw new IllegalArgumentException("Cannot add null category");
		}
		categorySet.add(category);
	}
	
	@Override
	public void removeCategory(Category category) {
		if (category == null) {
			throw new IllegalArgumentException("Cannot remove null category");
		}
		categorySet.remove(category);
	}

}
