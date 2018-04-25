package com.cschool.shop.managment.server.servlet.service;

import java.util.List;
import java.util.Set;

import com.cschool.shop.managment.client.service.CategoryServiceRPC;
import com.cschool.shop.managment.server.listener.ApplicationStartupListener;
import com.cschool.shop.managment.server.service.CategoryService;
import com.cschool.shop.managment.shared.model.Category;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CategoryServletRPCService extends RemoteServiceServlet implements CategoryServiceRPC {

	@Override
	public boolean addCategory(Category category) {
		getCategoryService().getCategoryList().add(category);
		return true;
	}
	
	@Override
	public List<Category> getAllCategories() {
		return getCategoryService().getCategoryList();
	}
	
	public CategoryService getCategoryService() {
		return (CategoryService) getServletContext().getAttribute(ApplicationStartupListener.CATEGORY_SERVICE_ATTRIBUTE);
	}

}
