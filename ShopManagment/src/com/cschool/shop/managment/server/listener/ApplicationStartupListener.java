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
		
		Category teleskopy = new Category(1, "Teleskopy");
		Category lornetki = new Category(2, "Lornetki");
		Category refraktory = new Category(3, "Refraktory");
		Category newtona = new Category(4, "Newtona");
		Category turystyczne = new Category(5, "Turystyczne");
		Category wojskowe = new Category(6, "Wojskowe");
		
		Set<Category> teleskopRefraktor = new HashSet<>();
		Set<Category> teleskopNewton = new HashSet<>();
		Set<Category> lornetkaWojskowa = new HashSet<>();
		Set<Category> lornetkaTurystyczna = new HashSet<>();
		
		teleskopRefraktor.add(teleskopy);
		teleskopRefraktor.add(refraktory);
		teleskopNewton.add(teleskopy);
		teleskopNewton.add(newtona);
		lornetkaWojskowa.add(lornetki);
		lornetkaWojskowa.add(wojskowe);
		lornetkaTurystyczna.add(lornetki);
		lornetkaTurystyczna.add(turystyczne);
		
		categoryService.addCategory(teleskopy);
		categoryService.addCategory(lornetki);
		categoryService.addCategory(refraktory);
		categoryService.addCategory(newtona);
		categoryService.addCategory(turystyczne);
		categoryService.addCategory(wojskowe);
		
		Product product1 = new Product(1,"Teleskop Sky-Watcher Synta R-70/900", teleskopRefraktor, 625, true, "https://teleskopy.pl/images/150_teleskop_sk709eq1.jpg");		
		Product product2 = new Product(2, "Teleskop Levenhuk SkyMatic 135 GT Newton", teleskopNewton, 1949, true, "https://teleskopy.pl/images/150_levenhuk_n135_gt_skymatic.jpg");	
		Product product3 = new Product(3, "Lornetka FUJINON 10x50 FMTR-SX", lornetkaWojskowa, 3299, true, "https://teleskopy.pl/images/150_fujinon_10x50_fmtr-sx.jpg");
		Product product4 = new Product(4, "Lornetka Delta Optical 10x50 Entry, lornetkaTurystyczna", lornetkaTurystyczna, 259, false, "https://teleskopy.pl/images/150_delta_optical_entry_10x50.jpg");
			
		productService.add(product1);
		productService.add(product2);
		productService.add(product3);
		productService.add(product4);
	}	
}
