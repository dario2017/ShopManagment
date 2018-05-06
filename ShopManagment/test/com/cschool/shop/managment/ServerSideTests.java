package com.cschool.shop.managment;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import com.cschool.shop.managment.client.viewshop.StaticFields;
import com.cschool.shop.managment.server.dao.ListProductDao;
import com.cschool.shop.managment.server.dao.ListUserDao;
import com.cschool.shop.managment.server.dao.SetCategoryDao;
import com.cschool.shop.managment.server.dao.exception.UserAlreadyExistException;
import com.cschool.shop.managment.server.model.User;
import com.cschool.shop.managment.server.service.CategoryServiceImpl;
import com.cschool.shop.managment.server.service.ProductServiceImpl;
import com.cschool.shop.managment.server.service.UserServiceImpl;
import com.cschool.shop.managment.shared.model.Category;
import com.cschool.shop.managment.shared.model.Product;

public class ServerSideTests {
	List<Product> productsList;
	User user;
	ListUserDao listUserDaoObj;
	UserServiceImpl userImpl;
	
	Category cat;
	Set<Category> categorySet;
	Product prod1;
	ListProductDao listProductDao;
	ProductServiceImpl productImpl;
	
	CategoryServiceImpl categoryImpl;
	SetCategoryDao setCategoryDao;
	
	@Before
	public void prepareFields() {
		productsList = new ArrayList<>();
		categorySet = new HashSet<>();
		
		StaticFields.setProductsList(productsList);
		StaticFields.setCategorySet(categorySet);
			
		listUserDaoObj = new ListUserDao();
		userImpl = new UserServiceImpl(listUserDaoObj);
		listProductDao = new ListProductDao();
		productImpl = new ProductServiceImpl(listProductDao);
		setCategoryDao = new SetCategoryDao();
		categoryImpl = new CategoryServiceImpl(setCategoryDao);
		
		user = new User(1, "admin", "admin".toCharArray());
		
		cat = new Category("Wojskowe");
		categorySet.add(cat);
		
		prod1 = new Product(1, "Lornetka", categorySet, 12, true, "empty link");
		
	}
	
	@Test
	public void creatingProductAndCategoryTest() {
		Set<Category> setCategory = new HashSet<>();
		setCategory.add(cat);
		
		Product product1 = new Product(1, "teleskop", setCategory, 23, true, "brak");
		Product product2 = new Product("szklo", setCategory, 1, false, "link");
		productsList.add(product1);
		Product product3 = new Product("plastik", setCategory, 12, true, "link");
		Product product4 = new Product();
		
		assertThat(product1.getPrice(), is(23.0));
		assertThat(product1.getImage(), is("brak"));
		assertThat(product1.getProductId(), is(1));
		assertThat(product1.getName(), is("teleskop"));
		
		assertThat(product2.getProductId(), is(1));
		
		product4.setName("lorneta");
		product4.setAvailable(true);
		product4.setCategorySet(setCategory);
		product4.setImage("any link");
		product4.setPrice(12);
		assertThat(product4.getCategorySet(), is(setCategory));
		assertThat(product4.isAvailable(), is(true));
	
		assertThat(product1.equals(product4), is(false));
		
		productsList.add(product1);			
	}
	
	@Test 
	public void addingUpdatingAndRemovingUserTest() {
		userImpl.add(user);
		
		user.setLogin("login");	
		userImpl.update(user);
		
		assertThat(userImpl.findById(1), is(user));
		assertThat(userImpl.findByLoginAndPassword("login", "admin".toCharArray()), is(user));
		assertThat(userImpl.findByLoginAndPassword("wrong login", "admin".toCharArray()), is(not(user)));
		assertThat(userImpl.findByLoginAndPassword("login", "wrong password".toCharArray()), is(not(user)));
		
		userImpl.remove(1);
		assertThat(listUserDaoObj.usersList.size(), is(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNullUserToUserListImplShouldThrowException() {
		userImpl.add(null);
	}
	
	@Test(expected = UserAlreadyExistException.class)
	public void addingSameUserToListShouldThrowException() {
		userImpl.add(user);
		userImpl.add(user);
	}
	
	@Test
	public void addingProductToListDaoTest() {
		productImpl.add(prod1);
		assertThat(productImpl.getProductList().contains(prod1), is(true));
	}
	
	@Test
	public void updatingProductInListDaoTest() {
		productImpl.add(prod1);
		prod1.setPrice(1500.0);;
		productImpl.uptadeProd(prod1);
		assertThat(productImpl.getProductList().contains(prod1), is(true));
		assertThat(productImpl.getProductList().get(0).getPrice(), is(1500.0));
	}
	
	@Test
	public void removingProductFromListDaoTest() {
		productImpl.add(prod1);
		assertThat(productImpl.getProductList().contains(prod1), is(true));
		productImpl.removeProd(prod1);
		assertThat(productImpl.getProductList().contains(prod1), is(false));
	}
	
	@Test
	public void addingCategoryToSetDaoTest() {
		Category cat1 = new Category("Random category");
		
		assertThat(categoryImpl.getCategorySet().isEmpty(), is(true));
		categoryImpl.addCategory(cat1);
		assertThat(categoryImpl.getCategorySet().contains(cat1), is(true));
	}
	
	@Test
	public void removingCategoryFromSetDaoTest() {
		Category cat1 = new Category("Random category");
		
		categoryImpl.addCategory(cat1);
		assertThat(categoryImpl.getCategorySet().contains(cat1), is(true));
		categoryImpl.removeCategory(cat1);
		assertThat(categoryImpl.getCategorySet().contains(cat1), is(false));
	}
}
