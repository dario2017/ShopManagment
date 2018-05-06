package com.cschool.shop.managment.server.service;

import com.cschool.shop.managment.server.dao.UserDao;
import com.cschool.shop.managment.server.model.User;

public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {	
		this.userDao = userDao;
	}
	
	@Override
	public void add(User user) {
		this.userDao.add(user);
		
	}

	@Override
	public void remove(long id) {
		this.userDao.remove(id);		
	}

	@Override
	public void update(User user) {
		this.userDao.update(user);		
	}

	@Override
	public User findById(long id) {
		return this.userDao.findById(id);
	}

	@Override
	public User findByLoginAndPassword(String login, char[] password) {
		return this.userDao.findByLoginAndPassword(login, password);
	}

}
