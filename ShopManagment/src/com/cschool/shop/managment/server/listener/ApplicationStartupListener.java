package com.cschool.shop.managment.server.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cschool.shop.managment.server.dao.ListProductDao;
import com.cschool.shop.managment.server.dao.ListUserDao;
import com.cschool.shop.managment.server.dao.SetCategoryDao;
import com.cschool.shop.managment.shared.model.Category;
import com.cschool.shop.managment.shared.model.Product;
import com.cschool.shop.managment.server.model.User;
import com.cschool.shop.managment.server.service.CategoryService;
import com.cschool.shop.managment.server.service.CategoryServiceImpl;
import com.cschool.shop.managment.server.service.ProductService;
import com.cschool.shop.managment.server.service.ProductServiceImpl;
import com.cschool.shop.managment.server.service.UserService;
import com.cschool.shop.managment.server.service.UserServiceImpl;

public class ApplicationStartupListener implements ServletContextListener {

	public static final String USER_SERVICE_ATTRIBUTE = "userService";
	public static final String PRODUCT_SERVICE_ATTRIBUTE = "productService";
	public static final String CATEGORY_SERVICE_ATTRIBUTE = "categoryService";
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		UserService userService = new UserServiceImpl(new ListUserDao());
		event.getServletContext().setAttribute(USER_SERVICE_ATTRIBUTE, userService);
		
		ProductService productService = new ProductServiceImpl(new ListProductDao());
		event.getServletContext().setAttribute(PRODUCT_SERVICE_ATTRIBUTE, productService);
		
		CategoryService categoryService = new CategoryServiceImpl(new SetCategoryDao());
		event.getServletContext().setAttribute(CATEGORY_SERVICE_ATTRIBUTE, categoryService);
		
	
		User user1 = new User(1, "admin", "admin".toCharArray());
		userService.add(user1);
		
		Category category1 = new Category(1, "Pojazdy");
		Category category2 = new Category(2, "Obuwie");
		Set<Category> categorySet1 = new HashSet<>();
		Set<Category> categorySet2 = new HashSet<>();
		categorySet1.add(category1);
		categorySet2.add(category2);
		
		System.out.println("categoria 1: " + category1.toString());
		categoryService.addCategory(category1);
		categoryService.addCategory(category2);
		System.out.println("category service list: " + categoryService.toString());
		
		
		Product product1 = new Product(1,"Rower GT Avalanche 1.0", categorySet1, 1400, true, "brak");		
		Product product2 = new Product(2, "Sanki", categorySet1, 2800, true, "brak");	
		Product product3 = new Product(3, "Adidasy", categorySet2, 550, true, "brak");
		
		productService.add(product1);
		productService.add(product2);
		productService.add(product3);
		System.out.println("Lista PRODUKTOW!!!: " + productService.getProductList().toString());
	}
	
}
