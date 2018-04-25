package com.cschool.shop.managment.server.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cschool.shop.managment.server.dao.ListProductDao;
import com.cschool.shop.managment.server.dao.ListUserDao;
import com.cschool.shop.managment.server.dao.ListCategoryDao;
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
		
		CategoryService categoryService = new CategoryServiceImpl(new ListCategoryDao());
		event.getServletContext().setAttribute(CATEGORY_SERVICE_ATTRIBUTE, categoryService);
		
	
		User user1 = new User(1, "admin", "admin".toCharArray());
		userService.add(user1);
		
		Category category1 = new Category("Jednoslady");
		Category category2 = new Category("Dwuslady");
		Set<Category> categorySet1 = new HashSet<>();
		Set<Category> categorySet2 = new HashSet<>();
		categorySet1.add(category1);
		categorySet2.add(category2);
		
		System.out.println("categoria 1: " + category1.toString());
		categoryService.addCategory(category1);
		categoryService.addCategory(category2);
		System.out.println("category service list: " + categoryService.toString());
		
		
		Product product1 = new Product();
		product1.setAvailable(true);
		product1.setImage("brak linku");
		product1.setName("Rower");
		product1.setPrice(1400);
		product1.setCategorySet(categorySet1);
		
		Product product2 = new Product();
		product2.setAvailable(true);
		product2.setImage("brak linku");
		product2.setName("Sanki");
		product2.setPrice(2800);
		product2.setCategorySet(categorySet2);
		
		Product product3 = new Product();
		product3.setAvailable(true);
		product3.setImage("brak linku");
		product3.setName("Hulajnoga");
		product3.setPrice(550);
		product3.setCategorySet(categorySet1);
		
		productService.add(product1);
		productService.add(product2);
		productService.add(product3);
		System.out.println("Lista PRODUKTOW!!!: " + productService.getProductList().toString());
	}
	
}
